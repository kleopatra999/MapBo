package com.liqingyi.mapbo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Parcelable {

	public String getPoiid() {
		return poiid;
	}

	public void setPoiid(String poiid) {
		this.poiid = poiid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private String poiid;
	private String title;
	private double lon;
	private double lat;
	private String type;
	private String url;
	private String source;

	public Place() {
		super();
	}

	public Place(Parcel parcel) {
		readToParcel(parcel);
	}

	public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() {

		public Place createFromParcel(Parcel source) {
			return new Place(source);
		}

		public Place[] newArray(int size) {
			return new Place[size];
		}

	};

	@Override
	public int describeContents() {
		return 0;
	}

	private void readToParcel(Parcel parcel) {
		poiid = parcel.readString();
		title = parcel.readString();
		type = parcel.readString();
		source = parcel.readString();
		lon = parcel.readDouble();
		lat = parcel.readDouble();
		url = parcel.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(poiid);
		dest.writeString(title);
		dest.writeString(type);
		dest.writeString(source);
		dest.writeDouble(lon);
		dest.writeDouble(lat);
		dest.writeString(url);
	}

}
