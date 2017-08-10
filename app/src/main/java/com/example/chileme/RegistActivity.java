package com.example.chileme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegistActivity extends AppCompatActivity {

    private EditText regist_username;
    private EditText regist_password;
    private EditText regist_phonenumber;
    private EditText regist_address;
    private EditText regist_money;
    public static String username_new;
    OkHttpClient okHttpClient = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        regist_username= (EditText) findViewById(R.id.regist_username);
        regist_password= (EditText) findViewById(R.id.regist_password);
        regist_phonenumber= (EditText) findViewById(R.id.regist_phonenumber);
        regist_address= (EditText) findViewById(R.id.regist_address);
        regist_money= (EditText) findViewById(R.id.regist_money);
    }

    public void return_Login(View view)
    {
        Intent intent=new Intent(RegistActivity.this,LoginActivity.class);
        startActivity(intent);
    }
    public void ensure_regist(View view)
    {
        String username=regist_username.getText().toString();
        String password=regist_password.getText().toString();
        String phonenumber=regist_phonenumber.getText().toString();
        String address=regist_address.getText().toString();
        float money=Float.parseFloat(regist_money.getText().toString());

        FormBody.Builder builder1 = new FormBody.Builder();
        FormBody formBody = builder1.add("username", username)
                .add("pwd", password).add("phonenumber",phonenumber)
                .add("address",address).add("money",money+"")
                .add("vip",0+"").build();

        Request.Builder builder = new Request.Builder();
        Request request = builder.url("http://192.168.137.1:8080/practice2/regist")
                .post(formBody)
                .build();
        exec(request);

        Intent intent=new Intent(RegistActivity.this,LoginActivity.class);
        startActivity(intent);

    }

    private void exec(Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("异常:","--->"+e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.i("成功","--->");
                String s = response.body().string();
                //System.out.println(s);
                try
                {
                    JSONObject jsonObject = new JSONObject(s);
                     username_new=jsonObject.getString("username");
                    Log.i(username_new,"---------------->");

                    //System.out.println(username_get+"  "+phone_get);
                }
                catch (Exception e)
                {

                }

                Message message = new Message();
                message.what = 1;
                message.obj = s;
                //handler.sendMessage(message);
            }

        });
    }
}
