package com.liqingyi.mapbo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.liqingyi.mapbo.actionbarcompat.BaseActivity;
import com.liqingyi.mapbo.fragment.SelectCityFragment;

public class SelectActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		Fragment fragment = new SelectCityFragment();
		transaction.add(R.id.created_select, fragment);
		transaction.commit();

	}

}
