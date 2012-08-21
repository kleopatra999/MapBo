package com.liqingyi.mapbo.model.google;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.liqingyi.mapbo.instancecreator.PredictionInstanceCreator;

import android.os.Parcel;
import android.os.Parcelable;

public class PredictionList implements Parcelable {

	public static PredictionList initializePrediction(String json) {
		Type listType = new TypeToken<ArrayList<Prediction>>() {
		}.getType();
		Gson gson = new GsonBuilder().registerTypeAdapter(listType,
				new PredictionInstanceCreator()).create();

		return gson.fromJson(json, PredictionList.class);

	}

	public ArrayList<Prediction> getPredictions() {
		return predictions;
	}

	public void setPredictions(ArrayList<Prediction> predictions) {
		this.predictions = predictions;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private ArrayList<Prediction> predictions;
	private String status;

	public PredictionList() {
		super();
		if (predictions == null)
			predictions = new ArrayList<Prediction>();
	}

	public PredictionList(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		parcel.readTypedList(predictions, Prediction.CREATOR);
		status = parcel.readString();

	}

	public static final Parcelable.Creator<PredictionList> CREATOR = new Parcelable.Creator<PredictionList>() {

		public PredictionList createFromParcel(Parcel source) {
			return new PredictionList(source);
		}

		public PredictionList[] newArray(int size) {
			return new PredictionList[size];
		}

	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeTypedList(predictions);
		dest.writeString(status);

	}

}
