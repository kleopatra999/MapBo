package com.liqingyi.mapbo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.liqingyi.mapbo.actionbarcompat.BaseActivity;
import com.liqingyi.mapbo.fragment.PoiListFragment;
import com.liqingyi.mapbo.model.SearchParameter;

public class GoogleToWeiboActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		SearchParameter searchParameter = getIntent().getParcelableExtra(
				"search");

		setContentView(R.layout.activity_google_to_weibo);

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		Fragment fragment = PoiListFragment.newInstance(null, null,
				searchParameter);
		transaction.add(R.id.created_google_to_weibo, fragment);
		transaction.commit();
	}

}
