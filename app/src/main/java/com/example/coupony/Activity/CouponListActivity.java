package com.example.coupony.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.coupony.Adapter.CouponListAdapter;
import com.example.coupony.Connect.RequestCallback;
import com.example.coupony.Data.Coupon;
import com.example.coupony.Data.Shop;
import com.example.coupony.Data.User;
import com.example.coupony.R;

import java.util.ArrayList;

public class CouponListActivity extends AppCompatActivity {

    private User user;
    private Shop shop;
    private ArrayList<Coupon> couponList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("user");
        shop = intent.getParcelableExtra("shop");

        couponList = new ArrayList<>();

        ListView usingCouponList = findViewById(R.id.list_using_coupon);
        CouponListAdapter couponListAdapter = new CouponListAdapter(this, R.layout.view_coupon, couponList);
        usingCouponList.setAdapter(couponListAdapter);

        findViewById(R.id.btn_saving_coupon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    RequestCallback requestCallback = new RequestCallback() {
        @Override
        public void callBack(String jsonResult) {

        }
    };
}
