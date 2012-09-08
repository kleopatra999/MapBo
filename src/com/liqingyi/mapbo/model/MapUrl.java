package com.liqingyi.mapbo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MapUrl implements Parcelable {

	public String getMap_image_url() {
		return map_image_url;
	}

	public void setMap_image_url(String map_image_url) {
		this.map_image_url = map_image_url;
	}

	private String map_image_url;

	public MapUrl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static final Parcelable.Creator<MapUrl> CREATOR = new Parcelable.Creator<MapUrl>() {

		public MapUrl createFromParcel(Parcel source) {
			return new MapUrl(source);
		}

		public MapUrl[] newArray(int size) {
			return new MapUrl[size];
		}

	};

	public MapUrl(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		map_image_url = parcel.readString();

	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(map_image_url);

	}

}
