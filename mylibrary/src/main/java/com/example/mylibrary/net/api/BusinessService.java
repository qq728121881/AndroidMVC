package com.example.mylibrary.net.api;


import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * 业务逻辑接口
 */
public interface BusinessService {

//    //提交读后感图片
//    @Multipart
//    @POST("/xxx/}")
//    Observable<List<UploadFileResponse.DATA>> uploadImgS(@Path("userId") String userId, @Path("tableName") String tableName, @PartMap Map<String, RequestBody> params);
//
//    //上传文件
//    @Multipart
//    @POST("/xxx/")
//    Observable<List<UploadFileResponse.DATA>> uploadFile(@Path("userId") String userId, @Part() List<MultipartBody.Part> parts);
//
//    //查询城市接口
//    @GET("/xxx/")
//    Observable<CityResponse> getCityById(@Query("cityId") String cityId);
//
//    //上传文件
//    @Multipart
//    @POST("/xxx/")
//    Observable<List<UploadFileResponse.DATA>> uploadFile(@Path("userId") String userId, @PartMap Map<String, RequestBody> params);

}
