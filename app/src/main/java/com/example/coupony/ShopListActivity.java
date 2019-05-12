package com.example.coupony;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.coupony.R;
import com.example.coupony.Utils.Constant;
import com.example.coupony.Utils.HttpConnect;
import com.example.coupony.Utils.HttpRequestCallback;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class ShopListActivity extends AppCompatActivity {

    private ArrayList<Shop> shopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        Intent intent = getIntent();
        User user = intent.getParcelableExtra("user");
        String category = intent.getStringExtra("category");

        final ListView listViewShop = findViewById(R.id.list_shop);

        shopList = new ArrayList<>();

        ShopListAdapter adapter = new ShopListAdapter(this, R.layout.listview_shop, shopList);

        listViewShop.setAdapter(adapter);
        listViewShop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Shop shop = shopList.get(position);

            }
        });

        HttpConnect httpConnect = new HttpConnect("category=coffee", Constant.SERVER_GETSHOPLIST_ADDRESS, requestCallback);
        httpConnect.execute();
    }

    HttpRequestCallback requestCallback = new HttpRequestCallback() {
        @Override
        public void callBack(String jsonResult) {
            Log.d("shopList", jsonResult);
        }
    };
}
