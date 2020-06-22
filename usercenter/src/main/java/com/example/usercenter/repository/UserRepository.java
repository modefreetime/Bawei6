package com.example.usercenter.repository;

import androidx.lifecycle.LiveData;

import com.example.core.repository.Repository;
import com.example.usercenter.entity.UserEntity;
import com.example.usercenter.model.UserModel;

/**
 * 用户模块数据仓库层
 */
public class UserRepository extends Repository<UserModel> {
    @Override
    protected UserModel createModel() {
        return new UserModel();
    }

    public LiveData<Boolean> login(UserEntity entity){
      return mModel.login(entity);
    }

}
