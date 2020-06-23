package com.example.usercenter;


import com.example.common.app.BaseAppcation;
import com.example.storage.core.StorageManager;
import com.example.storage.core.StorageType;

public class UserCenterApp extends BaseAppcation {
    @Override
    protected void initOtherConfig() {
        //初始化存储位置为SP
        StorageManager.getInstance().init(StorageType.SP);
    }
}