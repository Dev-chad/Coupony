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
    private ShopListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        Intent intent = getIntent();
        final User user = intent.getParcelableExtra("user");
        String category = intent.getStringExtra("category");

        final ListView listViewShop = findViewById(R.id.list_shop);

        shopList = new ArrayList<>();

        adapter = new ShopListAdapter(this, R.layout.listview_shop, shopList);

        listViewShop.setAdapter(adapter);
        listViewShop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Shop shop = shopList.get(position);

                Intent shopInfoIntent = new Intent(ShopListActivity.this, ShopInfoActivity.class);
                shopInfoIntent.putExtra("user", user);
                shopInfoIntent.putExtra("shop", shop);

                startActivity(shopInfoIntent);
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

                    shopList.clear();

                    for (int i = 0; i < count; i++) {
                        JSONObject jsonShopData = jsonShopList.getJSONObject(i);
                        JSONObject jsonOwnerData = jsonShopData.getJSONObject("owner");

                        User owner = new User(jsonOwnerData.getInt("idx"), jsonOwnerData.getString("id"), jsonOwnerData.getString("name"), jsonOwnerData.getBoolean("has_shop"), jsonOwnerData.getBoolean("is_super"), jsonOwnerData.getString("status"), jsonOwnerData.getString("business_number"));
                        Shop shop = new Shop(jsonShopData.getInt("idx"), jsonShopData.getString("name"), jsonShopData.getString("address"), jsonShopData.getString("phone"), jsonShopData.getString("description"), owner, jsonShopData.getString("category"), jsonShopData.getString("business_hour"), jsonShopData.getString("closed_day"), jsonShopData.getString("status"));

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
