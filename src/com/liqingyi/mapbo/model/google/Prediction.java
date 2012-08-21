package com.liqingyi.mapbo.model.google;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Prediction implements Parcelable {

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public ArrayList<Term> getMatched_substrings() {
		return matched_substrings;
	}

	public void setMatched_substrings(ArrayList<Term> matched_substrings) {
		this.matched_substrings = matched_substrings;
	}

	public ArrayList<Term> getTerms() {
		return terms;
	}

	public void setTerms(ArrayList<Term> terms) {
		this.terms = terms;
	}

	private String description;
	private String id;
	private String reference;
	private String[] types;
	private ArrayList<Term> matched_substrings;
	private ArrayList<Term> terms;

	public Prediction() {
		super();
		if (matched_substrings == null)
			matched_substrings = new ArrayList<Term>();
		if (terms == null)
			terms = new ArrayList<Term>();

	}

	public Prediction(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		description = parcel.readString();
		id = parcel.readString();
		reference = parcel.readString();
		types = parcel.createStringArray();
		parcel.readTypedList(matched_substrings, Term.CREATOR);
		parcel.readTypedList(terms, Term.CREATOR);
	}

	public static final Parcelable.Creator<Prediction> CREATOR = new Parcelable.Creator<Prediction>() {

		public Prediction createFromParcel(Parcel source) {
			return new Prediction(source);
		}

		public Prediction[] newArray(int size) {
			return new Prediction[size];
		}

	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(description);
		dest.writeString(id);
		dest.writeString(reference);

		dest.writeStringArray(types);
		dest.writeTypedList(matched_substrings);
		dest.writeTypedList(terms);

	}

}
