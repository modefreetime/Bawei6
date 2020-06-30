package com.example.usercenter.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.core.view.BaseActivity;
import com.example.core.viewmodel.BaseViewModel;
import com.example.net.protocol.BaseRespEntity;
import com.example.usercenter.R;
import com.example.usercenter.databinding.ActivityRegisterBinding;
import com.example.usercenter.entity.UserEntity;
import com.example.usercenter.viewmodel.UserViewModel;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding,UserViewModel> {

    @Override
    protected UserViewModel createVm() {
        return new UserViewModel(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initBinding() {
        binding= DataBindingUtil.setContentView(this, getLayoutId());
        binding.setVm(createVm());
        binding.setCommand(this);
    }

    public void getYZM(View view){
        UserViewModel vm = binding.getVm();
        LiveData<BaseRespEntity<String>> yzm = vm.yzm();
        yzm.observe(this, new Observer<BaseRespEntity<String>>() {
            @Override
            public void onChanged(BaseRespEntity<String> stringBaseRespEntity) {
                Notification.Builder builder = new Notification.Builder(RegisterActivity.this);
                builder.setSmallIcon(R.drawable.guangbo);
                builder.setContentTitle("验证码");
                builder.setContentText(stringBaseRespEntity.getData());
                NotificationManager service = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                service.notify(1,builder.build());
            }
        });
    }

    public void regi(View view){
        UserViewModel vm = binding.getVm();
        LiveData<BaseRespEntity<UserEntity>> regi = vm.regi();
        regi.observe(this, new Observer<BaseRespEntity<UserEntity>>() {
            @Override
            public void onChanged(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
                if(userEntityBaseRespEntity.getData()!=null){
                    showMsg(RegisterActivity.this,"注册成功");
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }else{
                    showMsg(RegisterActivity.this,"注册失败");
                }
            }
        });
    }
}
