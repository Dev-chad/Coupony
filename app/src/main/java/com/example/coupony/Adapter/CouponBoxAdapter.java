package com.example.coupony.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coupony.Activity.CouponBoxActivity;
import com.example.coupony.Data.CouponBox;
import com.example.coupony.R;

public class CouponBoxAdapter extends BaseAdapter {
    CouponBoxActivity ma;
    int layout;
    CouponBox[] data;

    public CouponBoxAdapter(CouponBoxActivity ma, int layout, CouponBox[] data){
        this.ma = ma;
        this.layout = layout;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ma.getLayoutInflater();
        View view = inflater.inflate(layout, null);

        ImageView img_couponbox = view.findViewById(R.id.img_couponbox);
        img_couponbox.setImageResource(data[position].getCoupon_img());

        TextView text_couponbox = view.findViewById(R.id.text_couponbox);
        text_couponbox.setText(data[position].getCoupon_name());

        return view;
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


