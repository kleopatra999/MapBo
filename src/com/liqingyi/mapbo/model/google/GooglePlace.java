package com.liqingyi.mapbo.model.google;

import android.os.Parcel;
import android.os.Parcelable;

public class GooglePlace implements Parcelable {

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

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

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public String getVicinity() {
		return vicinity;
	}

	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}

	private Geometry geometry;
	private String icon;
	private String id;
	private String name;
	private String reference;
	private String[] types;
	private String vicinity;

	public GooglePlace() {
		super();
		if (geometry == null)
			geometry = new Geometry();

	}

	public GooglePlace(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {

		geometry = parcel.readParcelable(Geometry.class.getClassLoader());
		icon = parcel.readString();
		id = parcel.readString();
		name = parcel.readString();
		reference = parcel.readString();
		types = parcel.createStringArray();
		vicinity = parcel.readString();

	}

	public static final Parcelable.Creator<GooglePlace> CREATOR = new Parcelable.Creator<GooglePlace>() {

		public GooglePlace createFromParcel(Parcel source) {
			return new GooglePlace(source);
		}

		public GooglePlace[] newArray(int size) {
			return new GooglePlace[size];
		}

	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(geometry, flags);
		dest.writeString(icon);
		dest.writeString(id);
		dest.writeString(name);
		dest.writeString(reference);
		dest.writeStringArray(types);
		dest.writeString(vicinity);

	}

}
