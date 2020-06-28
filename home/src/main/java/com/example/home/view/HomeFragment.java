package com.example.home.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;


import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import com.bumptech.glide.Glide;
import com.example.common.app.BaseAppcation;
import com.example.core.view.BaseFragment;
import com.example.home.R;
import com.example.home.databinding.HomeLayoutBinding;
import com.example.home.viewmodel.HomeViewModel;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<HomeLayoutBinding, HomeViewModel> {

    private Banner banner_home_main;
    private ViewFlipper vf_main;
    public ObservableField<String> imagePath = new ObservableField<>();
    @Override
    protected HomeViewModel createVm() {
        return new HomeViewModel();
    }

    @Override
    protected void initBinding() {
        imagePath.set("http://hbimg.b0.upaiyun.com/8a75ab36c175489634b6c8621eea02fd8c83bb82c3869-Waz6eO_fw658");
        binding.setMine(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_layout;
    }

    @Override
    protected void initView() {
        banner_home_main = view.findViewById(R.id.banner_home_main);
        vf_main=view.findViewById(R.id.vf_main);
    }

    @Override
    protected void initData() {
        List<String> imgPath=new ArrayList<>();
        imgPath.add("http://hbimg.b0.upaiyun.com/0cdfedffcedb13445e4def3f2d6891bb32cb03de828b-m2zK4U_fw658");
        imgPath.add("http://hbimg.b0.upaiyun.com/8a75ab36c175489634b6c8621eea02fd8c83bb82c3869-Waz6eO_fw658");
        imgPath.add("http://hbimg.b0.upaiyun.com/a2a321fb4e128e2327674fee6c3be76bb7d6f70929ca3-IVhr33_fw658");
        imgPath.add("http://hbimg.b0.upaiyun.com/861f92e7514b297b0cd5833b3ffb52f8df37b4ec218f8-BmVyhw_fw658");

        ArrayList<String> titleList = new ArrayList<>();
        titleList.add("金融1");
        titleList.add("金融2");
        titleList.add("金融3");
        titleList.add("金融4");
        banner_home_main.setImages(imgPath);
        banner_home_main.setBannerTitles(titleList);
        banner_home_main.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner_home_main.isAutoPlay(true);
        banner_home_main.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner_home_main.setBannerAnimation(Transformer.Default);
        banner_home_main.setDelayTime(2000);
        banner_home_main.start();

        for (int i = 0; i < titleList.size(); i++) {
            View view = getLayoutInflater().inflate(R.layout.item_viewflipper, null);
            TextView tv = view.findViewById(R.id.tv_item_flipper);
            tv.setText(titleList.get(i));
            vf_main.addView(view);
        }
        vf_main.setFlipInterval(2000);
        vf_main.startFlipping();
    }

    @Override
    protected void initEvent() {

    }

    @BindingAdapter("imgSrc")
    public static void imgPath(ImageView view,String path){
        Glide.with(BaseAppcation.getAppContext())
                .load(path)
                .into(view);
    }

}
