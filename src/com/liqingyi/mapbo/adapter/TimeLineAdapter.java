package com.liqingyi.mapbo.adapter;

import java.text.ParseException;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liqingyi.mapbo.CommentActivity;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.model.Status;
import com.liqingyi.mapbo.util.UIUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TimeLineAdapter extends BaseAdapter {

	private DisplayImageOptions options;
	private ImageLoader imageLoader;
	private LayoutInflater mInflater;
	private ArrayList<Status> list;
	private Context context;

	public TimeLineAdapter(Context context, ArrayList<Status> list) {
		super();
		this.list = list;
		this.mInflater = LayoutInflater.from(context);
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.ic_launcher).cacheInMemory()
				.cacheOnDisc().build();
		imageLoader = ImageLoader.getInstance();

		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Status getItem(int position) {
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
			convertView = mInflater.inflate(R.layout.item_timeline, null);

			holder.imageView = (ImageView) convertView
					.findViewById(R.id.timeline_icon);
			holder.created_at = (TextView) convertView
					.findViewById(R.id.timeline_created_at);
			holder.text = (TextView) convertView
					.findViewById(R.id.timeline_text);
			holder.source = (TextView) convertView
					.findViewById(R.id.timeline_source);
			holder.screen_name = (TextView) convertView
					.findViewById(R.id.timeline_screen_name);
			holder.comments_count = (TextView) convertView
					.findViewById(R.id.timeline_comments_count);

			holder.reposts_count = (TextView) convertView
					.findViewById(R.id.timeline_reposts_count);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final Status status = getItem(position);

		if (status.getComments_count() > 0)
			System.out.println("*********" + status.getId());

		imageLoader.displayImage(status.getUser().getProfile_image_url(),
				holder.imageView, options);

		try {
			String string = UIUtils.formatCreated_at(status.getCreated_at());
			holder.created_at.setText(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		holder.text.setText(status.getText());
		holder.screen_name.setText(status.getUser().getScreen_name());
		holder.source.setText(Html.fromHtml(status.getSource()));

		holder.comments_count.setText("评论数："
				+ Long.toString(status.getComments_count()));

		holder.comments_count.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, CommentActivity.class);
				intent.putExtra("id", status.getIdstr());
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				context.startActivity(intent);
			}
		});

		holder.reposts_count.setText("转发数"
				+ Long.toString(status.getReposts_count()));

		return convertView;
	}

	class ViewHolder {
		ImageView imageView;
		TextView created_at;
		TextView text;
		TextView source;
		TextView screen_name;
		TextView comments_count;
		TextView reposts_count;
	}

}
