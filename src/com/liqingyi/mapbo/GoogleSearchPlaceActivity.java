package com.liqingyi.mapbo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.liqingyi.mapbo.actionbarcompat.BaseActivity;
import com.liqingyi.mapbo.fragment.GoogleMapFragment;
import com.liqingyi.mapbo.fragment.GooglePlaceFragment;
import com.liqingyi.mapbo.model.google.GooglePlaceList;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class GoogleSearchPlaceActivity extends BaseActivity {

	EditText keyWord;
	EditText radius;
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_google_search);

		initUI();
	}

	private void initUI() {

		keyWord = (EditText) findViewById(R.id.google_search_keyword);
		radius = (EditText) findViewById(R.id.google_search_radius);
		button = (Button) findViewById(R.id.google_search_button);

		keyWord.setText("酒吧");
		radius.setText("5000");

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String string = keyWord.getText().toString();
				String string2 = radius.getText().toString();
				googlePlaceSearch(string, string2);
			}
		});

	}

	private void googlePlaceSearch(String keyword, String radius) {

		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("key", "AIzaSyAMt9-aFfwn7S9tUaRZixRU3yabCAbfYzw");
		params.put("location", "39.90923,116.397428");
		params.put("radius", radius);
		params.put("types", "");// 可选
		params.put("language", "zh-CN");// 可选
		params.put("name", keyword);// 可选
		params.put("sensor", "false");// 可选

		client.get(getApplicationContext(),
				"https://maps.googleapis.com/maps/api/place/search/json",
				params, new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(String content) {
						System.out.println("************");

						GooglePlaceList placeList = GooglePlaceList
								.initializeGooglePlaceList(content);
						// showMap(placeList);
						showList(placeList);
						super.onSuccess(content);
					}

					@Override
					public void onFinish() {

						super.onFinish();
					}
				});

	}

	void showMap(GooglePlaceList placeList) {
		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		Fragment fragment = GoogleMapFragment
				.newInstance(placeList, null, null);
		transaction.add(R.id.created_google_place, fragment);
		transaction.commit();
	}

	private void showList(GooglePlaceList placeList) {
		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		Fragment fragment = GooglePlaceFragment.newInstance(placeList);
		transaction.add(R.id.created_google_place, fragment);
		transaction.commit();
	}

}
