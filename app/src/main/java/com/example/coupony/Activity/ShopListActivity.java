package com.example.coupony.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.coupony.Data.Shop;
import com.example.coupony.Data.User;
import com.example.coupony.R;
import com.example.coupony.Utils.Constant;
import com.example.coupony.Utils.HttpConnect;
import com.example.coupony.Utils.HttpRequestCallback;
import com.example.coupony.adapter.ShopListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

            try {
                JSONObject jsonObject = new JSONObject(jsonResult);

                if (jsonObject.getString("result").equals("ok")) {
                    int count = jsonObject.getInt("count");
                    JSONArray jsonShopList = jsonObject.getJSONArray("shop_list");

                    ArrayList<Shop> shopList = new ArrayList<>();

                    for (int i = 0; i < count; i++) {
                        JSONObject jsonShopData = jsonShopList.getJSONObject(i);

                        //Shop shop = new Shop(jsonObject.getInt("idx"), jsonShopData.getString("name"), jsonShopData.getString("address"), jsonShopData.getString("description"), jsonShopData.get)
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
