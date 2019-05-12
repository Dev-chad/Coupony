package com.example.coupony;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.coupony.R;

import java.util.ArrayList;

public class ShopListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        Intent intent = getIntent();
        User user = intent.getParcelableExtra("user");
        String category = intent.getStringExtra("category");

        final ListView listViewShop = findViewById(R.id.list_shop);

        final ArrayList<Shop> shopList = new ArrayList<>();

        shopList.add(new Shop(1, "스타벅스", "화곡동", "커피 전문점", 1, "coffee", "2019-05-11", "Y", "https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/5ns/image/N7ezeQHfjA5Ekdl3TIQjwWYEs2c.png"));

        ShopListAdapter adapter = new ShopListAdapter(this, R.layout.listview_shop, shopList);

        listViewShop.setAdapter(adapter);

        listViewShop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Shop shop = shopList.get(position);

                Toast.makeText(ShopListActivity.this, shop.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
