package com.liqingyi.mapbo.adapter;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.model.Poi;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class PoiAdapter extends BaseAdapter {
	private DisplayImageOptions options;
	private ImageLoader imageLoader;
	private LayoutInflater mInflater;
	private ArrayList<Poi> mPois;

	public PoiAdapter(Context context, ArrayList<Poi> pois) {
		super();
		this.mPois = pois;
		this.mInflater = LayoutInflater.from(context);
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.icon_picture).cacheInMemory()
				.cacheOnDisc().build();
		imageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getCount() {
		return mPois.size();
	}

	@Override
	public Poi getItem(int position) {
		return mPois.get(position);
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
			convertView = mInflater.inflate(R.layout.item_poi, null);
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.address = (TextView) convertView.findViewById(R.id.address);

			holder.distance = (TextView) convertView
					.findViewById(R.id.distance);

			holder.checkin_num = (TextView) convertView
					.findViewById(R.id.checkin_num);

			holder.photo_num = (TextView) convertView
					.findViewById(R.id.photo_num);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final Poi poi = getItem(position);
		imageLoader.displayImage(poi.getIcon(), holder.icon, options);
		holder.title.setText(poi.getTitle());
		holder.distance.setText("距您:" + Long.toString(poi.getDistance()) + "米");

		System.out.println(poi.getTitle());
		holder.address.setText(poi.getAddress());

		holder.checkin_num.setText("签到:" + Long.toString(poi.getCheckin_num()));

		holder.photo_num.setText("照片:" + Long.toString(poi.getPhoto_num()));

		return convertView;
	}

	class ViewHolder {
		ImageView icon;
		TextView title;
		TextView address;
		TextView distance;
		TextView checkin_num;
		TextView photo_num;

	}

}
