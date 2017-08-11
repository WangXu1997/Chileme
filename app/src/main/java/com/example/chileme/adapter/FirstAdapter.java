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
import com.example.chileme.vo.FavoriteStore;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by Wang Xu on 2017/8/11.
 */

public class FirstAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<FavoriteStore> list;
    private ImageView img;
    private Context mContext;
    private ViewHolder holder;
    Drawable drawable;
    private String url0 ="http://192.168.137.1:8080/practice2/upload/";
    private OkHttpClient okHttpClient = new OkHttpClient();
    public FirstAdapter(Context context, List<FavoriteStore> data) {
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
            v = inflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder(v);
            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.storename.setText(list.get(position).getStoreUsername()+" >");
        holder.storeintroduction.setText(list.get(position).getStoreIntroduction());
        holder.totalcount.setText("");
        holder.history_sale.setText("共售"+list.get(position).getHistorySale()+"单");
        holder.grade.setText(list.get(position).getGrade()+"%");
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
            Thread.sleep(500);

            holder.img.setImageDrawable(drawable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(mContext, ShopFoodActivity.class);
//               // intent.putExtra("storeNameCurrent",holder.storename.getText().toString());
//                intent.putExtra("storeNameCurrent","666");
//                intent.putExtra("storeidCurrent",holder.storeid.getText());
//                Log.i("------------------>>>>>",holder.storename.getText().toString());
//                mContext.startActivity(intent);
//            }
//        });

        return v;
    }
    private class ViewHolder {
        ImageView img=null;
        TextView storename = null;
        TextView storeintroduction = null;
        TextView totalcount = null;
        TextView grade=null;
        TextView history_sale=null;
        TextView storeid=null;

        ViewHolder(View v) {
            img=(ImageView)v.findViewById(R.id.item_img2);
            storename=(TextView)v.findViewById(R.id.text21);
            storeintroduction=(TextView)v.findViewById(R.id.text22);
            totalcount=(TextView)v.findViewById(R.id.text23);
            history_sale=(TextView)v.findViewById(R.id.text24);
            grade=(TextView)v.findViewById(R.id.text25);
            storeid=(TextView)v.findViewById(R.id.text26);
        }

    }
}
