package com.example.usercenter.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.common.utils.LogUtils;
import com.example.core.view.BaseActivity;
import com.example.net.protocol.BaseRespEntity;
import com.example.usercenter.R;
import com.example.usercenter.databinding.ActivityLoginBinding;
import com.example.usercenter.entity.UserEntity;
import com.example.usercenter.model.api.UserApi;
import com.example.usercenter.viewmodel.UserViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, UserViewModel> {


    public void loginOnclick(View view) {
        String username = vm.userEntity.getUsername();
        String pwd = vm.userEntity.getPwd();
        if (TextUtils.isEmpty(username)) {
            showMsg(this, getResources().getString(R.string.user_input_username));
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            showMsg(this, getResources().getString(R.string.user_input_pwd));
            return;
        }
        LiveData<BaseRespEntity<UserEntity>> result = vm.login();
        result.observe(this, new Observer<BaseRespEntity<UserEntity>>() {
            @Override
            public void onChanged(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
                if (userEntityBaseRespEntity != null) {
                    if(userEntityBaseRespEntity.getData()!=null) {
                        showMsg(LoginActivity.this, "成功");
                    }else{
                        showMsg(LoginActivity.this, "失败");
                    }
                } else {
                    showMsg(LoginActivity.this, "失败");
                }
            }
        });
    }

    @Override
    protected UserViewModel createVm() {
        return new UserViewModel(this);
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

    public void rememberPwdOnclick(View view){
        ((CheckBox)view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                showMsg(LoginActivity.this,b+"");
            }
        });
    }

    public void forgetpwd(View view){
        startActivity(new Intent(this,UpDateActivity.class));
    }


}
