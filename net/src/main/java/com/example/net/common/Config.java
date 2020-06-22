package com.example.net.common;

import com.example.net.BuildConfig;

public class Config {
    /**
     * 验证码 用于后台请求TOken使用
     */
    public final static String AUTH_CODE = "b51421a216d1521171581841d11001e213119219b15f1f41";
    /**
     * 网络请求超时时长
     */
    public final static int TIME_OUT = 10;
    //网络请求的基础地址
    public final static String BASE_URL = BuildConfig.baseUrl;

    //测试服务器
    public final static String TEST_SERVER_URL = BuildConfig.testServerUrl;
    public final static String NEW_URLHEADER_KEY = "newUrl";
    public final static String NEW_URLHEADER_VALUE = TEST_SERVER_URL;
}
