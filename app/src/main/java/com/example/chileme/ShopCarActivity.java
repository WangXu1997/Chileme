package com.example.chileme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopCarActivity extends AppCompatActivity {
    private Spinner mySpinner;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private List<Map<String,Object>> lists=new ArrayList<>();
    private  String[] foodnames={"肯德基","麦当劳","必胜客","德克士","土大力","彤德莱"};
    private  String[] num={"1","2","3","1","5","1"};
    private  String[] price={"12","15","13","17","19","21"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        listView=(ListView) findViewById(R.id.listViewfinalfood);
        String[] keys={"foodnames","num","price"};
        int[] ids={R.id.final_food_name,R.id.final_food_num,R.id.final_food_price};
        SimpleAdapter simpleAdapter = new SimpleAdapter(ShopCarActivity.this,lists,R.layout.list_final_food,keys,ids);
        listView.setAdapter(simpleAdapter);
        for (int i=0;i<foodnames.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("foodnames",foodnames[i]);
            map.put("num",num[i]);
            map.put("price",price[i]);


            lists.add(map);}

    }

}