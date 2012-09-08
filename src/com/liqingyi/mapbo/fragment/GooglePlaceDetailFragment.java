package com.liqingyi.mapbo.fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.liqingyi.mapbo.GoogleToWeiboActivity;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.adapter.GoogleReviewAdapter;
import com.liqingyi.mapbo.model.SearchParameter;
import com.liqingyi.mapbo.model.google.DetailsResult;
import com.liqingyi.mapbo.model.google.GooglePlace;
import com.liqingyi.mapbo.model.google.GooglePlaceDetails;
import com.liqingyi.mapbo.model.google.Review;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class GooglePlaceDetailFragment extends Fragment {

	GooglePlace place;

	private DisplayImageOptions options;
	private ImageLoader imageLoader;

	TextView formatted_address;
	TextView formatted_phone_number;
	TextView name_zh;
	TextView name;
	TextView url;
	TextView website;
	ImageView icon;
	RatingBar rating;
	ListView reviewListView;

	public static GooglePlaceDetailFragment newInstance(GooglePlace place) {
		GooglePlaceDetailFragment fragment = new GooglePlaceDetailFragment();
		Bundle bundle = new Bundle();
		bundle.putParcelable("google_place", place);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Bundle args = getArguments();
		if (args != null) {
			place = args.getParcelable("google_place");
			options = new DisplayImageOptions.Builder()
					.showStubImage(R.drawable.ic_launcher).cacheInMemory()
					.cacheOnDisc().build();
			imageLoader = ImageLoader.getInstance();
		}
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_google_place_details,
				container, false);
		initUI(view);
		setDetail();

		return view;
	}

	void initUI(View view) {
		formatted_address = (TextView) view
				.findViewById(R.id.formatted_address);
		formatted_phone_number = (TextView) view
				.findViewById(R.id.formatted_phone_number);
		name_zh = (TextView) view.findViewById(R.id.name_zh);

		name_zh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),
						GoogleToWeiboActivity.class);
				SearchParameter parameter = new SearchParameter();
				parameter.setKeyword(place.getName());
				parameter.setCity("");
				intent.putExtra("search", parameter);
				startActivity(intent);
			}
		});

		name = (TextView) view.findViewById(R.id.name);
		url = (TextView) view.findViewById(R.id.url);
		website = (TextView) view.findViewById(R.id.website);
		icon = (ImageView) view.findViewById(R.id.icon);
		rating = (RatingBar) view.findViewById(R.id.rating);
		reviewListView = (ListView) view.findViewById(R.id.review_list);
	}

	void setUI(DetailsResult details) {
		formatted_address.setText(details.getFormatted_address());
		formatted_phone_number.setText(details.getFormatted_phone_number());
		name_zh.setText(place.getName());
		name.setText(details.getName());
		url.setText(details.getUrl());
		website.setText(details.getWebsite());
		imageLoader.displayImage(details.getIcon(), icon, options);
		rating.setNumStars(5);
		rating.setRating(details.getRating());

		ArrayList<Review> reviews = details.getReviews();

		if (reviews != null && !reviews.isEmpty()) {
			GoogleReviewAdapter reviewAdapter = new GoogleReviewAdapter(
					getActivity(), reviews);
			reviewListView.setAdapter(reviewAdapter);
			reviewAdapter.notifyDataSetChanged();
		} else {
			reviewListView.setVisibility(View.GONE);
		}

	}

	void setDetail() {
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("reference", place.getReference());
		params.put("language", "zh-CN");
		params.put("sensor", "false");
		params.put("key", "AIzaSyAMt9-aFfwn7S9tUaRZixRU3yabCAbfYzw");

		client.get(getActivity(),
				"https://maps.googleapis.com/maps/api/place/details/json",
				params, new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(String content) {
						System.out.println(content);

						GooglePlaceDetails details = GooglePlaceDetails
								.initializeGooglePlaceDetails(content);

						DetailsResult result = details.getResult();

						setUI(result);

						super.onSuccess(content);
					}

				});
	}

}
