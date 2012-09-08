package com.liqingyi.mapbo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchParameter implements Parcelable {

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public SearchParameter() {
		super();
		city = "0010";
		category = "";
	}

	private String keyword;
	private String city;
	private String category;

	public static final Parcelable.Creator<SearchParameter> CREATOR = new Parcelable.Creator<SearchParameter>() {

		public SearchParameter createFromParcel(Parcel source) {
			return new SearchParameter(source);
		}

		public SearchParameter[] newArray(int size) {
			return new SearchParameter[size];
		}

	};

	public SearchParameter(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		keyword = parcel.readString();
		city = parcel.readString();
		category = parcel.readString();

	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(keyword);
		dest.writeString(city);
		dest.writeString(category);

	}

}
