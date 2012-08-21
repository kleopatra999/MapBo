package com.liqingyi.mapbo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.liqingyi.mapbo.actionbarcompat.BaseActivity;
import com.liqingyi.mapbo.fragment.GooglePlaceDetailFragment;
import com.liqingyi.mapbo.model.google.GooglePlace;

public class GooglePlaceDetailsActivity extends BaseActivity {

	GooglePlace place;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_google_place_details);

		place = getIntent().getParcelableExtra("place");

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		Fragment fragment = GooglePlaceDetailFragment.newInstance(place);
		transaction.add(R.id.created_google_place_details, fragment);
		transaction.commit();
	}

}
