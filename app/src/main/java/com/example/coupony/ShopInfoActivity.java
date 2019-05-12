package com.example.coupony;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_info);

        Intent intent = getIntent();
        Shop shop = intent.getParcelableExtra("shop");

        ImageView imageViewLogo = findViewById(R.id.image_logo);
        TextView textViewShopName = findViewById(R.id.text_shop_name);
        TextView textViewIntroduce = findViewById(R.id.text_introduce);
        TextView textViewTime = findViewById(R.id.text_time);
        TextView textViewClosedDay = findViewById(R.id.text_closed_day);
        TextView textViewCall = findViewById(R.id.text_call);
        TextView textViewOwnerName = findViewById(R.id.text_owner_name);
        TextView textViewBusinessNumber = findViewById(R.id.text_business_number);
    }
}
