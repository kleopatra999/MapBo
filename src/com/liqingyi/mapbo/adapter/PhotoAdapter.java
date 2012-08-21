package com.liqingyi.mapbo.adapter;

import java.text.ParseException;
import java.util.ArrayList;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.model.Annotation;
import com.liqingyi.mapbo.model.Place;
import com.liqingyi.mapbo.model.Status;
import com.liqingyi.mapbo.util.UIUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotoAdapter extends BaseAdapter {

	private ArrayList<Status> list;
	private DisplayImageOptions options;
	private ImageLoader imageLoader;
	private LayoutInflater mInflater;

	public PhotoAdapter(Context context, ArrayList<Status> list) {
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
			convertView = mInflater.inflate(R.layout.item_photo, null);
			holder.thumbnail_pic = (ImageView) convertView
					.findViewById(R.id.photo_thumbnail_pic);
			holder.created_at = (TextView) convertView
					.findViewById(R.id.photo_created_at);
			// holder.text = (TextView)
			// convertView.findViewById(R.id.photo_text);
			holder.screen_name = (TextView) convertView
					.findViewById(R.id.photo_screen_name);
			holder.source = (TextView) convertView
					.findViewById(R.id.photo_source);

			holder.distance = (TextView) convertView
					.findViewById(R.id.photo_distance);

			holder.title = (TextView) convertView
					.findViewById(R.id.photo_title);

			holder.profile_image_url = (ImageView) convertView
					.findViewById(R.id.photo_profile_image_url);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Status status = getItem(position);
		imageLoader.displayImage(status.getThumbnail_pic(),
				holder.thumbnail_pic, options);

		imageLoader.displayImage(status.getUser().getProfile_image_url(),
				holder.profile_image_url, options);
		try {
			ArrayList<Annotation> annotations = status.getAnnotations();
			if (!annotations.isEmpty())
				for (Annotation annotation : annotations) {
					Place place = annotation.getPlace();
					System.out.println(place.getTitle());

					holder.title.setText(place.getTitle());
				}
		} catch (Exception e) {

		}

		try {
			String string = UIUtils.formatCreated_at(status.getCreated_at());
			holder.created_at.setText(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// holder.text.setText(status.getText());
		holder.screen_name.setText(status.getUser().getScreen_name());
		holder.source.setText(Html.fromHtml(status.getSource()));
		holder.distance.setText(Long.toString(status.getDistance()));
		return convertView;
	}

	class ViewHolder {
		ImageView thumbnail_pic;
		ImageView profile_image_url;
		TextView created_at;
		// TextView text;
		TextView source;
		TextView screen_name;
		TextView distance;
		TextView title;
	}

}
