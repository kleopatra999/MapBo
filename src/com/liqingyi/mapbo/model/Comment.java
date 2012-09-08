package com.liqingyi.mapbo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Comment implements Parcelable {

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getIdstr() {
		return idstr;
	}

	public void setIdstr(String idstr) {
		this.idstr = idstr;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	private String created_at;
	private long id;
	private String text;
	private String source;
	private User user;
	private String mid;
	private String idstr;
	private Status status;

	public Comment() {
		super();
		if (user == null)
			user = new User();

		if (status == null)
			status = new Status();
	}

	public static final Parcelable.Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() {

		public Comment createFromParcel(Parcel source) {
			return new Comment(source);
		}

		public Comment[] newArray(int size) {
			return new Comment[size];
		}

	};

	public Comment(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		created_at = parcel.readString();
		id = parcel.readLong();
		text = parcel.readString();
		source = parcel.readString();
		user = parcel.readParcelable(User.class.getClassLoader());
		mid = parcel.readString();
		idstr = parcel.readString();
		status = parcel.readParcelable(Status.class.getClassLoader());

	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(created_at);
		dest.writeLong(id);
		dest.writeString(text);
		dest.writeString(source);
		dest.writeParcelable(user, flags);
		dest.writeString(mid);
		dest.writeString(idstr);
		dest.writeParcelable(status, flags);
	}

}
