package com.liqingyi.mapbo.adapter;

import java.util.ArrayList;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.model.User;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

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
				.showStubImage(R.drawable.bg_picture).cacheInMemory()
				.cacheOnDisc().build();
		imageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public User getItem(int position) {
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
			convertView = mInflater.inflate(R.layout.item_user, null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.user_image_url);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		User user = getItem(position);

		imageLoader.displayImage(user.getAvatar_large(), holder.imageView,
				options);

		return convertView;
	}

	class ViewHolder {
		ImageView imageView;
	}

}
