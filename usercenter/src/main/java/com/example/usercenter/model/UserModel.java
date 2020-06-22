package com.example.usercenter.model;

import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.common.utils.LogUtils;
import com.example.core.model.IModel;
import com.example.usercenter.entity.UserEntity;

/**
 * model层
 */
public class UserModel implements IModel {

    public LiveData<Boolean> login(UserEntity entity){
        LogUtils.d(entity.getUsername());
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        if(Looper.getMainLooper().getThread()==Thread.currentThread()){
            result.setValue(true);
        }else {
            result.postValue(true);
        }
        return result;
    }

}
