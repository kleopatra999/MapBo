package com.liqingyi.mapbo.fragment;

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
import com.liqingyi.mapbo.PoiUserListActivity;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.adapter.UserAdapter;
import com.liqingyi.mapbo.model.Geo;
import com.liqingyi.mapbo.model.Poi;
import com.liqingyi.mapbo.model.User;
import com.liqingyi.mapbo.model.UserList;
import com.liqingyi.mapbo.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.liqingyi.mapbo.pulltorefresh.PullToRefreshListView;
import com.weibo.net.Utility;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboException;
import com.weibo.net.WeiboParameters;

public class UserListFragment extends Fragment {

	private Poi poi;
	private Geo geo;

	private ArrayList<User> users;

	private PullToRefreshListView mPullToRefreshListView;
	private ListView actualListView;
	private int page = 1;
	private UserAdapter userAdapter;

	public static UserListFragment newInstance(Geo geo, Poi poi) {
		UserListFragment fragment = new UserListFragment();
		Bundle bundle = new Bundle();
		bundle.putParcelable("geo", geo);
		bundle.putParcelable("poi", poi);

		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		Bundle args = getArguments();
		if (args != null) {
			geo = args.getParcelable("geo");
			poi = args.getParcelable("poi");
		}

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_users, container, false);
		mPullToRefreshListView = (PullToRefreshListView) view
				.findViewById(R.id.list_users);
		mPullToRefreshListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				page++;
				new LoadPoiUserListTsaks().execute(Weibo.getInstance());

			}
		});
		actualListView = mPullToRefreshListView.getRefreshableView();

		actualListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(getActivity(),
						PoiUserListActivity.class);
				intent.putExtra("user", userAdapter.getItem(arg2 - 1));
				startActivity(intent);

			}
		});

		new LoadPoiUserListTsaks().execute(Weibo.getInstance());

		return view;
	}

	class LoadPoiUserListTsaks extends AsyncTask<Weibo, Integer, String> {

		@Override
		protected String doInBackground(Weibo... params) {

			if (poi != null) {
				return getPoiUsers(params[0]);
			}
			if (geo != null) {
				return getNearbyUsers(params[0]);
			}

			return "";
		}

		@Override
		protected void onPostExecute(String result) {
			System.out.println(result);

			if (!"".equals(result) && !"[]".equals(result)) {

				UserList userList = UserList.newStatuses(result);

				if (users == null) {
					users = new ArrayList<User>();
				}

				if (userAdapter != null)
					userAdapter = null;

				users.addAll(userList.getUsers());

				userAdapter = new UserAdapter(getActivity(), users);
				actualListView.setAdapter(userAdapter);
				actualListView.setSelection(10 * (page - 1));
				userAdapter.notifyDataSetChanged();

			}
			mPullToRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}

	}

	/**
	 * 附近发微博的人
	 * 
	 * @param parameter
	 * @return
	 */
	String getNearbyUsers(Weibo parameter) {
		String url = Weibo.SERVER + "place/nearby/users.json";
		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("lat", geo.getLat());
		bundle.add("long", geo.getLon());
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

	/**
	 * 获取在某个地点签到的人的列表
	 * 
	 * @param parameter
	 * @return
	 */
	String getPoiUsers(Weibo parameter) {
		String url = Weibo.SERVER + "place/pois/users.json";
		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("poiid", poi.getPoiid());
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

}
