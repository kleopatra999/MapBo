package com.liqingyi.mapbo.fragment;

import com.liqingyi.mapbo.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class GooglePlacesAutocompleteFragment extends Fragment {

	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(
				R.layout.fragment_google_place_autocomplete, container, false);

		listView = (ListView) view
				.findViewById(R.id.google_place_autocomplete_list);
		return view;
	}

}
