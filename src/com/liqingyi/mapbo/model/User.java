package com.liqingyi.mapbo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

	public boolean isFollowing() {
		return following;
	}

	public void setFollowing(boolean following) {
		this.following = following;
	}

	public boolean isAllow_all_act_msg() {
		return allow_all_act_msg;
	}

	public void setAllow_all_act_msg(boolean allow_all_act_msg) {
		this.allow_all_act_msg = allow_all_act_msg;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public boolean isAllow_all_comment() {
		return allow_all_comment;
	}

	public void setAllow_all_comment(boolean allow_all_comment) {
		this.allow_all_comment = allow_all_comment;
	}

	public boolean isFollow_me() {
		return follow_me;
	}

	public void setFollow_me(boolean follow_me) {
		this.follow_me = follow_me;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getScreen_name() {
		return screen_name;
	}

	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProfile_image_url() {
		return profile_image_url;
	}

	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getFollowers_count() {
		return followers_count;
	}

	public void setFollowers_count(long followers_count) {
		this.followers_count = followers_count;
	}

	public long getFriends_count() {
		return friends_count;
	}

	public void setFriends_count(long friends_count) {
		this.friends_count = friends_count;
	}

	public long getStatuses_count() {
		return statuses_count;
	}

	public void setStatuses_count(long statuses_count) {
		this.statuses_count = statuses_count;
	}

	public long getFavourites_count() {
		return favourites_count;
	}

	public void setFavourites_count(long favourites_count) {
		this.favourites_count = favourites_count;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAvatar_large() {
		return avatar_large;
	}

	public void setAvatar_large(String avatar_large) {
		this.avatar_large = avatar_large;
	}

	public String getVerified_reason() {
		return verified_reason;
	}

	public void setVerified_reason(String verified_reason) {
		this.verified_reason = verified_reason;
	}

	public long getOnline_status() {
		return online_status;
	}

	public void setOnline_status(long online_status) {
		this.online_status = online_status;
	}

	public long getBi_followers_count() {
		return bi_followers_count;
	}

	public void setBi_followers_count(long bi_followers_count) {
		this.bi_followers_count = bi_followers_count;
	}

	public String getIdstr() {
		return idstr;
	}

	public void setIdstr(String idstr) {
		this.idstr = idstr;
	}

	public String getProfile_url() {
		return profile_url;
	}

	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
	}

	public boolean isGeo_enabled() {
		return geo_enabled;
	}

	public void setGeo_enabled(boolean geo_enabled) {
		this.geo_enabled = geo_enabled;
	}

	public long getVerified_type() {
		return verified_type;
	}

	public void setVerified_type(long verified_type) {
		this.verified_type = verified_type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	public long getUlevel() {
		return ulevel;
	}

	public void setUlevel(long ulevel) {
		this.ulevel = ulevel;
	}

	public Badge getBadge() {
		return badge;
	}

	public void setBadge(Badge badge) {
		this.badge = badge;
	}

	public String getCheckin_at() {
		return checkin_at;
	}

	public void setCheckin_at(String checkin_at) {
		this.checkin_at = checkin_at;
	}

	private long id;
	private String idstr;
	private String screen_name;
	private String name;
	private String province;
	private String city;
	private String location;
	private String description;
	private String url;
	private String profile_image_url;
	private String profile_url;
	private String domain;
	private String gender;
	private long followers_count;
	private long friends_count;
	private long statuses_count;
	private long favourites_count;
	private String created_at;
	private boolean following;
	private boolean allow_all_act_msg;
	private boolean geo_enabled;
	private boolean verified;
	private long verified_type;
	private String remark;
	private Status status;
	private boolean allow_all_comment;
	private String avatar_large;
	private String verified_reason;

	private boolean follow_me;
	private long online_status;
	private long bi_followers_count;
	private String lang;
	private long level;
	private long type;
	private long ulevel;
	private Badge badge;
	private String checkin_at;

	public User() {
		super();
	}

	public User(Parcel parcel) {
		super();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {
		id = parcel.readLong();
		screen_name = parcel.readString();
		name = parcel.readString();
		province = parcel.readString();
		city = parcel.readString();
		location = parcel.readString();
		description = parcel.readString();
		url = parcel.readString();
		profile_image_url = parcel.readString();
		domain = parcel.readString();
		gender = parcel.readString();
		followers_count = parcel.readLong();
		friends_count = parcel.readLong();
		statuses_count = parcel.readLong();
		favourites_count = parcel.readLong();
		created_at = parcel.readString();

		String following = parcel.readString();
		if ("true".equals(following)) {
			this.following = true;
		} else {
			this.following = false;
		}

		String allow_all_act_msg = parcel.readString();
		if ("true".equals(allow_all_act_msg)) {
			this.allow_all_act_msg = true;
		} else {
			this.allow_all_act_msg = false;
		}

		remark = parcel.readString();

		String geo_enabled = parcel.readString();
		if ("true".equals(geo_enabled)) {
			this.geo_enabled = true;
		} else {
			this.geo_enabled = false;
		}

		String verified = parcel.readString();
		if ("true".equals(verified)) {
			this.verified = true;
		} else {
			this.verified = false;
		}

		String allow_all_comment = parcel.readString();
		if ("true".equals(allow_all_comment)) {
			this.allow_all_comment = true;
		} else {
			this.allow_all_comment = false;
		}

		avatar_large = parcel.readString();
		verified_reason = parcel.readString();

		String follow_me = parcel.readString();
		if ("true".equals(follow_me)) {
			this.follow_me = true;
		} else {
			this.follow_me = false;
		}

		online_status = parcel.readLong();
		bi_followers_count = parcel.readLong();

		idstr = parcel.readString();
		profile_url = parcel.readString();
		verified_type = parcel.readLong();
		// ???
		status = parcel.readParcelable(Status.class.getClassLoader());
		lang = parcel.readString();
		level = parcel.readLong();
		type = parcel.readLong();
		ulevel = parcel.readLong();
		badge = parcel.readParcelable(Badge.class.getClassLoader());
		checkin_at = parcel.readString();

	}

	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

		public User createFromParcel(Parcel source) {
			return new User(source);
		}

		public User[] newArray(int size) {
			return new User[size];
		}

	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(screen_name);
		dest.writeString(name);
		
		dest.writeString(province);
		dest.writeString(city);
		
		dest.writeString(location);
		dest.writeString(description);
		dest.writeString(url);
		dest.writeString(profile_image_url);
		dest.writeString(domain);
		
		dest.writeString(gender);
		dest.writeLong(followers_count);
		dest.writeLong(friends_count);
		dest.writeLong(statuses_count);
		
		dest.writeLong(favourites_count);
		dest.writeString(created_at);
		
		dest.writeString(String.valueOf(following));
		dest.writeString(String.valueOf(allow_all_act_msg));
		dest.writeString(remark);
		
		dest.writeString(String.valueOf(geo_enabled));
		dest.writeString(String.valueOf(verified));
		dest.writeString(String.valueOf(allow_all_comment));
		
		dest.writeString(avatar_large);
		dest.writeString(verified_reason);
		
		dest.writeString(String.valueOf(follow_me));
		dest.writeLong(online_status);
		dest.writeLong(bi_followers_count);

		dest.writeString(idstr);
		dest.writeString(profile_url);
		dest.writeLong(verified_type);
		
		dest.writeParcelable(status, flags);
		dest.writeString(lang);

		dest.writeLong(level);
		dest.writeLong(type);
		dest.writeLong(ulevel);
		dest.writeParcelable(badge, flags);
		dest.writeString(checkin_at);
	}
}
