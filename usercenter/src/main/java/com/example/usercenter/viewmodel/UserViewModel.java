package com.example.usercenter.viewmodel;

import androidx.lifecycle.LiveData;

import com.example.core.viewmodel.BaseViewModel;
import com.example.net.protocol.BaseRespEntity;
import com.example.usercenter.entity.UserEntity;
import com.example.usercenter.repository.UserRepository;

public class UserViewModel extends BaseViewModel {

    public UserEntity userEntity = new UserEntity();

    public UserViewModel(){
        registerRepository(UserRepository.class.getSimpleName(),new UserRepository());
    }

    public LiveData<BaseRespEntity<UserEntity>> login(){
        UserRepository userRepository=getRepository(UserRepository.class.getSimpleName());
        return userRepository.login(userEntity);
    }
}
