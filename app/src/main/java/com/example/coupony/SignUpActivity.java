package com.example.coupony;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coupony.Utils.Constant;
import com.example.coupony.Utils.HttpConnect;
import com.example.coupony.Utils.HttpRequestCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final Button button = findViewById(R.id.btn_hide);
        final EditText editText = findViewById(R.id.edit_password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getInputType() == 1) {
                    editText.setInputType(0x81);
                    button.setText("HIDE");
                } else {
                    editText.setInputType(1);
                    button.setText("SHOW");
                }
            }
        });
        findViewById(R.id.btn_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //새로운 회원 정보 DB에 넘겨주는 코드 추가
                // userid username password

                EditText userid = findViewById(R.id.userid);
                EditText username = findViewById(R.id.username);
                EditText userpassword = findViewById(R.id.edit_password);

                String id = userid.getText().toString();
                String name = username.getText().toString();
                String password = userpassword.getText().toString();
                String param = "userid=" + id + "&name=" + name + "&password=" + password;

                if (id.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "이메일을 입력해주세요.", Toast.LENGTH_LONG).show();
                } else if (name.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "이름을 입력해주세요.", Toast.LENGTH_LONG).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show();
                } else {
                    HttpConnect httpConnect = new HttpConnect(param, Constant.SERVER_SIGNUP_ADDRESS, httpRequestCallback);
                    httpConnect.execute();

                }


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
                if(result.equals("ok")){
                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(SignUpActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}