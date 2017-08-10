package com.example.chileme;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.main_view)
public class MainViewActivity extends AppCompatActivity {

    private  int[] nameID={R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5,R.drawable.a6,
            R.drawable.a7,R.drawable.a8};
    private  int[] nameID2={R.drawable.a5,R.drawable.a6, R.drawable.a7,R.drawable.a8};
    private List<String> list = new ArrayList<String>();
    private Spinner mySpinner;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private List<Map<String,Object>> lists=new ArrayList<>();
    private  int[] imgIDs={R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5,R.drawable.p6,};
    private  String[] names={"肯德基","麦当劳","必胜客","德克士","土大力","彤德莱"};
    private  int[] prices={4,5,3,4,1,0};
    private  int[] checks={94,85,93,84,91,90};
    private  String[] detail={"炸鸡汉堡，心动超值","发现麦当劳的经典汉堡、当期新品、优惠活动和最新优惠券","人气比萨四合一,小食,饮料花式拼.还有麻辣小龙虾比萨新上市"
            ,"德克士脆皮炸鸡","韩式餐饮","肥肠好吃的火锅"};


    @ViewInject(R.id.gridView)
    private GridView gird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        list.add("北京");
        list.add("上海");
        list.add("深圳");
        list.add("福州");
        list.add("厦门");
        mySpinner = (Spinner)findViewById(R.id.Spinner_city);
        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mySpinner.setAdapter(adapter);
        //第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        listView=(ListView) findViewById(R.id.listView);
        String[] keys={"img","title","price","check","detail"};
        int[] ids={R.id.item_img,R.id.item_title,R.id.item_price,R.id.item_check,R.id.item_detail};
        SimpleAdapter simpleAdapter = new SimpleAdapter(MainViewActivity.this,lists,R.layout.list_item,keys,ids);
        listView.setAdapter(simpleAdapter);
        for (int i=0;i<imgIDs.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("img",imgIDs[i]);
            map.put("title",names[i]);
            map.put("price","销售量："+prices[i]+"单");
            map.put("check","好评率："+checks[i]+"%");
            map.put("detail","简介："+detail[i]);
            lists.add(map);
        }


        MyAdapter myAdapter= new MyAdapter(MainViewActivity.this);
        gird.setAdapter(myAdapter);
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
}

