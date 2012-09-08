package com.liqingyi.mapbo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import com.liqingyi.mapbo.actionbarcompat.BaseActivity;
import com.liqingyi.mapbo.fragment.TimeLimeListFragment;
import com.liqingyi.mapbo.model.Poi;

public class TipsActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comments);
		Poi poi = getIntent().getParcelableExtra("poi");

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		Fragment fragment = TimeLimeListFragment.newInstance(null, poi, null,
				false);
		transaction.add(R.id.created_comments, fragment);
		transaction.commit();

	}

}
