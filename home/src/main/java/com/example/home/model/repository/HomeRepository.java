package com.example.home.model.repository;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.common.app.BaseAppcation;
import com.example.common.async.CacheThreadPool;
import com.example.common.net.NetStateUtils;
import com.example.core.repository.Repository;
import com.example.home.entity.BannerEntity;
import com.example.home.entity.ProductEntity;
import com.example.home.entity.SyMsgEntity;
import com.example.home.model.database.HomeDBHelper;
import com.example.home.model.service.HomeLocalModel;
import com.example.home.model.service.HomeRemoteModel;
import com.example.net.protocol.BaseRespEntity;

import java.util.List;

public class HomeRepository extends Repository<HomeRemoteModel> {

    private HomeLocalModel homeLocalModel = null;

    public HomeRepository(LifecycleOwner owner) {
        super(owner);
        this.homeLocalModel = new HomeLocalModel();
    }

    @Override
    protected HomeRemoteModel createModel() {
        return new HomeRemoteModel();
    }

    LiveData<BaseRespEntity<List<BannerEntity>>> banner = null;

    public LiveData<BaseRespEntity<List<BannerEntity>>> getBanner() {

        if (NetStateUtils.isNetWorkAvailable(BaseAppcation.getAppContext())) {
            banner = mModel.getBanner();
            banner.observe(owner, new Observer<BaseRespEntity<List<BannerEntity>>>() {
                @Override
                public void onChanged(final BaseRespEntity<List<BannerEntity>> listBaseRespEntity) {
                    CacheThreadPool.getInstance().execute(new Runnable() {
                        @Override
                        public void run() {
                            HomeDBHelper.getInstance()
                                    .getDb()
                                    .homeDao()
                                    .insertBannerAll(listBaseRespEntity.getData());
                        }
                    });
                }
            });
        } else {
            final MutableLiveData<BaseRespEntity<List<BannerEntity>>> data = new MutableLiveData<>();
            CacheThreadPool.getInstance()
                    .execute(new Runnable() {
                        @Override
                        public void run() {
                            List<BannerEntity> list = homeLocalModel.getBanner();
                            data.postValue(new BaseRespEntity<List<BannerEntity>>(list));
                        }
                    });
            return data;
        }

        return banner;
    }

    LiveData<BaseRespEntity<List<SyMsgEntity>>> systemMsgs = null;

    //获取系统消息
    public LiveData<BaseRespEntity<List<SyMsgEntity>>> getSyMsg() {
        //网络可以情况加载网络数据
        if (NetStateUtils.isNetWorkAvailable(BaseAppcation.getAppContext())) {
            systemMsgs = mModel.getSyMsg();
            //将网络数据结果存储到本地sqlite数据库
            systemMsgs.observe(owner, new Observer<BaseRespEntity<List<SyMsgEntity>>>() {
                @Override
                public void onChanged(final BaseRespEntity<List<SyMsgEntity>> listBaseRespEntity) {
                    CacheThreadPool.getInstance().execute(new Runnable() {
                        @Override
                        public void run() {
                            HomeDBHelper.getInstance().getDb().homeDao().insertSyMsgAll(listBaseRespEntity.getData());
                        }
                    });
                }
            });
        } else {
            //从本地sqlite数据库提取缓存的数据
            final MutableLiveData<BaseRespEntity<List<SyMsgEntity>>> data = new MutableLiveData<>();
            CacheThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    List<SyMsgEntity> liveData = homeLocalModel.getSyMsg();
                    data.postValue(new BaseRespEntity<List<SyMsgEntity>>(liveData));
                }
            });

            return data;
        }

        return systemMsgs;
    }

    //获取新用户的推荐产品
    public LiveData<BaseRespEntity<List<ProductEntity>>> getNewUserProduct() {
        LiveData<BaseRespEntity<List<ProductEntity>>> newUserProduct = null;
        //网络可以情况加载网络数据
        if (NetStateUtils.isNetWorkAvailable(BaseAppcation.getAppContext())) {
            newUserProduct = mModel.getNewUserProduct();
            //将网络数据结果存储到本地sqlite数据库
            newUserProduct.observe(owner, new Observer<BaseRespEntity<List<ProductEntity>>>() {
                @Override
                public void onChanged(final BaseRespEntity<List<ProductEntity>> listBaseRespEntity) {
                    CacheThreadPool.getInstance().execute(new Runnable() {
                        @Override
                        public void run() {
                            HomeDBHelper.getInstance().getDb().homeDao().insertProductAll(listBaseRespEntity.getData());

                        }
                    });
                }
            });
        } else {
            //从本地sqlite数据库提取缓存的数据
            final MutableLiveData<BaseRespEntity<List<ProductEntity>>> data = new MutableLiveData<>();
            CacheThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    List<ProductEntity> liveData = homeLocalModel.getNewUserProduct();
                    data.postValue(new BaseRespEntity<List<ProductEntity>>(liveData));
                }
            });

            return data;
        }

        return newUserProduct;
    }

    public LiveData<BaseRespEntity<List<ProductEntity>>> getProduct() {
        LiveData<BaseRespEntity<List<ProductEntity>>> product = null;
        //网络可以情况加载网络数据
        if (NetStateUtils.isNetWorkAvailable(BaseAppcation.getAppContext())) {
            product = mModel.getProduct();
            //将网络数据结果存储到本地sqlite数据库
            product.observe(owner, new Observer<BaseRespEntity<List<ProductEntity>>>() {
                @Override
                public void onChanged(final BaseRespEntity<List<ProductEntity>> listBaseRespEntity) {
                    CacheThreadPool.getInstance().execute(new Runnable() {
                        @Override
                        public void run() {
                            HomeDBHelper.getInstance().getDb().homeDao().insertProductAll(listBaseRespEntity.getData());
                        }
                    });
                }
            });
        } else {
            //从本地sqlite数据库提取缓存的数据
            final MutableLiveData<BaseRespEntity<List<ProductEntity>>> data = new MutableLiveData<>();
            CacheThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    List<ProductEntity> liveData = homeLocalModel.getProduct();

                    data.postValue(new BaseRespEntity<List<ProductEntity>>(liveData));
                }
            });
            return data;
        }
        return product;
    }
}
