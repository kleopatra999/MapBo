package com.liqingyi.mapbo.model;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.liqingyi.mapbo.instancecreator.StateInstanceCreator;
import com.liqingyi.mapbo.instancecreator.StatusesInstanceCreator;

import android.os.Parcel;
import android.os.Parcelable;

public class Statuses implements Parcelable {

	public static Statuses newStatuses(String json) {

		if (!"[]".equals(json))
			try {
				JSONObject object = new JSONObject(json);

				Type listType = new TypeToken<ArrayList<com.liqingyi.mapbo.model.Status>>() {
				}.getType();
				Type stateType = new TypeToken<ArrayList<com.liqingyi.mapbo.model.State>>() {
				}.getType();

				Gson gson = new GsonBuilder()
						.registerTypeAdapter(listType,
								new StatusesInstanceCreator())
						.registerTypeAdapter(stateType,
								new StateInstanceCreator()).create();

				return gson.fromJson(object.toString(), Statuses.class);

			} catch (JSONException e) {
				e.printStackTrace();
			}

		return null;

	}

	public ArrayList<Status> getStatuses() {
		return statuses;
	}

	public void setStatuses(ArrayList<Status> statuses) {
		this.statuses = statuses;
	}

	public long getTotal_number() {
		return total_number;
	}

	public void setTotal_number(long total_number) {
		this.total_number = total_number;
	}

	public ArrayList<State> getStates() {
		return states;
	}

	public void setStates(ArrayList<State> states) {
		this.states = states;
	}

	private ArrayList<Status> statuses;
	private long total_number;
	private ArrayList<State> states;

	public Statuses() {
		super();
		statuses = new ArrayList<Status>();
		states = new ArrayList<State>();
	}

	public static final Parcelable.Creator<Statuses> CREATOR = new Parcelable.Creator<Statuses>() {

		public Statuses createFromParcel(Parcel source) {
			return new Statuses(source);
		}

		public Statuses[] newArray(int size) {
			return new Statuses[size];
		}

	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeLong(total_number);
		parcel.writeTypedList(statuses);
		parcel.writeTypedList(states);
	}

	public Statuses(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		total_number = parcel.readLong();
		parcel.readTypedList(statuses, Status.CREATOR);
		parcel.readTypedList(states, State.CREATOR);
	}

}
