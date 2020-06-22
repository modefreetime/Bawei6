package com.example.usercenter.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.core.view.BaseActivity;
import com.example.usercenter.R;
import com.example.usercenter.databinding.ActivityLoginBinding;
import com.example.usercenter.viewmodel.UserViewModel;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, UserViewModel> {


    public void loginOnclick(View view){
        String username = vm.userEntity.getUsername();
        String pwd = vm.userEntity.getPwd();
        if(TextUtils.isEmpty(username)){
            showMsg(this,getResources().getString(R.string.user_input_username));
            return;
        }if(TextUtils.isEmpty(pwd)){
            showMsg(this,getResources().getString(R.string.user_input_pwd));
            return;
        }
        LiveData<Boolean> result = vm.login();
        if(result.getValue()){
            showMsg(this,getResources().getString(R.string.user_login_success));

        }else{
            showMsg(this,getResources().getString(R.string.user_login_failed));

        }
        result.observe(LoginActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

            }
        });
    }

    @Override
    protected UserViewModel createVm() {
        return new UserViewModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initBinding() {
        binding.setVm(vm);
        binding.setCommand(this);
    }


}
