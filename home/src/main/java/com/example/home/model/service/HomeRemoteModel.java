package com.example.home.model.service;

import androidx.lifecycle.LiveData;

import com.example.core.model.IModel;
import com.example.home.entity.BannerEntity;
import com.example.home.entity.ProductEntity;
import com.example.home.entity.SyMsgEntity;
import com.example.home.model.api.HomeApi;
import com.example.net.RetrofitFactory;
import com.example.net.protocol.BaseRespEntity;

import java.util.List;

/**
 * 远程数据
 */
public class HomeRemoteModel implements IModel {

    private final HomeApi homeApi;

    public HomeRemoteModel() {
        homeApi = RetrofitFactory.getInstance().create(HomeApi.class);
    }

    public LiveData<BaseRespEntity<List<BannerEntity>>> getBanner() {
        LiveData<BaseRespEntity<List<BannerEntity>>> banner = homeApi.getBanner();

        return banner;
    }

    //获取系统消息
    public LiveData<BaseRespEntity<List<SyMsgEntity>>> getSyMsg() {
        LiveData<BaseRespEntity<List<SyMsgEntity>>> syMsg = homeApi.getSyMsg();
        return syMsg;
    }

    //获取新用户的推荐产品
    public LiveData<BaseRespEntity<List<ProductEntity>>> getNewUserProduct() {
        LiveData<BaseRespEntity<List<ProductEntity>>> newUserProduct = homeApi.getNewUserProduct();
        return newUserProduct;
    }

    public LiveData<BaseRespEntity<List<ProductEntity>>> getProduct() {
        LiveData<BaseRespEntity<List<ProductEntity>>> product = homeApi.getProduct();
        return product;
    }

}
