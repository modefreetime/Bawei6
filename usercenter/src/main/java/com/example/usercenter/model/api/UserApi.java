package com.example.usercenter.model.api;

import androidx.lifecycle.LiveData;

import com.example.net.common.Config;
import com.example.net.protocol.BaseRespEntity;
import com.example.net.protocol.TokenRespEntity;
import com.example.usercenter.entity.UserEntity;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {



    @POST("api/User/login")
    LiveData<BaseRespEntity<UserEntity>> login(@Body UserEntity entity);

    @POST("api/User/register")
    LiveData<BaseRespEntity<UserEntity>> register(@Body UserEntity entity);

    @GET("api/User/getAuthCode?")
    LiveData<BaseRespEntity<String>> getYzm(@Query("phoneNumber") String phone);

    @FormUrlEncoded
    @POST("api/User/modifyPwd")
    LiveData<BaseRespEntity<Boolean>> update(@Field("userid")String userid,@Field("pwd")String pwd);

}
