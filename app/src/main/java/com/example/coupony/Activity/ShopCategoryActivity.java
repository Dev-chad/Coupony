package com.example.coupony.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coupony.Data.User;
import com.example.coupony.R;

public class ShopCategoryActivity extends AppCompatActivity {
    private User user;
    private int[] imgViewIds = {R.id.view_beauty, R.id.view_clothes, R.id.view_coffee, R.id.view_daily, R.id.view_eletric, R.id.view_etc, R.id.view_food, R.id.view_hair};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_category);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("user");

        for(int resId : imgViewIds){
            findViewById(resId).setOnClickListener(btnListener);
        }

    }

    Button.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String category = v.getContentDescription().toString();

            Intent intent = new Intent(ShopCategoryActivity.this, ShopListActivity.class);
            intent.putExtra("category", category);
            intent.putExtra("user", user);

            startActivity(intent);
        }
    };
}
