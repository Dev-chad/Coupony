package com.example.coupony.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.coupony.Data.User;
import com.example.coupony.R;

public class UserPageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        Intent intent = getIntent();
        User user = intent.getParcelableExtra("user");

        ListView userlistView = findViewById(R.id.list_user);
        ListView ownerlistView = findViewById(R.id.list_owner);

        if (user.isbOwner()) {
            ownerlistView.setVisibility(View.VISIBLE);
        } else {
            ownerlistView.setVisibility(View.INVISIBLE);
        }

        final String[] userlist = {"개인 정보", "비밀번호 변경", "쿠폰함", "즐겨찾는 매장", "로그아웃"};
        final String[] ownerlist = {"매장 정보", "쿠폰 발급하기", "정보 수정 요청", "임시 운영 중지"};

        ArrayAdapter<String> useradapter =
                new ArrayAdapter<>(
                        this, android.R.layout.simple_list_item_1, userlist);
        userlistView.setAdapter(useradapter);

        ArrayAdapter<String> owneradapter =
                new ArrayAdapter<>(
                        this, android.R.layout.simple_list_item_1, ownerlist);
        ownerlistView.setAdapter(owneradapter);


        userlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = userlist[position];
                Intent intent = new Intent(UserPageActivity.this, ShopListActivity.class);      // ShopListActivity.class 바꾸기!
                startActivity(intent);
            }
        });
        ownerlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = ownerlist[position];
                Intent intent = new Intent(UserPageActivity.this, ShopListActivity.class);      // ShopListActivity.class 바꾸기!
                startActivity(intent);
            }
        });
    }
}
