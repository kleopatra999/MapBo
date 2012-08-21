package com.liqingyi.mapbo;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import com.liqingyi.mapbo.actionbarcompat.BaseActivity;

public class TimeLineActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_timeline);

		setTitle("动态");

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
