package com.example.coupony;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.coupony.R;

public class ShopCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_category);
        Intent intent = getIntent();

        User user = intent.getParcelableExtra("User");

        Toast.makeText(this, user.getName(), Toast.LENGTH_SHORT).show();
    }
}
