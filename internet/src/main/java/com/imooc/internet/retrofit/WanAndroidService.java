package com.imooc.internet.retrofit;

import com.imooc.internet.bean.CollectsBean;
import com.imooc.internet.bean.LoginBean;

import io.reactivex.rxjava3.core.Flowable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Author   ： cxw
 * Date     ： 2022/4/20 09:46
 * Explain  :  请在此输入文件说明
 */
interface WanAndroidService {

    @POST("user/login")
    @FormUrlEncoded
    Flowable<LoginBean> login(@Field("username") String username, @Field("password") String password);

    @GET("lg/collect/list/{id}/json")
    Flowable<CollectsBean> collects(@Path("id") int id);

    //上传
    @POST("post")
    @Multipart
    Call<ResponseBody> upload(@Part MultipartBody.Part file);


    //如果下载文件较大，一定要加此注解避免oom
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url);
}
