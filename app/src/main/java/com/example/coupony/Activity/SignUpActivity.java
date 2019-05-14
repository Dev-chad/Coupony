package com.example.coupony.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coupony.R;
import com.example.coupony.Utils.Constant;
import com.example.coupony.Utils.Encrypt;
import com.example.coupony.Connect.HttpConnect;
import com.example.coupony.Connect.RequestCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText editTextId = findViewById(R.id.userid);
        final EditText editTextName = findViewById(R.id.username);
        final EditText editTextPassword = findViewById(R.id.edit_password);
        final Button btnHide = findViewById(R.id.btn_hide);

        btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextPassword.getInputType() == 1) {
                    editTextPassword.setInputType(0x81);
                    btnHide.setText("HIDE");
                } else {
                    editTextPassword.setInputType(1);
                    btnHide.setText("SHOW");
                }
            }
        });

        findViewById(R.id.btn_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextId.getText().toString();
                String name = editTextName.getText().toString();
                String password = editTextPassword.getText().toString();

                if (id.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "이메일을 입력해주세요.", Toast.LENGTH_LONG).show();
                } else if (name.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "이름을 입력해주세요.", Toast.LENGTH_LONG).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show();
                } else {
                    String param = "id=" + id + "&name=" + name + "&password=" + Encrypt.getSHA256(password);

                    HttpConnect httpConnect = new HttpConnect(param, Constant.SERVER_SIGNUP_ADDRESS, requestCallback);
                    httpConnect.execute();
                }
            }
        });
    }

    RequestCallback requestCallback = new RequestCallback() {
        @Override
        public void callBack(String jsonResult) {
            Log.d("result", jsonResult);

            try {
                JSONObject jsonObject = new JSONObject(jsonResult);

                String result = jsonObject.getString("result");
                if(result.equals("ok")){
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