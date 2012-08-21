package com.liqingyi.mapbo.model.google;

import android.os.Parcel;
import android.os.Parcelable;

public class Geometry implements Parcelable {

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	private Location location;

	public Geometry() {
		super();
		if (location == null)
			location = new Location();
	}

	public Geometry(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		location = parcel.readParcelable(Location.class.getClassLoader());
	}
	
	public static final Parcelable.Creator<Geometry> CREATOR = new Parcelable.Creator<Geometry>() {

		public Geometry createFromParcel(Parcel source) {
			return new Geometry(source);
		}

		public Geometry[] newArray(int size) {
			return new Geometry[size];
		}

	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(location, flags);

	}

}
