package com.example.chileme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends AppCompatActivity {
    @ViewInject(R.id.pager)
    private ViewPager pager;
    private int[] imgIds = {R.drawable.welcome1,R.drawable.welcome2,R.drawable.welcome3};
    private List<ImageView> imageViews = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        //x.view().inject(this);
        //动画效果
        // pager.setPageTransformer(true,new DepthPageTransformer());
        pager.setPageTransformer(true,new ZoomOutPageTransformer());
        pager.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                ImageView img = new ImageView(WelcomeActivity.this);
                img.setImageResource(imgIds[position]);
                img.setScaleType(ImageView.ScaleType.CENTER_CROP);//不变形

                container.addView(img);
                imageViews.add(img);

                ImageView imageView = imageViews.get(position);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position){
                            case 2:
                                Intent intent = new Intent(WelcomeActivity.this, MainFragment.class);
                                intent.putExtra("flag",1);
                                startActivity(intent);
                                break;
                        }
                    }
                });
                return img;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                //移除ImageView
                container.removeView(imageViews.get(position));
            }

            @Override
            public int getCount() {
                return imgIds.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
    }
}