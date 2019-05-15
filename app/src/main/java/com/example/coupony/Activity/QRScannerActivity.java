package com.example.coupony.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coupony.Connect.HttpConnect;
import com.example.coupony.Connect.RequestCallback;
import com.example.coupony.Data.User;
import com.example.coupony.R;
import com.example.coupony.Utils.Constant;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import static java.security.AccessController.getContext;

public class QRScannerActivity extends AppCompatActivity {
    //view Objects
    private Button buttonScan;
    private TextView textViewName, textViewAddress, textViewResult;

    //qr code scanner object
    private IntentIntegrator qrScan;

    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);

        //View Objects
        buttonScan = (Button) findViewById(R.id.buttonScan);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewAddress = (TextView) findViewById(R.id.textViewAddress);
        textViewResult = (TextView) findViewById(R.id.textViewResult);

        //intializing scan object
        qrScan = new IntentIntegrator(this);

        //button onClick
        buttonScan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //scan option
                qrScan.setPrompt("Scanning...");
                //qrScan.setOrientationLocked(false);
                qrScan.initiateScan();
            }
        });
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(QRScannerActivity.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                //qrcode 결과가 있으면
                Toast.makeText(QRScannerActivity.this, "스캔완료!", Toast.LENGTH_SHORT).show();
                try {
                    //data를 json으로 변환
                    JSONObject obj = new JSONObject(result.getContents());
                    int useridx = obj.getInt("user_idx");
                    int shopidx = obj.getInt("shop_idx");
                    int couponidx = obj.getInt("coupon_idx");
                    String couponname = obj.getString("coupon_name");
                    String coupondesc = obj.getString("coupon_desc");
                    String coupontype = obj.getString("coupon_type");
                    type = coupontype;

                    String param = "user_idx=" + useridx + "&shop_idx=" + shopidx + "&coupon_idx=" + couponidx +
                            "&coupon_name=" + couponname + "&coupon_desc=" + coupondesc + "&coupon_type=" + coupontype;

                    HttpConnect httpConnect = new HttpConnect(param, Constant.SERVER_USING_COUPON_ADDRESS, requestCallback);

                    // HttpConnect httpConnect = new HttpConnect(param, Constant.SERVER_LOGIN_ADDRESS, requestCallback);
                    httpConnect.execute();

                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(MainActivity.this, result.getContents(), Toast.LENGTH_LONG).show();
                    textViewResult.setText(result.getContents());
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    RequestCallback requestCallback = new RequestCallback() {
        @Override
        public void callBack(String jsonResult) {
            Log.d("result", jsonResult);

            try {
                JSONObject jsonObject = new JSONObject(jsonResult);

                String result = jsonObject.getString("result");
                if (result.equals("ok")) {
                    // 쿠폰사용
                    //s적립, u사용

                    if (type.equals("U")) {
                        Toast.makeText(QRScannerActivity.this, "쿠폰이 사용되었습니다.", Toast.LENGTH_LONG).show();
                        // textViewAddress.setText(obj.getString("address"));
                        textViewAddress.setText("쿠폰이 사용되었습니다.");
                    } else if (type.equals("S")) {
                        Toast.makeText(QRScannerActivity.this, "쿠폰을 적립하였습니다.", Toast.LENGTH_LONG).show();
                    }

                } else {
                    //쿠폰에러
                    Toast.makeText(QRScannerActivity.this, "유효하지 않은 쿠폰입니다.", Toast.LENGTH_LONG).show();
                    //textViewAddress.setText("유효하지 않은 쿠폰입니다.");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}