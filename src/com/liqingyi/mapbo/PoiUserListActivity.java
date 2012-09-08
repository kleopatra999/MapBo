package com.liqingyi.mapbo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import com.liqingyi.mapbo.actionbarcompat.BaseActivity;
import com.liqingyi.mapbo.fragment.GoogleMapFragment;
import com.liqingyi.mapbo.fragment.PhotoListFragment;
import com.liqingyi.mapbo.menudrawer.MenuDrawer;
import com.liqingyi.mapbo.menudrawer.MenuDrawerManager;
import com.liqingyi.mapbo.menudrawer.MenuScrollView;
import com.liqingyi.mapbo.model.User;

public class PoiUserListActivity extends BaseActivity implements
		OnClickListener {

	Fragment currentFragmentDisplayed = null;
	// set to false if you do not wish that fragments save their states on
	// configuration changes
	private static final boolean RETAIN_FRAGMENT_STATE_ON_CONFIG_CHANGE = true;

	private User user;
	private static final String STATE_MENUDRAWER = "com.liqingyi.mapbo.menuDrawer";
	private static final String STATE_ACTIVE_VIEW_ID = "com.liqingyi.mapbo.activeViewId";

	private MenuDrawerManager mMenuDrawer;

	private int mActiveViewId;

	@Override
	protected void onCreate(Bundle inState) {
		super.onCreate(inState);

		user = getIntent().getParcelableExtra("user");

		if (inState != null) {
			mActiveViewId = inState.getInt(STATE_ACTIVE_VIEW_ID);
		}

		mMenuDrawer = new MenuDrawerManager(this, MenuDrawer.MENU_DRAG_WINDOW);
		mMenuDrawer.setContentView(R.layout.activity_poi_users);
		mMenuDrawer.setMenuView(R.layout.menu_scrollview);

		MenuScrollView msv = (MenuScrollView) mMenuDrawer.getMenuView();
		msv.setOnScrollChangedListener(new MenuScrollView.OnScrollChangedListener() {
			@Override
			public void onScrollChanged() {
				mMenuDrawer.getMenuDrawer().invalidate();
			}
		});

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		findViewById(R.id.item1).setOnClickListener(this);
		findViewById(R.id.item2).setOnClickListener(this);
		findViewById(R.id.item3).setOnClickListener(this);
		findViewById(R.id.item4).setOnClickListener(this);
		findViewById(R.id.item5).setOnClickListener(this);
		findViewById(R.id.item6).setOnClickListener(this);

	}

	@Override
	protected void onStart() {
		displayFragment(PhotoListFragment.class);
		super.onStart();
	}

	@Override
	protected void onRestoreInstanceState(Bundle inState) {
		super.onRestoreInstanceState(inState);
		mMenuDrawer.onRestoreDrawerState(inState
				.getParcelable(STATE_MENUDRAWER));
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelable(STATE_MENUDRAWER,
				mMenuDrawer.onSaveDrawerState());
		outState.putInt(STATE_ACTIVE_VIEW_ID, mActiveViewId);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// 触发事件
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			mMenuDrawer.toggleMenu();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		if (mMenuDrawer.isMenuVisible()) {
			mMenuDrawer.closeMenu();
			return;
		}

		super.onBackPressed();
	}

	@Override
	public void onClick(View v) {
		mMenuDrawer.setActiveView(v);
		if (v.getId() == R.id.item2) {
			displayFragment(GoogleMapFragment.class);
		}

		if (v.getId() == R.id.item1) {
			displayFragment(PhotoListFragment.class);
		}
		mMenuDrawer.closeMenu();
		mActiveViewId = v.getId();
	}

	private void displayFragment(Class<?> fragmentClass) {

		Fragment fragment = getFragmentManager().findFragmentByTag(
				fragmentClass.getName());

		Bundle bundle = new Bundle();
		bundle.putParcelable("user", user);

		if (fragment == null) {
			fragment = Fragment.instantiate(getApplicationContext(),
					fragmentClass.getName(), bundle);
			fragment.setRetainInstance(RETAIN_FRAGMENT_STATE_ON_CONFIG_CHANGE);
		}

		if (currentFragmentDisplayed != fragment) {

			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();

			if (currentFragmentDisplayed != null) {
				transaction.hide(currentFragmentDisplayed);
			}

			if (!fragment.isAdded()) {
				transaction.add(R.id.created_users, fragment,
						fragmentClass.getName());
			} else {
				transaction.show(fragment);
			}

			transaction.commit();

			currentFragmentDisplayed = fragment;
		}
	}
}
