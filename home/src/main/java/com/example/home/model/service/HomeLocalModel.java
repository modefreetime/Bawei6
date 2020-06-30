package com.example.home.model.service;

import androidx.lifecycle.LiveData;

import com.example.core.model.IModel;
import com.example.home.entity.BannerEntity;
import com.example.home.entity.ProductEntity;
import com.example.home.entity.SyMsgEntity;
import com.example.home.model.database.HomeDBHelper;
import com.example.net.protocol.BaseRespEntity;

import java.util.List;

/**
 * 本地数据
 */
public class HomeLocalModel implements IModel {

    public List<BannerEntity> getBanner() {
        List<BannerEntity> banner = HomeDBHelper
                .getInstance()
                .getDb()
                .homeDao()
                .getBannerAll();

        return banner;
    }

    public void insertBannerAll(List<BannerEntity> bannerEntities) {
        HomeDBHelper.getInstance()
                .getDb()
                .homeDao()
                .insertBannerAll(bannerEntities);
    }

    //获取系统消息
    public List<SyMsgEntity> getSyMsg() {
        List<SyMsgEntity> syMsg = HomeDBHelper
                .getInstance()
                .getDb()
                .homeDao().getSyMsgAll();
        return syMsg;
    }

    public void insertSyMsgAll(List<SyMsgEntity> syMsgEntities) {
        HomeDBHelper.getInstance()
                .getDb()
                .homeDao()
                .insertSyMsgAll(syMsgEntities);
    }

    //获取新用户的推荐产品
    public List<ProductEntity> getNewUserProduct() {
        List<ProductEntity> newUserProduct = HomeDBHelper
                .getInstance().getDb()
                .homeDao()
                .getNewUserProductAll();

        ;
        return newUserProduct;
    }

    public List<ProductEntity> getProduct() {
        List<ProductEntity> product = HomeDBHelper
                .getInstance()
                .getDb()
                .homeDao()
                .getProductAll();
        return product;
    }
    public void insertProductAll(List<ProductEntity> productEntities) {
        HomeDBHelper.getInstance()
                .getDb()
                .homeDao()
                .insertProductAll(productEntities);
    }
}
