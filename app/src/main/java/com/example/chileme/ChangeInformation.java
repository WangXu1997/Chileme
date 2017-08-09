package com.example.chileme;

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

public class ChangeInformation extends AppCompatActivity {
    private EditText change_username_new;
    private EditText change_password_new;
    private EditText change_phonenumber_new;
    private EditText change_address_new;
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
        setContentView(R.layout.activity_change_information);

        change_username_new= (EditText) findViewById(R.id.change_username_new);
        change_password_new= (EditText) findViewById(R.id.change_password_new);
        change_phonenumber_new= (EditText) findViewById(R.id.change_phonenumber_new);
        change_address_new= (EditText) findViewById(R.id.change_address_new);

    }

    public void ensure_change_user(View view)
    {

        String username_new=change_username_new.getText().toString();
        String password_new=change_password_new.getText().toString();
        String phonenumber_new=change_phonenumber_new.getText().toString();
        String address_new=change_address_new.getText().toString();
        Log.i(LoginActivity.userid+"","-------------->");
        Request request = new Request.Builder()
                .url("http://192.168.137.1:8080/practice2/changeUser?username="+username_new+
                        "&pwd="+password_new+"&phonenumber="+phonenumber_new+ "&address="+address_new
                        +"&user_id="+LoginActivity.userid+"&money="+LoginActivity.money+"&vip="+LoginActivity.vip)
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

                    //System.out.println(username_get+"  "+phone_get);
                }
                catch (Exception e)
                {

                }

                Message message = new Message();
                message.what = 1;
                message.obj = s;
                handler.sendMessage(message);
            }

        });
    }
}
