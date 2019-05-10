package com.example.coupony;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.coupony.R;

public class ShopListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        Intent intent = getIntent();
        User user = intent.getParcelableExtra("user");
        String category = intent.getStringExtra("category");

        Toast.makeText(this, user.getName() + "\t" + category, Toast.LENGTH_SHORT).show();
    }
}
