package com.liqingyi.mapbo.model.google;

import android.os.Parcel;
import android.os.Parcelable;

public class AddressComponent implements Parcelable {

	public String getLong_name() {
		return long_name;
	}

	public void setLong_name(String long_name) {
		this.long_name = long_name;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	private String long_name;
	private String short_name;
	private String[] types;

	public AddressComponent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddressComponent(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		long_name = parcel.readString();
		short_name = parcel.readString();
		types = parcel.createStringArray();

	}

	public static final Parcelable.Creator<AddressComponent> CREATOR = new Parcelable.Creator<AddressComponent>() {

		public AddressComponent createFromParcel(Parcel source) {
			return new AddressComponent(source);
		}

		public AddressComponent[] newArray(int size) {
			return new AddressComponent[size];
		}

	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(long_name);
		dest.writeString(short_name);
		dest.writeStringArray(types);

	}

}
