package com.bw.movie.utils;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/23 8:52<p>
 * <p>更改时间：2019/1/23 8:52<p>
 * <p>版本号：1<p>
 */
public interface  MyApiService {
    @GET
    Observable<ResponseBody> get(@Url String url,@HeaderMap Map<String,Object> headmap, @QueryMap Map<String,Object> map);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> post(@Url String url, @HeaderMap Map<String,Object> headmap, @FieldMap Map<String, Object>map);

    @POST
    Observable<ResponseBody> img(@Url String url, @HeaderMap Map<String,Object> headmap, @Body MultipartBody body);

}
