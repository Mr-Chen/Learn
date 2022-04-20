package com.imooc.internet.retrofit;

import com.imooc.internet.bean.BannerBean;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Author   ： cxw
 * Date     ： 2022/4/20 06:47
 * Explain  :  请在此输入文件说明
 */
interface PostManService {

    @GET("login")
//    Call<ResponseBody> login(@Query("username") String username, @Query("password") String password);
    Call<ResponseBody> login(@QueryMap() Map<String, String> map);

    @POST("banners")
    @FormUrlEncoded
    Call<BannerBean> banner(@Field("param") String param);

    //拼接url
    @GET("{id}/list")
    Call<ResponseBody> dynamicUrl(@Path("id") String id, @Query("name") String name);
}
