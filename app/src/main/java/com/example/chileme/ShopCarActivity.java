package com.example.chileme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.chileme.vo.Food;
import com.example.chileme.vo.buy_store;

import org.litepal.crud.DataSupport;

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

public class ShopCarActivity extends AppCompatActivity {
    private Spinner mySpinner;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private List<Map<String,Object>> lists=new ArrayList<>();
    private  String[] foodnames = new String[20];
    private  String[] num = new String[20];
    private  String[] price = new String[20];
    private TextView textView1;
    private  TextView textView2;
    private JSONObject object;
    private JSONArray jsonArray;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;


    OkHttpClient okHttpClient = new OkHttpClient();
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String result = (String) msg.obj;
                Log.i("------>", result);
                jsonArray = JSON.parseArray(result);
                Log.i("", "" + jsonArray.size());
                object = jsonArray.getJSONObject(0);
                textView3.setText((String) object.get("userAddress"));
                textView4.setText((String) object.get("userUsername"));
                textView5.setText((String) object.get("userPhoneNumber"));

            }
            }
        };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);


        int k=0;
        doEvent();
        List<Food> list = DataSupport.findAll(Food.class);
        for (int j=list.size();j>list.size()-ShopFoodActivity.length;j--)
        {
            Food food= DataSupport.find(Food.class,j);


//            String name=food.getFood_name();
//            System.out.println(name+"---------------->");

            System.out.println("========================"+food.getFood_name());
            System.out.println("========================"+String.valueOf(food.getNumber()));
            System.out.println("========================"+String.valueOf(food.getPrice()));

            foodnames[k]=food.getFood_name();
            num[k]= String.valueOf(food.getNumber());
            price[k]= String.valueOf(food.getPrice());

            k++;

        }




        listView=(ListView) findViewById(R.id.listViewfinalfood);
        textView1=(TextView)findViewById(R.id.shopcar_shopname);
        textView2=(TextView)findViewById(R.id.shopcar_totalprice);
        textView3=(TextView)findViewById(R.id.tv_shop_summary);
        textView4=(TextView)findViewById(R.id.tv_shop_type);
        textView5=(TextView)findViewById(R.id.tv_shop_send);


        List<buy_store> list0 = DataSupport.findAll(buy_store.class);
        buy_store store0=DataSupport.find(buy_store.class,list0.size());
        textView1.setText(store0.getStore_name());
        textView2.setText("总价为：  ￥"+store0.getTotalprice());

        String[] keys={"foodnames","num","price"};
        int[] ids={R.id.final_food_name,R.id.final_food_num,R.id.final_food_price};
        SimpleAdapter simpleAdapter = new SimpleAdapter(ShopCarActivity.this,lists,R.layout.list_final_food,keys,ids);
        listView.setAdapter(simpleAdapter);
        for (int i=0;i<ShopFoodActivity.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("foodnames",foodnames[i]);
            map.put("num",num[i]);
            map.put("price",price[i]);


            lists.add(map);}

    }
    private void doEvent(){
        Request request = new Request.Builder()
                .url("http://192.168.137.1:8080/practice2/order_findUserByUserId")
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
                String s = response.body().string();
                Message message = new Message();
                message.what = 1;
                message.obj = s;
                handler.sendMessage(message);
            }
        });
    }
    public void goAccount0(View view){
        Intent intent=new Intent(this,PaySuccessActivity.class);
        startActivity(intent);
    }
}