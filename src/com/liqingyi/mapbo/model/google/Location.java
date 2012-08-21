package com.liqingyi.mapbo.model.google;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	private double lat;
	private double lng;

	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Location(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		lat = parcel.readDouble();
		lng = parcel.readDouble();

	}

	public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {

		public Location createFromParcel(Parcel source) {
			return new Location(source);
		}

		public Location[] newArray(int size) {
			return new Location[size];
		}

	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(lat);
		dest.writeDouble(lng);

	}

}
