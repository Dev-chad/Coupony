package com.example.coupony;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private int idx;
    private String userid;
    private String name;
    private boolean bShop;
    private boolean bSuper;
    private String status;

    public User(int idx, String userid, String name, boolean bShop, boolean bSuper, String status) {
        this.idx = idx;
        this.userid = userid;
        this.name = name;
        this.bShop = bShop;
        this.bSuper = bSuper;
        this.status = status;
    }

    protected User(Parcel in) {
        idx = in.readInt();
        userid = in.readString();
        name = in.readString();
        bShop = in.readByte() != 0;
        bSuper = in.readByte() != 0;
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idx);
        dest.writeString(userid);
        dest.writeString(name);
        dest.writeByte((byte) (bShop ? 1 : 0));
        dest.writeByte((byte) (bSuper ? 1 : 0));
        dest.writeString(status);
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

    public String getUserID() {
        return userid;
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
}
