package com.example.coupony.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.coupony.Adapter.CouponListAdapter;
import com.example.coupony.Connect.HttpConnect;
import com.example.coupony.Connect.RequestCallback;
import com.example.coupony.Data.Coupon;
import com.example.coupony.Data.Shop;
import com.example.coupony.Data.User;
import com.example.coupony.R;
import com.example.coupony.Utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CouponListActivity extends AppCompatActivity {

    private final int[] savingCouponIds = {R.id.img_saving1, R.id.img_saving2, R.id.img_saving3, R.id.img_saving4, R.id.img_saving5, R.id.img_saving6, R.id.img_saving7, R.id.img_saving8, R.id.img_saving9, R.id.img_saving10};

    private User user;
    private Shop shop;
    private Coupon savingCoupon;
    private ArrayList<Coupon> couponList;
    private TextView textViewMakeCoupon;
    private LinearLayout layoutSavingCoupon;
    private CouponListAdapter couponListAdapter;
    private Button btnSavingCoupon;

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

        final ListView usingCouponList = findViewById(R.id.list_using_coupon);
        couponListAdapter = new CouponListAdapter(this, R.layout.view_coupon, couponList);
        usingCouponList.setAdapter(couponListAdapter);

        btnSavingCoupon = findViewById(R.id.btn_saving_coupon);
        btnSavingCoupon.setVisibility(View.INVISIBLE);
        btnSavingCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQRCodeDialog(savingCoupon);
            }
        });

        textViewMakeCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String param = "userIdx=" + user.getIdx() + "&shopIdx=" + shop.getIdx() + "&name=" + shop.getName() + " 적립식 쿠폰" + "&description=적립식 쿠폰" + "&type=S&requestCode=2";

                HttpConnect httpConnect = new HttpConnect(param, Constant.SERVER_MAKECOUPON_ADDRESS, requestCallback);
                httpConnect.execute();
            }
        });

        usingCouponList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showQRCodeDialog(couponList.get(position));
            }
        });

        getCouponListByServer();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_userpage:
                Intent userintent = new Intent(CouponListActivity.this, UserPageActivity.class);
                userintent.putExtra("user", user);
                startActivity(userintent);
                break;
            case R.id.menu_scan:
                Intent scanintent = new Intent(CouponListActivity.this, UserPageActivity.class);  // 쿠폰 스캔하기로 바꾸기
                scanintent.putExtra("user", user);
                startActivity(scanintent);
                break;
        }
        return true;
    }

    RequestCallback requestCallback = new RequestCallback() {
        @Override
        public void callBack(String jsonResult) {
            Log.d("result", jsonResult);

            try {
                JSONObject jsonObject = new JSONObject(jsonResult);

                if (jsonObject.getString("result").equals("ok")) {
                    int savingCount = jsonObject.getInt("saving_count");

                    if (savingCount > -1) {
                        textViewMakeCoupon.setVisibility(View.GONE);
                        layoutSavingCoupon.setVisibility(View.VISIBLE);
                        btnSavingCoupon.setVisibility(View.VISIBLE);

                        for (int i = 0; i < 10; i++) {
                            ImageView imgSaving = findViewById(savingCouponIds[i]);

                            if (i < savingCount) {
                                imgSaving.setAlpha(1.0f);
                                imgSaving.startAnimation(AnimationUtils.loadAnimation(CouponListActivity.this, R.anim.bounce));
                            } else {
                                imgSaving.setAlpha(0.3f);
                            }
                        }

                        savingCoupon = new Coupon(jsonObject.getJSONObject("saving_coupon_data"));
                    }

                    couponList.clear();

                    int usingCouponCount = jsonObject.getInt("using_coupon_count");
                    JSONArray usingCouponArray = jsonObject.getJSONArray("using_coupon_list");

                    for (int i = 0; i < usingCouponCount; i++) {
                        couponList.add(new Coupon(usingCouponArray.getJSONObject(i)));
                    }

                    couponListAdapter.notifyDataSetChanged();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private void showQRCodeDialog(Coupon coupon) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_qr);

        TextView tv = dialog.findViewById(R.id.text_shop_name);
        tv.setText(coupon.getName());

        ImageView iv = dialog.findViewById(R.id.img_qr);
        String param = "{\"user_idx\":" + user.getIdx() + ", \"shop_idx\":" + shop.getIdx() + ", \"coupon_idx\":" + coupon.getIdx() + ", \"coupon_name\":\"" + coupon.getName() + "\", \"coupon_desc\":\"" + coupon.getDescription() + "\", \"coupon_type\":\"" + coupon.getType() + "\"}";
        String url = "https://chart.googleapis.com/chart?chs=150x150&cht=qr&chl=" + param + "&choe=UTF-8";
        Glide.with(CouponListActivity.this).load(url).into(iv);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                getCouponListByServer();
            }
        });

        dialog.show();
    }

    private void getCouponListByServer() {
        String param = "userIdx=" + user.getIdx() + "&shopIdx=" + shop.getIdx();

        HttpConnect httpConnect = new HttpConnect(param, Constant.SERVER_GETCOUPONLIST_ADDRESS, requestCallback);
        httpConnect.execute();
    }

}
