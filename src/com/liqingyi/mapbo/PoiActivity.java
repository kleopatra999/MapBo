package com.liqingyi.mapbo;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.liqingyi.mapbo.actionbarcompat.BaseActivity;
import com.liqingyi.mapbo.adapter.TabsAdapter;
import com.liqingyi.mapbo.fragment.PhotoListFragment;
import com.liqingyi.mapbo.fragment.TimeLimeListFragment;
import com.liqingyi.mapbo.fragment.UserListFragment;
import com.liqingyi.mapbo.model.Poi;

public class PoiActivity extends BaseActivity {

	private ViewPager mViewPager;
	private TabsAdapter mTabsAdapter;
	private Poi poi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.pager);
		setContentView(mViewPager);

		poi = getIntent().getParcelableExtra("poi");

		final ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
		mTabsAdapter = new TabsAdapter(this, mViewPager);

		setTitle("地点相关");

		Bundle bundle = new Bundle();
		bundle.putParcelable("poi", poi);
		bundle.putBoolean("is", false);

		mTabsAdapter.addTab(bar.newTab().setText("签到人"),
				UserListFragment.class, bundle);
		mTabsAdapter.addTab(bar.newTab().setText("动态"),
				TimeLimeListFragment.class, bundle);
		mTabsAdapter.addTab(bar.newTab().setText("照片"),
				PhotoListFragment.class, bundle);

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
		super.onSaveInstanceState(outState);
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
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_refresh:
			Intent intent = new Intent(getApplicationContext(),
					PoiShowActivity.class);
			intent.putExtra("poi", poi);
			startActivity(intent);
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

}
