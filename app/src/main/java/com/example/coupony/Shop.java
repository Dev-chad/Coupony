package com.example.coupony;

import android.graphics.Bitmap;

public class Shop {
    private int idx;
    private String name;
    private String address;
    private String desc;
    private int ownerIdx;
    private String category;
    private String regDate;
    private String status;
    private String logoUrl;
    private Bitmap logo;

    public Shop(int idx, String name, String address, String desc, int ownerIdx, String category, String regDate, String status, String logoUrl) {
        this.idx = idx;
        this.name = name;
        this.address = address;
        this.desc = desc;
        this.ownerIdx = ownerIdx;
        this.category = category;
        this.regDate = regDate;
        this.status = status;
        this.logoUrl = logoUrl;
    }

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getOwnerIdx() {
        return ownerIdx;
    }

    public void setOwnerIdx(int ownerIdx) {
        this.ownerIdx = ownerIdx;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
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

    public Bitmap getLogo() {
        return logo;
    }

    public void setLogo(Bitmap logo) {
        this.logo = logo;
    }
}
