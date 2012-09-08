package com.liqingyi.mapbo.fragment;

import java.util.ArrayList;

import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.SearchPoiActivity;
import com.liqingyi.mapbo.adapter.ProvinceAdapter;
import com.liqingyi.mapbo.model.Province;
import com.weibo.net.Utility;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboException;
import com.weibo.net.WeiboParameters;

import android.R.integer;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Spinner;

public class SelectFragment extends Fragment {

	Spinner spinner;
	ListView listView;
	ProvinceAdapter provinceAdapter;
	Province province;
	ProvinceAdapter cityAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_select, container, false);

		spinner = (Spinner) view.findViewById(R.id.select_spinner_province);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				province = provinceAdapter.getItem(arg2);
				new LoadCityTasks().execute(Weibo.getInstance());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		listView = (ListView) view.findViewById(R.id.select_list_city);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Province province = cityAdapter.getItem(arg2);
				Intent intent = new Intent(getActivity(),
						SearchPoiActivity.class);
				intent.putExtra("city", province);
				getActivity().setResult(Activity.RESULT_OK, intent);
				getActivity().finish();
			}
		});

		new LoadTasks().execute(Weibo.getInstance());

		return view;
	}

	/**
	 * 获取省份列表
	 * 
	 * @param parameter
	 * @return
	 */
	private String getProvince(Weibo parameter) {

		String url = Weibo.SERVER + "common/get_province.json";

		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("country", "001");
		bundle.add("language", "zh-cn");
		try {
			return parameter.request(getActivity(), url, bundle,
					Utility.HTTPMETHOD_GET, parameter.getAccessToken());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取城市列表
	 * 
	 * @param parameter
	 * @return
	 */
	private String getCity(Weibo parameter) {
		String url = Weibo.SERVER + "common/get_city.json";

		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("province", province.getId());
		bundle.add("language", "zh-cn");
		try {
			return parameter.request(getActivity(), url, bundle,
					Utility.HTTPMETHOD_GET, parameter.getAccessToken());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

		return null;
	}

	class LoadTasks extends AsyncTask<Weibo, Integer, String> {

		@Override
		protected String doInBackground(Weibo... params) {

			return getProvince(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {

			ArrayList<Province> provinces = Province.getProvinceList(result);

			provinceAdapter = new ProvinceAdapter(getActivity(), provinces);
			spinner.setAdapter(provinceAdapter);

			super.onPostExecute(result);
		}

	}

	class LoadCityTasks extends AsyncTask<Weibo, integer, String> {

		@Override
		protected String doInBackground(Weibo... params) {

			return getCity(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			
			System.out.println(result);

			if (cityAdapter != null)
				cityAdapter = null;

			ArrayList<Province> citys = Province.getProvinceList(result);
			cityAdapter = new ProvinceAdapter(getActivity(), citys);
			listView.setAdapter(cityAdapter);

			super.onPostExecute(result);
		}

	}

}
