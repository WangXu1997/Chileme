package com.example.chileme;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.chileme.adapter.FirstAdapter;
import com.example.chileme.vo.FavoriteStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstPageFragment extends Fragment {
    private  int[] nameID={R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4};
   // private  int[] nameID={R.drawable.a5,R.drawable.a6, R.drawable.a7,R.drawable.a8};
    private  int[] nameID2={R.drawable.a5,R.drawable.a6, R.drawable.a7,R.drawable.a8};
    private List<String> list = new ArrayList<String>();
    private Spinner mySpinner;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private List<Map<String,Object>> lists=new ArrayList<>();
//    private  int[] imgIDs={R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5,R.drawable.p6,};
//    private  String[] names={"肯德基","麦当劳","必胜客","德克士","土大力","彤德莱"};
//    private  int[] prices={4,5,3,4,1,0};
//    private  int[] checks={94,85,93,84,91,90};
//    private  String[] detail={"炸鸡汉堡，心动超值","发现麦当劳的经典汉堡、当期新品、优惠活动和最新优惠券","人气比萨四合一,小食,饮料花式拼.还有麻辣小龙虾比萨新上市"
//            ,"德克士脆皮炸鸡","韩式餐饮","肥肠好吃的火锅"};
    private GridView gird,gird2;
    private JSONObject object;
    private List<FavoriteStore> list1=new ArrayList<>();
    private JSONArray jsonArray;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String result = (String) msg.obj;
                Log.i("---", result);
                jsonArray = JSON.parseArray(result);
                Log.i("", "" + jsonArray.size());
                for (int i = 0; i < jsonArray.size(); i++) {
                    FavoriteStore favoriteStore = new FavoriteStore();
                    Log.i("", "" + jsonArray.size());
                    object = jsonArray.getJSONObject(i);
                    favoriteStore.setStoreIntroduction((String) object.get("storeIntroduction"));
                    favoriteStore.setStoreUsername((String) object.get("storeUsername"));
                    favoriteStore.setStorePhotoSource((String) object.get("storePhotoSource"));
                    favoriteStore.setHistorySale((int) object.get("historySale"));
                    favoriteStore.setGrade((int) object.get("grade"));
                    favoriteStore.setStoreid((int) object.get("storeid"));
                    list1.add(favoriteStore);
                }
                FirstAdapter firstAdapter = new FirstAdapter(getActivity(), list1);
                listView.setAdapter(firstAdapter);
            }
        }
        };

    OkHttpClient okHttpClient = new OkHttpClient();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_first_page, container, false);
        gird=(GridView)view.findViewById(R.id.gridView);
        gird2=(GridView)view.findViewById(R.id.gridView2);
        list.add("津南区");
        list.add("滨海新区");
        list.add("南开区");
        list.add("和平区");
        mySpinner = (Spinner)view.findViewById(R.id.Spinner_city);
        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, list);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mySpinner.setAdapter(adapter);
        //第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中

        listView=(ListView) view.findViewById(R.id.listView_first);
        doEvent();
        MyAdapter myAdapter= new MyAdapter(getActivity());
        MyAdapter2 myAdapter2= new MyAdapter2(getActivity());
        gird.setAdapter(myAdapter);
        gird2.setAdapter(myAdapter2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), ShopFoodActivity.class);
               intent.putExtra("storeNameCurrent",list1.get(position).getStoreUsername());
                intent.putExtra("storeidCurrent",list1.get(position).getStoreid());
                startActivity(intent);
            }
        });
        return view;
    }
    private void doEvent(){
        Request request = new Request.Builder()
                .url("http://192.168.137.1:8080/practice2/order_findAllStoreFirst")
                .get()
                .build();
        exec(request);
    }
    private void exec(Request request) {
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {

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
                message.what = 1;
                message.obj = s;
                handler.sendMessage(message);
            }
        });
    }
    class MyAdapter extends BaseAdapter {
        private Context mContext;
        public MyAdapter(Context context)
        {
            mContext=context;
        }
        @Override
        public int getCount() {
            return nameID.length;
        }

        @Override
        public Object getItem(int position) {
            return nameID[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout linear=new LinearLayout(mContext);
            linear.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
            linear.setOrientation(LinearLayout.VERTICAL);
            ImageView imageView=new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(230,230));
            imageView.setImageResource(nameID[position]);
            linear.addView(imageView);


            return linear;
        }
    }
    class MyAdapter2 extends BaseAdapter {
        private Context mContext;
        public MyAdapter2(Context context)
        {
            mContext=context;
        }
        @Override
        public int getCount() {
            return nameID.length;
        }

        @Override
        public Object getItem(int position) {
            return nameID[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout linear=new LinearLayout(mContext);
            linear.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
            linear.setOrientation(LinearLayout.VERTICAL);
            ImageView imageView=new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(230,230));
            imageView.setImageResource(nameID2[position]);
            linear.addView(imageView);


            return linear;
        }
    }

}
