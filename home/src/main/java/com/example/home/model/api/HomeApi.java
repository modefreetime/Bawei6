package com.example.home.model.api;

import androidx.lifecycle.LiveData;

import com.example.home.entity.BannerEntity;
import com.example.home.entity.ProductEntity;
import com.example.home.entity.SyMsgEntity;
import com.example.net.protocol.BaseRespEntity;

import java.util.List;

import retrofit2.http.GET;

public interface HomeApi {


    //获取banner信息
    @GET("api/Img/getBannerImg")
    LiveData<BaseRespEntity<List<BannerEntity>>> getBanner();

    //获取系统消息
    @GET("api/SystemMsg/getSystemMsg")
    LiveData<BaseRespEntity<List<SyMsgEntity>>> getSyMsg();

    //获取新用户的推荐产品
    @GET("api/Product/getNewUserProcducts")
    LiveData<BaseRespEntity<List<ProductEntity>>> getNewUserProduct();

    @GET("api/Product/getProcducts")
    LiveData<BaseRespEntity<List<ProductEntity>>> getProduct();

}
