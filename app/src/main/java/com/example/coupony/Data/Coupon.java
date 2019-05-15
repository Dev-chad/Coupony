package com.example.coupony.Data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Coupon implements Parcelable {

    private int idx;
    private String name;
    private String description;
    private int shopIdx;
    private int ownerIdx;
    private int count;
    private String type;
    private String startDate;
    private String endDate;
    private String status;

    public Coupon(int idx, String name, String description, int shopIdx, int ownerIdx, String type, String startDate, String endDate, String status) {
        this.idx = idx;
        this.name = name;
        this.description = description;
        this.shopIdx = shopIdx;
        this.ownerIdx = ownerIdx;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Coupon(JSONObject jsonObject) throws JSONException {
        idx = jsonObject.getInt("idx");
        name = jsonObject.getString("name");
        description = jsonObject.getString("description");
        shopIdx = jsonObject.getInt("shop_idx");
        ownerIdx = jsonObject.getInt("owner_idx");
        type = jsonObject.getString("type");
        startDate = jsonObject.getString("start_date");
        endDate = jsonObject.getString("end_date");
        status = jsonObject.getString("status");
    }


    protected Coupon(Parcel in) {
        idx = in.readInt();
        name = in.readString();
        description = in.readString();
        shopIdx = in.readInt();
        ownerIdx = in.readInt();
        count = in.readInt();
        type = in.readString();
        startDate = in.readString();
        endDate = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idx);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(shopIdx);
        dest.writeInt(ownerIdx);
        dest.writeInt(count);
        dest.writeString(type);
        dest.writeString(startDate);
        dest.writeString(endDate);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Coupon> CREATOR = new Creator<Coupon>() {
        @Override
        public Coupon createFromParcel(Parcel in) {
            return new Coupon(in);
        }

        @Override
        public Coupon[] newArray(int size) {
            return new Coupon[size];
        }
    };

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getShopIdx() {
        return shopIdx;
    }

    public void setShopIdx(int shopIdx) {
        this.shopIdx = shopIdx;
    }

    public int getOwnerIdx() {
        return ownerIdx;
    }

    public void setOwnerIdx(int ownerIdx) {
        this.ownerIdx = ownerIdx;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
