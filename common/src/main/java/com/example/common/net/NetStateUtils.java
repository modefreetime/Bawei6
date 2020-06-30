package com.example.common.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class NetStateUtils {

    /**
     * 网络是否可用
     * @param context
     * @return
     */
    public static boolean isNetWorkAvailable(Context context){
//        if(context!=null){
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//            if(networkInfo==null){
//                return false;
//            }
//            return networkInfo.isAvailable();
//        }
//        return false;

        NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

        if (networkInfo != null && networkInfo.length > 0)
        {
            for (int i = 0; i < networkInfo.length; i++)
            {
                System.out.println(i + "===状态===" + networkInfo[i].getState());
                System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                // 判断当前网络状态是否为连接状态
                if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        return false;

    }

    public static boolean isConnected(){

        URL url;
        InputStream stream = null;
        try {
            url= new URL("https://www.baidu.com");
            stream= url.openStream();
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (stream!=null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return false;
    }

}
