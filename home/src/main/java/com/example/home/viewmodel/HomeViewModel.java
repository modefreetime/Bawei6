package com.example.home.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.core.viewmodel.BaseViewModel;
import com.example.home.entity.BannerEntity;
import com.example.home.entity.ProductEntity;
import com.example.home.entity.SyMsgEntity;
import com.example.home.model.repository.HomeRepository;
import com.example.net.protocol.BaseRespEntity;

import java.util.List;

public class HomeViewModel extends BaseViewModel {

    public HomeViewModel(LifecycleOwner _owner) {
        super(_owner);
        registerRepository(HomeRepository.class.getSimpleName(),new HomeRepository(_owner));
    }
    /**
     * 获取Banner实体信息
     */
    public LiveData<BaseRespEntity<List<BannerEntity>>> getBanner(){
        HomeRepository repository= super.getRepository(HomeRepository.class.getSimpleName());
        return repository.getBanner();
    }

    /**
     * 获取系统消息
     */
    public LiveData<BaseRespEntity<List<SyMsgEntity>>> getSystemMsgs(){
        HomeRepository repository= super.getRepository(HomeRepository.class.getSimpleName());
        return repository.getSyMsg();
    }

    /**
     * 获取新用户的推荐产品
     */
    public LiveData<BaseRespEntity<List<ProductEntity>>> getNewUserProduct(){

        HomeRepository repository= super.getRepository(HomeRepository.class.getSimpleName());
        return repository.getNewUserProduct();
    }

    /**
     * 获取推荐核心产品数据
     */
    public LiveData<BaseRespEntity<List<ProductEntity>>> getProduct(){
        HomeRepository repository= super.getRepository(HomeRepository.class.getSimpleName());
        return repository.getProduct();
    }

}
