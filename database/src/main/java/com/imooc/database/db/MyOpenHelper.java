package com.imooc.database.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.imooc.database.Constant;

import androidx.annotation.Nullable;

/**
 * Author   ： cxw
 * Date     ： 2022/4/25 19:21
 * Explain  :  请在此输入文件说明
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "LOGE";

    public MyOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);


    }

    public MyOpenHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate() ");
        String sql = "create table " + Constant.TABLE_NAME + "(id Integer primary key," + Constant.NAME + " varchar(10)," + Constant.CLASS + " varchar(10)," + Constant.AGE + " Integer)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, "onUpgrade() ");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.e(TAG, "onOpen() ");
    }
}
