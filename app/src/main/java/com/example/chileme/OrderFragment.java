package com.example.chileme;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

import com.example.chileme.vo.AllOrder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {

    private ListViewForScrollView listView;
    private ListViewForScrollView listView2;
    private List<Map<String,Object>> lists=new ArrayList<>();
    private List<Map<String,Object>> lists2=new ArrayList<>();
    private int[] imgIds={R.drawable.wechat};
    private ScrollView sv;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                List<AllOrder> result = (List<AllOrder>) msg.obj;
                setListView(result);
            }
        }
    };
    OkHttpClient okHttpClient = new OkHttpClient();
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_order, container, false);
        sv=(ScrollView) view.findViewById(R.id.scrollView);
        sv.smoothScrollTo(0, 0);
        listView=(ListViewForScrollView)view.findViewById(R.id.listview);
        listView2=(ListViewForScrollView)view.findViewById(R.id.listview2);
        String[] keys={"img"};
        String[] keys2={"text11","text12","text13"};
        int[] ids={R.id.text11,R.id.text12,R.id.text13};
        int[] ids2={R.id.item_img2};
        SimpleAdapter simpleAdapter=new SimpleAdapter(this.getActivity(),lists,R.layout.list_item1,keys2,ids);
        listView.setAdapter(simpleAdapter);
        doEvent();
        SimpleAdapter simpleAdapter2=new SimpleAdapter(this.getActivity(),lists2,R.layout.list_item2,keys,ids2);
        listView2.setAdapter(simpleAdapter2);
        for(int i=0;i<2;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("img",imgIds[0]);
            lists2.add(map);
        }
        return view;
    }

    private void doEvent(){
        Request request = new Request.Builder()
                .url("http://192.168.40.22:8080/practice2/order_findAllOrder")
                .get()
                .build();
        exec(request);
    }
    private void exec(Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败
                Log.i("异常：","--->"+e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //成功
                Log.i("成功：","--->");
                List<AllOrder> list = (List<AllOrder>) response.body();
                Message message = new Message();
                message.what = 1;
                message.obj = list;
                handler.sendMessage(message);
            }
        });
    }
    private void setListView(List<AllOrder> list){
        for(int i=0;i<list.size();i++){
            Map<String,Object> map=new HashMap<>();
            map.put("text11",list.get(i).getStore_name()+" >");
            map.put("text12",list.get(i).getFood_name()+" 等"+list.get(i).getTotalCount()+"件商品");
            map.put("text13","￥"+list.get(i).getTotalPrice());
            lists.add(map);
        }
        Log.i("到这了","!");
    }
}
