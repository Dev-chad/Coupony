package com.example.coupony.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.coupony.Activity.CouponListActivity;
import com.example.coupony.Data.Coupon;
import com.example.coupony.R;
import com.example.coupony.Utils.Constant;

import java.util.ArrayList;

public class CouponListAdapter extends BaseAdapter {
    private CouponListActivity couponListActivity;
    private int layout;
    private ArrayList<Coupon> couponList;

    public CouponListAdapter(CouponListActivity couponListActivity, int layout, ArrayList<Coupon> couponList) {
        this.couponListActivity = couponListActivity;
        this.layout = layout;
        this.couponList = couponList;
    }

    @Override
    public int getCount() {
        return couponList.size();
    }

    @Override
    public Object getItem(int position) {
        return couponList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = couponListActivity.getLayoutInflater();
        View view = inflater.inflate(layout, null);

        Coupon coupon = couponList.get(position);

        ImageView imgCoupon = view.findViewById(R.id.img_coupon);
        TextView textCouponName = view.findViewById(R.id.text_coupon);
        Button btnCoupon = view.findViewById(R.id.btn_coupon);

        String logoUrl = Constant.SERVER_ADDRESS+"/image/logo/logo_" + coupon.getShopIdx() + ".png";

        //Glide.with(couponListActivity).load(logoUrl).into(imgCoupon);
        Glide.with(couponListActivity).load("https://chart.googleapis.com/chart?chs=200x200&cht=qr&chl=Hello%20world&choe=UTF-8").into(imgCoupon);

        textCouponName.setText(coupon.getName());

        return view;
    }

}
