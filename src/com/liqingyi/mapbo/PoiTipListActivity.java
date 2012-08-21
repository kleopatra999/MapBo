package com.liqingyi.mapbo;

import com.liqingyi.mapbo.actionbarcompat.BaseActivity;
import com.liqingyi.mapbo.fragment.TipListFragment;
import com.liqingyi.mapbo.model.User;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class PoiTipListActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_poi_tips);
		setTitle("点评");
		User user = getIntent().getParcelableExtra("user");

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		Fragment fragment = TipListFragment.newInstance(user, null);
		transaction.add(R.id.created_tips, fragment);
		transaction.commit();

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
