package com.example.coupony.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.coupony.Adapter.ShopListAdapter;
import com.example.coupony.Connect.HttpConnect;
import com.example.coupony.Connect.RequestCallback;
import com.example.coupony.Data.Shop;
import com.example.coupony.R;
import com.example.coupony.Utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShopRegisterMangerActivity extends AppCompatActivity {

    ArrayList<Shop> shopList;
    ShopListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_register_manger);

        shopList = new ArrayList<>();

        ListView listShop = findViewById(R.id.list_shop);
        adapter = new ShopListAdapter(ShopRegisterMangerActivity.this, R.layout.listview_shop, shopList, ShopListAdapter.MODE_REGISTER);

        listShop.setAdapter(adapter);

        HttpConnect httpConnect = new HttpConnect("status=N", Constant.SERVER_GETSHOPLIST_ADDRESS, requestCallback);
        httpConnect.execute();
    }

    RequestCallback requestCallback = new RequestCallback() {
        @Override
        public void callBack(String jsonResult) {
            Log.d("shopList", jsonResult);

            try {
                JSONObject jsonObject = new JSONObject(jsonResult);

                if (jsonObject.getString("result").equals("ok")) {
                    int count = jsonObject.getInt("count");
                    JSONArray jsonShopList = jsonObject.getJSONArray("shop_list");

                    shopList.clear();

                    for (int i = 0; i < count; i++) {
                        JSONObject jsonShopData = jsonShopList.getJSONObject(i);
                        Shop shop = new Shop(jsonShopData);
                        shopList.add(shop);
                    }

                    adapter.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
