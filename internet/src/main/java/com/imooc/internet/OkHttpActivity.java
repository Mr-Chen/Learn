package com.imooc.internet;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OkHttpActivity extends AppCompatActivity {

    private static final String TAG = "LOGE";
    private OkHttpClient client;
    private String REQUEST_HOST = "https://httpbin.org/";

    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);

        checkPermissions();


        client = new OkHttpClient();
    }

    private void checkPermissions() {
        List<String> list = new ArrayList<>();
        for (String permission : permissions) {
            int flag = ContextCompat.checkSelfPermission(this, permission);
            if (flag != PackageManager.PERMISSION_GRANTED) {
                list.add(permission);
            }

        }

        if (list.size() > 0) {
            ActivityCompat.requestPermissions(this, list.toArray(new String[list.size()]), 110);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 110) {

        }
    }

    //get同步
    public void getSync(View view) {

        new Thread() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(REQUEST_HOST + "get?a=hello&b=你好")
                        .get()//默认为get
                        .build();

                Call call = client.newCall(request);
                try {
                    Response response = call.execute();
                    Log.e(TAG, "getSync: code=" + response.code() +
                            " \n body=" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    //get异步
    public void getAsync(View view) {
        Request request = new Request.Builder()
                .url(REQUEST_HOST + "?a=thank&b=you")
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "getAsync: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.e(TAG, "getAsync: code=" + response.code() +
                        " \n body=" + response.body().string());
            }
        });
    }

    //post同步
    public void postSync(View view) {
        new Thread() {

            @Override
            public void run() {

                FormBody formBody = new FormBody.Builder()
                        .add("param1", "value1")
                        .add("param2", "value2")
                        .build();


                Request request = new Request.Builder()
                        .url(REQUEST_HOST + "post")
                        .post(formBody)
                        .build();

                Call call = client.newCall(request);
                try {
                    Response response = call.execute();
                    Log.e(TAG, "postSync: code=" + response.code() +
                            " \n body=" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //post异步
    public void postAsync(View view) {
        FormBody formBody = new FormBody.Builder()
                .add("postArms1", "value1")
                .add("postArms2", "value2")
                .build();

        Request request = new Request.Builder()
                .url(REQUEST_HOST + "post")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "postAsync: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.e(TAG, "postAsync: code=" + response.code() +
                        " \n body=" + response.body().string());
            }
        });
    }

    //文件上传
    public void uploadFile(View view) {

        //打开手机相册选取图片
        Intent pickerIntent = new Intent(Intent.ACTION_PICK, null);
        pickerIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(pickerIntent, 100);

    }

    //拦截器
    public void intercept(View view) {

        OkHttpClient interClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        //统一前置处理  添加系统或者版本号
                        Request request = chain.request().newBuilder().addHeader("os", "android")
                                .addHeader("version", "1.0")
                                .build();

                        Response response = chain.proceed(request);
                        return response;
                    }
                })
                .addNetworkInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {

                        Log.e(TAG, "intercept: 系统 = " + chain.request().header("os"));
                        return chain.proceed(chain.request());
                    }
                })
                .build();

        Request request = new Request.Builder()
                .url(REQUEST_HOST + "get?name=intercept&value=okhttp")
                .build();
        Call call = interClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "intercept: code = " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.e(TAG, "intercept: code = " + response.code() + "\n" + response.body().string());
            }
        });

    }

    //玩Android 登录api
    String url = "https://www.wanandroid.com/user/login";
    //将cookie保存到内存
    Map<String, List<Cookie>> cookies = new HashMap<>();
    OkHttpClient cookieClient = new OkHttpClient.Builder()
            .cookieJar(new CookieJar() {
                @Override
                public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
                    cookies.clear();
                    cookies.put(httpUrl.host(), list);
                }

                @NonNull
                @Override
                public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
                    List<Cookie> cookieList = cookies.get(httpUrl.host());

                    return cookieList == null ? new ArrayList<>() : cookieList;
                }
            })
            .build();

    public void cookie(View view) {



        FormBody body = new FormBody.Builder()
                .add("username", "chenfc8578")
                .add("password", "chenxw8578")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();


        Call call = cookieClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "cookie: code = " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.e(TAG, "cookie: code = " + response.code() + "\n" + response.body().string());
            }
        });




    }

    public void collect(View view) {
        //访问收藏接口
        Request collRequest = new Request.Builder()
                .url("https://www.wanandroid.com/lg/collect/list/0/json")
                .build();
        Call collCall = cookieClient.newCall(collRequest);
        collCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "collects: code = " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.e(TAG, "collects: code = " + response.code() + "\n" + response.body().string());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {

            String path = GetPathFromUri.getPath(OkHttpActivity.this, data.getData());
            Log.e(TAG, "uploadFile:path " + path);
            String path1 = "/storage/emulated/0/DCIM/Camera/20200709_173215.jpg";
            String path2 = "/storage/emulated/0/DCIM/Camera/20200709_173021.jpg";
            File file1 = new File(path1);
            File file2 = new File(path2);

            MultipartBody requestBody = new MultipartBody.Builder()
                    .addFormDataPart("file1", file1.getName(), RequestBody.create(file1, MediaType.parse("image/jpeg")))
                    .addFormDataPart("file2", file2.getName(), RequestBody.create(file2, MediaType.parse("image/jpeg")))
                    .build();
            Request request = new Request.Builder()
                    .url(REQUEST_HOST + "post")
                    .post(requestBody)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e(TAG, "uploadFile: " + e.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    Log.e(TAG, "uploadFile: code = " + response.code());
                }
            });



        }
    }
}