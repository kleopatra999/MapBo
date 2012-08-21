package com.liqingyi.mapbo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Kuainv implements Parcelable {
	private long level;

	public Kuainv() {
		super();
	}

	public static final Parcelable.Creator<Kuainv> CREATOR = new Parcelable.Creator<Kuainv>() {

		public Kuainv createFromParcel(Parcel source) {
			return new Kuainv(source);
		}

		public Kuainv[] newArray(int size) {
			return new Kuainv[size];
		}

	};

	public Kuainv(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		level = parcel.readLong();

	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(level);
	}

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

}
