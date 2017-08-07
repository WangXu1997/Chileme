package com.example.chileme;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_order, container, false);
        sv=(ScrollView) view.findViewById(R.id.scrollView);
        sv.smoothScrollTo(0, 0);
        listView=(ListViewForScrollView)view.findViewById(R.id.listview);
        listView2=(ListViewForScrollView)view.findViewById(R.id.listview2);
        String[] keys={"img"};
        int[] ids={R.id.item_img};
        int[] ids2={R.id.item_img2};
        SimpleAdapter simpleAdapter=new SimpleAdapter(this.getActivity(),lists,R.layout.list_item1,keys,ids);
        listView.setAdapter(simpleAdapter);
        for(int i=0;i<2;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("img",imgIds[0]);
            lists.add(map);
        }
        SimpleAdapter simpleAdapter2=new SimpleAdapter(this.getActivity(),lists2,R.layout.list_item2,keys,ids2);
        listView2.setAdapter(simpleAdapter2);
        for(int i=0;i<2;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("img",imgIds[0]);
            lists2.add(map);
        }
        return view;
    }

}
