package com.example.coupony.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.coupony.Connect.HttpConnect;
import com.example.coupony.Connect.RequestCallback;
import com.example.coupony.Data.Shop;
import com.example.coupony.R;
import com.example.coupony.Utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ShopListAdapter extends BaseAdapter {
    final public static int MODE_VIEW = 0;
    final public static int MODE_REGISTER = 1;

    private Activity activity;
    private int layoutId;
    private ArrayList<Shop> shopList;
    private int mode;

    public ShopListAdapter(Activity activity, int layoutId, ArrayList<Shop> shopList, int mode) {
        this.activity = activity;
        this.layoutId = layoutId;
        this.shopList = shopList;
        this.mode = mode;
    }

    @Override
    public int getCount() {
        return shopList.size();
    }

    @Override
    public Shop getItem(int position) {
        return shopList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(layoutId, null);

        final Shop shop = shopList.get(position);

        final ImageView imageLogo = view.findViewById(R.id.image_logo);
        TextView textName = view.findViewById(R.id.text_name);
        TextView textDesc = view.findViewById(R.id.text_desc);

        textName.setText(shop.getName());
        textDesc.setText(shop.getDesc());

        if (shop.getLogoUrl().isEmpty()) {
            imageLogo.setImageResource(R.drawable.smile);
        } else {
            Glide.with(view).load(shop.getLogoUrl()).into(imageLogo);
        }

        Button btnAccept = view.findViewById(R.id.btn_accept);

        if(mode == MODE_REGISTER){
            btnAccept.setVisibility(View.VISIBLE);
            btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String param = "shop_idx="+shop.getIdx() + "&status=Y";

                    HttpConnect httpConnect = new HttpConnect(param, Constant.SERVER_EDITSHOP_ADDRESS, new RequestCallback() {
                        @Override
                        public void callBack(String jsonResult) {
                            try{
                                JSONObject jsonObject = new JSONObject(jsonResult);

                                if(jsonObject.getString("result").equals("ok")){
                                    shopList.remove(shop);

                                    notifyDataSetChanged();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    httpConnect.execute();
                }
            });
        } else {
            btnAccept.setVisibility(View.INVISIBLE);
        }

        return view;
    }


}
