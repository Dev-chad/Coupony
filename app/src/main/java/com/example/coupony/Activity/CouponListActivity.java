package com.example.coupony.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.coupony.Data.Coupon;
import com.example.coupony.Data.User;
import com.example.coupony.R;

public class CouponListActivity extends AppCompatActivity {

    private User user;
    private Coupon coupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);

        Intent intent = getIntent();

        user = intent.getParcelableExtra("user");
        coupon = intent.getParcelableExtra("coupon");

        findViewById(R.id.btn_saving_coupon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}
