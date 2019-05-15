package com.example.coupony.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.coupony.Adapter.CouponListAdapter;
import com.example.coupony.Connect.HttpConnect;
import com.example.coupony.Connect.RequestCallback;
import com.example.coupony.Data.Coupon;
import com.example.coupony.Data.Shop;
import com.example.coupony.Data.User;
import com.example.coupony.R;
import com.example.coupony.Utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CouponListActivity extends AppCompatActivity {

    private final int[] savingCouponIds = {R.id.img_saving1, R.id.img_saving2, R.id.img_saving3, R.id.img_saving4, R.id.img_saving5, R.id.img_saving6, R.id.img_saving7, R.id.img_saving8, R.id.img_saving9, R.id.img_saving10};

    private User user;
    private Shop shop;
    private ArrayList<Coupon> couponList;
    private TextView textViewMakeCoupon;
    private LinearLayout layoutSavingCoupon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("user");
        shop = intent.getParcelableExtra("shop");

        textViewMakeCoupon = findViewById(R.id.text_make_saving_coupon);
        layoutSavingCoupon = findViewById(R.id.layout_saving_coupon);

        couponList = new ArrayList<>();

        ListView usingCouponList = findViewById(R.id.list_using_coupon);
        CouponListAdapter couponListAdapter = new CouponListAdapter(this, R.layout.view_coupon, couponList);
        usingCouponList.setAdapter(couponListAdapter);

        findViewById(R.id.btn_saving_coupon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        textViewMakeCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String param = "userIdx=" + user.getIdx() + "&shopIdx=" + shop.getIdx() + "&name=" + shop.getName()+" 적립식 쿠폰" + "&description=적립식 쿠폰" + "&type=S&requestCode=2";

                HttpConnect httpConnect = new HttpConnect(param, Constant.SERVER_MAKECOUPON_ADDRESS, requestCallback);
                httpConnect.execute();
            }
        });

        String param = "userIdx=" + user.getIdx() + "&shopIdx=" + shop.getIdx() + "&requestCode=1";

        HttpConnect httpConnect = new HttpConnect(param, Constant.SERVER_GETCOUPONLIST_ADDRESS, requestCallback);
        httpConnect.execute();
    }

    RequestCallback requestCallback = new RequestCallback() {
        @Override
        public void callBack(String jsonResult) {
            Log.d("result", jsonResult);

            try {
                JSONObject jsonObject = new JSONObject(jsonResult);

                if(jsonObject.getInt("requestCode") == 1){
                    if(jsonObject.getString("result").equals("ok")){
                        int savingCount = jsonObject.getInt("saving_count");

                        if(savingCount > -1){
                            textViewMakeCoupon.setVisibility(View.GONE);
                            layoutSavingCoupon.setVisibility(View.VISIBLE);

                            for(int i=0; i<10; i++){
                                ImageView imgSaving = findViewById(savingCouponIds[i]);

                                if(i < savingCount){
                                    imgSaving.setAlpha(1.0f);
                                    imgSaving.startAnimation(AnimationUtils.loadAnimation(CouponListActivity.this, R.anim.bounce));
                                } else {
                                    imgSaving.setAlpha(0.3f);
                                }
                            }
                        }
                    }
                } else {
                    if(jsonObject.getString("result").equals("ok")){
                        textViewMakeCoupon.setVisibility(View.GONE);
                        layoutSavingCoupon.setVisibility(View.VISIBLE);

                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
