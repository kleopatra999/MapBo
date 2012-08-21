package com.liqingyi.mapbo.model.google;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable {

	public ArrayList<Aspect> getAspects() {
		return aspects;
	}

	public void setAspects(ArrayList<Aspect> aspects) {
		this.aspects = aspects;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getAuthor_url() {
		return author_url;
	}

	public void setAuthor_url(String author_url) {
		this.author_url = author_url;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	private ArrayList<Aspect> aspects;
	private String author_name;
	private String author_url;
	private String text;
	private String time;

	public Review() {
		super();
		if (aspects == null)
			aspects = new ArrayList<Aspect>();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Review(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		parcel.readTypedList(aspects, Aspect.CREATOR);
		author_name = parcel.readString();
		author_url = parcel.readString();
		text = parcel.readString();
		time = parcel.readString();

	}

	public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {

		public Review createFromParcel(Parcel source) {
			return new Review(source);
		}

		public Review[] newArray(int size) {
			return new Review[size];
		}

	};

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeTypedList(aspects);
		dest.writeString(author_name);
		dest.writeString(author_url);
		dest.writeString(text);
		dest.writeString(time);

	}

}
