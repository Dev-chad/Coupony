package com.example.coupony.Data;

public class CouponBox  {
    private int coupon_img;
    private String coupon_name;
    CouponBox(int coupon_img, String coupon_name){this.coupon_img=coupon_img; this.coupon_name=coupon_name;}

    public int getCoupon_img(){ return coupon_img; }
    public String getCoupon_name(){ return  coupon_name; }
}
