package com.example.core.viewmodel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.example.core.repository.Repository;

import java.util.HashMap;
import java.util.Map;

public class BaseViewModel extends ViewModel implements LifecycleObserver {

    protected Map<String, Repository> repositoryMap;

    public BaseViewModel() {
        repositoryMap = new HashMap<>();
    }

    /**
     * 注册数据仓库
     *
     * @param key
     * @param value
     */
    protected void registerRepository(String key, Repository value) {
        if (repositoryMap.containsKey(key)) {
            return;
        }
        repositoryMap.put(key, value);
    }

    /**
     * 注销数据仓库
     * @param key
     */
    protected void removeRepository(String key) {
        if (repositoryMap.containsKey(key)) {
            repositoryMap.remove(key);
        }
    }

    /**
     * 获取具体的数据仓库
     * @param key
     * @param <SubRepository>
     * @return
     */
    protected <SubRepository extends Repository> SubRepository getRepository(String key){
        if(repositoryMap.containsKey(key)){
            return (SubRepository)repositoryMap.get(key);
        }
        return null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void disConnectOwner(){
        repositoryMap.clear();
        repositoryMap=null;
    }

}
