package com.example.coupony.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coupony.Activity.CouponListActivity;
import com.example.coupony.Data.Coupon;
import com.example.coupony.R;

public class CouponListAdapter extends BaseAdapter {
    CouponListActivity couponListActivity;
    int layout;
    Coupon[] coupons;

    CouponListAdapter(CouponListActivity couponListActivity, int layout, Coupon[] coupons) {
        this.couponListActivity = couponListActivity;
        this.layout = layout;
        this.coupons = coupons;
    }

    @Override
    public int getCount() {
        return coupons.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = couponListActivity.getLayoutInflater();
        View viewCoupon = inflater.inflate(layout, null);

        ImageView img_coupon = viewCoupon.findViewById(R.id.img_coupon);
        //img_coupon.setImageResource(coupons[position].img_coupon);

        TextView text_coupon = viewCoupon.findViewById(R.id.text_coupon);
        //text_coupon.setText(coupons[position].name_coupon);

        return viewCoupon;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
