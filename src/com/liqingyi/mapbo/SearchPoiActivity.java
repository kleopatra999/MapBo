package com.liqingyi.mapbo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.liqingyi.mapbo.actionbarcompat.BaseActivity;
import com.liqingyi.mapbo.fragment.GooglePlaceFragment;
import com.liqingyi.mapbo.fragment.PoiListFragment;
import com.liqingyi.mapbo.model.google.GooglePlaceList;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.weibo.net.Utility;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboException;
import com.weibo.net.WeiboParameters;

public class SearchPoiActivity extends BaseActivity {
	EditText keyWord;
	EditText radius;
	Button button;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_poi_search);
		keyWord = (EditText) findViewById(R.id.search_keyword);
		radius = (EditText) findViewById(R.id.search_radius);
		button = (Button) findViewById(R.id.search_button);

		keyWord.setText("酒吧");
		radius.setText("5000");

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String string = keyWord.getText().toString();
				String string2 = radius.getText().toString();

				showList(null, string);
				// googlePlaceSearch(string, string2);
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
						showList(placeList, null);
						super.onSuccess(content);
					}

					@Override
					public void onFinish() {

						super.onFinish();
					}
				});

	}

	private void showList(GooglePlaceList googlePlaceList, String keyword) {
		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		Fragment fragment = null;
		if (googlePlaceList != null) {
			fragment = GooglePlaceFragment.newInstance(googlePlaceList);
		} else if (keyword != null && !"".equals(keyword)) {
			fragment = PoiListFragment.newInstance(null, null, keyword);
		}
		transaction.replace(R.id.created_google_place, fragment);
		transaction.commit();
	}

	/**
	 * 获取地点分类
	 * 
	 * @param parameter
	 * @return
	 */

	private String getCategory(Weibo parameter) {
		String url = Weibo.SERVER + "place/pois/category.json";
		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("pid", "0");
		bundle.add("flag", "0");
		try {
			return parameter.request(getApplicationContext(), url, bundle,
					Utility.HTTPMETHOD_GET, parameter.getAccessToken());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

		return null;

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
