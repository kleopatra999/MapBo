package com.liqingyi.mapbo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Geo implements Parcelable {

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private String type;
	private double[] coordinates;
	private String lon;
	private String lat;
	private String longitude;
	private String latitude;
	private String city;
	private String province;
	private String city_name;
	private String province_name;
	private String district_name;
	private String name;
	private String street;
	private String tel;

	private String distance;
	private String direction;
	private String address;

	public Geo() {
		super();
	}

	public Geo(Parcel parcel) {
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		type = parcel.readString();
		coordinates = parcel.createDoubleArray();
		lon = parcel.readString();
		lat = parcel.readString();

		longitude = parcel.readString();
		latitude = parcel.readString();
		city = parcel.readString();
		province = parcel.readString();
		city_name = parcel.readString();
		province_name = parcel.readString();
		district_name = parcel.readString();
		name = parcel.readString();
		street = parcel.readString();
		tel = parcel.readString();
		distance = parcel.readString();
		direction = parcel.readString();
		address = parcel.readString();
	}

	public static final Parcelable.Creator<Geo> CREATOR = new Parcelable.Creator<Geo>() {

		public Geo createFromParcel(Parcel source) {
			return new Geo(source);
		}

		public Geo[] newArray(int size) {
			return new Geo[size];
		}

	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(type);
		dest.writeDoubleArray(coordinates);
		dest.writeString(lon);
		dest.writeString(lat);

		dest.writeString(longitude);
		dest.writeString(latitude);
		dest.writeString(city);
		dest.writeString(province);
		dest.writeString(city_name);
		dest.writeString(province_name);
		dest.writeString(district_name);
		dest.writeString(name);
		dest.writeString(street);
		dest.writeString(tel);
		dest.writeString(distance);
		dest.writeString(direction);
		dest.writeString(address);
	}

}
