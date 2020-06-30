package com.example.usercenter.view;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.view.View;

import com.example.core.view.BaseActivity;
import com.example.net.protocol.BaseRespEntity;
import com.example.usercenter.R;
import com.example.usercenter.databinding.ActivityUpDateBinding;
import com.example.usercenter.viewmodel.UserViewModel;
import com.example.wiget.TitleBar;

public class UpDateActivity extends BaseActivity<ActivityUpDateBinding, UserViewModel> {

    private TitleBar titleBar;
    private LiveData<BaseRespEntity<Boolean>> update;

    @Override
    protected UserViewModel createVm() {
        return new UserViewModel(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_up_date;
    }

    @Override
    protected void initBinding() {
        binding= DataBindingUtil.setContentView(this,R.layout.activity_up_date);
        binding.setCommand(this);
        binding.setVm(new UserViewModel(this));
        titleBar=findViewById(R.id.tb_bar);
        titleBar.setTitleBarClickListener(new TitleBar.TitleBarClickListener() {
            @Override
            public void leftClick(View view) {

            }

            @Override
            public void rightClick(View view) {
                UserViewModel vm = binding.getVm();
                update = vm.update();
                update.observe(UpDateActivity.this, new Observer<BaseRespEntity<Boolean>>() {
                    @Override
                    public void onChanged(BaseRespEntity<Boolean> booleanBaseRespEntity) {
                        if(booleanBaseRespEntity.getData()){
                            showMsg(UpDateActivity.this,"修改成功");
                            startActivity(new Intent(UpDateActivity.this,LoginActivity.class));
                        }
                    }
                });
            }
        });
    }

    public void getYzm(View view){
        UserViewModel vm = binding.getVm();
        LiveData<BaseRespEntity<String>> yzm = vm.updateYzm();
        yzm.observe(this, new Observer<BaseRespEntity<String>>() {
            @Override
            public void onChanged(BaseRespEntity<String> stringBaseRespEntity) {
                Notification.Builder builder = new Notification.Builder(UpDateActivity.this);
                builder.setSmallIcon(R.drawable.guangbo);
                builder.setContentTitle("验证码");
                builder.setContentText(stringBaseRespEntity.getData());
                NotificationManager service = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                service.notify(2,builder.build());
            }
        });
    }
}
