package com.imooc.internet.retrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.imooc.internet.R;
import com.imooc.internet.bean.BannerBean;
import com.imooc.internet.bean.CollectsBean;
import com.imooc.internet.bean.LoginBean;

import org.reactivestreams.Publisher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Retrofitctivity extends AppCompatActivity {

    private TextView tv;

    private Retrofit retrofit;

    private String POST_SERVER_HOST = "https://ab83ba10-cca9-4b0f-afae-ebe20c0a6c7b.mock.pstmn.io";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofitctivity);

        tv = findViewById(R.id.tv);

        retrofit = new Retrofit.Builder()
                .baseUrl(POST_SERVER_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public void get(View view) {
        PostManService postManService = retrofit.create(PostManService.class);
//        Call<ResponseBody> login = postManService.login("123", "123");
        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("username", "123");
        loginMap.put("password", "123");
        Call<ResponseBody> login = postManService.login(loginMap);
        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                if (response != null) {
                    try {
                        tv.setText(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    tv.setText("error");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                tv.setText(t.getMessage());

            }
        });

    }


    public void post(View view) {
        PostManService postManService = retrofit.create(PostManService.class);
//        Call<ResponseBody> call = postManService.banner("banner");
        Call<BannerBean> call = postManService.banner("banner");
        call.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {


                if (response != null) {
                    try {
                        if (response.errorBody() != null) {
                            tv.setText(response.errorBody().string());
                        } else {
                            BannerBean body = response.body();
                            tv.setText(body.getData().get(0).getDesc());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    tv.setText("error");
                }
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {

                tv.setText(t.getMessage());

            }
        });
    }

    //动态url
    public void dynamic(View view) {
        PostManService postManService = retrofit.create(PostManService.class);
        Call<ResponseBody> call = postManService.dynamicUrl("1", "list");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                if (response != null) {
                    try {
                        if (response.errorBody() != null) {
                            tv.setText(response.errorBody().string());
                        } else {
                            tv.setText(response.body().string());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    tv.setText("error");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                tv.setText(t.getMessage());

            }
        });

    }

    //rxjava适配器
    public void rxjava(View view) {

        Map<String, List<Cookie>> cookies = new HashMap<>();
        Retrofit wanRetrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .callFactory((okhttp3.Call.Factory) new OkHttpClient.Builder()
                        .cookieJar(new CookieJar() {
                            @Override
                            public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
                                cookies.clear();
                                cookies.put(httpUrl.host(), list);
                            }

                            @NonNull
                            @Override
                            public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
                                List<Cookie> cookies1 = cookies.get(httpUrl.host());
                                return cookies1 == null ? new ArrayList<>() : cookies1;
                            }
                        })
                        .build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        WanAndroidService wanService = wanRetrofit.create(WanAndroidService.class);
        wanService.login("chenfc8578", "chenxw8578")
                .flatMap(new Function<LoginBean, Publisher<CollectsBean>>() {
                    @Override
                    public Publisher<CollectsBean> apply(LoginBean loginBean) throws Throwable {
                        Flowable<CollectsBean> collects = wanService.collects(0);
                        return collects;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<CollectsBean>() {
                    @Override
                    public void accept(CollectsBean collectsBean) throws Throwable {
                        tv.setText(collectsBean.getData().getDatas().get(0).getTitle());
                    }
                });
    }

    //文件上传
    public void upload(View view) {
        Retrofit uploadFit = new Retrofit.Builder()
                .baseUrl("https://httpbin.org/")
                .build();

        File file = new File("/storage/emulated/0/DCIM/Camera/20200709_173215.jpg");
        MultipartBody.Part multipartBody =  MultipartBody.Part
                .createFormData("file",file.getName(),
                        RequestBody.create(file,MediaType.parse("image/jpeg")));

        WanAndroidService wanService = uploadFit.create(WanAndroidService.class);
        Call<ResponseBody> call = wanService.upload(multipartBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null) {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                String url = t.getMessage();

            }
        });
    }
}