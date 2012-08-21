package com.liqingyi.mapbo.adapter;

import java.util.ArrayList;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.model.google.GooglePlace;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GooglePlaceAdapter extends BaseAdapter {

	private ArrayList<GooglePlace> list;
	private DisplayImageOptions options;
	private ImageLoader imageLoader;
	private LayoutInflater mInflater;

	public GooglePlaceAdapter(Context context, ArrayList<GooglePlace> list) {
		super();
		this.list = list;
		this.mInflater = LayoutInflater.from(context);
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.ic_launcher).cacheInMemory()
				.cacheOnDisc().build();
		imageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getCount() {

		return list.size();
	}

	@Override
	public GooglePlace getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (null == convertView) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_google_place, null);

			holder.imageView = (ImageView) convertView
					.findViewById(R.id.google_place_icon);
			holder.name = (TextView) convertView
					.findViewById(R.id.google_place_name);
			holder.vicinity = (TextView) convertView
					.findViewById(R.id.google_place_vicinity);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		GooglePlace place = getItem(position);

		holder.name.setText(place.getName());
		holder.vicinity.setText(place.getVicinity());
		imageLoader.displayImage(place.getIcon(), holder.imageView, options);
		return convertView;
	}

	class ViewHolder {
		TextView name;
		TextView vicinity;
		ImageView imageView;
	}

}
