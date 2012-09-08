package com.liqingyi.mapbo.model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.os.Parcel;
import android.os.Parcelable;

public class Province implements Parcelable {

	public static ArrayList<Province> getProvinceList(String json) {

		Gson gson = new Gson();
		Type type = new TypeToken<ArrayList<Map<String, String>>>() {
		}.getType();

		ArrayList<Map<String, String>> maps = gson.fromJson(json, type);

		ArrayList<Province> provinces = new ArrayList<Province>();

		for (Map<String, String> map : maps) {

			Set<Entry<String, String>> set = map.entrySet();
			Iterator<Entry<String, String>> iterable = set.iterator();
			while (iterable.hasNext()) {
				Map.Entry<java.lang.String, java.lang.String> entry = (Map.Entry<java.lang.String, java.lang.String>) iterable
						.next();
				provinces.add(new Province(entry.getKey(), entry.getValue()));

			}

		}

		return provinces;

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

	private String id;
	private String name;

	public Province() {
		super();
	}

	public Province(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public static final Parcelable.Creator<Province> CREATOR = new Parcelable.Creator<Province>() {

		public Province createFromParcel(Parcel source) {
			return new Province(source);
		}

		public Province[] newArray(int size) {
			return new Province[size];
		}

	};

	public Province(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		id = parcel.readString();
		name = parcel.readString();

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
	}

}
