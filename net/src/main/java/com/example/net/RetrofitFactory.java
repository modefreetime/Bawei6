package com.example.net;

import android.text.TextUtils;

import com.example.net.common.Config;
import com.example.net.common.TokenApi;
import com.example.net.common.TokenRespEntity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

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
                .connectTimeout(Config.TIME_OUT,TimeUnit.SECONDS)
                .writeTimeout(Config.TIME_OUT,TimeUnit.SECONDS)
                .addNetworkInterceptor(createNetWorkInterceptor())
                .addInterceptor(tokenInterceptor())
                .addInterceptor(headerParamsInterceptor())
                .build();


        return client;
    }

    /**
     * 头信息兰拦截器
     * @return
     */
    private Interceptor headerParamsInterceptor() {

        return null;
    }

    /**
     * token拦截器
     * @return
     */
    private Interceptor tokenInterceptor() {
        Interceptor interceptor = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response proceed = chain.proceed(request);

                if(checkHttpCode401(proceed)){
                   String token =requestToken();
                   if(TextUtils.isEmpty(token)){
                       return proceed;
                   }
                    Request.Builder builder = request.newBuilder().addHeader("Authorization", "bearer "+token);
                    Request newRequest = builder.build();
                    return chain.proceed(newRequest);
                }


                return null;
            }
        };
        return interceptor;
    }

    private String requestToken() {
       TokenApi tokenApi= create(TokenApi.class);
       Call<TokenRespEntity> tokenService= tokenApi.getToken("password",Config.AUTH_CODE,"");
       try {
          retrofit2.Response<TokenRespEntity> result= tokenService.execute();
          if(result!=null && result.body()!=null){
              return result.body().getAccess_token();
          }
       }catch (IOException e){

       }
       return "";
    }

    private boolean checkHttpCode401(Response proceed) {
        if(proceed==null){
            return false;
        }
        if(proceed.code()==401){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 网络请求的拦截器
     * @return
     */
    private Interceptor createNetWorkInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    public <T>T create(Class<T> service){
        return retrofit.create(service);
    }


}
