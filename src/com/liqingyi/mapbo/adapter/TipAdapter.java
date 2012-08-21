package com.liqingyi.mapbo.adapter;

import java.util.ArrayList;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.model.Status;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TipAdapter extends BaseAdapter {

	private ArrayList<Status> list;
	private DisplayImageOptions options;
	private ImageLoader imageLoader;
	private LayoutInflater mInflater;

	public TipAdapter(Context context, ArrayList<Status> list) {
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
	public Status getItem(int position) {
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
			convertView = mInflater.inflate(R.layout.item_tip, null);

			holder.imageView=(ImageView)convertView.findViewById(R.id.tip_icon);
			holder.text = (TextView) convertView.findViewById(R.id.tip_text);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Status status = getItem(position);
		
	imageLoader.displayImage(status.getUser().getProfile_image_url(), holder.imageView, options);
		
		holder.text.setText(status.getText());
		return convertView;
	}

	class ViewHolder {
		ImageView imageView;
		TextView text;
	}

}
