package com.example.net;

import android.os.Build;
import android.text.TextUtils;

import com.example.net.common.Config;
import com.example.net.api.TokenApi;
import com.example.net.protocol.TokenRespEntity;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit工具类
 */
public class RetrofitFactory {
    private volatile static RetrofitFactory instance = null;
    private Retrofit retrofit;

    private RetrofitFactory() {
        initRetrofit();
    }

    public static RetrofitFactory getInstance() {
        if (instance == null) {
            synchronized (RetrofitFactory.class) {
                if (instance == null) {
                    instance = new RetrofitFactory();
                }
            }
        }
        return instance;
    }


    /**
     * 初始化Retrofit
     */
    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(createOkhttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 创建okhttp客户端
     *
     * @return
     */
    private OkHttpClient createOkhttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(Config.TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(Config.TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(Config.TIME_OUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(createNetWorkInterceptor())
                .addInterceptor(tokenInterceptor())
                .addInterceptor(headerParamsInterceptor())
                .addInterceptor(changeBaseUrlInterceptor())
                .build();


        return client;
    }

    private Interceptor changeBaseUrlInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //拿到request请求对象
                Request request = chain.request();
                //从request请求中拿到url
                HttpUrl oldUrl = request.url();
                //创建一个新的request builder
                Request.Builder newBuilder = request.newBuilder();
                //从当前请求中获取对应key的请求头信息
                List<String> header = request.headers(Config.NEW_URLHEADER_KEY);
                //如果包含该请求
                if (header != null && header.size() > 0) {
                    //移除http请求的头信息,因为他只在程序内有效
                    newBuilder.removeHeader(Config.NEW_URLHEADER_KEY);
                    //获取第一个头信息
                    String headersValue = header.get(0);
                    HttpUrl newBaseUrl = null;
                    //如果存在知道value
                    if (headersValue.equals(Config.NEW_URLHEADER_VALUE)) {
                        //将baseurl更换成testserverurl
                        newBaseUrl = HttpUrl.parse(Config.TEST_SERVER_URL);
                    } else {
                        //没有指定的value
                        newBaseUrl = oldUrl;
                    }
                    //构建一个新的httpurl
                    HttpUrl newUrl = oldUrl.newBuilder()
                            .scheme(newBaseUrl.scheme())
                            .host(newBaseUrl.host())
                            .port(newBaseUrl.port())
                            .build();
                    //构建一个新的request对象
                    Request newBuild = newBuilder.url(newUrl).build();
                    //传递到下游节点
                    return chain.proceed(newBuild);

                }
                //不包含直接执行原请求
                return chain.proceed(request);
            }
        };
        return interceptor;
    }

    /**
     * 头信息兰拦截器
     *
     * @return
     */
    private Interceptor headerParamsInterceptor() {
        Interceptor interceptor = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request newRequest = request.newBuilder()
                        .addHeader("v0", Build.MANUFACTURER)
                        .addHeader("v1", Build.MODEL)
                        .addHeader("v2", Build.TYPE)
                        .addHeader("v3", Build.VERSION.SDK_INT + "")
                        .build();
                return chain.proceed(newRequest);
            }
        };
        return interceptor;
    }

    /**
     * token拦截器
     *
     * @return
     */
    private Interceptor tokenInterceptor() {
        Interceptor interceptor = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);

                if (checkHttpCode401(response)) {
                    String token = requestToken();
                    if (TextUtils.isEmpty(token)) {
                        return response;
                    }
                    Request.Builder builder = request.newBuilder().addHeader("Authorization", "bearer " + token);
                    Request newRequest = builder.build();
                    return chain.proceed(newRequest);
                }


                return response;
            }
        };
        return interceptor;
    }

    private String requestToken() {
        TokenApi tokenApi = create(TokenApi.class);
        Call<TokenRespEntity> tokenService = tokenApi.getToken("password", Config.AUTH_CODE, "");
        try {
            retrofit2.Response<TokenRespEntity> result = tokenService.execute();
            if (result != null && result.body() != null) {
                return result.body().getAccess_token();
            }
        } catch (IOException e) {

        }
        return "";
    }

    private boolean checkHttpCode401(Response proceed) {
        if (proceed == null) {
            return false;
        }
        if (proceed.code() == 401) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 网络请求的拦截器
     *
     * @return
     */
    private Interceptor createNetWorkInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }


}
