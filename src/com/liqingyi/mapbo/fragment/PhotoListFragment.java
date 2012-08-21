package com.liqingyi.mapbo.fragment;

import java.util.ArrayList;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.adapter.PhotoAdapter;
import com.liqingyi.mapbo.anim.Rotate3dAnimation;
import com.liqingyi.mapbo.model.Geo;
import com.liqingyi.mapbo.model.Poi;
import com.liqingyi.mapbo.model.Status;
import com.liqingyi.mapbo.model.Statuses;
import com.liqingyi.mapbo.model.User;
import com.liqingyi.mapbo.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.liqingyi.mapbo.pulltorefresh.PullToRefreshGridView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weibo.net.Utility;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboException;
import com.weibo.net.WeiboParameters;

public class PhotoListFragment extends Fragment implements OnClickListener {

	private Poi poi;
	private User user;
	private Geo geo;

	private DisplayImageOptions options;
	private ImageLoader imageLoader;

	private PhotoAdapter adapter;
	private PullToRefreshGridView mPullToRefreshListView;
	private GridView actualListView;
	private ArrayList<Status> list;
	private int page = 1;

	private ViewGroup mContainer;
	private ImageView mImageView;

	public static PhotoListFragment newInstance(User user, Poi poi, Geo geo) {
		PhotoListFragment fragment = new PhotoListFragment();
		Bundle bundle = new Bundle();
		bundle.putParcelable("user", user);
		bundle.putParcelable("poi", poi);
		bundle.putParcelable("geo", geo);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		Bundle args = getArguments();
		if (args != null) {
			poi = args.getParcelable("poi");
			user = args.getParcelable("user");
			geo = args.getParcelable("geo");

			options = new DisplayImageOptions.Builder()
					.showStubImage(R.drawable.ic_launcher).cacheInMemory()
					.cacheOnDisc().build();
			imageLoader = ImageLoader.getInstance();
		}

		super.onCreate(savedInstanceState);
	}

	private void applyRotation(int position, float start, float end) {
		// Find the center of the container
		final float centerX = mContainer.getWidth() / 2.0f;
		final float centerY = mContainer.getHeight() / 2.0f;

		// Create a new 3D rotation with the supplied parameter
		// The animation listener is used to trigger the next animation
		final Rotate3dAnimation rotation = new Rotate3dAnimation(start, end,
				centerX, centerY, 310.0f, true);
		rotation.setDuration(500);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView(position));

		mContainer.startAnimation(rotation);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater
				.inflate(R.layout.fragment_photos, container, false);

		mPullToRefreshListView = (PullToRefreshGridView) view
				.findViewById(R.id.listView_photos);

		mContainer = (ViewGroup) view.findViewById(R.id.container);
		mImageView = (ImageView) view.findViewById(R.id.picture);

		// Prepare the ImageView
		mImageView.setClickable(true);
		mImageView.setFocusable(true);
		mImageView.setOnClickListener(this);

		mContainer
				.setPersistentDrawingCache(ViewGroup.PERSISTENT_ANIMATION_CACHE);

		mPullToRefreshListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				page++;
				new LoadPoiPhotoListTsaks().execute(Weibo.getInstance());

			}
		});

		actualListView = mPullToRefreshListView.getRefreshableView();
		actualListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				System.out.println(arg2);

				Status status = adapter.getItem(arg2);

				imageLoader.displayImage(status.getOriginal_pic(), mImageView,
						options);

				applyRotation(arg2, 0, 90);

			}
		});

		new LoadPoiPhotoListTsaks().execute(Weibo.getInstance());

		return view;
	}

	class LoadPoiPhotoListTsaks extends AsyncTask<Weibo, Integer, String> {

		@Override
		protected String doInBackground(Weibo... params) {

			if (poi != null) {
				return getPoiPhotos(params[0]);
			}

			if (user != null) {
				return getUserPhotos(params[0]);
			}

			if (geo != null) {
				System.out.println("*******照********");
				System.out.println(getNearbyPhotos(params[0]));
				return getNearbyPhotos(params[0]);
			}

			return "";

		}

		@Override
		protected void onPostExecute(String result) {
			System.out.println(result);

			if (!"".equals(result) && !"[]".equalsIgnoreCase(result)) {

				Statuses statuses = Statuses.newStatuses(result);

				if (list == null) {
					list = new ArrayList<com.liqingyi.mapbo.model.Status>();
				}

				if (adapter != null)
					adapter = null;

				list.addAll(statuses.getStatuses());
				adapter = new PhotoAdapter(getActivity(), list);

				actualListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				actualListView.setSelection(10 * (page - 1));
			}
			mPullToRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}

	/**
	 * 获取地点照片列表
	 * 
	 * @param parameter
	 * @return
	 */
	String getPoiPhotos(Weibo parameter) {

		String url = Weibo.SERVER + "place/pois/photos.json";

		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("poiid", poi.getPoiid());
		bundle.add("count", "10");
		bundle.add("page", Integer.toString(page));
		bundle.add("sort", "0");
		bundle.add("base_app", "0");

		try {
			return parameter.request(getActivity(), url, bundle,
					Utility.HTTPMETHOD_GET, parameter.getAccessToken());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取用户照片列表
	 * 
	 * @param parameter
	 * @return
	 */
	String getUserPhotos(Weibo parameter) {

		String url = Weibo.SERVER + "place/users/photos.json";

		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("uid", user.getIdstr());
		bundle.add("count", "10");
		bundle.add("page", Integer.toString(page));
		bundle.add("base_app", "0");

		try {
			return parameter.request(getActivity(), url, bundle,
					Utility.HTTPMETHOD_GET, parameter.getAccessToken());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取附近照片
	 * 
	 * @param parameter
	 * @return
	 */
	String getNearbyPhotos(Weibo parameter) {
		String url = Weibo.SERVER + "place/nearby/photos.json";
		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("lat", geo.getLat());// 纬度
		bundle.add("long", geo.getLon());// 经度
		bundle.add("range", "2000");
		bundle.add("count", "10");
		bundle.add("page", Integer.toString(page));
		bundle.add("starttime", "");
		bundle.add("endtime", "");
		bundle.add("sort", "0");
		bundle.add("offset", "0");

		try {
			return parameter.request(getActivity(), url, bundle,
					Utility.HTTPMETHOD_GET, parameter.getAccessToken());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void onClick(View v) {
		applyRotation(-1, 180, 90);

	}

	private final class DisplayNextView implements Animation.AnimationListener {
		private final int mPosition;

		private DisplayNextView(int position) {
			mPosition = position;
		}

		public void onAnimationStart(Animation animation) {
		}

		public void onAnimationEnd(Animation animation) {
			mContainer.post(new SwapViews(mPosition));
		}

		public void onAnimationRepeat(Animation animation) {
		}
	}

	private final class SwapViews implements Runnable {
		private final int mPosition;

		public SwapViews(int position) {
			mPosition = position;
		}

		public void run() {
			final float centerX = mContainer.getWidth() / 2.0f;
			final float centerY = mContainer.getHeight() / 2.0f;
			Rotate3dAnimation rotation;

			if (mPosition > -1) {
				actualListView.setVisibility(View.GONE);
				mImageView.setVisibility(View.VISIBLE);
				mImageView.requestFocus();

				rotation = new Rotate3dAnimation(90, 180, centerX, centerY,
						310.0f, false);
			} else {
				mImageView.setVisibility(View.GONE);
				actualListView.setVisibility(View.VISIBLE);
				actualListView.requestFocus();

				rotation = new Rotate3dAnimation(90, 0, centerX, centerY,
						310.0f, false);
			}

			rotation.setDuration(500);
			rotation.setFillAfter(true);
			rotation.setInterpolator(new DecelerateInterpolator());

			mContainer.startAnimation(rotation);
		}
	}

}
