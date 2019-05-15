package com.example.coupony.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import com.example.coupony.Adapter.CouponBoxAdapter;
import com.example.coupony.Data.Coupon;
import com.example.coupony.Data.CouponBox;
import com.example.coupony.R;

import java.util.ArrayList;

public class CouponBoxActivity extends AppCompatActivity {

    private Coupon coupon;
    private ArrayList<CouponBox> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_box);

        LayoutInflater inflater = getLayoutInflater();
        View view_coupon = inflater.inflate(R.layout.view_coupon, null);

        datas = new ArrayList<>();


        //CouponBox[] datas = { new CouponBox(R.drawable.a)};

        ListView listView = findViewById(R.id.list_coupon);
        //CouponBoxAdapter adapter = new CouponBoxAdapter(this, R.layout.couponboxview, datas);
        ///listView.setAdapter(adapter);
    }
}



