package com.imooc.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.imooc.database.db.DBManager;
import com.imooc.database.db.MyOpenHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName, etClass, etAge;
    private Button btnAdd, btnUpdate, btnDelete, btnQuery;

    private MyOpenHelper helper;
    private String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

    private String strName, strClass, strAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, permissions, 0);


        etName = findViewById(R.id.et_name);
        etClass = findViewById(R.id.et_class);
        etAge = findViewById(R.id.et_age);
        btnAdd = findViewById(R.id.btn_add);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
        btnQuery = findViewById(R.id.btn_qury);

        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
    }

    public void createDB(View view) {

        helper = DBManager.getInstance(this);
        SQLiteDatabase database = helper.getWritableDatabase();
        DBManager.copyDB(this);


    }

    @Override
    public void onClick(View v) {
        createDB();

        strName = etName.getText().toString();
        strClass = etClass.getText().toString();
        strAge = etAge.getText().toString();

        switch (v.getId()) {
            case R.id.btn_add:
                if (isEmpty()) return;
                SQLiteDatabase database = DBManager.getInstance(MainActivity.this).getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Constant.NAME, strName);
                values.put(Constant.CLASS, strClass);
                values.put(Constant.AGE, Integer.parseInt(strAge));
                long insert = database.insert(Constant.TABLE_NAME, null, values);
                if (insert > 0) {
                    toast("添加成功");
                }else{
                    toast("添加失败");
                }
                database.close();
                break;
            case R.id.btn_update:
                if (isEmpty()) return;
                break;
            case R.id.btn_delete:
                if (isEmpty()) return;
                break;
            case R.id.btn_qury:
                DBManager.copyDB(this);
                break;
        }
    }

    /**
     * 创建数据库
     */
    private void createDB() {
        helper = DBManager.getInstance(this);
        SQLiteDatabase database = helper.getWritableDatabase();
    }

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private boolean isEmpty() {
        if (strName.isEmpty() || strClass.isEmpty()
                || strAge.isEmpty()) {
            toast("姓名，班级，年龄都不能为空");
            return true;
        }
        return false;
    }
}