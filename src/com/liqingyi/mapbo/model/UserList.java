package com.liqingyi.mapbo.model;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.liqingyi.mapbo.instancecreator.StateInstanceCreator;
import com.liqingyi.mapbo.instancecreator.UserInstanceCreator;

import android.os.Parcel;
import android.os.Parcelable;

public class UserList implements Parcelable {

	public static UserList newStatuses(String json) {

		try {
			JSONObject object = new JSONObject(json);
			Type listType = new TypeToken<ArrayList<User>>() {
			}.getType();
			Type stateType = new TypeToken<ArrayList<com.liqingyi.mapbo.model.State>>() {
			}.getType();

			Gson gson = new GsonBuilder()
					.registerTypeAdapter(listType, new UserInstanceCreator())
					.registerTypeAdapter(stateType, new StateInstanceCreator())
					.create();
			return gson.fromJson(object.toString(), UserList.class);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
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

	private ArrayList<User> users;
	private long total_number;
	private ArrayList<State> states;

	public UserList() {
		super();
		users = new ArrayList<User>();
		states = new ArrayList<State>();
	}

	public UserList(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		parcel.readTypedList(users, User.CREATOR);
		total_number = parcel.readLong();
		parcel.readTypedList(states, State.CREATOR);

	}

	public static final Parcelable.Creator<UserList> CREATOR = new Parcelable.Creator<UserList>() {

		public UserList createFromParcel(Parcel source) {
			return new UserList(source);
		}

		public UserList[] newArray(int size) {
			return new UserList[size];
		}

	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeTypedList(users);
		dest.writeLong(total_number);
		dest.writeTypedList(states);
	}

}
