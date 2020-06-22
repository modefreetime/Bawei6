package com.example.usercenter.model.api;

import com.example.net.common.Config;
import com.example.net.protocol.BaseRespEntity;
import com.example.net.protocol.TokenRespEntity;
import com.example.usercenter.entity.UserEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserApi {


    @Headers({Config.NEW_URLHEADER_KEY+":"+Config.NEW_URLHEADER_VALUE})
    @POST("/login")
    Call<TokenRespEntity> getTest();


    @POST("api/User/login")
    Call<BaseRespEntity<UserEntity>> login(@Body UserEntity entity);
}
