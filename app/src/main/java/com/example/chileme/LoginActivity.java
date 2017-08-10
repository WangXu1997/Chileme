package com.example.chileme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
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
    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1106256495";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;

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
        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID,LoginActivity.this.getApplicationContext());

        input_username0= (EditText) findViewById(R.id.input_username);
        String regist_username=RegistActivity.username_new;
        if (regist_username!=null)
        {input_username0.setText(regist_username.toCharArray(),0,regist_username.length());}
        input_password0= (EditText) findViewById(R.id.input_password);
    }

    public void login_button(View view)
    {
        String username0=input_username0.getText().toString();
        String pwd0=input_password0.getText().toString();
        //  http://192.168.40.22:8080/practice2/login?username=范卫松&pwd=fanweisong
       // Request request = new Request.Builder()
                // .url("http://192.168.40.33:8080/practice/login?username="+username0+"&pwd="+pwd0)
               // .get()
               // .build();
       // exec(request);

        FormBody.Builder builder1 = new FormBody.Builder();
        FormBody formBody = builder1.add("username", username0)
                .add("pwd", pwd0).build();

        Request.Builder builder = new Request.Builder();
        Request request1 = builder.url("http://192.168.56.1:8080/practice2/login")
                .post(formBody)
                .build();
        exec(request1);


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
    public void login_QQ(View view)
    {
        mIUiListener = new BaseUiListener();
        //all表示获取所有权限
        mTencent.login(LoginActivity.this,"all", mIUiListener);
    }
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(), qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG, "登录成功" + response.toString());
                        String s=response.toString();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            String nickname=jsonObject.getString("nickname");
                            Log.i(nickname,"-------------->");

                            userid=2;
                            username="bbb";
                            phonenumber="222";
                            password="1234";
                            money=100;
                            address="国祥公寓";
                            vip=1;
                            photo=null;

                            Intent intent=new Intent(LoginActivity.this,MainFragment.class);
                            intent.putExtra("flag",4);
                            startActivity(intent);
                            finish();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG, "登录失败" + uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG, "登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }
        /**
         * 在调用Login的Activity或者Fragment中重写onActivityResult方法
         * @param requestCode
         * @param resultCode
         * @param data
         */
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == Constants.REQUEST_LOGIN) {
                Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
            }
            super.onActivityResult(requestCode, resultCode, data);

    }
    public void regist_user(View view)
    {
        Intent intent=new Intent(LoginActivity.this,RegistActivity.class);
        startActivity(intent);
    }
}
