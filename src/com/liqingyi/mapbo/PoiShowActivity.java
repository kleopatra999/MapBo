package com.liqingyi.mapbo;

import com.liqingyi.mapbo.actionbarcompat.BaseActivity;
import com.liqingyi.mapbo.fragment.GoogleMapFragment;
import com.liqingyi.mapbo.fragment.PoiShowFragment;
import com.liqingyi.mapbo.model.Poi;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class PoiShowActivity extends BaseActivity {
	Poi poi;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_poi_show);
		poi = getIntent().getParcelableExtra("poi");

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		Fragment fragment = PoiShowFragment.newInstance(poi);

		Fragment fragmentMap = GoogleMapFragment.newInstance(null, poi,null);
		
		
		transaction.add(R.id.created_poi_show_map, fragmentMap);
		transaction.add(R.id.created_poi_show, fragment);
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
