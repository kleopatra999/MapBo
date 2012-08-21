package com.liqingyi.mapbo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Annotation implements Parcelable {
	private Place place;
	private Wpinfo wpinfo;

	public Annotation() {
		super();
		place = new Place();
		wpinfo = new Wpinfo();
	}

	public Annotation(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		// ??
		place = parcel.readParcelable(Place.class.getClassLoader());
		wpinfo = parcel.readParcelable(Wpinfo.class.getClassLoader());
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Wpinfo getWpinfo() {
		return wpinfo;
	}

	public void setWpinfo(Wpinfo wpinfo) {
		this.wpinfo = wpinfo;
	}

	public static final Parcelable.Creator<Annotation> CREATOR = new Parcelable.Creator<Annotation>() {

		public Annotation createFromParcel(Parcel parcel) {
			return new Annotation(parcel);
		}

		public Annotation[] newArray(int size) {
			return new Annotation[size];
		}

	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(place, flags);
		dest.writeParcelable(wpinfo, flags);
	}

}
