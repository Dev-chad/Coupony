package com.example.coupony;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                if(editText.getInputType() == 1){
                    editText.setInputType(0x81);
                    button.setText("HIDE");
                }else {
                    editText.setInputType(1);
                    button.setText("SHOW");
                }
            }
        });
    }
}