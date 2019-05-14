package com.example.coupony.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.coupony.Connect.Mailer;
import com.example.coupony.Connect.SMTPAuthenticator;
import com.example.coupony.R;

public class FindPasswordActivity extends AppCompatActivity {

    EditText edit_email;
    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);

        btn_send = findViewById(R.id.btn_send);
        edit_email = findViewById(R.id.edit_email);

        btn_send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new MailTransferThread(edit_email.getText().toString()).start();
            }
        });

        Intent intent = new Intent(this.getIntent());
    }
    private class MailTransferThread extends Thread {
        String address;

        public MailTransferThread(String address) {
            this.address = address;
        }

        @Override
        public void run() {
            Mailer m = new Mailer();
            m.sendMail(address, "[Coupony] 임시 비밀번호", "임시비밀번호는 ㅇㅇㅇㅇㅇㅇㅇ 입니다.", new SMTPAuthenticator());
        }
    }
}



