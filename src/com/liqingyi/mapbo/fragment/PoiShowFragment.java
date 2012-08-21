package com.liqingyi.mapbo.fragment;

import com.google.gson.Gson;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.model.Poi;
import com.weibo.net.Utility;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboException;
import com.weibo.net.WeiboParameters;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PoiShowFragment extends Fragment {

	private Poi poi;
	private TextView extra;

	public static PoiShowFragment newInstance(Poi poi) {
		PoiShowFragment fragment = new PoiShowFragment();
		Bundle bundle = new Bundle();
		bundle.putParcelable("poi", poi);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Bundle args = getArguments();
		if (args != null) {
			poi = args.getParcelable("poi");
		}
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_poi_show, container,
				false);
		extra = (TextView) view.findViewById(R.id.poi_show_extra);

		new LoadPoiShowTasks().execute(Weibo.getInstance());
		return view;
	}

	String getPoiShow(Weibo parameter) {

		String url = Weibo.SERVER + "place/pois/show.json";
		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("poiid", poi.getPoiid());
		bundle.add("base_app", "0");

		try {
			return parameter.request(getActivity(), url, bundle,
					Utility.HTTPMETHOD_GET, parameter.getAccessToken());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

		return null;

	}

	class LoadPoiShowTasks extends AsyncTask<Weibo, Integer, String> {

		@Override
		protected String doInBackground(Weibo... params) {

			return getPoiShow(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {

			System.out.println(result);
			Gson gson = new Gson();
			Poi poi = gson.fromJson(result, Poi.class);
			extra.setText(poi.getTitle());
			super.onPostExecute(result);
		}

	}

}
