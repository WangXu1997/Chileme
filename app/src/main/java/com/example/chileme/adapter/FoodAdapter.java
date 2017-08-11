package com.example.chileme.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chileme.R;
import com.example.chileme.vo.StoreFood;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by Wang Xu on 2017/8/11.
 */

public class FoodAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<StoreFood> list;
    private ImageView img;
    private ViewHolder holder;
    int num=0;
    onCountChangeListen listen;
    Handler handler;
    Drawable drawable;
    private String url0 ="http://192.168.137.1:8080/practice2/upload/";

    private OkHttpClient okHttpClient = new OkHttpClient();
    public FoodAdapter(Context context, List<StoreFood> data) {
        inflater = LayoutInflater.from(context);
        this.list = data;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = inflater.inflate(R.layout.list_food, parent, false);
            holder = new ViewHolder(v);
            v.setTag(holder);




        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.foodname.setText(list.get(position).getFoodName());
        holder.foodIntroduction.setText("共售"+list.get(position).getPeopleBuy()+"单       "+list.get(position).getGrade()+"%");
        holder.price.setText("￥"+list.get(position).getPrice());
        holder.count.setText(list.get(position).getNum()+"");
        holder.img_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listen.onIncrease(list.get(position).getNum(),position);
            }
        });
        holder.img_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).getNum()>0)
                        listen.onDecrease(list.get(position).getNum(),position);
            }
        });
        final String url=url0+list.get(position).getPhotoSource();
        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    drawable= Drawable.createFromStream(new URL(url).openStream(),"1.jpg" );

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            Thread.sleep(500);

            holder.img.setImageDrawable(drawable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




        return v;
    }

    public void addCount(int position){
        Integer num = list.get(position).getNum();
        list.get(position).setNum(num+1);
    }
    public void subCount(int position){
        Integer num = list.get(position).getNum();
        list.get(position).setNum(num-1);
    }

    public void setListener(onCountChangeListen listener){
        this.listen=listener;
    }

    private class ViewHolder {
        ImageView img=null;
        TextView foodIntroduction = null;
        TextView foodname = null;
        TextView price = null;
        TextView count = null;
        ImageView img_decrease=null;
        ImageView img_increase=null;

        ViewHolder(View v) {
            img=(ImageView)v.findViewById(R.id.iv_food);
            foodIntroduction=(TextView)v.findViewById(R.id.tv_summary);
            foodname=(TextView)v.findViewById(R.id.tv_name);
            price=(TextView)v.findViewById(R.id.tv_price);
            count=(TextView)v.findViewById(R.id.edit1);
            img_decrease=(ImageView)v.findViewById(R.id.delete1);
            img_increase=(ImageView)v.findViewById(R.id.add1);
        }

    }
}
