package com.example.chileme.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chileme.R;
import com.example.chileme.vo.HistoryOrder;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by Wang Xu on 2017/8/10.
 */

public class HistoryAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<HistoryOrder> list;
    private ImageView img;
    private Context mContext;
    private ViewHolder holder;
    Drawable drawable;
    private String url0 ="http://192.168.137.1:8080/practice2/upload/";
    private OkHttpClient okHttpClient = new OkHttpClient();
    public HistoryAdapter(Context context, List<HistoryOrder> data) {
        mContext = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = inflater.inflate(R.layout.list_item2, parent, false);
            holder = new ViewHolder(v);
            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.storename.setText(list.get(position).getStoreUsername()+" >");
        holder.storeintroduction.setText(list.get(position).getStoreIntroduction());
        holder.totalcount.setText("买过"+list.get(position).getHistoryCount()+"次");
        final String url=url0+list.get(position).getStorePhotoSource();
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
            Thread.sleep(50);

            holder.img.setImageDrawable(drawable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return v;
    }
    private class ViewHolder {
        ImageView img=null;
        TextView storename = null;
        TextView storeintroduction = null;
        TextView totalcount = null;

        ViewHolder(View v) {
            img=(ImageView)v.findViewById(R.id.item_img2);
            storename=(TextView)v.findViewById(R.id.text21);
            storeintroduction=(TextView)v.findViewById(R.id.text22);
            totalcount=(TextView)v.findViewById(R.id.text23);
        }

    }
}
