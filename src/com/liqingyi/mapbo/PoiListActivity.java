package com.liqingyi.mapbo;

import android.app.ActionBar;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.liqingyi.mapbo.actionbarcompat.BaseActivity;
import com.liqingyi.mapbo.adapter.TabsAdapter;
import com.liqingyi.mapbo.fragment.PhotoListFragment;
import com.liqingyi.mapbo.fragment.PoiListFragment;
import com.liqingyi.mapbo.fragment.TimeLimeListFragment;
import com.liqingyi.mapbo.fragment.UserListFragment;
import com.liqingyi.mapbo.model.Geo;
import com.weibo.net.Utility;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboException;
import com.weibo.net.WeiboParameters;

public class PoiListActivity extends BaseActivity {

	ViewPager mViewPager;
	TabsAdapter mTabsAdapter;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setTitle("列表");

		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.pager);
		setContentView(mViewPager);

		final ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
		mTabsAdapter = new TabsAdapter(this, mViewPager);

		Geo geo = new Geo();
		geo.setLat("39.90923");
		geo.setLon("116.397428");
		Bundle bundle = new Bundle();
		bundle.putParcelable("geo", geo);
		bundle.putBoolean("is", true);

		mTabsAdapter.addTab(bar.newTab().setText("附近地点"),
				PoiListFragment.class, bundle);
		mTabsAdapter.addTab(bar.newTab().setText("发微博人"),
				UserListFragment.class, bundle);
		mTabsAdapter.addTab(bar.newTab().setText("附近照片"),
				PhotoListFragment.class, bundle);

		mTabsAdapter.addTab(bar.newTab().setText("周边动态"),
				TimeLimeListFragment.class, bundle);

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);

		// Calling super after populating the menu is necessary here to ensure
		// that the
		// action bar helpers have a chance to handle this event.
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_search:
			Intent intent = new Intent(getApplicationContext(),
					SearchPoiActivity.class);

			startActivity(intent);
			break;

		default:
			break;
		}

		return super.onMenuItemSelected(featureId, item);
	}

	/**
	 * 
	 * @param parameter
	 * @return
	 */
	private String getPoi(Weibo parameter) {

		String url = Weibo.SERVER + "location/geo/geo_to_address.json";

		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());

		bundle.add("coordinate", "116.397428,39.90923");

		try {
			return parameter.request(getApplicationContext(), url, bundle,
					Utility.HTTPMETHOD_GET, parameter.getAccessToken());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 根据关键词按坐标点范围获取POI点的信息
	 * https://api.weibo.com/2/location/pois/search/by_geo.json
	 * 
	 * @author liqy
	 * 
	 */
	@SuppressWarnings("unused")
	private String by_Geo(Weibo parameter) {

		String url = Weibo.SERVER + "location/pois/search/by_geo.json";

		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("coordinate", "116.397428,39.90923");
		bundle.add("q", "");
		bundle.add("category", "11");
		bundle.add("pid", "");
		bundle.add("city", "0010");
		bundle.add("range", "");
		bundle.add("page", "1");
		bundle.add("count", "10");

		try {
			return parameter.request(getApplicationContext(), url, bundle,
					Utility.HTTPMETHOD_GET, parameter.getAccessToken());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

		return null;

	}

	class LoadTasks extends AsyncTask<Weibo, Integer, String> {

		@Override
		protected String doInBackground(Weibo... params) {

			return getPoi(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			System.out.println(result);

			super.onPostExecute(result);
		}

	}

}
