package com.example.coupony.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.coupony.R;

public class CouponListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);

        LayoutInflater inflater = getLayoutInflater();
        View view_coupon = inflater.inflate(R.layout.view_coupon, null);

       // Coupon[] coupons = { new Coupon(R.drawable.a, "스타벅스"), new Coupon(R.drawable.b, "이디야"), new Coupon(R.drawable.c, "투썸플레이스"), new Coupon(R.drawable.d, "커피빈"), };

        ListView couponListView = findViewById(R.id.list_coupon);
        //CouponListAdapter adapter_coupon = new CouponListAdapter(this, R.layout.view_coupon, coupons);
        //couponListView.setAdapter(adapter_coupon);
    }
}
