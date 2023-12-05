package com.karacamehmet.dinedashr.room;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import com.karacamehmet.dinedashr.data.entity.YemekListeleme;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface YemekListelemeDao {
    @Query("SELECT * FROm yemekListeleme")
    Single<List<YemekListeleme>> yemekListelemeYukle();

    @Update
    Completable yemekListelemeGuncelle(YemekListeleme yemekListeleme);
}
