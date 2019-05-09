package com.example.coupony;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coupony.Utils.Encrypt;
import com.example.coupony.Utils.ShopCategory;

public class SignInActivity extends AppCompatActivity {

    String id = "jieun";
    String password = "wldms";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        findViewById(R.id.btn_signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText1 = (EditText) findViewById(R.id.input_e);
                EditText editText2 = (EditText) findViewById(R.id.input_p);
                String str1 = editText1.getText().toString();
                String str2 = editText2.getText().toString();

                //Toast.makeText(SignInActivity.this, Encrypt.getSHA256(str2), Toast.LENGTH_LONG).show();

                if (str1.equals(id) && str2.equals(password)) {
                    Toast.makeText(SignInActivity.this, "같아요", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignInActivity.this, ShopCategory.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SignInActivity.this, "달라요", Toast.LENGTH_LONG).show();
                }
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
}



