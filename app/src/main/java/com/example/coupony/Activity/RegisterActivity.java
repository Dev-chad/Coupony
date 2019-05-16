package com.example.coupony.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coupony.Data.User;
import com.example.coupony.R;

public class RegisterActivity extends AppCompatActivity {

    Spinner spinner;
    String[] data;
    String[] dataEng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText editShop = findViewById(R.id.edit_shop);
        final EditText editAddress = findViewById(R.id.edit_address);
        final EditText editBusiness = findViewById(R.id.edit_business);
        final EditText editService = findViewById(R.id.edit_service);

        final User user = getIntent().getParcelableExtra("user");

        spinner = findViewById(R.id.spinner_category);
       // spinner.setOnItemSelectedListener(this);

        data = new String[]{"선택하세요.", "음식점", "카페", "의류", "전자제품", "생활용품", "헤어", "뷰티", "기타"};
        dataEng = new String[]{"Select", "food", "coffee", "clothes", "electric", "life", "hair", "beauty", "etc"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
      //  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String shopName = editShop.getText().toString();
                String businessNumber = editBusiness.getText().toString();
                String shopAddress = editAddress.getText().toString();
                String couponService = editService.getText().toString();
                String category = dataEng[position];
                int ownerIdx = user.getIdx();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
