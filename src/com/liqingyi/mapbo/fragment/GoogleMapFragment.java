package com.liqingyi.mapbo.fragment;

import java.util.ArrayList;
import java.util.List;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.googlemap.CustomItemizedOverlay;
import com.liqingyi.mapbo.googlemap.CustomOverlayItem;
import com.liqingyi.mapbo.googlemap.MapArgument;
import com.liqingyi.mapbo.model.Poi;
import com.liqingyi.mapbo.model.PoiList;
import com.liqingyi.mapbo.model.User;
import com.liqingyi.mapbo.model.google.GooglePlaceList;
import com.weibo.net.Utility;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboException;
import com.weibo.net.WeiboParameters;

public class GoogleMapFragment extends Fragment {

	private MapView mapView;
	private List<Overlay> mapOverlays;
	private CustomItemizedOverlay<CustomOverlayItem> simpleItemizedOverlay;
	GooglePlaceList placeList;
	Poi poi;
	User user;

	public static GoogleMapFragment newInstance(GooglePlaceList placeList,
			Poi poi, User user) {
		GoogleMapFragment mapFragment = new GoogleMapFragment();

		Bundle args = new Bundle();
		args.putParcelable("placeList", placeList);
		args.putParcelable("poi", poi);
		args.putParcelable("user", user);
		mapFragment.setArguments(args);

		return mapFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		Bundle bundle = getArguments();

		if (bundle != null) {
			placeList = bundle.getParcelable("placeList");
			poi = bundle.getParcelable("poi");
			user = bundle.getParcelable("user");
		}

		super.onCreate(savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		if (simpleItemizedOverlay.getFocus() != null) {
			outState.putInt("focused",
					simpleItemizedOverlay.getLastFocusedIndex());
		}
		super.onSaveInstanceState(outState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_map, container, false);

		mapView = (MapView) view.findViewById(R.id.map);
		mapView.setBuiltInZoomControls(true);

		if (poi != null)
			mapView.getController().setZoom(15);

		if (user != null)
			mapView.getController().setZoom(5);

		mapOverlays = mapView.getOverlays();
		final Drawable drawable = getResources().getDrawable(
				R.drawable.map_marker_male);
		simpleItemizedOverlay = new CustomItemizedOverlay<CustomOverlayItem>(
				drawable, mapView);

		if (savedInstanceState == null) {
			// 设置地图中心点
			if (poi != null) {
				GeoPoint point = new GeoPoint((int) (Double.parseDouble(poi
						.getLat()) * 1E6), (int) (Double.parseDouble(poi
						.getLon()) * 1E6));

				mapView.getController().animateTo(point);

				new LoadTasks().execute(Weibo.getInstance());

			}

			if (user != null) {
				new LoadTasks().execute(Weibo.getInstance());
			}

		} else {
			int focused;
			focused = savedInstanceState.getInt("focused", -1);
			if (focused >= 0)
				simpleItemizedOverlay.setFocus(simpleItemizedOverlay
						.getItem(focused));

		}

		return view;
	}

	/**
	 * 附近地点列表
	 * 
	 * @param parameter
	 * @return
	 */
	private String nearByPois(Weibo parameter) {
		String url = Weibo.SERVER + "place/nearby/pois.json";
		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("lat", poi.getLat());// 纬度
		bundle.add("long", poi.getLon());// 经度
		bundle.add("range", "500");
		bundle.add("q", "");
		bundle.add("category", poi.getCategory());
		bundle.add("count", "50");
		bundle.add("page", "1");
		bundle.add("sort", "0");
		bundle.add("offset", "0");

		try {
			return parameter.request(getActivity(), url, bundle,
					Utility.HTTPMETHOD_GET, parameter.getAccessToken());
		} catch (WeiboException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 获取用户签到过的地点列表
	 * 
	 * @param parameter
	 * @return
	 */
	private String checkInsPois(Weibo parameter) {

		String url = Weibo.SERVER + "place/users/checkins.json";

		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("uid", user.getIdstr());
		bundle.add("count", "50");
		bundle.add("page", "1");
		bundle.add("offset", "0");

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

			if (poi != null)
				return nearByPois(params[0]);

			if (user != null)
				return checkInsPois(params[0]);

			return "";

		}

		@Override
		protected void onPostExecute(String result) {
			if (!"".equals(result) && !"[]".equals(result)) {
				PoiList poiList = PoiList.initializePoiList(result);

				ArrayList<Poi> places = poiList.getPois();

				for (Poi poi : places) {
					CustomOverlayItem item = new CustomOverlayItem(
							new GeoPoint(
									(int) (Double.parseDouble(poi.getLat()) * 1E6),
									(int) (Double.parseDouble(poi.getLon()) * 1E6)),
							poi.getTitle(), poi.getAddress(), new MapArgument(
									poi));

					simpleItemizedOverlay.addOverlay(item);
				}

				mapOverlays.add(simpleItemizedOverlay);

				mapView.invalidate();

			}
			super.onPostExecute(result);
		}

	}
}
