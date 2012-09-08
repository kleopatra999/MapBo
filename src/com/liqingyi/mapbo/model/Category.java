package com.liqingyi.mapbo.model;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {

	public static ArrayList<Category> getCategory(String json) {
		Type categoryType = new TypeToken<ArrayList<Category>>() {
		}.getType();
		Gson gson = new Gson();

		return gson.fromJson(json, categoryType);
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	private String id;
	private String name;
	private String pid;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {

		public Category createFromParcel(Parcel source) {
			return new Category(source);
		}

		public Category[] newArray(int size) {
			return new Category[size];
		}

	};

	public Category(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		id = parcel.readString();
		name = parcel.readString();
		pid = parcel.readString();

	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(name);
		dest.writeString(pid);
	}

}
