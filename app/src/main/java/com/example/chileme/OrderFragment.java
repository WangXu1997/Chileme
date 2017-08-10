package com.example.chileme;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.chileme.adapter.AllOrderAdapter;
import com.example.chileme.adapter.FavoriteAdapter;
import com.example.chileme.adapter.HistoryAdapter;
import com.example.chileme.vo.AllOrder;
import com.example.chileme.vo.FavoriteStore;
import com.example.chileme.vo.HistoryOrder;

import java.io.IOException;
import java.util.ArrayList;
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
//    private TextView textView1;
//    private TextView textView2;
    private List<Map<String,Object>> lists=new ArrayList<>();
    private List<Map<String,Object>> lists2=new ArrayList<>();
    private List<Map<String,Object>> lists3=new ArrayList<>();
    private int[] imgIds={R.drawable.wechat};
    private ScrollView sv;
    private JSONObject object;
    private List<AllOrder> list =new ArrayList<>();
    private List<HistoryOrder> list2=new ArrayList<>();
    private List<FavoriteStore> list3=new ArrayList<>();
    private JSONArray jsonArray;
   // private Button button;
    //private TextView textView;
   // String[] keys2={"img","text11","text12","text13","text14","text15"};
    //int[] ids={R.id.item_img,R.id.text11,R.id.text12,R.id.text13,R.id.text14,R.id.text15};
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                String result = (String) msg.obj;
                Log.i("---",result);
                jsonArray= JSON.parseArray(result);
                Log.i("",""+jsonArray.size());
                for(int i=0;i<jsonArray.size();i++){
                    AllOrder allOrder=new AllOrder();
                    Log.i("",""+jsonArray.size());
                    object=jsonArray.getJSONObject(i);
                    allOrder.setFood_name((String) object.get("food_name"));
                    allOrder.setPhoto_source((String) object.get("photo_source"));
                    allOrder.setState((boolean) object.get("state"));
                    allOrder.setStore_name((String) object.get("store_name"));
                    allOrder.setTotalCount((int)object.get("totalCount"));
                    allOrder.setTotalPrice((String) object.get("totalPrice"));
                    list.add(allOrder);
                }
                AllOrderAdapter allOrderAdapter=new AllOrderAdapter(getActivity(),list);
                listView.setAdapter(allOrderAdapter);
                doEvent2();
            }
            if(msg.what == 2){
                String result = (String) msg.obj;
                Log.i("---",result);
                jsonArray= JSON.parseArray(result);
                Log.i("",""+jsonArray.size());
                for(int i=0;i<jsonArray.size();i++){
                    HistoryOrder historyOrder=new HistoryOrder();
                    Log.i("",""+jsonArray.size());
                    object=jsonArray.getJSONObject(i);
                    historyOrder.setStorePhotoSource((String)object.get("storePhotoSource"));
                    historyOrder.setStoreUsername((String)object.get("storeUsername"));
                    historyOrder.setStoreIntroduction((String)object.get("storeIntroduction"));
                    historyOrder.setHistoryCount((int)object.get("historyCount"));
                    list2.add(historyOrder);
                }
                HistoryAdapter historyAdapter=new HistoryAdapter(getActivity(),list2);
                listView2.setAdapter(historyAdapter);
            }if(msg.what == 3){
                String result = (String) msg.obj;
                Log.i("---",result);
                jsonArray= JSON.parseArray(result);
                Log.i("",""+jsonArray.size());
                for(int i=0;i<jsonArray.size();i++){
                    FavoriteStore favoriteStore=new FavoriteStore();
                    Log.i("",""+jsonArray.size());
                    object=jsonArray.getJSONObject(i);
                    favoriteStore.setStoreIntroduction((String)object.get("storeIntroduction"));
                    favoriteStore.setStoreUsername((String)object.get("storeUsername"));
                    favoriteStore.setStorePhotoSource((String)object.get("storePhotoSource"));
                    list3.add(favoriteStore);
                }
                FavoriteAdapter favoriteAdapter=new FavoriteAdapter(getActivity(),list3);
                listView2.setAdapter(favoriteAdapter);
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


        doEvent();
        TextView his = (TextView) view.findViewById(R.id.history);
        his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        doEvent2();
            }
        });
        //doEvent3();
        return view;
    }

    private void doEvent(){
        Request request = new Request.Builder()
                .url("http://192.168.56.1:8080/practice2/order_findAllOrder")
                .get()
                .build();
        exec(request,1);
    }
    private void doEvent2(){
        Request request = new Request.Builder()
                .url("http://192.168.56.1:8080/practice2/order_findHistoryOrder1")
                .get()
                .build();
        exec(request,2);
    }
    private void doEvent3(){
        Request request = new Request.Builder()
                .url("http://192.168.56.1:8080/practice2/order_findHistoryOrder2")
                .get()
                .build();
        exec(request,3);
    }
    private void exec(Request request, final int flag) {
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
                String s = response.body().string();
                Message message = new Message();
                message.what = flag;
                message.obj = s;
                handler.sendMessage(message);
            }
        });
    }
public void enterOrder(View view){
    Request request = new Request.Builder()
            .url("http://192.168.56.1:8080/practice2/order_enterOrder")
            .get()
            .build();
    Intent intent =new Intent(this.getActivity(),MainFragment.class);
    intent.putExtra("flag",3);
    startActivity(intent);
}
}
