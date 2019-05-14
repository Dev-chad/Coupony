package com.example.coupony.Data;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.coupony.Utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Shop implements Parcelable {
    private int idx;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String phone;
    private String desc;
    private User owner;
    private String category;
    private String businessHour;
    private String closedDay;
    private String status;
    private String logoUrl;

    public Shop(int idx, String name, String address, double latitude, double longitude, String phone, String desc, User owner, String category, String businessHour, String closedDay, String status) {
        this.idx = idx;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.desc = desc;
        this.owner = owner;
        this.category = category;
        this.businessHour = businessHour;
        this.closedDay = closedDay;
        this.status = status;
        this.logoUrl = Constant.SERVER_ADDRESS + "/image/logo/logo_" + idx + ".png";
    }

    public Shop(JSONObject jsonObject) throws JSONException {
        idx = jsonObject.getInt("idx");
        name = jsonObject.getString("name");
        address = jsonObject.getString("address");
        latitude = jsonObject.getDouble("latitude");
        longitude = jsonObject.getDouble("longitude");
        phone = jsonObject.getString("phone");
        desc = jsonObject.getString("description");
        category = jsonObject.getString("category");
        businessHour = jsonObject.getString("business_hour");
        closedDay = jsonObject.getString("closed_day");
        status = jsonObject.getString("status");
        logoUrl = Constant.SERVER_ADDRESS + "/image/logo/logo_" + idx + ".png";

        JSONObject jsonOwnerData = jsonObject.getJSONObject("owner");
        owner = new User(jsonOwnerData);
    }


    protected Shop(Parcel in) {
        idx = in.readInt();
        name = in.readString();
        address = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        phone = in.readString();
        desc = in.readString();
        owner = in.readParcelable(User.class.getClassLoader());
        category = in.readString();
        businessHour = in.readString();
        closedDay = in.readString();
        status = in.readString();
        logoUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idx);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(phone);
        dest.writeString(desc);
        dest.writeParcelable(owner, flags);
        dest.writeString(category);
        dest.writeString(businessHour);
        dest.writeString(closedDay);
        dest.writeString(status);
        dest.writeString(logoUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Shop> CREATOR = new Creator<Shop>() {
        @Override
        public Shop createFromParcel(Parcel in) {
            return new Shop(in);
        }

        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBusinessHour() {
        return businessHour;
    }

    public void setBusinessHour(String businessHour) {
        this.businessHour = businessHour;
    }

    public String getClosedDay() {
        return closedDay;
    }

    public void setClosedDay(String closedDay) {
        this.closedDay = closedDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
