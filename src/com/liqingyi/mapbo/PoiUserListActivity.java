package com.liqingyi.mapbo;

import android.app.ActionBar;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import com.liqingyi.mapbo.actionbarcompat.BaseActivity;
import com.liqingyi.mapbo.adapter.TabsAdapter;
import com.liqingyi.mapbo.fragment.PhotoListFragment;
import com.liqingyi.mapbo.fragment.PoiListFragment;
import com.liqingyi.mapbo.fragment.TimeLimeListFragment;
import com.liqingyi.mapbo.fragment.TipListFragment;
import com.liqingyi.mapbo.model.User;

public class PoiUserListActivity extends BaseActivity {

	private User user;
	private ViewPager mViewPager;
	private TabsAdapter mTabsAdapter;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		// setContentView(R.layout.activity_poi_users);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.pager);
		setContentView(mViewPager);
		setTitle("用户相关");
		user = getIntent().getParcelableExtra("user");

		final ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
		mTabsAdapter = new TabsAdapter(this, mViewPager);

		Bundle bundle = new Bundle();
		bundle.putParcelable("user", user);

		mTabsAdapter.addTab(bar.newTab().setText("用户签到"),
				PoiListFragment.class, bundle);

		mTabsAdapter.addTab(bar.newTab().setText("用户点评"),
				TipListFragment.class, bundle);

		mTabsAdapter.addTab(bar.newTab().setText("用户照片"),
				PhotoListFragment.class, bundle);

		mTabsAdapter.addTab(bar.newTab().setText("用户动态"),
				TimeLimeListFragment.class, bundle);

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

}
