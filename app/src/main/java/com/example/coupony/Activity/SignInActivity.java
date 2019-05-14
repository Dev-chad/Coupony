package com.example.coupony.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.coupony.Data.User;
import com.example.coupony.R;
import com.example.coupony.Utils.Constant;
import com.example.coupony.Utils.Encrypt;
import com.example.coupony.Connect.HttpConnect;
import com.example.coupony.Connect.HttpRequestCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        findViewById(R.id.btn_signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextId = findViewById(R.id.input_e);
                EditText editTextPassword = findViewById(R.id.input_p);

                String id = editTextId.getText().toString();
                String password = Encrypt.getSHA256(editTextPassword.getText().toString());
                //String password = editText2.getText().toString();
                String param = "id=" + id + "&password=" + password;

                HttpConnect httpConnect = new HttpConnect(param, Constant.SERVER_LOGIN_ADDRESS, httpRequestCallback);
                httpConnect.execute();
            }
        });

        findViewById(R.id.layout_sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.view_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, FindPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    HttpRequestCallback httpRequestCallback = new HttpRequestCallback() {
        @Override
        public void callBack(String jsonResult) {
            Log.d("result", jsonResult);

            try {
                JSONObject jsonObject = new JSONObject(jsonResult);

                String result = jsonObject.getString("result");
                if (result.equals("ok")) {
                    JSONObject userObj = jsonObject.getJSONObject("user_data");

                    User user = new User(userObj.getInt("idx"), userObj.getString("id"),
                            userObj.getString("name"), userObj.getBoolean("has_shop"),
                            userObj.getBoolean("is_super"), userObj.getString("status"), userObj.getString("business_number"));

                    Intent intent = new Intent(SignInActivity.this, ShopCategoryActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}



