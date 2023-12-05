package com.karacamehmet.dinedashr.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "yemekListeleme")
public class YemekListeleme implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "yemekListelemeId")
    @NonNull
    private int yemekListelemeId;
    @ColumnInfo(name = "yemekListelemeTur")
    @NonNull
    private int yemekListelemeTur;
    @ColumnInfo(name = "yemekListelemeFavori")
    @NonNull
    private int yemekListelemeFavori;

    public YemekListeleme() {
    }

    public YemekListeleme(int yemekListelemeId, int yemekListelemeTur, int yemekListelemeFavori) {
        this.yemekListelemeId = yemekListelemeId;
        this.yemekListelemeTur = yemekListelemeTur;
        this.yemekListelemeFavori = yemekListelemeFavori;
    }

    public int getYemekListelemeId() {
        return yemekListelemeId;
    }

    public void setYemekListelemeId(int yemekListelemeId) {
        this.yemekListelemeId = yemekListelemeId;
    }

    public int getYemekListelemeTur() {
        return yemekListelemeTur;
    }

    public void setYemekListelemeTur(int yemekListelemeTur) {
        this.yemekListelemeTur = yemekListelemeTur;
    }

    public int getYemekListelemeFavori() {
        return yemekListelemeFavori;
    }

    public void setYemekListelemeFavori(int yemekListelemeFavori) {
        this.yemekListelemeFavori = yemekListelemeFavori;
    }
}
