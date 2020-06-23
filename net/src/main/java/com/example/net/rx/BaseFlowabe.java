package com.example.net.rx;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BaseFlowabe {
    public static <T> void doObservable(Flowable<T> flowable, BaseSubscriber<T> f) {
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(f);
    }
}
