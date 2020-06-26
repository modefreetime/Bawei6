package com.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.usercenter.R;
import com.example.usercenter.databinding.ActivityMainBinding;
import com.example.usercenter.view.LoginActivity;
import com.example.usercenter.view.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setCommand(this);
    }

    public void regi(View view){
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void login(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }
}
