package com.example.chileme;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UserInformation extends Activity {
    private TextView mine_username;
    private TextView mine_password;
    private TextView mine_address;
    private TextView mine_phonenumber;
    private static int CAMERA_REQUEST_CODE = 1;//摄像头返回data
    private static int GALLERY_REQUEST_CODE = 2;//图库返回data
    private Bitmap head;//头像Bitmap
    private static String path="/sdcard/myHead/";//sd路径
    private RoundedImageView roundedImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        initView();
        roundedImageView= (RoundedImageView) findViewById(R.id.touxiang_user);
        mine_username= (TextView) findViewById(R.id.mine_username);
        mine_password= (TextView) findViewById(R.id.mine_password);
        mine_address= (TextView) findViewById(R.id.mine_address);
        mine_phonenumber= (TextView) findViewById(R.id.mine_phonenumber);

        mine_username.setText(LoginActivity.username);
        mine_phonenumber.setText(LoginActivity.phonenumber);
        mine_address.setText(LoginActivity.address);
        mine_password.setText(LoginActivity.password);

    }

    public RoundedImageView getRoundedImageView() {
        return roundedImageView;
    }

    public void setRoundedImageView(RoundedImageView roundedImageView) {
        this.roundedImageView = roundedImageView;
    }

    public void upload_photo(View view)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(UserInformation.this);
        builder.setTitle("上传头像");
        //builder.setIcon(R.drawable.);
        //builder.setMessage("确认要退出吗？");
        String []items={"拍照","从手机相册选择"};
        builder.setItems(items,new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(change_mine.this, "你点击的是条目" +i, Toast.LENGTH_SHORT).show();
                if (i==0)
                {
                    getCamera();
                }
                if (i==1)
                {
                    getPhoto();
                }
            }
        });
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //确定
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //取消
//            }
//        });
        builder.create().show();
    }
    //    public  void show_photo(Bitmap bm)
//    {
//        roundedImageView.setImageBitmap(bm);//将图片显示在界面
//
//    }
    public void getPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    public void getCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 下面这句指定调用相机拍照后的照片存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                Environment.getExternalStorageDirectory(), "photo.jpg")));
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());//裁剪图片
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory()
                            + "/photo.jpg");
                    cropPhoto(Uri.fromFile(temp));//裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if(head!=null){
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(head);//保存在SD卡中
                        roundedImageView.setImageBitmap(head);//用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    };

    private void initView() {
        Bitmap bt = BitmapFactory.decodeFile(path + "photo.jpg");//从Sd中找头像，转换成Bitmap
        if(bt!=null){
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);//转换成drawable
            roundedImageView.setImageDrawable(drawable);
        }else {
            /**
             *  如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
             */
        }
    }

    /**
     * 调用系统的裁剪
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }
    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName =path + "photo.jpg";//图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void return_Mine_view(View view)
    {
     //   Intent intent=new Intent(UserInformation.this,PersonalFragment.class);
        Intent intent=new Intent(UserInformation.this,MainFragment.class);
        intent.putExtra("flag",4);
        startActivity(intent);
    }
    public void transform_change_username(View view)
    {
        Intent intent0=new Intent(UserInformation.this,ChangeInformation.class);
        startActivity(intent0);
    }
}
