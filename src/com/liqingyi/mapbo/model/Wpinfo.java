package com.liqingyi.mapbo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Wpinfo implements Parcelable {

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Wpinfo() {
		super();
	}

	private String type;
	private String url;
	private String title;

	public static final Parcelable.Creator<Wpinfo> CREATOR = new Parcelable.Creator<Wpinfo>() {

		public Wpinfo createFromParcel(Parcel source) {
			return new Wpinfo(source);
		}

		public Wpinfo[] newArray(int size) {
			return new Wpinfo[size];
		}

	};

	public Wpinfo(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		type = parcel.readString();
		url = parcel.readString();
		title = parcel.readString();

	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(type);
		dest.writeString(url);
		dest.writeString(title);
	}

}
