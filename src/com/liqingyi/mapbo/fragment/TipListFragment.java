package com.liqingyi.mapbo.fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.adapter.TipAdapter;
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

public class TipListFragment extends Fragment {

	private Poi poi;
	private User user;

	private TipAdapter adapter;
	private PullToRefreshListView mPullToRefreshListView;
	private ListView actualListView;
	private ArrayList<Status> list;
	private int page = 1;

	public static TipListFragment newInstance(User user, Poi poi) {
		TipListFragment fragment = new TipListFragment();
		Bundle bundle = new Bundle();
		bundle.putParcelable("user", user);
		bundle.putParcelable("poi", poi);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		Bundle args = getArguments();
		if (args != null) {
			user = args.getParcelable("user");
			poi = args.getParcelable("poi");
		}

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_tips, container, false);

		mPullToRefreshListView = (PullToRefreshListView) view
				.findViewById(R.id.list_tips);

		mPullToRefreshListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				page++;
				new LoadPoiTipListTsaks().execute(Weibo.getInstance());

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

		new LoadPoiTipListTsaks().execute(Weibo.getInstance());

		return view;
	}

	class LoadPoiTipListTsaks extends AsyncTask<Weibo, Integer, String> {

		@Override
		protected String doInBackground(Weibo... params) {

			if (user != null) {
				return getUserTips(params[0]);
			}

			if (poi != null) {
				return getPoiTips(params[0]);
			}

			return "";
		}

		@Override
		protected void onPostExecute(String result) {
			System.out.println("--------------------");

			if (!"".equals(result) && !"[]".equals(result)) {

				Statuses statuses = Statuses.newStatuses(result);

				if (list == null) {
					list = new ArrayList<com.liqingyi.mapbo.model.Status>();
				}

				if (adapter != null)
					adapter = null;

				list.addAll(statuses.getStatuses());
				adapter = new TipAdapter(getActivity(), list);

				actualListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				actualListView.setSelection(10 * (page - 1));
			}
			mPullToRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}

	}

	/**
	 * 用户点评
	 * 
	 * @param parameter
	 * @return
	 */
	String getUserTips(Weibo parameter) {

		String url = Weibo.SERVER + "place/users/tips.json";

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
	 * 地点
	 * 
	 * @param parameter
	 * @return
	 */
	String getPoiTips(Weibo parameter) {
		String url = Weibo.SERVER + "place/pois/tips.json";
		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("poiid", poi.getPoiid());
		bundle.add("count", "10");
		bundle.add("page", Integer.toString(page));
		bundle.add("sort", "1");
		bundle.add("base_app", "0");

		try {
			return parameter.request(getActivity(), url, bundle,
					Utility.HTTPMETHOD_GET, parameter.getAccessToken());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

		return null;
	}

}
