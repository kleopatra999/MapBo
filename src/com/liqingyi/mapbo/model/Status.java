package com.liqingyi.mapbo.model;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Status implements Parcelable {

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

	public boolean isFavorited() {
		return favorited;
	}

	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}

	public boolean isTruncated() {
		return truncated;
	}

	public void setTruncated(boolean truncated) {
		this.truncated = truncated;
	}

	public String getIn_reply_to_status_id() {
		return in_reply_to_status_id;
	}

	public void setIn_reply_to_status_id(String in_reply_to_status_id) {
		this.in_reply_to_status_id = in_reply_to_status_id;
	}

	public String getIn_reply_to_user_id() {
		return in_reply_to_user_id;
	}

	public void setIn_reply_to_user_id(String in_reply_to_user_id) {
		this.in_reply_to_user_id = in_reply_to_user_id;
	}

	public String getIn_reply_to_screen_name() {
		return in_reply_to_screen_name;
	}

	public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
		this.in_reply_to_screen_name = in_reply_to_screen_name;
	}

	public Geo getGeo() {
		return geo;
	}

	public void setGeo(Geo geo) {
		this.geo = geo;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public long getReposts_count() {
		return reposts_count;
	}

	public void setReposts_count(long reposts_count) {
		this.reposts_count = reposts_count;
	}

	public long getComments_count() {
		return comments_count;
	}

	public void setComments_count(long comments_count) {
		this.comments_count = comments_count;
	}

	public ArrayList<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(ArrayList<Annotation> annotations) {
		this.annotations = annotations;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIdstr() {
		return idstr;
	}

	public void setIdstr(String idstr) {
		this.idstr = idstr;
	}

	public String getThumbnail_pic() {
		return thumbnail_pic;
	}

	public void setThumbnail_pic(String thumbnail_pic) {
		this.thumbnail_pic = thumbnail_pic;
	}

	public String getBmiddle_pic() {
		return bmiddle_pic;
	}

	public void setBmiddle_pic(String bmiddle_pic) {
		this.bmiddle_pic = bmiddle_pic;
	}

	public String getOriginal_pic() {
		return original_pic;
	}

	public void setOriginal_pic(String original_pic) {
		this.original_pic = original_pic;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	public long getMlevel() {
		return mlevel;
	}

	public void setMlevel(long mlevel) {
		this.mlevel = mlevel;
	}

	public Visible getVisible() {
		return visible;
	}

	public void setVisible(Visible visible) {
		this.visible = visible;
	}

	public Status() {
		super();
		geo = new Geo();
		user = new User();
		annotations = new ArrayList<Annotation>();
	}

	private String created_at;
	private long id;
	private String mid;
	private String idstr;
	private String text;
	private String source;
	private boolean favorited;
	private boolean truncated;
	private String in_reply_to_status_id;
	private String in_reply_to_user_id;
	private String in_reply_to_screen_name;

	private String thumbnail_pic;
	private String bmiddle_pic;
	private String original_pic;

	private Geo geo;
	private ArrayList<Annotation> annotations;
	private long reposts_count;
	private long comments_count;
	private User user;
	private long distance;
	private long mlevel;
	private Visible visible;

	public Status(Parcel parcel) {
		this();
		readToParcel(parcel);
	}

	private void readToParcel(Parcel parcel) {

		idstr = parcel.readString();
		distance = parcel.readLong();
		mlevel = parcel.readLong();
		visible = parcel.readParcelable(Visible.class.getClassLoader());

		thumbnail_pic = parcel.readString();
		bmiddle_pic = parcel.readString();
		original_pic = parcel.readString();

		created_at = parcel.readString();
		id = parcel.readLong();
		text = parcel.readString();
		source = parcel.readString();
		String favorited = parcel.readString();
		if ("true".equals(favorited)) {
			this.favorited = true;
		} else {
			this.favorited = false;
		}
		String truncated = parcel.readString();
		if ("true".equals(truncated)) {
			this.truncated = true;
		} else {
			this.truncated = false;
		}
		in_reply_to_status_id = parcel.readString();
		in_reply_to_user_id = parcel.readString();
		in_reply_to_screen_name = parcel.readString();
		mid = parcel.readString();
		reposts_count = parcel.readLong();
		comments_count = parcel.readLong();

		// ???
		geo = parcel.readParcelable(Geo.class.getClassLoader());
		user = parcel.readParcelable(User.class.getClassLoader());

		parcel.readTypedList(annotations, Annotation.CREATOR);

	}

	public static final Parcelable.Creator<Status> CREATOR = new Parcelable.Creator<Status>() {

		public Status createFromParcel(Parcel parcel) {
			return new Status(parcel);
		}

		public Status[] newArray(int size) {
			return new Status[size];
		}

	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {

		parcel.writeString(idstr);
		parcel.writeLong(mlevel);
		parcel.writeLong(distance);
		parcel.writeParcelable(visible, flags);
		
		parcel.writeString(thumbnail_pic);
		parcel.writeString(bmiddle_pic);
		parcel.writeString(original_pic);

		parcel.writeString(created_at);
		parcel.writeLong(id);
		parcel.writeString(text);
		parcel.writeString(source);
		
		parcel.writeString(String.valueOf(favorited));
		parcel.writeString(String.valueOf(truncated));
		
		parcel.writeString(in_reply_to_status_id);
		parcel.writeString(in_reply_to_user_id);
		parcel.writeString(in_reply_to_screen_name);
		parcel.writeString(mid);
		parcel.writeLong(reposts_count);
		parcel.writeLong(comments_count);
		parcel.writeParcelable(geo, flags);
		parcel.writeParcelable(user, flags);
		parcel.writeTypedList(annotations);

	}

}
