package com.karacamehmet.dinedashr.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.karacamehmet.dinedashr.data.entity.YemekListeleme;

@Database(entities = {YemekListeleme.class}, version = 1)
public abstract class YemekListelemeDB extends RoomDatabase {
    public abstract YemekListelemeDao getYemekListelemeDao();
}
