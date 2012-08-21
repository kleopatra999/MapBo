package com.liqingyi.mapbo.model.google;

import android.os.Parcel;
import android.os.Parcelable;

public class Aspect implements Parcelable {

	public long getRating() {
		return rating;
	}

	public void setRating(long rating) {
		this.rating = rating;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private long rating;
	private String type;

	public Aspect() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Aspect(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		rating = parcel.readLong();
		type = parcel.readString();

	}

	public static final Parcelable.Creator<Aspect> CREATOR = new Parcelable.Creator<Aspect>() {

		public Aspect createFromParcel(Parcel source) {
			return new Aspect(source);
		}

		public Aspect[] newArray(int size) {
			return new Aspect[size];
		}

	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(rating);
		dest.writeString(type);

	}

}
