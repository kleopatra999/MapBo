package com.liqingyi.mapbo.adapter;

import java.util.ArrayList;
import com.liqingyi.mapbo.R;
import com.liqingyi.mapbo.model.Province;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProvinceAdapter extends BaseAdapter {

	private ArrayList<Province> list;
	private LayoutInflater mInflater;

	public ProvinceAdapter(Context context, ArrayList<Province> list) {
		super();
		this.list = list;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Province getItem(int position) {
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
			convertView = mInflater.inflate(R.layout.item_category, null);

			holder.text = (TextView) convertView
					.findViewById(R.id.poi_category);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Province province = getItem(position);
		holder.text.setText(province.getName());
		return convertView;
	}

	class ViewHolder {
		TextView text;
	}

}
