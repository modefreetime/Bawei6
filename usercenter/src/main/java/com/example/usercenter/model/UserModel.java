package com.example.usercenter.model;

import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.common.utils.LogUtils;
import com.example.core.model.IModel;
import com.example.net.RetrofitFactory;
import com.example.net.protocol.BaseRespEntity;
import com.example.net.rx.BaseObservable;
import com.example.net.rx.BaseObserver;
import com.example.usercenter.entity.UserEntity;
import com.example.usercenter.model.api.UserApi;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * model层
 */
public class UserModel implements IModel {

    public LiveData<BaseRespEntity<UserEntity>> login(final UserEntity entity) {
//        LogUtils.d(entity.getUsername());
//        if(Looper.getMainLooper().getThread()==Thread.currentThread()){
//            result.setValue(true);
//        }else {
//            result.postValue(true);
//        }
        final MutableLiveData<BaseRespEntity<UserEntity>> result = new MutableLiveData<>();


        Flowable<BaseRespEntity<UserEntity>> flowable = Flowable.create(new FlowableOnSubscribe<BaseRespEntity<UserEntity>>() {
            @Override
            public void subscribe(final FlowableEmitter<BaseRespEntity<UserEntity>> emitter) throws Exception {
                UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);
                Call<BaseRespEntity<UserEntity>> call = userApi.login(entity);
                call.enqueue(new Callback<BaseRespEntity<UserEntity>>() {
                    @Override
                    public void onResponse(Call<BaseRespEntity<UserEntity>> call, Response<BaseRespEntity<UserEntity>> response) {
                        if (response.body().getCode() == -1) {
                            emitter.onError(new RuntimeException("用户登录失败"));
                            return;
                        }
                        emitter.onNext(response.body());
                        emitter.onComplete();
                    }

                    @Override
                    public void onFailure(Call<BaseRespEntity<UserEntity>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });
            }
        }, BackpressureStrategy.LATEST);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseRespEntity<UserEntity>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
                        result.postValue(userEntityBaseRespEntity);
                    }

                    @Override
                    public void onError(Throwable t) {
                        result.postValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


//        UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);
//        Call<BaseRespEntity<UserEntity>> call = userApi.login(entity);
//        call.enqueue(new Callback<BaseRespEntity<UserEntity>>() {
//            @Override
//            public void onResponse(Call<BaseRespEntity<UserEntity>> call, Response<BaseRespEntity<UserEntity>> response) {
//                result.postValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<BaseRespEntity<UserEntity>> call, Throwable t) {
//                result.postValue(null);
//                LogUtils.d(t.getMessage());
//            }
//        });

//        Observable<BaseRespEntity<UserEntity>> observable = Observable.create(new ObservableOnSubscribe<BaseRespEntity<UserEntity>>() {
//            @Override
//            public void subscribe(final ObservableEmitter<BaseRespEntity<UserEntity>> emitter) throws Exception {
//                UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);
//                Call<BaseRespEntity<UserEntity>> call = userApi.login(entity);
//                call.enqueue(new Callback<BaseRespEntity<UserEntity>>() {
//                    @Override
//                    public void onResponse(Call<BaseRespEntity<UserEntity>> call, Response<BaseRespEntity<UserEntity>> response) {
//                        if (response.body().getCode() == -1) {
//                            emitter.onError(new RuntimeException("用户登录失败"));
//                            return;
//                        }
//                        emitter.onNext(response.body());
//                        emitter.onComplete();
//                    }
//
//                    @Override
//                    public void onFailure(Call<BaseRespEntity<UserEntity>> call, Throwable t) {
//                        emitter.onError(t);
//                    }
//                });
//            }
//        });
//        BaseObservable.doObservable(observable,new BaseObserver<BaseRespEntity<UserEntity>>(){
//            @Override
//            public void onNext(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
//                super.onNext(userEntityBaseRespEntity);
//                result.postValue(userEntityBaseRespEntity);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                result.postValue(null);
//            }
//        });

        return result;
    }

}
