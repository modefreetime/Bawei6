package com.example.usercenter.repository;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.core.repository.Repository;
import com.example.net.protocol.BaseRespEntity;
import com.example.usercenter.entity.UserEntity;
import com.example.usercenter.model.UserModel;

/**
 * 用户模块数据仓库层
 */
public class UserRepository extends Repository<UserModel> {
    public UserRepository(LifecycleOwner _owner) {
        super(_owner);
    }

    @Override
    protected UserModel createModel() {
        return new UserModel();
    }

    public LiveData<BaseRespEntity<UserEntity>> login(UserEntity entity){
      return mModel.login(entity);
    }

    public LiveData<BaseRespEntity<UserEntity>> regi(UserEntity entity){
        return mModel.regi(entity);
    }

    public LiveData<BaseRespEntity<String>> yzm(String phone){
        return mModel.yzm(phone);
    }

    public LiveData<BaseRespEntity<Boolean>> update(String userid,String pwd){
        return mModel.update(userid,pwd);
    }

}
