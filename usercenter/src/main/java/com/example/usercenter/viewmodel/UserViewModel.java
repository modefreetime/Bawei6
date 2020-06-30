package com.example.usercenter.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.core.viewmodel.BaseViewModel;
import com.example.net.protocol.BaseRespEntity;
import com.example.usercenter.entity.UserEntity;
import com.example.usercenter.repository.UserRepository;

public class UserViewModel extends BaseViewModel {

    public UserEntity userEntity = new UserEntity();
    public UserEntity regiEntity = new UserEntity();
    public UserEntity updateEntity = new UserEntity();

    public UserViewModel(LifecycleOwner owner){
        super(owner);
        registerRepository(UserRepository.class.getSimpleName(),new UserRepository(super.owner));
    }

    public LiveData<BaseRespEntity<UserEntity>> login(){
        UserRepository userRepository=getRepository(UserRepository.class.getSimpleName());
        return userRepository.login(userEntity);
    }
    public LiveData<BaseRespEntity<UserEntity>> regi(){
        UserRepository userRepository=getRepository(UserRepository.class.getSimpleName());
        return userRepository.regi(regiEntity);
    }

    public LiveData<BaseRespEntity<String>> yzm(){
        UserRepository userRepository=getRepository(UserRepository.class.getSimpleName());
        return userRepository.yzm(regiEntity.getUsername());
    }
    public LiveData<BaseRespEntity<String>> updateYzm(){
        UserRepository userRepository=getRepository(UserRepository.class.getSimpleName());
        return userRepository.yzm(updateEntity.getUsername());
    }
    public LiveData<BaseRespEntity<Boolean>> update(){
        UserRepository userRepository=getRepository(UserRepository.class.getSimpleName());
        return userRepository.update(updateEntity.getUsername(),updateEntity.getPwd());
    }

}
