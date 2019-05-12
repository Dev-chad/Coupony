package com.example.coupony;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.coupony.Utils.HttpRequestCallback;

import java.util.ArrayList;

public class ShopListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        Intent intent = getIntent();
        final User user = intent.getParcelableExtra("user");
        String category = intent.getStringExtra("category");

        final ListView listViewShop = findViewById(R.id.list_shop);
        final ArrayList<Shop> shopList = new ArrayList<>();

        ShopListAdapter adapter = new ShopListAdapter(this, R.layout.listview_shop, shopList);
        listViewShop.setAdapter(adapter);
        listViewShop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Shop shop = shopList.get(position);

                Intent intent = new Intent(ShopListActivity.this, ShopInfoActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("shop", shop);

                startActivity(intent);
            }
        });
    }

    HttpRequestCallback httpRequestCallback = new HttpRequestCallback() {
        @Override
        public void callBack(String jsonResult) {

        }
    };
}
