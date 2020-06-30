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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.example.common.app.BaseAppcation;
import com.example.core.view.BaseFragment;
import com.example.home.R;
import com.example.home.databinding.HomeLayoutBinding;
import com.example.home.entity.BannerEntity;
import com.example.home.entity.ProductEntity;
import com.example.home.entity.SyMsgEntity;
import com.example.home.viewmodel.HomeViewModel;
import com.example.net.protocol.BaseRespEntity;
import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
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
    private SmartRefreshLayout smrl;

    @Override
    protected HomeViewModel createVm() {
        return new HomeViewModel(this);
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
        vf_main = view.findViewById(R.id.vf_main);
        smrl = view.findViewById(R.id.smrl_home);
    }

    @Override
    protected void initData() {
        initBannerData();
        initVFData();
        initNewProduct();
        vf_main.setFlipInterval(2000);
        vf_main.startFlipping();
        smrl.setRefreshHeader(new BezierRadarHeader(getActivity()));
        smrl.setRefreshFooter(new BallPulseFooter(getActivity()));
        smrl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        smrl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    private void initNewProduct() {
        final TextView pro_name = view.findViewById(R.id.tv_product_name);
        final TextView pro_num = view.findViewById(R.id.tv_product_num);
        final TextView pro_amount = view.findViewById(R.id.tv_product_amount);
        final TextView pro_day = view.findViewById(R.id.tv_product_day);
        LiveData<BaseRespEntity<List<ProductEntity>>> product = vm.getNewUserProduct();
        product.observe(getActivity(), new Observer<BaseRespEntity<List<ProductEntity>>>() {
            @Override
            public void onChanged(BaseRespEntity<List<ProductEntity>> listBaseRespEntity) {
                if(listBaseRespEntity !=null && listBaseRespEntity.getData()!=null){
                    List<ProductEntity> data = listBaseRespEntity.getData();
                    pro_name.setText(data.get(0).getProductname());
                    pro_num.setText(data.get(0).getYearrate()*100+"%");
                    pro_amount.setText("起投金额:"+data.get(0).getMinbugamount()+"元");
                    pro_day.setText("投资期限:"+data.get(0).getLockdays()+"天");
                }
            }
        });
    }

    private void initVFData() {
        LiveData<BaseRespEntity<List<SyMsgEntity>>> msgs = vm.getSystemMsgs();
        msgs.observe(getActivity(), new Observer<BaseRespEntity<List<SyMsgEntity>>>() {
            @Override
            public void onChanged(BaseRespEntity<List<SyMsgEntity>> listBaseRespEntity) {
                if (listBaseRespEntity != null && listBaseRespEntity.getData() != null) {
                    List<SyMsgEntity> data = listBaseRespEntity.getData();
                    for (SyMsgEntity item : data) {
                        View view = getLayoutInflater().inflate(R.layout.item_viewflipper, null);
                        TextView tv = view.findViewById(R.id.tv_item_flipper);
                        tv.setText(item.getMsg());
                        vf_main.addView(view);
                    }
                    vf_main.setFlipInterval(2000);
                    vf_main.startFlipping();
                }
            }
        });
    }

    private void initBannerData() {
        final LiveData<BaseRespEntity<List<BannerEntity>>> banner = vm.getBanner();
        banner.observe(getActivity(), new Observer<BaseRespEntity<List<BannerEntity>>>() {
            @Override
            public void onChanged(BaseRespEntity<List<BannerEntity>> listBaseRespEntity) {
                if (listBaseRespEntity != null && listBaseRespEntity.getData() != null) {
                    List<String> imgs = new ArrayList<>();
                    List<String> titles = new ArrayList<>();
                    for (BannerEntity entity :
                            listBaseRespEntity.getData()) {
                        imgs.add(entity.getImgurl());
                        titles.add(entity.getDesc());
                    }
                    bindBanner(imgs, titles);
                }
            }
        });
    }

    private void bindBanner(List<String> imgs, List<String> titles) {
        banner_home_main.setImages(imgs);
        banner_home_main.setBannerTitles(titles);
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
    }

    @Override
    protected void initEvent() {

    }

    @BindingAdapter("imgSrc")
    public static void imgPath(ImageView view, String path) {
        Glide.with(BaseAppcation.getAppContext())
                .load(path)
                .into(view);
    }

}
