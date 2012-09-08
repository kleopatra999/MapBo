package com.liqingyi.mapbo.googlemap;

import com.liqingyi.mapbo.model.Poi;
import com.liqingyi.mapbo.model.google.GooglePlace;
import android.os.Parcel;
import android.os.Parcelable;

public class MapArgument implements Parcelable {

	public GooglePlace getGooglePlace() {
		return googlePlace;
	}

	public void setGooglePlace(GooglePlace googlePlace) {
		this.googlePlace = googlePlace;
	}

	public Poi getPoi() {
		return poi;
	}

	public void setPoi(Poi poi) {
		this.poi = poi;
	}

	public MapArgument() {
		super();
		if (googlePlace == null) {
			googlePlace = new GooglePlace();
		}

		if (poi == null) {
			poi = new Poi();
		}
	}

	public MapArgument(Poi poi) {
		super();
		this.poi = poi;
	}

	public MapArgument(GooglePlace googlePlace) {
		super();
		this.googlePlace = googlePlace;

	}

	private GooglePlace googlePlace;
	private Poi poi;

	public static final Parcelable.Creator<MapArgument> CREATOR = new Parcelable.Creator<MapArgument>() {

		public MapArgument createFromParcel(Parcel source) {
			return new MapArgument(source);
		}

		public MapArgument[] newArray(int size) {
			return new MapArgument[size];
		}

	};

	public MapArgument(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		googlePlace = parcel.readParcelable(GooglePlace.class.getClassLoader());
		poi = parcel.readParcelable(Poi.class.getClassLoader());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(googlePlace, flags);
		dest.writeParcelable(poi, flags);

	}

}
