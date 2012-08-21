package com.liqingyi.mapbo.model.google;

import android.os.Parcel;
import android.os.Parcelable;

public class Term implements Parcelable {

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private int length;
	private int offset;
	private String value;

	public Term() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Term(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		length = parcel.readInt();
		offset = parcel.readInt();

		value = parcel.readString();

	}

	public static final Parcelable.Creator<Term> CREATOR = new Parcelable.Creator<Term>() {

		public Term createFromParcel(Parcel source) {
			return new Term(source);
		}

		public Term[] newArray(int size) {
			return new Term[size];
		}

	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(length);
		dest.writeInt(offset);
		dest.writeString(value);

	}

}
