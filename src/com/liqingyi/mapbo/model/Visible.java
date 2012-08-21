package com.liqingyi.mapbo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Visible implements Parcelable {

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	public long getList_id() {
		return list_id;
	}

	public void setList_id(long list_id) {
		this.list_id = list_id;
	}

	public Visible() {
		super();
	}

	private long type;
	private long list_id;

	public static final Parcelable.Creator<Visible> CREATOR = new Parcelable.Creator<Visible>() {

		public Visible createFromParcel(Parcel source) {
			return new Visible(source);
		}

		public Visible[] newArray(int size) {
			return new Visible[size];
		}

	};

	public Visible(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		type = parcel.readLong();
		list_id = parcel.readLong();

	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(type);
		dest.writeLong(list_id);
	}

}
