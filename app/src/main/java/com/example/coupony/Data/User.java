package com.example.coupony.Data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class User implements Parcelable {
    private int idx;
    private String id;
    private String name;
    private boolean bOwner;
    private boolean bSuper;
    private String status;
    private String businessNumber;

    public User(int idx, String id, String name, boolean bOwner, boolean bSuper, String status, String businessNumber) {
        this.idx = idx;
        this.id = id;
        this.name = name;
        this.bOwner = bOwner;
        this.bSuper = bSuper;
        this.status = status;
        this.businessNumber = businessNumber;
    }

    public User(JSONObject jsonObject) throws JSONException {
        idx = jsonObject.getInt("idx");
        id = jsonObject.getString("id");
        name = jsonObject.getString("name");
        bOwner = jsonObject.getBoolean("has_shop");
        bSuper = jsonObject.getBoolean("is_super");
        status = jsonObject.getString("status");
        businessNumber = jsonObject.getString("business_number");
    }

    protected User(Parcel in) {
        idx = in.readInt();
        id = in.readString();
        name = in.readString();
        bOwner = in.readByte() != 0;
        bSuper = in.readByte() != 0;
        status = in.readString();
        businessNumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idx);
        dest.writeString(id);
        dest.writeString(name);
        dest.writeByte((byte) (bOwner ? 1 : 0));
        dest.writeByte((byte) (bSuper ? 1 : 0));
        dest.writeString(status);
        dest.writeString(businessNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getIdx() {
        return idx;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isbOwner() {
        return bOwner;
    }

    public void setbOwner(boolean bOwner) {
        this.bOwner = bOwner;
    }

    public boolean isbSuper() {
        return bSuper;
    }

    public void setbSuper(boolean bSuper) {
        this.bSuper = bSuper;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }
}
