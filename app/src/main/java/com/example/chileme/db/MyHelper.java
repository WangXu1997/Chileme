package com.example.chileme.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by a on 2017/8/4.
 */

public class MyHelper extends SQLiteOpenHelper {

    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("info","MyHelper======>onCreate");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("info","MyHelper=======>onUpgrade");
        if (oldVersion==1&&newVersion==2)
        {
            String sql="create table if not exists userinfo(username text not null primary key,pwd text not null )";
            db.execSQL(sql);
        }

    }
}

