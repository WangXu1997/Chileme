package com.example.chileme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_food);

        listView=(ListView) findViewById(R.id.listViewfood2);
        String[] keys={"img","title","detail","price"};
        int[] ids={R.id.iv_food,R.id.tv_name,R.id.tv_summary,R.id.tv_price};
        SimpleAdapter simpleAdapter = new SimpleAdapter(ShopFoodActivity.this,lists,R.layout.list_food,keys,ids);
        listView.setAdapter(simpleAdapter);
        for (int i=0;i<imgIDs.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("img",imgIDs[i]);
            map.put("title",names[i]);
            map.put("detail","简介："+detail[i]);
            map.put("price","￥"+prices[i]);

            lists.add(map);}

        View view = LayoutInflater.from(this).inflate(R.layout.list_food,null);

        add1=(ImageView) view.findViewById(R.id.add1);
        delete1=(ImageView) view.findViewById(R.id.delete1);
        edit1= (TextView) view.findViewById(R.id.edit1);
        edit1.setText("0");

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(
                        TextUtils.isEmpty(edit1.getText().toString()) ? "1" : edit1.getText().toString());
                edit1.setText(String.valueOf(num + 1));
            }
        });
        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(
                        TextUtils.isEmpty(edit1.getText().toString()) ? "1" : edit1.getText().toString());
                if(num==0){}
                else {
                    edit1.setText(String.valueOf(num - 1));
                }
            }
        });
    }

}
