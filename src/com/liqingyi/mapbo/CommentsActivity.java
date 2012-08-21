package com.liqingyi.mapbo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.liqingyi.mapbo.actionbarcompat.BaseActivity;
import com.liqingyi.mapbo.fragment.CommentsFragment;

public class CommentsActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comments);
		String id = getIntent().getStringExtra("id");

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		Fragment fragment = CommentsFragment.newInstance(id);
		transaction.add(R.id.created_comments, fragment);
		transaction.commit();

	}

}
