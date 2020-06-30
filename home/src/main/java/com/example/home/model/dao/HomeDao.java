package com.example.home.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.home.entity.BannerEntity;
import com.example.home.entity.ProductEntity;
import com.example.home.entity.SyMsgEntity;

import java.util.List;
@Dao
public interface HomeDao {


    @Query("select * from tb_banner")
     List<BannerEntity> getBannerAll();

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    void insertBannerAll(List<BannerEntity> bannerEntities);

    @Query("select * from tb_symsg")
    List<SyMsgEntity> getSyMsgAll();

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    void insertSyMsgAll(List<SyMsgEntity> msgEntities);


    @Query("select * from tb_product where isnew=1")
    List<ProductEntity> getNewUserProductAll();
    @Query("select * from tb_product where isnew=0")
    List<ProductEntity> getProductAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProductAll(List<ProductEntity> products);


}
