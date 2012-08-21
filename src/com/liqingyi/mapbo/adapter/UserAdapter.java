package com.liqingyi.mapbo.adapter;

import java.text.ParseException;
import java.util.ArrayList;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.model.Annotation;
import com.liqingyi.mapbo.model.Place;
import com.liqingyi.mapbo.model.User;
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

public class UserAdapter extends BaseAdapter {

	private ArrayList<User> list;
	private DisplayImageOptions options;
	private ImageLoader imageLoader;
	private LayoutInflater mInflater;

	public UserAdapter(Context context, ArrayList<User> list) {
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
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public User getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (null == convertView) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_user, null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.user_image_url);
			holder.screen_name = (TextView) convertView
					.findViewById(R.id.user_screen_name);
			holder.location = (TextView) convertView
					.findViewById(R.id.user_location);
			holder.description = (TextView) convertView
					.findViewById(R.id.user_description);
			holder.created_at = (TextView) convertView
					.findViewById(R.id.user_created_at);
			holder.text = (TextView) convertView.findViewById(R.id.user_text);
			holder.source = (TextView) convertView
					.findViewById(R.id.user_source);
			holder.title = (TextView) convertView.findViewById(R.id.user_title);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		User user = getItem(position);
		imageLoader.displayImage(user.getAvatar_large(), holder.imageView,
				options);
		holder.screen_name.setText(user.getScreen_name());
		holder.location.setText(user.getLocation());
		holder.description.setText(user.getDescription());
		try {
			String string = UIUtils.formatCreated_at(user.getCreated_at());
			holder.created_at.setText(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		holder.text.setText(user.getStatus().getText());
		holder.source.setText(Html.fromHtml(user.getStatus().getSource()));
		try {
			ArrayList<Annotation> annotations = user.getStatus()
					.getAnnotations();
			if (!annotations.isEmpty())
				for (Annotation annotation : annotations) {
					Place place = annotation.getPlace();
					System.out.println(holder.title.getText()
							+ place.getTitle());

					holder.title.setText(place.getTitle());
				}
		} catch (Exception e) {

		}

		return convertView;
	}

	class ViewHolder {
		ImageView imageView;
		TextView screen_name;
		TextView location;
		TextView description;
		TextView created_at;
		TextView text;
		TextView source;
		TextView title;
	}

}
