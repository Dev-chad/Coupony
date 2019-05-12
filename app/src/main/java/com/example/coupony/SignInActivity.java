package com.example.coupony;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.coupony.Utils.Constant;
import com.example.coupony.Utils.Encrypt;
import com.example.coupony.Utils.HttpConnect;
import com.example.coupony.Utils.HttpRequestCallback;

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
                EditText editText1 = findViewById(R.id.input_e);
                EditText editText2 = findViewById(R.id.input_p);
                String id = editText1.getText().toString();
                // String password = Encrypt.getSHA256(editText2.getText().toString());
                String password = editText2.getText().toString();
                String param = "userId=" + id + "&password=" + password;

                HttpConnect httpConnect = new HttpConnect(param, Constant.SERVER_LOGIN_ADDRESS, httpRequestCallback);
                httpConnect.execute();
            }
        });

        findViewById(R.id.view_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.view_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, FindPassword.class);
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

                    User user = new User(userObj.getInt("idx"), userObj.getString("userId"),
                            userObj.getString("name"), userObj.getBoolean("has_shop"),
                            userObj.getBoolean("is_super"), userObj.getString("status"), userObj.getString("business_number"));

                    Intent intent = new Intent(SignInActivity.this, ShopCategory.class);
                    intent.putExtra("user", user);
                    startActivity(intent);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}



