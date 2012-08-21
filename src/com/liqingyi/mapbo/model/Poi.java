package com.liqingyi.mapbo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Poi implements Parcelable {

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getWeibo_id() {
		return weibo_id;
	}

	public void setWeibo_id(String weibo_id) {
		this.weibo_id = weibo_id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCategorys() {
		return categorys;
	}

	public void setCategorys(String categorys) {
		this.categorys = categorys;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCheckin_user_num() {
		return checkin_user_num;
	}

	public void setCheckin_user_num(String checkin_user_num) {
		this.checkin_user_num = checkin_user_num;
	}

	public long getHerenow_user_num() {
		return herenow_user_num;
	}

	public void setHerenow_user_num(long herenow_user_num) {
		this.herenow_user_num = herenow_user_num;
	}

	public long getCheckin_num() {
		return checkin_num;
	}

	public void setCheckin_num(long checkin_num) {
		this.checkin_num = checkin_num;
	}

	public long getTip_num() {
		return tip_num;
	}

	public void setTip_num(long tip_num) {
		this.tip_num = tip_num;
	}

	public long getPhoto_num() {
		return photo_num;
	}

	public void setPhoto_num(long photo_num) {
		this.photo_num = photo_num;
	}

	public long getTodo_num() {
		return todo_num;
	}

	public void setTodo_num(long todo_num) {
		this.todo_num = todo_num;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getDriving_directions() {
		return driving_directions;
	}

	public void setDriving_directions(String driving_directions) {
		this.driving_directions = driving_directions;
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String parking) {
		this.parking = parking;
	}

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	private String poiid;
	private String title;
	private String address;
	private String lon;
	private String lat;

	private String category;
	private String city;
	private String province;
	private String country;
	private String url;

	private String phone;
	private String postcode;
	private String weibo_id;
	private String icon;
	private String extra;

	private String rid;
	private String transport;
	private String business;
	private String driving_directions;

	private String parking;
	private String circle;
	private String categorys;
	private String category_name;
	private String checkin_user_num;
	private long herenow_user_num;
	private long checkin_num;
	private long tip_num;
	private long photo_num;
	private long todo_num;
	private long distance;

	public Poi() {
		super();
	}

	public Poi(Parcel parcel) {
		readToParcel(parcel);
	}

	public static final Parcelable.Creator<Poi> CREATOR = new Parcelable.Creator<Poi>() {

		public Poi createFromParcel(Parcel source) {
			return new Poi(source);
		}

		public Poi[] newArray(int size) {
			return new Poi[size];
		}

	};

	@Override
	public int describeContents() {
		return 0;
	}

	private void readToParcel(Parcel parcel) {
		poiid = parcel.readString();
		title = parcel.readString();
		address = parcel.readString();
		lon = parcel.readString();
		lat = parcel.readString();
		category = parcel.readString();
		city = parcel.readString();
		province = parcel.readString();
		country = parcel.readString();
		url = parcel.readString();
		phone = parcel.readString();
		postcode = parcel.readString();
		weibo_id = parcel.readString();
		icon = parcel.readString();
		extra = parcel.readString();
		rid = parcel.readString();
		transport = parcel.readString();
		business = parcel.readString();
		driving_directions = parcel.readString();
		parking = parcel.readString();
		circle = parcel.readString();
		categorys = parcel.readString();
		category_name = parcel.readString();
		checkin_user_num = parcel.readString();
		herenow_user_num = parcel.readLong();
		checkin_num = parcel.readLong();
		tip_num = parcel.readLong();
		photo_num = parcel.readLong();
		todo_num = parcel.readLong();
		distance = parcel.readLong();

	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(poiid);
		dest.writeString(title);
		dest.writeString(address);
		dest.writeString(lon);
		dest.writeString(lat);
		dest.writeString(category);
		dest.writeString(city);
		dest.writeString(province);
		dest.writeString(country);
		dest.writeString(url);
		dest.writeString(phone);
		dest.writeString(postcode);
		dest.writeString(weibo_id);
		dest.writeString(icon);
		dest.writeString(extra);
		dest.writeString(rid);
		dest.writeString(transport);
		dest.writeString(business);
		dest.writeString(driving_directions);
		dest.writeString(parking);
		dest.writeString(circle);
		dest.writeString(categorys);
		dest.writeString(category_name);
		dest.writeString(checkin_user_num);
		dest.writeLong(herenow_user_num);
		dest.writeLong(checkin_num);
		dest.writeLong(tip_num);
		dest.writeLong(photo_num);
		dest.writeLong(todo_num);
		dest.writeLong(distance);

	}

}
