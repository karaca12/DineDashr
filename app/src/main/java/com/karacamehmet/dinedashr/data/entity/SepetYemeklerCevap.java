package com.karacamehmet.dinedashr.data.entity;

import java.util.List;

public class SepetYemeklerCevap {
    private List<SepetYemekler> sepet_yemekler;
    private int success;

    public SepetYemeklerCevap() {
    }

    public SepetYemeklerCevap(List<SepetYemekler> sepet_yemekler, int success) {
        this.sepet_yemekler = sepet_yemekler;
        this.success = success;
    }

    public List<SepetYemekler> getSepet_yemekler() {
        return sepet_yemekler;
    }

    public void setSepet_yemekler(List<SepetYemekler> sepet_yemekler) {
        this.sepet_yemekler = sepet_yemekler;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
