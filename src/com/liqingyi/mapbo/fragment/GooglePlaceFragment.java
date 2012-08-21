package com.liqingyi.mapbo.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.liqingyi.mapbo.GooglePlaceDetailsActivity;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.adapter.GooglePlaceAdapter;
import com.liqingyi.mapbo.model.google.GooglePlace;

import com.liqingyi.mapbo.model.google.GooglePlaceList;

public class GooglePlaceFragment extends Fragment implements
		OnItemClickListener {

	GooglePlaceList googlePlaces;
	ListView listView;
	GooglePlaceAdapter adapter;

	public static GooglePlaceFragment newInstance(GooglePlaceList places) {
		GooglePlaceFragment fragment = new GooglePlaceFragment();
		Bundle bundle = new Bundle();
		bundle.putParcelable("google_place", places);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Bundle args = getArguments();
		if (args != null) {
			googlePlaces = args.getParcelable("google_place");

		}
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_google_place, container,
				false);

		listView = (ListView) view.findViewById(R.id.google_place_list);
		listView.setOnItemClickListener(this);

		adapter = new GooglePlaceAdapter(getActivity(),
				googlePlaces.getResults());
		listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		GooglePlace place = adapter.getItem(arg2);
		Intent intent=new Intent(getActivity(), GooglePlaceDetailsActivity.class);
		intent.putExtra("place", place);
		
		startActivity(intent);
	

	}

}
