package com.example.home.model.database;

import androidx.room.Room;

import com.example.common.app.BaseAppcation;

public class HomeDBHelper {

    private final static String DB_NAME="home_db";
    private final HomeDataBase homeDataBase;

    private HomeDBHelper() {
        homeDataBase = Room.databaseBuilder(BaseAppcation.getAppContext(), HomeDataBase.class, DB_NAME).build();
    }

    private static HomeDBHelper helper = new HomeDBHelper();

    public static HomeDBHelper getInstance(){
        return helper;
    }

    public HomeDataBase getDb(){
        return homeDataBase;
    }

    public void DBClose(){
        if(homeDataBase!=null && homeDataBase.isOpen()){
            homeDataBase.close();
        }
    }

}
