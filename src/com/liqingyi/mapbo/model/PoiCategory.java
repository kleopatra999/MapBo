package com.liqingyi.mapbo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PoiCategory implements Parcelable {

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public PoiCategory() {
		super();

	}

	public PoiCategory(Parcel parcel) {
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		id = parcel.readString();
		name = parcel.readString();
		pid = parcel.readString();

	}

	private String id;
	private String name;
	private String pid;

	public static final Parcelable.Creator<PoiCategory> CREATOR = new Parcelable.Creator<PoiCategory>() {

		public PoiCategory createFromParcel(Parcel source) {
			return new PoiCategory(source);
		}

		public PoiCategory[] newArray(int size) {
			return new PoiCategory[size];
		}

	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(name);
		dest.writeString(pid);

	}

}
