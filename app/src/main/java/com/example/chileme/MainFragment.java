package com.example.chileme;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import okhttp3.Request;

@ContentView(R.layout.main_fragment)
public class MainFragment extends AppCompatActivity {

    @ViewInject(R.id.firstpage_frag)
    private TextView firstpage_frag;
    @ViewInject(R.id.discover_frag)
    private TextView discover_frag;
    @ViewInject(R.id.order_frag)
    private TextView order_frag;
    @ViewInject(R.id.personal_frag)
    private TextView personal_frag;
    private Fragment contentFragment;
    public static  FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initUi();
    }
    private void initUi() {
        firstpage_frag.setOnClickListener(itemClick);
        discover_frag.setOnClickListener(itemClick);
        order_frag.setOnClickListener(itemClick);
        personal_frag.setOnClickListener(itemClick);

        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        Fragment init=new FirstPageFragment();
        transaction.replace(R.id.fragmentPager, init,"fragment");
        transaction.commit();
    }


    private View.OnClickListener itemClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            firstpage_frag.setBackgroundColor(0xffffffff);
            firstpage_frag.setTextColor(0Xff4169E1);
            discover_frag.setBackgroundColor(0Xffffffff);
            discover_frag.setTextColor(0Xff4169E1);
            order_frag.setBackgroundColor(0xffffffff);
            order_frag.setTextColor(0Xff4169E1);
            personal_frag.setBackgroundColor(0xffffffff);
            personal_frag.setTextColor(0Xff4169E1);
            TextView textview = (TextView) v;
            textview.setBackgroundColor(0Xff4169E1);
            textview.setTextColor(0xff000000);
            transaction = fragmentManager.beginTransaction();
            switch (v.getId()) {
                case R.id.firstpage_frag:
                    contentFragment = new FirstPageFragment();
                    transaction.replace(R.id.fragmentPager, contentFragment);
                    break;
                case R.id.discover_frag:
                    contentFragment = new DiscoverFragment();
                    transaction.replace(R.id.fragmentPager, contentFragment);
                    break;
                case R.id.order_frag:
                    contentFragment = new OrderFragment();
                    transaction.replace(R.id.fragmentPager, contentFragment);
                    break;
                case R.id.personal_frag:
                    contentFragment = new PersonalFragment();
                    transaction.replace(R.id.fragmentPager, contentFragment);
                    break;
                case R.id.text15:
                    Request request = new Request.Builder()
                            .url("http://192.168.40.23:8080/practice2/order_enterOrder")
                            .get()
                            .build();
                    Intent intent=new Intent(MainFragment.this,OrderFragment.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
            transaction.commit();
        }
    };
    public static  void enterOrder(View view){
        Request request = new Request.Builder()
                .url("http://192.168.40.23:8080/practice2/order_enterOrder")
                .get()
                .build();
        Fragment contentFragment = new OrderFragment();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentPager, contentFragment);
    }
}
