package com.example.chileme.adapter;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chileme.MainFragment;
import com.example.chileme.OrderFragment;
import com.example.chileme.R;
import com.example.chileme.vo.AllOrder;

import java.util.List;

import okhttp3.Request;

/**
 * Created by Wang Xu on 2017/8/9.
 */

public class AllOrderAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<AllOrder> list;

    public AllOrderAdapter(Context context, List<AllOrder> data) {
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
        ViewHolder holder;

        if (v == null) {
            v = inflater.inflate(R.layout.list_item1, parent, false);
            holder = new ViewHolder(v);
            v.setTag(holder);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Request request = new Request.Builder()
                            .url("http://192.168.137.1:8080/practice2/order_enterOrder")
                            .get()
                            .build();
                    Fragment contentFragment = new OrderFragment();
                    FragmentTransaction transaction= MainFragment.fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragmentPager, contentFragment);
                    Log.i("66666666666","3333333");
                }
            });
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.img.setImageResource(R.drawable.wechat);
        holder.storename.setText(list.get(position).getStore_name()+" >");
        holder.foodname.setText(list.get(position).getFood_name()+" 等"+list.get(position).getTotalCount()+"件商品");
        holder.totalprice.setText("￥"+list.get(position).getTotalPrice());
        if(list.get(position).isState()==true){
            holder.orderstate.setText("订单已完成");
            holder.button.setText("再来一单");
            holder.button.setClickable(false);
        }
        else{
            holder.orderstate.setText("商家配送中");
            holder.button.setText("确认送达");
            holder.button.setClickable(true);
            Log.i("----->","----->");
        }

        return v;
    }
    private class ViewHolder {
        ImageView img=null;
        TextView storename = null;
        TextView foodname = null;
        TextView totalprice = null;
        TextView orderstate = null;
        Button button;

        ViewHolder(View v) {
            img=(ImageView)v.findViewById(R.id.item_img);
            storename=(TextView)v.findViewById(R.id.text11);
            foodname=(TextView)v.findViewById(R.id.text12);
            totalprice=(TextView)v.findViewById(R.id.text13);
            orderstate=(TextView)v.findViewById(R.id.text14);
            button=(Button)v.findViewById(R.id.text15);
        }

    }

}
