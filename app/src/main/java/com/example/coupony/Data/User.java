package com.example.coupony.Data;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private int idx;
    private String userId;
    private String name;
    private boolean bShop;
    private boolean bSuper;
    private String status;
    private String businessNumber;

    public User(int idx, String userId, String name, boolean bShop, boolean bSuper, String status, String businessNumber) {
        this.idx = idx;
        this.userId = userId;
        this.name = name;
        this.bShop = bShop;
        this.bSuper = bSuper;
        this.status = status;
        this.businessNumber = businessNumber;
    }

    protected User(Parcel in) {
        idx = in.readInt();
        userId = in.readString();
        name = in.readString();
        bShop = in.readByte() != 0;
        bSuper = in.readByte() != 0;
        status = in.readString();
        businessNumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idx);
        dest.writeString(userId);
        dest.writeString(name);
        dest.writeByte((byte) (bShop ? 1 : 0));
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

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isbShop() {
        return bShop;
    }

    public void setbShop(boolean bShop) {
        this.bShop = bShop;
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
