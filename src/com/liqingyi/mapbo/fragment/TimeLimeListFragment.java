package com.liqingyi.mapbo.fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.adapter.TimeLineAdapter;
import com.liqingyi.mapbo.model.Geo;
import com.liqingyi.mapbo.model.Poi;
import com.liqingyi.mapbo.model.Status;
import com.liqingyi.mapbo.model.Statuses;
import com.liqingyi.mapbo.model.User;
import com.liqingyi.mapbo.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.liqingyi.mapbo.pulltorefresh.PullToRefreshListView;
import com.weibo.net.Utility;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboException;
import com.weibo.net.WeiboParameters;

public class TimeLimeListFragment extends Fragment {

	private Poi poi;
	private User user;
	private boolean isaround;
	private Geo geo;

	private TimeLineAdapter adapter;
	private PullToRefreshListView mPullToRefreshListView;
	private ListView actualListView;
	private ArrayList<Status> list;
	private int page = 1;

	public static TimeLimeListFragment newInstance(User user, Poi poi, Geo geo,
			boolean b) {
		TimeLimeListFragment fragment = new TimeLimeListFragment();
		Bundle bundle = new Bundle();
		bundle.putParcelable("user", user);
		bundle.putParcelable("poi", poi);
		bundle.putParcelable("geo", geo);
		bundle.putBoolean("is", b);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		Bundle args = getArguments();
		if (args != null) {
			user = args.getParcelable("user");
			poi = args.getParcelable("poi");
			geo = args.getParcelable("geo");
			isaround = args.getBoolean("is");
		}

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_timelines, container,
				false);

		mPullToRefreshListView = (PullToRefreshListView) view
				.findViewById(R.id.listView_timeline);
		mPullToRefreshListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				page++;
				new LoadTimeLineTasks().execute(Weibo.getInstance());
			}
		});

		actualListView = mPullToRefreshListView.getRefreshableView();
		actualListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

			}
		});

		new LoadTimeLineTasks().execute(Weibo.getInstance());

		return view;
	}

	class LoadTimeLineTasks extends AsyncTask<Weibo, Integer, String> {

		@Override
		protected String doInBackground(Weibo... params) {
			if (user != null) {
				return getUserTimeLine(params[0]);
			}

			if ((poi != null || geo != null) && isaround) {// 周边
				return getNearbyTimeLine(params[0]);
			}

			if (poi != null && !isaround) {// 单个地点
				return getPoiTimeLine(params[0]);
			}
			return getFriendsTimeLine(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {

			System.out.println("*********************");
			System.out.println(result);

			if (!"".equals(result) && !"[]".equals(result)) {

				Statuses statuses = Statuses.newStatuses(result);

				if (list == null) {
					list = new ArrayList<com.liqingyi.mapbo.model.Status>();
				}

				if (adapter != null)
					adapter = null;

				list.addAll(statuses.getStatuses());
				adapter = new TimeLineAdapter(getActivity(), list);

				actualListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				actualListView.setSelection(10 * (page - 1));
			}
			mPullToRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}

	}

	/**
	 * 获取某个位置地点的动态
	 * 
	 * @param parameter
	 * @return
	 */
	String getPoiTimeLine(Weibo parameter) {

		String url = Weibo.SERVER + "place/poi_timeline.json";

		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("poiid", poi.getPoiid());
		bundle.add("since_id", "");
		bundle.add("max_id", "");
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
	 * 获取用户好友的位置动态（当前登陆）
	 * 
	 * @param parameter
	 * @return
	 */
	String getFriendsTimeLine(Weibo parameter) {

		String url = Weibo.SERVER + "place/friends_timeline.json";
		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("since_id", "");
		bundle.add("max_id", "");
		bundle.add("count", "10");
		bundle.add("page", "1");
		bundle.add("type", "0");

		try {
			return parameter.request(getActivity(), url, bundle,
					Utility.HTTPMETHOD_GET, parameter.getAccessToken());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 获取某个用户的位置动态
	 * 
	 * @param parameter
	 * @return
	 */
	String getUserTimeLine(Weibo parameter) {

		String url = Weibo.SERVER + "place/user_timeline.json";
		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("uid", user.getIdstr());
		bundle.add("since_id", "");
		bundle.add("max_id", "");
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
	 * 获取某个位置周边的动态
	 * 
	 * 
	 * @param parameter
	 * @return
	 */
	String getNearbyTimeLine(Weibo parameter) {

		String url = Weibo.SERVER + "place/nearby_timeline.json";
		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());

		if (geo != null) {
			bundle.add("lat", geo.getLat());
			bundle.add("long", geo.getLon());
		} else {
			bundle.add("lat", poi.getLat());
			bundle.add("long", poi.getLon());
		}

		bundle.add("range", "2000");
		bundle.add("starttime", "");
		bundle.add("endtime", "");
		bundle.add("sort", "0");
		bundle.add("count", "10");
		bundle.add("page", Integer.toString(page));
		bundle.add("base_app", "0");
		bundle.add("offset", "0");
		try {
			return parameter.request(getActivity(), url, bundle,
					Utility.HTTPMETHOD_GET, parameter.getAccessToken());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

		return null;

	}

}
