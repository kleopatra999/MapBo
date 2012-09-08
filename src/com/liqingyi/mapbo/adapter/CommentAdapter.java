package com.liqingyi.mapbo.adapter;

import java.text.ParseException;
import java.util.ArrayList;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.model.Comment;
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

public class CommentAdapter extends BaseAdapter {

	private ArrayList<Comment> list;
	private DisplayImageOptions options;
	private ImageLoader imageLoader;
	private LayoutInflater mInflater;

	public CommentAdapter(Context context, ArrayList<Comment> comments) {
		super();
		this.list = comments;
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
	public Comment getItem(int position) {
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
			convertView = mInflater.inflate(R.layout.item_comment, null);

			holder.imageView = (ImageView) convertView
					.findViewById(R.id.comment_profile_image_url);
			holder.created_at = (TextView) convertView
					.findViewById(R.id.comment_created_at);
			holder.text = (TextView) convertView
					.findViewById(R.id.comment_text);
			holder.source = (TextView) convertView
					.findViewById(R.id.comment_source);
			holder.screen_name = (TextView) convertView
					.findViewById(R.id.comment_screen_name);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Comment comment = getItem(position);
		imageLoader.displayImage(comment.getUser().getProfile_image_url(),
				holder.imageView, options);

		try {
			String string = UIUtils.formatCreated_at(comment.getCreated_at());
			holder.created_at.setText(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		holder.text.setText(comment.getText());
		holder.screen_name.setText(comment.getUser().getScreen_name());
		holder.source.setText(Html.fromHtml(comment.getSource()));

		return convertView;
	}

	class ViewHolder {
		TextView created_at;
		TextView text;
		TextView source;
		TextView screen_name;
		ImageView imageView;
	}

}
