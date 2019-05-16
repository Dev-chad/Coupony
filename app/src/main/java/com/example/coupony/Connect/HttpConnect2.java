package com.example.coupony.Connect;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class HttpConnect2 extends AsyncTask<Void, Void, String> {

    private String param;
    private String targetUrl;
    private RequestCallback requestCallback;
    private HashMap<String, String> textDatas;
    private HashMap<String, File> fileDatas;

    public HttpConnect2(String targetUrl, HashMap<String, String> textDatas, RequestCallback requestCallback) {
        this.targetUrl = targetUrl;
        this.textDatas = textDatas;
        this.requestCallback = requestCallback;
    }

    public HttpConnect2(String targetUrl, HashMap<String, String> textDatas, HashMap<String, File> fileDatas, RequestCallback requestCallback) {
        this.targetUrl = targetUrl;
        this.textDatas = textDatas;
        this.fileDatas = fileDatas;
        this.requestCallback = requestCallback;
    }

    @Override
    protected String doInBackground(Void... voids) {

        String boundary = "^-----^";
        String LINE_FEED = "\r\n";
        String charset = "UTF-8";
        OutputStream outputStream;
        PrintWriter writer;

        String result = null;
        try {

            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-Type", "multipart/form-data;charset=utf-8;boundary=" + boundary);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(15000);

            outputStream = connection.getOutputStream();
            writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);

            /** Body에 데이터를 넣어줘야 할경우 없으면 Pass **/

            for (String key : textDatas.keySet()) {
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + key + "\"").append(LINE_FEED);
                writer.append("Content-Type: text/plain; charset=" + charset).append(LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(textDatas.get(key)).append(LINE_FEED);
                writer.flush();
            }


            /** 파일 데이터를 넣는 부분**/
            for (String key : fileDatas.keySet()) {
                File file = fileDatas.get(key);

                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"").append(LINE_FEED);
                writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(file.getName())).append(LINE_FEED);
                writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
                writer.append(LINE_FEED);
                writer.flush();

                FileInputStream inputStream = new FileInputStream(file);
                byte[] buffer = new byte[(int) file.length()];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
                inputStream.close();
                writer.append(LINE_FEED);
                writer.flush();
            }

            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                result = response.toString();


            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();
            }

        } catch (ConnectException e) {
            Log.e("TAG", "ConnectException");
            e.printStackTrace();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String jsonResult) {
        super.onPostExecute(jsonResult);

        Log.d("result", jsonResult);
    }
}
