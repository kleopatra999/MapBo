package com.liqingyi.mapbo.fragment;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.SearchPoiActivity;
import com.liqingyi.mapbo.adapter.ProvinceAdapter;
import com.liqingyi.mapbo.model.Province;

public class SelectCityFragment extends Fragment {

	ListView listView;
	ProvinceAdapter adapter;
	EditText editText;
	ArrayList<Province> provinces;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_select_city, container,
				false);
		listView = (ListView) view.findViewById(R.id.select_city_list);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Province province = adapter.getItem(arg2);
				Intent intent = new Intent(getActivity(),
						SearchPoiActivity.class);
				intent.putExtra("city", province);
				getActivity().setResult(Activity.RESULT_OK, intent);
				getActivity().finish();

			}
		});

		editText = (EditText) view.findViewById(R.id.select_city_edit);
		editText.setText("上海");

		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				ArrayList<Province> tempList = new ArrayList<Province>();
				for (Province province : provinces) {
					if (province.getName().contains(s.toString()))
						tempList.add(province);
				}

				if (adapter != null)
					adapter = null;
				setCityAdapter(tempList);

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		new ReadTasks().execute("file/city_json.txt");

		return view;
	}

	class ReadTasks extends AsyncTask<String, Integer, ArrayList<Province>> {

		@Override
		protected ArrayList<Province> doInBackground(String... params) {
			try {

				InputStream inputStream = getActivity().getAssets().open(
						params[0]);
				int length = inputStream.available();
				byte[] buffer = new byte[length];
				inputStream.read(buffer);
				inputStream.close();
				String data = new String(buffer, "UTF-8");

				Gson gson = new Gson();
				Type poiType = new TypeToken<ArrayList<Province>>() {
				}.getType();

				return gson.fromJson(data, poiType);

			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Province> result) {
			if (result != null) {

				provinces = result;
				setCityAdapter(provinces);

			}
			super.onPostExecute(result);
		}

	}

	private void setCityAdapter(ArrayList<Province> provinces) {
		adapter = new ProvinceAdapter(getActivity(), provinces);
		listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

}
