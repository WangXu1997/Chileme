package com.example.chileme;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.chileme.adapter.FoodAdapter;
import com.example.chileme.adapter.onCountChangeListen;
import com.example.chileme.vo.StoreFood;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShopFoodActivity extends AppCompatActivity {
    private Spinner mySpinner;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private List<Map<String,Object>> lists=new ArrayList<>();
    private  int[] imgIDs={R.drawable.p1,R.drawable.p1,R.drawable.p1,R.drawable.p1,R.drawable.p1,R.drawable.p1,};
    private  String[] names={"肯德基","麦当劳","必胜客","德克士","土大力","彤德莱"};
    private  String[] detail={"肯德基","麦当劳","必胜客","德克士","土大力","彤德莱"};
    private  int[] prices={4,5,3,4,1,0};
    private ImageView add1;
    private ImageView delete1;
    private TextView edit1;
    private TextView shopName;
    private ImageView shopPhoto;
    private TextView shopIntroduction;
    private TextView shopGrade;
    private TextView shopSaleCount;
    private JSONObject object;
    private JSONArray jsonArray;
    private int currentId;
    private List<StoreFood> list2=new ArrayList<>();
    Drawable drawable;
    private String url0 ="http://192.168.137.1:8080/practice2/upload/";
    FoodAdapter foodAdapter;
    OkHttpClient okHttpClient = new OkHttpClient();

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                String result = (String) msg.obj;
                Log.i("------>",result);
                jsonArray= JSON.parseArray(result);
                Log.i("",""+jsonArray.size());
                object=jsonArray.getJSONObject(0);
                shopName.setText((String)object.get("storeUsername"));
                shopIntroduction.setText((String)object.get("storeIntroduction"));
               // shopGrade.setText((String)object.get("grade"));
                //shopSaleCount.setText((String)object.get("historySale"));
                final String url=url0+(String)object.get("storePhotoSource");
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

                    shopPhoto.setImageDrawable(drawable);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

               doEvent2();
            }
            if(msg.what == 2){
                String result = (String) msg.obj;
                Log.i("------>",result);
                jsonArray= JSON.parseArray(result);
                Log.i("",""+jsonArray.size());
                for(int i=0;i<jsonArray.size();i++){
                    StoreFood storeFood=new StoreFood();
                    object=jsonArray.getJSONObject(i);
                    storeFood.setGrade(Float.parseFloat(object.get("grade").toString()));
                    storeFood.setFoodId((int)object.get("foodId"));
                    storeFood.setFoodName((String)object.get("foodName"));
                    storeFood.setPeopleBuy((int)object.get("peopleBuy"));
                    storeFood.setPhotoSource((String)object.get("photoSource"));
                    storeFood.setPrice(Float.parseFloat(object.get("price").toString()));
                    storeFood.setNum(0);
                    list2.add(storeFood);
                }
                final FoodAdapter foodAdapter=new FoodAdapter(ShopFoodActivity.this,list2);
                listener=new onCountChangeListen() {
                    @Override
                    public void onIncrease(int count,int position) {
                        foodAdapter.addCount(position);
                        foodAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onDecrease(int count,int position) {
                        foodAdapter.subCount(position);
                        foodAdapter.notifyDataSetChanged();
                    }
                };
                foodAdapter.setListener(listener);
                listView.setAdapter(foodAdapter);


            }
        }
    };
    private onCountChangeListen listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_food);


        shopName=(TextView)findViewById(R.id.tv_shop_name) ;
        shopPhoto=(ImageView)findViewById(R.id.iv_shop) ;
        shopIntroduction=(TextView)findViewById(R.id.tv_shop_summary) ;
        shopGrade=(TextView)findViewById(R.id.tv_shop_type) ;
        shopSaleCount=(TextView)findViewById(R.id.tv_shop_send) ;

        Intent intent=getIntent();
        String storeIdCurrent=intent.getStringExtra("storeNameCurrent");
        int storeId=intent.getIntExtra("storeidCurrent",1);
        Log.i("---------",storeIdCurrent);
        Log.i("---------",storeId+"");
        shopName.setText(storeIdCurrent);

        currentId=storeId;
        doEvent();

        listView=(ListView) findViewById(R.id.listViewfood2);

    }
    private void doEvent(){
        Request request = new Request.Builder()
                .url("http://192.168.137.1:8080/practice2/order_findCurrentStore?storeIdCurrent="+currentId)
                .get()
                .build();
        exec(request,1);
    }
    private void doEvent2(){
        Request request = new Request.Builder()
                .url("http://192.168.137.1:8080/practice2/order_findCurrentFoodByStoreId?storeIdCurrent="+currentId)
                .get()
                .build();
        exec(request,2);
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
}
