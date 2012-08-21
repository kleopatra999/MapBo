package com.liqingyi.mapbo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class State implements Parcelable {

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getState() {
		return state;
	}

	public void setState(long state) {
		this.state = state;
	}

	public State() {
		super();
	}
	
	public State(Parcel parcel) {
		super();
		readToParcel(parcel);
	}


	private void readToParcel(Parcel parcel) {
		id=parcel.readString();
		state=parcel.readLong();
		
	}


	private String id;
	private long state;
	
	public static final Parcelable.Creator<State> CREATOR = new Parcelable.Creator<State>() {

		public State createFromParcel(Parcel source) {
			return new State(source);
		}

		public State[] newArray(int size) {
			return new State[size];
		}

	};


	@Override
	public int describeContents() {

		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeLong(state);

	}

}
