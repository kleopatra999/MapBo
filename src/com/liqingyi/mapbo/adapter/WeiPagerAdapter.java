package com.liqingyi.mapbo.adapter;

import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

public class WeiPagerAdapter extends FragmentStatePagerAdapter {

	private List<Fragment> fragments;

	public WeiPagerAdapter(FragmentManager arg0, List<Fragment> fragments) {
		super(arg0);
		this.fragments = fragments;

	}

	@Override
	public Fragment getItem(int arg0) {
		return this.fragments.get(arg0);
	}

	@Override
	public int getCount() {
		return this.fragments.size();
	}

}
