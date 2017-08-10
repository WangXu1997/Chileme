package com.example.chileme;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import okhttp3.Request;

@ContentView(R.layout.main_fragment)
public class MainFragment extends FragmentActivity {

    @ViewInject(R.id.firstpage_frag)
    private ImageView firstpage_frag;
    @ViewInject(R.id.discover_frag)
    private ImageView discover_frag;
    @ViewInject(R.id.order_frag)
    private ImageView order_frag;
    @ViewInject(R.id.personal_frag)
    private ImageView personal_frag;
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
        Fragment init=new Fragment();
        Intent intent=getIntent();
        int flag=intent.getIntExtra("flag",1);
        switch (flag){
            case 1:
                init=new FirstPageFragment();
                firstpage_frag.setImageResource(R.drawable.x102);

                break;
            case 2:
                init=new DiscoverFragment();
                discover_frag.setImageResource(R.drawable.x202);
                break;
            case 3:
                init=new OrderFragment();
                order_frag.setImageResource(R.drawable.x302);
                break;
            case 4:
                init=new PersonalFragment();
                personal_frag.setImageResource(R.drawable.x402);
                break;
            default:
                break;
        }

        transaction.replace(R.id.fragmentPager, init,"fragment");
        transaction.commit();
    }


    private View.OnClickListener itemClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            firstpage_frag.setImageResource(R.drawable.x101);
            discover_frag.setImageResource(R.drawable.x201);

            order_frag.setImageResource(R.drawable.x301);

            personal_frag.setImageResource(R.drawable.x401);

//            TextView textview = (TextView) v;
//            textview.setBackgroundColor(0Xff4169E1);
//            textview.setTextColor(0xff000000);
            ImageView imageView=(ImageView) v;
            transaction = fragmentManager.beginTransaction();
            switch (v.getId()) {
                case R.id.firstpage_frag:
                    imageView.setImageResource(R.drawable.x102);
                    contentFragment = new FirstPageFragment();
                    transaction.replace(R.id.fragmentPager, contentFragment);
                    break;
                case R.id.discover_frag:
                    imageView.setImageResource(R.drawable.x202);
                    contentFragment = new DiscoverFragment();
                    transaction.replace(R.id.fragmentPager, contentFragment);
                    break;
                case R.id.order_frag:
                    imageView.setImageResource(R.drawable.x302);
                    contentFragment = new OrderFragment();
                    transaction.replace(R.id.fragmentPager, contentFragment);
                    break;
                case R.id.personal_frag:
                    imageView.setImageResource(R.drawable.x402);
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
                .url("http://192.168.137.1:8080/practice2/order_enterOrder")
                .get()
                .build();
        Fragment contentFragment = new OrderFragment();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentPager, contentFragment);
    }
}
