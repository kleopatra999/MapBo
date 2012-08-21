package com.liqingyi.mapbo.model.google;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.liqingyi.mapbo.instancecreator.GooglePlaceInstanceCreator;

import android.os.Parcel;
import android.os.Parcelable;

public class GooglePlaceList implements Parcelable {

	public static GooglePlaceList initializeGooglePlaceList(String json) {
		Type listType = new TypeToken<ArrayList<GooglePlace>>() {
		}.getType();
		Gson gson = new GsonBuilder().registerTypeAdapter(listType,
				new GooglePlaceInstanceCreator()).create();

		return gson.fromJson(json, GooglePlaceList.class);

	}

	public ArrayList<GooglePlace> getResults() {
		return results;
	}

	public void setResults(ArrayList<GooglePlace> results) {
		this.results = results;
	}

	public String[] getHtml_attributions() {
		return html_attributions;
	}

	public void setHtml_attributions(String[] html_attributions) {
		this.html_attributions = html_attributions;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private ArrayList<GooglePlace> results;
	private String[] html_attributions;
	private String status;

	public GooglePlaceList() {
		super();
		if (results == null)
			results = new ArrayList<GooglePlace>();
	}

	public GooglePlaceList(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		parcel.readTypedList(results, GooglePlace.CREATOR);
		html_attributions = parcel.createStringArray();
		status = parcel.readString();

	}

	public static final Parcelable.Creator<GooglePlaceList> CREATOR = new Parcelable.Creator<GooglePlaceList>() {

		public GooglePlaceList createFromParcel(Parcel source) {
			return new GooglePlaceList(source);
		}

		public GooglePlaceList[] newArray(int size) {
			return new GooglePlaceList[size];
		}

	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeTypedList(results);
		dest.writeStringArray(html_attributions);

		dest.writeString(status);

	}

}
