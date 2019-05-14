package com.example.coupony.Activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.coupony.Data.Shop;
import com.example.coupony.Data.User;
import com.example.coupony.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShopInfoActivity extends AppCompatActivity {

    private Shop shop;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_info);

        Intent intent = getIntent();

        user = intent.getParcelableExtra("user");
        shop = intent.getParcelableExtra("shop");
        User owner = shop.getOwner();

        final ImageView imageViewLogo = findViewById(R.id.image_logo);
        TextView textViewShopName = findViewById(R.id.text_shop_name);
        TextView textViewIntroduce = findViewById(R.id.text_introduce);
        TextView textViewTime = findViewById(R.id.text_time);
        TextView textViewClosedDay = findViewById(R.id.text_closed_day);
        TextView textViewCall = findViewById(R.id.text_call);
        TextView textViewOwnerName = findViewById(R.id.text_owner_name);
        TextView textViewBusinessNumber = findViewById(R.id.text_business_number);

        textViewShopName.setText(shop.getName());
        textViewIntroduce.setText(shop.getDesc());
        textViewTime.setText(shop.getBusinessHour());
        textViewClosedDay.setText(shop.getClosedDay());
        textViewCall.setText(shop.getPhone());
        textViewOwnerName.setText(owner.getName());
        textViewBusinessNumber.setText(owner.getBusinessNumber());

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(ShopInfoActivity.this).load(shop.getLogoUrl()).into(imageViewLogo);
                    }
                });
            }
        }).start();*/
        
        Glide.with(ShopInfoActivity.this).load(shop.getLogoUrl()).into(imageViewLogo);
        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(onMapReadyCallback);
    }

    OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng pos = new LatLng(shop.getLatitude(), shop.getLongitude());

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(pos);
            markerOptions.snippet(shop.getName());
            googleMap.addMarker(markerOptions.position(pos).title(shop.getName()));

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 17));
        }
    };
}
