package com.liqingyi.mapbo.adapter;

import java.util.ArrayList;

import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.model.google.Review;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GoogleReviewAdapter extends BaseAdapter {

	private ArrayList<Review> reviews;
	private LayoutInflater mInflater;

	public GoogleReviewAdapter(Context context, ArrayList<Review> reviews) {
		super();
		this.reviews = reviews;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return reviews.size();
	}

	@Override
	public Review getItem(int position) {
		// TODO Auto-generated method stub
		return reviews.get(position);
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
			convertView = mInflater.inflate(R.layout.item_review, null);

			holder.author_name = (TextView) convertView
					.findViewById(R.id.review_author_name);
			holder.text = (TextView) convertView.findViewById(R.id.review_text);
			holder.author_url = (TextView) convertView
					.findViewById(R.id.review_author_url);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Review review = getItem(position);
		holder.author_name.setText(review.getAuthor_name());
		holder.text.setText(review.getText());
		holder.author_url.setText(review.getAuthor_url());

		return convertView;
	}

	class ViewHolder {
		TextView author_name;
		TextView text;
		TextView author_url;
	}

}
