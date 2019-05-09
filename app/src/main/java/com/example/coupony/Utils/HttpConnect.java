package com.example.coupony.Utils;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.coupony.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpConnect extends AsyncTask<Void, Void, String> {

    private String param;
    private String url;
    private HttpRequestCallback requestCallback;

    public HttpConnect(String param, String url, HttpRequestCallback requestCallback){
        this.param = param;
        this.url = url;
        this.requestCallback = requestCallback;
    }

    @Override
    protected String doInBackground(Void... unused) {

        /* 인풋 파라메터값 생성 */

        try {
            /* 서버연결 */
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.connect();

            /* 안드로이드 -> 서버 파라메터값 전달 */
            OutputStream outs = conn.getOutputStream();
            outs.write(param.getBytes("UTF-8"));
            outs.flush();
            outs.close();

            /* 서버 -> 안드로이드 파라메터값 전달 */
            InputStream is = null;
            BufferedReader in = null;
            String data = "";

            is = conn.getInputStream();
            in = new BufferedReader(new InputStreamReader(is), 8 * 1024);
            String line = null;
            StringBuffer buff = new StringBuffer();
            while ((line = in.readLine()) != null) {
                buff.append(line + "\n");
            }

            data = buff.toString().trim();


            return data;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        requestCallback.callBack(result);
    }
}