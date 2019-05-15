package com.example.coupony.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        for (int resId : imgViewIds) {
            findViewById(resId).setOnClickListener(btnListener);
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        MenuItem menuscan = menu.findItem(R.id.menu_scan);
        if (user.isbOwner()){
            menuscan.setVisible(true);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_userpage:
                    Intent userintent = new Intent(ShopCategoryActivity.this, UserPageActivity.class);
                    userintent.putExtra("user", user);
                    startActivity(userintent);
                    break;
                case R.id.menu_scan:
                    Intent scanintent = new Intent(ShopCategoryActivity.this, QRScannerActivity.class);  // 쿠폰 스캔하기로 바꾸기
                    scanintent.putExtra("user", user);
                    startActivity(scanintent);
                    break;
            }
        return true;
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
