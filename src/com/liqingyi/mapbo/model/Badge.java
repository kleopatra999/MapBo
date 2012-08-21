package com.liqingyi.mapbo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Badge implements Parcelable {

	public Badge() {
		super();
		kuainv = new Kuainv();
	}

	public Kuainv getKuainv() {
		return kuainv;
	}

	public void setKuainv(Kuainv kuainv) {
		this.kuainv = kuainv;
	}

	public long getUc_domain() {
		return uc_domain;
	}

	public void setUc_domain(long uc_domain) {
		this.uc_domain = uc_domain;
	}

	public long getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(long enterprise) {
		this.enterprise = enterprise;
	}

	public long getAnniversary() {
		return anniversary;
	}

	public void setAnniversary(long anniversary) {
		this.anniversary = anniversary;
	}

	private Kuainv kuainv;
	private long uc_domain;
	private long enterprise;
	private long anniversary;

	public static final Parcelable.Creator<Badge> CREATOR = new Parcelable.Creator<Badge>() {

		public Badge createFromParcel(Parcel source) {
			return new Badge(source);
		}

		public Badge[] newArray(int size) {
			return new Badge[size];
		}

	};

	public Badge(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		kuainv = parcel.readParcelable(Kuainv.class.getClassLoader());
		uc_domain = parcel.readLong();
		enterprise = parcel.readLong();
		anniversary = parcel.readLong();

	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(kuainv, flags);
		dest.writeLong(uc_domain);
		dest.writeLong(enterprise);
		dest.writeLong(anniversary);

	}

}
