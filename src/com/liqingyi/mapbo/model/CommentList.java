package com.liqingyi.mapbo.model;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.liqingyi.mapbo.instancecreator.CommentInstanceCreator;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentList implements Parcelable {

	public static CommentList initializeCommentList(String json) {
		Type poiType = new TypeToken<ArrayList<Comment>>() {
		}.getType();

		Gson gson = new GsonBuilder().registerTypeAdapter(poiType,
				new CommentInstanceCreator()).create();

		return gson.fromJson(json, CommentList.class);
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public long getTotal_number() {
		return total_number;
	}

	public void setTotal_number(long total_number) {
		this.total_number = total_number;
	}

	private ArrayList<Comment> comments;
	private long total_number;

	public CommentList() {
		super();
		if (comments == null)
			comments = new ArrayList<Comment>();
	}

	public CommentList(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		parcel.readTypedList(comments, Comment.CREATOR);
		total_number = parcel.readLong();

	}

	public static final Parcelable.Creator<CommentList> CREATOR = new Parcelable.Creator<CommentList>() {

		public CommentList createFromParcel(Parcel source) {
			return new CommentList(source);
		}

		public CommentList[] newArray(int size) {
			return new CommentList[size];
		}

	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeTypedList(comments);
		dest.writeLong(total_number);

	}

}
