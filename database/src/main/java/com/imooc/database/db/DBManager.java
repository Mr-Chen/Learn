package com.imooc.database.db;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author   ： cxw
 * Date     ： 2022/4/25 19:23
 * Explain  :  请在此输入文件说明
 */
public class DBManager {

    private static MyOpenHelper helper;

    public static MyOpenHelper getInstance(Context context) {
        if (helper == null) {
            helper = new MyOpenHelper(context);
        }

        return helper;
    }


    public static void copyDB(Context context) {
        //找到文件的路径  /data/data/包名/databases/数据库名称
        File dbFile = new File(Environment.getDataDirectory().getAbsolutePath() + "/data/" + context.getPackageName() + "/databases/info.db");
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //文件复制到sd卡中
            fis = new FileInputStream(dbFile);
            fos = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/info.db");
            int len = 0;
            byte[] buffer = new byte[2048];
            while (-1 != (len = fis.read(buffer))) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) fos.close();
                if (fis != null) fis.close();
                Toast.makeText(context, "copy结束", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
