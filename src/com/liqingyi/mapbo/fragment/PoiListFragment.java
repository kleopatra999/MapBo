package com.liqingyi.mapbo.fragment;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.liqingyi.mapbo.PoiActivity;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.adapter.PoiAdapter;
import com.liqingyi.mapbo.model.Geo;
import com.liqingyi.mapbo.model.Poi;
import com.liqingyi.mapbo.model.PoiList;
import com.liqingyi.mapbo.model.User;
import com.liqingyi.mapbo.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.liqingyi.mapbo.pulltorefresh.PullToRefreshListView;
import com.weibo.net.Utility;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboException;
import com.weibo.net.WeiboParameters;

public class PoiListFragment extends Fragment {

	private User user;
	private Geo geo;
	private String keyword;

	private PoiAdapter adapter;
	private PullToRefreshListView mPullToRefreshListView;
	private ListView actualListView;
	private ArrayList<Poi> pois;
	private int page = 1;

	public static PoiListFragment newInstance(User user, Geo geo, String keyword) {
		PoiListFragment fragment = new PoiListFragment();
		Bundle bundle = new Bundle();
		bundle.putParcelable("user", user);
		bundle.putParcelable("geo", geo);
		bundle.putString("keyword", keyword);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Bundle args = getArguments();
		if (args != null) {
			user = args.getParcelable("user");
			geo = args.getParcelable("geo");
			keyword = args.getString("keyword");
		}

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_poi_list, container,
				false);

		mPullToRefreshListView = (PullToRefreshListView) view
				.findViewById(R.id.poi_list);

		mPullToRefreshListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				page++;
				new LoadPoiListTasks().execute(Weibo.getInstance());

			}
		});

		actualListView = mPullToRefreshListView.getRefreshableView();

		actualListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Intent intent = new Intent(getActivity(), PoiActivity.class);
				intent.putExtra("poi", adapter.getItem(arg2 - 1));
				startActivity(intent);

			}
		});

		new LoadPoiListTasks().execute(Weibo.getInstance());

		return view;
	}

	class LoadPoiListTasks extends AsyncTask<Weibo, Integer, String> {

		@Override
		protected String doInBackground(Weibo... params) {

			if (geo != null) {

				return nearByPois(params[0]);
			}

			if (user != null) {
				return checkInsPois(params[0]);
			}

			if (keyword != null && !"".equals(keyword)) {
				return searchPoi(params[0]);
			}

			return "";
		}

		@Override
		protected void onPostExecute(String result) {
			setList(result);
			super.onPostExecute(result);
		}
	}

	private void setList(String result) {
		if (!"".equals(result) && !"[]".equals(result)) {
			PoiList poiList = PoiList.initializePoiList(result);
			if (pois == null)
				pois = new ArrayList<Poi>();

			if (adapter != null)
				adapter = null;

			pois.addAll(poiList.getPois());

			adapter = new PoiAdapter(getActivity(), pois);
			actualListView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			actualListView.setSelection(10 * (page - 1));

		}

		mPullToRefreshListView.onRefreshComplete();
	}

	/**
	 * 附近地点列表
	 * 
	 * @param parameter
	 * @return
	 */
	private String nearByPois(Weibo parameter) {
		String url = Weibo.SERVER + "place/nearby/pois.json";
		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("lat", geo.getLat());// 纬度
		bundle.add("long", geo.getLon());// 经度
		bundle.add("range", "2000");
		bundle.add("q", "");
		bundle.add("category", "");
		bundle.add("count", "10");
		bundle.add("page", Integer.toString(page));
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

	/**
	 * 获取用户签到过的地点列表
	 * 
	 * @param parameter
	 * @return
	 */
	private String checkInsPois(Weibo parameter) {

		String url = Weibo.SERVER + "place/users/checkins.json";

		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("uid", user.getIdstr());
		bundle.add("count", "10");
		bundle.add("page", Integer.toString(page));
		bundle.add("offset", "0");

		try {
			return parameter.request(getActivity(), url, bundle,
					Utility.HTTPMETHOD_GET, parameter.getAccessToken());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 按省市查询地点
	 * 
	 * @param parameter
	 * @return
	 */
	private String searchPoi(Weibo parameter) {
		String url = Weibo.SERVER + "place/pois/search.json";
		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		try {
			bundle.add("keyword", URLDecoder.decode(keyword, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		bundle.add("city", "0010");
		bundle.add("category", "");
		bundle.add("count", "10");
		bundle.add("page", "1");

		try {
			return parameter.request(getActivity(), url, bundle,
					Utility.HTTPMETHOD_GET, parameter.getAccessToken());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

		return null;

	}

}
