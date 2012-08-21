package com.liqingyi.mapbo.model.google;

import com.google.gson.Gson;
import android.os.Parcel;
import android.os.Parcelable;

public class GooglePlaceDetails implements Parcelable {

	public static GooglePlaceDetails initializeGooglePlaceDetails(String json) {

		Gson gson = new Gson();
		return gson.fromJson(json, GooglePlaceDetails.class);

	}

	public String[] getHtml_attributions() {
		return html_attributions;
	}

	public void setHtml_attributions(String[] html_attributions) {
		this.html_attributions = html_attributions;
	}

	public DetailsResult getResult() {
		return result;
	}

	public void setResult(DetailsResult result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String[] html_attributions;
	private DetailsResult result;
	private String status;

	public GooglePlaceDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GooglePlaceDetails(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		html_attributions = parcel.createStringArray();
		result = parcel.readParcelable(DetailsResult.class.getClassLoader());
		status = parcel.readString();

	}

	public static final Parcelable.Creator<GooglePlaceDetails> CREATOR = new Parcelable.Creator<GooglePlaceDetails>() {

		public GooglePlaceDetails createFromParcel(Parcel source) {
			return new GooglePlaceDetails(source);
		}

		public GooglePlaceDetails[] newArray(int size) {
			return new GooglePlaceDetails[size];
		}

	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringArray(html_attributions);
		dest.writeParcelable(result, flags);
		dest.writeString(status);

	}

}
