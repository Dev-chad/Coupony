package com.example.coupony.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coupony.Data.Shop;
import com.example.coupony.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ShopListAdapter extends BaseAdapter {
    private Activity activity;
    private int layoutId;
    private ArrayList<Shop> shopList;

    public ShopListAdapter(Activity activity, int layoutId, ArrayList<Shop> shopList) {
        this.activity = activity;
        this.layoutId = layoutId;
        this.shopList = shopList;
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
        } else if(shop.getLogo() != null){
            imageLogo.setImageBitmap(BitmapFactory.decodeByteArray(shop.getLogo(), 0, shop.getLogo().length));
        }else {

            final Handler handler = new Handler();

            Thread imageLoadThread = new Thread() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(shop.getLogoUrl());

                        HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                        connect.setDoInput(true);
                        connect.connect();

                        InputStream is = connect.getInputStream();
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                        Bitmap bmp = BitmapFactory.decodeStream(is);
                        bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                        shop.setLogo(outputStream.toByteArray());

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                imageLogo.setImageBitmap(BitmapFactory.decodeByteArray(shop.getLogo(), 0, shop.getLogo().length));
                            }
                        });

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            imageLoadThread.start();
        }

        return view;
    }


}
