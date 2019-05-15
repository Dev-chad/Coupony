package com.example.coupony.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.coupony.Data.Shop;
import com.example.coupony.Data.User;
import com.example.coupony.R;
import com.example.coupony.Utils.Constant;
import com.example.coupony.Connect.HttpConnect;
import com.example.coupony.Connect.RequestCallback;
import com.example.coupony.Adapter.ShopListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShopListActivity extends AppCompatActivity {

    private ArrayList<Shop> shopList;
    private ShopListAdapter adapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("user");
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


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        MenuItem menuscan = menu.findItem(R.id.menu_scan);
        if (user.isbOwner()){
            menuscan.setVisible(true);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_userpage:
                Intent userintent = new Intent(ShopListActivity.this, UserPageActivity.class);
                userintent.putExtra("user", user);
                startActivity(userintent);
                break;
            case R.id.menu_scan:
                Intent scanintent = new Intent(ShopListActivity.this, QRScannerActivity.class);  // 쿠폰 스캔하기로 바꾸기
                scanintent.putExtra("user", user);
                startActivity(scanintent);
                break;
        }
        return true;
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
