package com.liqingyi.mapbo.model;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.liqingyi.mapbo.instancecreator.PoiInstanceCreator;

import android.os.Parcel;
import android.os.Parcelable;

public class PoiList implements Parcelable {

	public static PoiList initializePoiList(String json) {

		Type poiType = new TypeToken<ArrayList<Poi>>() {
		}.getType();

		Gson gson = new GsonBuilder().registerTypeAdapter(poiType,
				new PoiInstanceCreator()).create();

		return gson.fromJson(json, PoiList.class);
	}

	private ArrayList<Poi> pois;
	private long total_number;
	private Geo geo;

	public ArrayList<Poi> getPois() {
		return pois;
	}

	public void setPois(ArrayList<Poi> pois) {
		this.pois = pois;
	}

	public long getTotal_number() {
		return total_number;
	}

	public void setTotal_number(long total_number) {
		this.total_number = total_number;
	}

	public Geo getGeo() {
		return geo;
	}

	public void setGeo(Geo geo) {
		this.geo = geo;
	}

	public PoiList() {
		super();
		if (null == pois)
			pois = new ArrayList<Poi>();
		if (null == geo)
			geo = new Geo();
	}

	@Override
	public int describeContents() {
		return 0;

	}

	public PoiList(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		parcel.readTypedList(pois, Poi.CREATOR);
		total_number = parcel.readLong();
		geo = parcel.readParcelable(Geo.class.getClassLoader());

	}

	public static final Parcelable.Creator<PoiList> CREATOR = new Parcelable.Creator<PoiList>() {

		public PoiList createFromParcel(Parcel source) {
			return new PoiList(source);
		}

		public PoiList[] newArray(int size) {
			return new PoiList[size];
		}

	};

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeTypedList(pois);
		dest.writeLong(total_number);
		dest.writeParcelable(geo, flags);
	}

}
