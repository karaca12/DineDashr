package com.karacamehmet.dinedashr.di;


import android.content.Context;

import androidx.room.Room;

import com.karacamehmet.dinedashr.data.repo.SepetYemeklerDaoRepository;
import com.karacamehmet.dinedashr.data.repo.YemeklerDaoRepository;
import com.karacamehmet.dinedashr.retrofit.ApiUtils;
import com.karacamehmet.dinedashr.retrofit.SepetYemeklerDao;
import com.karacamehmet.dinedashr.retrofit.YemeklerDao;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public YemeklerDaoRepository provideYemeklerDaoRepository(YemeklerDao yemeklerDao) {
        return new YemeklerDaoRepository(yemeklerDao);
    }

    @Provides
    @Singleton
    public YemeklerDao provideYemeklerDao() {
        return ApiUtils.getYemeklerDao();
    }

    @Provides
    @Singleton
    public SepetYemeklerDaoRepository provideSepetYemeklerDaoRepository(SepetYemeklerDao sepetYemeklerDao) {
        return new SepetYemeklerDaoRepository(sepetYemeklerDao);
    }

    @Provides
    @Singleton
    public SepetYemeklerDao provideSepetYemeklerDao() {
        return ApiUtils.getSepetYemeklerDao();
    }

}
