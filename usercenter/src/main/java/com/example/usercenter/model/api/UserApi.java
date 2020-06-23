package com.example.usercenter.model.api;

import androidx.lifecycle.LiveData;

import com.example.net.common.Config;
import com.example.net.protocol.BaseRespEntity;
import com.example.net.protocol.TokenRespEntity;
import com.example.usercenter.entity.UserEntity;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserApi {



    @POST("api/User/login")
    LiveData<BaseRespEntity<UserEntity>> login(@Body UserEntity entity);
}
