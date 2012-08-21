package com.liqingyi.mapbo.model.google;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class DetailsResult implements Parcelable {

	public ArrayList<AddressComponent> getAddress_components() {
		return address_components;
	}

	public void setAddress_components(
			ArrayList<AddressComponent> address_components) {
		this.address_components = address_components;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public String getFormatted_phone_number() {
		return formatted_phone_number;
	}

	public void setFormatted_phone_number(String formatted_phone_number) {
		this.formatted_phone_number = formatted_phone_number;
	}

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

	public String getInternational_phone_number() {
		return international_phone_number;
	}

	public void setInternational_phone_number(String international_phone_number) {
		this.international_phone_number = international_phone_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getUtc_offset() {
		return utc_offset;
	}

	public void setUtc_offset(long utc_offset) {
		this.utc_offset = utc_offset;
	}

	public String getVicinity() {
		return vicinity;
	}

	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	private ArrayList<AddressComponent> address_components;
	private String formatted_address;
	private String formatted_phone_number;
	private Geometry geometry;
	private String icon;
	private String id;
	private String international_phone_number;
	private String name;
	private float rating;
	private String reference;
	private ArrayList<Review> reviews;
	private String[] types;
	private String url;
	private long utc_offset;
	private String vicinity;
	private String website;

	public DetailsResult() {
		super();
		if (address_components == null)
			address_components = new ArrayList<AddressComponent>();
		if (geometry == null)
			geometry = new Geometry();
		if (reviews == null)
			reviews = new ArrayList<Review>();

	}

	public DetailsResult(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		parcel.readTypedList(address_components, AddressComponent.CREATOR);
		formatted_address = parcel.readString();
		formatted_phone_number = parcel.readString();
		geometry = parcel.readParcelable(Geometry.class.getClassLoader());
		icon = parcel.readString();
		id = parcel.readString();
		international_phone_number = parcel.readString();
		name = parcel.readString();
		icon = parcel.readString();
		rating = parcel.readFloat();

		reference = parcel.readString();
		parcel.readTypedList(reviews, Review.CREATOR);
		types = parcel.createStringArray();
		url = parcel.readString();
		utc_offset = parcel.readLong();
		vicinity = parcel.readString();
		website = parcel.readString();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static final Parcelable.Creator<DetailsResult> CREATOR = new Parcelable.Creator<DetailsResult>() {

		public DetailsResult createFromParcel(Parcel source) {
			return new DetailsResult(source);
		}

		public DetailsResult[] newArray(int size) {
			return new DetailsResult[size];
		}

	};

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeTypedList(address_components);
		dest.writeString(formatted_address);
		dest.writeString(formatted_phone_number);
		dest.writeParcelable(geometry, flags);
		dest.writeString(icon);

		dest.writeString(id);
		dest.writeString(international_phone_number);
		dest.writeString(name);
		dest.writeFloat(rating);

		dest.writeString(reference);
		dest.writeTypedList(reviews);
		dest.writeStringArray(types);
		dest.writeString(url);

		dest.writeLong(utc_offset);
		dest.writeString(vicinity);
		dest.writeString(website);

	}

}
