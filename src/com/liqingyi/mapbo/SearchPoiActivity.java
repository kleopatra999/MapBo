package com.liqingyi.mapbo;

import java.util.ArrayList;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.liqingyi.mapbo.actionbarcompat.BaseActivity;
import com.liqingyi.mapbo.adapter.CategoryAdapter;
import com.liqingyi.mapbo.fragment.PoiListFragment;
import com.liqingyi.mapbo.model.Category;
import com.liqingyi.mapbo.model.Province;
import com.liqingyi.mapbo.model.SearchParameter;
import com.weibo.net.Utility;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboException;
import com.weibo.net.WeiboParameters;

public class SearchPoiActivity extends BaseActivity {
	EditText keyWord;
	Spinner spinner;
	Button button;
	Button select;
	CategoryAdapter categoryAdapter;
	SearchParameter searchParameter;
	static final private int GET_CODE = 0;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_poi_search);
		initUi();
		new LoadCategoryTasks().execute(Weibo.getInstance());
	}

	private void initUi() {

		searchParameter = new SearchParameter();

		keyWord = (EditText) findViewById(R.id.search_keyword);
		keyWord.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				searchParameter.setKeyword(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		button = (Button) findViewById(R.id.search_button);
		spinner = (Spinner) findViewById(R.id.category_spinner);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Category category = categoryAdapter.getItem(arg2);

				searchParameter.setCategory(category.getId());

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		keyWord.setText("酒吧");
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String string = keyWord.getText().toString();

				showList(string);

			}
		});

		select = (Button) findViewById(R.id.select_button);
		select.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivityForResult(new Intent(getApplicationContext(),
						SelectActivity.class), GET_CODE);

			}
		});
	}

	private void showList(String keyword) {
		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		Fragment fragment = null;
		fragment = PoiListFragment.newInstance(null, null, searchParameter);
		transaction.replace(R.id.created_poi_place, fragment);
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

	class LoadCategoryTasks extends AsyncTask<Weibo, Integer, String> {

		@Override
		protected String doInBackground(Weibo... params) {

			return getCategory(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {

			ArrayList<Category> list = Category.getCategory(result);
			categoryAdapter = new CategoryAdapter(getApplicationContext(), list);
			spinner.setAdapter(categoryAdapter);

			super.onPostExecute(result);
		}

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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_search:
			startActivity(new Intent(getApplicationContext(),
					GoogleSearchPlaceActivity.class));
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		if (arg0 == GET_CODE) {
			Province province = arg2.getParcelableExtra("city");
			select.setText(province.getName());
			searchParameter.setCity(province.getId());
		}
		super.onActivityResult(arg0, arg1, arg2);
	}

}
