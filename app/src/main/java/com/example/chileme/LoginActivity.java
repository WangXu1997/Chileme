package com.example.chileme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    public  static String username;
    public  static String phonenumber;
    public  static String password;
    public  static double money;
    public static  String address;
    public static  int vip;
    public  static  String photo;
    public static int userid;
    // private TextView text;
    private EditText input_username0;
    private EditText input_password0;

    OkHttpClient okHttpClient = new OkHttpClient();

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                //String result = (String) msg.obj;
                //text.setText(result);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        input_username0= (EditText) findViewById(R.id.input_username);
        input_password0= (EditText) findViewById(R.id.input_password);
    }

    public void login_button(View view)
    {
        String username0=input_username0.getText().toString();
        String pwd0=input_password0.getText().toString();
        //  http://192.168.40.22:8080/practice2/login?username=范卫松&pwd=fanweisong
        Request request = new Request.Builder()
                .url("http://192.168.56.1:8080/practice2/login?username="+username0+"&pwd="+pwd0)
                .get()
                .build();
        exec(request);


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
                    userid=jsonObject.getInt("user_id");
                    username=jsonObject.getString("username");
                    phonenumber=jsonObject.getString("phonenumber");
                    password=jsonObject.getString("password");
                    money=jsonObject.getDouble("money");
                    address=jsonObject.getString("address");
                    vip=jsonObject.getInt("vip");
                    photo=jsonObject.getString("photo");



                    //System.out.println(username_get+"  "+phone_get);
                }
                catch (Exception e)
                {

                }
                Log.i(userid+"","--------------->");
                Log.i(vip+"","--------------->");
                Log.i(money+"","--------------->");
                Intent intent=new Intent(LoginActivity.this,MainFragment.class);
                intent.putExtra("flag",4);
                startActivity(intent);
                finish();

                Message message = new Message();
                message.what = 1;
                message.obj = s;
                handler.sendMessage(message);
            }

        });
    }
}
