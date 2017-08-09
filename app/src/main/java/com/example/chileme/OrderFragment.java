package com.example.chileme;


import android.app.Fragment;
import android.app.FragmentTransaction;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.chileme.adapter.AllOrderAdapter;
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

import static com.example.chileme.MainFragment.fragmentManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {

    private ListViewForScrollView listView;
    private ListViewForScrollView listView2;
    private List<Map<String,Object>> lists=new ArrayList<>();
    private List<Map<String,Object>> lists2=new ArrayList<>();
    private List<Map<String,Object>> lists3=new ArrayList<>();
    private int[] imgIds={R.drawable.wechat};
    private ScrollView sv;
    private JSONObject object;
    private List<AllOrder> list =new ArrayList<>();
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
              //  setListView(list);
               // SimpleAdapter simpleAdapter=new SimpleAdapter(getActivity(),lists,R.layout.list_item1,keys2,ids);
               // listView.setAdapter(simpleAdapter);
                AllOrderAdapter allOrderAdapter=new AllOrderAdapter(getActivity(),list);
                listView.setAdapter(allOrderAdapter);

//                button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        enter(v);
//                    }
//                });
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
       // button=(Button) view.findViewById(R.id.text15);
       // textView=(TextView) view.findViewById(R.id.text14);
        String[] keys={"img"};

        int[] ids2={R.id.item_img2};

        doEvent();

        SimpleAdapter simpleAdapter2=new SimpleAdapter(this.getActivity(),lists2,R.layout.list_item2,keys,ids2);
        listView2.setAdapter(simpleAdapter2);
        for(int i=0;i<2;i++){
            Map<String,Object> map0=new HashMap<>();
            map0.put("img",imgIds[0]);
            lists2.add(map0);
        }
        return view;
    }

    private void doEvent(){
        Request request = new Request.Builder()
                .url("http://192.168.40.23:8080/practice2/order_findAllOrder")
                .get()
                .build();
        exec(request,1);
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
//    private List<Map<String,Object>> setListView(List<AllOrder> list){
//        for(int i=0;i<list.size();i++){
//            Map<String,Object> map=new HashMap<>();
//            int judge;
//            map.put("img",imgIds[0]);
//            map.put("text11",list.get(i).getStore_name()+" >");
//            map.put("text12",list.get(i).getFood_name()+" 等"+list.get(i).getTotalCount()+"件商品");
//            map.put("text13","￥"+list.get(i).getTotalPrice());
//            if(list.get(i).isState()==true){
//                map.put("text14","订单已完成");
//                map.put("text15","再来一单");
//                judge=0;
//            }
//            else{
//                map.put("text14","商家配送中");
//                map.put("text15","确认送达");
//                judge=1;
//            }
//            if(judge==0)
//                button.setClickable(false);
//            lists.add(map);
//        }
//        return lists;
//    }
public void enterOrder(View view){
    Request request = new Request.Builder()
            .url("http://192.168.40.23:8080/practice2/order_enterOrder")
            .get()
            .build();
    Fragment contentFragment = new OrderFragment();
    FragmentTransaction transaction=fragmentManager.beginTransaction();
    transaction.replace(R.id.fragmentPager, contentFragment);
}
}
