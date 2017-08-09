package com.example.chileme;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.q42.android.scrollingimageview.ScrollingImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment {

    private TextView new_username;
    private TextView new_phonenumber;
    private ScrollingImageView scrollingImageView;
    private TextView mine_money;
    private TextView mine_view_address;
    private TextView mine_view_vip;
    private com.makeramen.roundedimageview.RoundedImageView mine_touxiang;
    private ImageView mine_person;
    private TextView mine_jiantou;
    private Button logout_btn;
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_personal, container, false);

        new_username= (TextView) view.findViewById(R.id.username);
        //new_phonenumber= (TextView) findViewById(R.id.phonenumber);
        new_username.setText(LoginActivity.username);
        mine_money= (TextView) view.findViewById(R.id.mine_money);
        String money0=LoginActivity.money+"";
        mine_money.setText(money0);
        mine_view_address= (TextView)view. findViewById(R.id.mine_view_address);
        mine_view_address.setText(LoginActivity.address);
        //new_phonenumber.setText(LoginActivity.phonenumber);
        mine_touxiang=(com.makeramen.roundedimageview.RoundedImageView)view.findViewById(R.id.touxiang);
        mine_jiantou=(TextView)view.findViewById(R.id.jiantou);
        mine_person=(ImageView)view.findViewById(R.id.person);
        logout_btn=(Button)view.findViewById(R.id.logout);
        mine_view_vip= (TextView) view.findViewById(R.id.mine_view_vip);
        int is_vip=LoginActivity.vip;
        if (is_vip==0)
        {
            mine_view_vip.setText("不是会员？点此开通");
        }
        if (is_vip==1)
        {
            mine_view_vip.setText("恭喜你，已是会员！");
        }
        scrollingImageView= (ScrollingImageView)view.findViewById(R.id.ad_user);
        scrollingImageView.start();
//        scrollingBackground.stop();
//        scrollingBackground.start();

//        super_vip= (TextView) findViewById(R.id.super_vip_textView);
//        hongbao= (TextView) findViewById(R.id.hongbao_textView);
//        SpannableStringBuilder builder = new SpannableStringBuilder(super_vip.getText().toString());
//        SpannableStringBuilder builder1=new SpannableStringBuilder(hongbao.getText().toString());
        if(LoginActivity.username==null){
            logout_btn.setText("点此登录");
        }
        else{
            logout_btn.setText("退出登录");
        }
        ForegroundColorSpan greenColor=new ForegroundColorSpan(Color.GREEN);
        ForegroundColorSpan redColor=new ForegroundColorSpan(Color.RED);
        ForegroundColorSpan blackColor=new ForegroundColorSpan(Color.BLACK);
        ForegroundColorSpan greenColor0=new ForegroundColorSpan(Color.rgb(0,255,0));
        ForegroundColorSpan yellowColor=new ForegroundColorSpan(Color.rgb(245,190,0));

        mine_touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),UserInformation.class);
                startActivity(intent);
            }
        });
        mine_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),UserInformation.class);
                startActivity(intent);
            }
        });
        mine_jiantou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),UserInformation.class);
                startActivity(intent);
            }
        });
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    public void regist_vip()
    {

    }
}
