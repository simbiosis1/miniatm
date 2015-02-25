package org.simbiosis.miniatm.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SavingDv implements Parcelable {
	Long id;
	String code;
	String name;
	String address;

	@Override
	public int describeContents() {
		return 0;
	}

	/**
	 * Storing the Student data to Parcel object
	 **/
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(code);
		dest.writeString(name);
		dest.writeString(address);
	}

	public static final Parcelable.Creator<SavingDv> CREATOR = new Parcelable.Creator<SavingDv>() {

		@Override
		public SavingDv createFromParcel(Parcel source) {
			return new SavingDv(source);
		}

		@Override
		public SavingDv[] newArray(int size) {
			return new SavingDv[size];
		}
	};

	/**
	 * Retrieving Student data from Parcel object This constructor is invoked by
	 * the method createFromParcel(Parcel source) of the object CREATOR
	 **/
	private SavingDv(Parcel in) {
		this.id = in.readLong();
		this.code = in.readString();
		this.name = in.readString();
		this.address = in.readString();
	}

	public SavingDv(String code, String name, String address) {
		id = 0L;
		this.code = code;
		this.name = name;
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
