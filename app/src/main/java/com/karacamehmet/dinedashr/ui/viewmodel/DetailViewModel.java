package com.karacamehmet.dinedashr.ui.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;

import com.karacamehmet.dinedashr.data.repo.SepetYemeklerDaoRepository;
import com.karacamehmet.dinedashr.data.repo.YemeklerDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetailViewModel extends ViewModel {
    public YemeklerDaoRepository yemeklerDaoRepository;
    public SepetYemeklerDaoRepository sepetYemeklerDaoRepository;
    private SharedPreferences sharedPreferences;


    @Inject
    public DetailViewModel(YemeklerDaoRepository yemeklerDaoRepository,
                           SepetYemeklerDaoRepository sepetYemeklerDaoRepository,
                           Application application) {
        this.yemeklerDaoRepository = yemeklerDaoRepository;
        this.sepetYemeklerDaoRepository = sepetYemeklerDaoRepository;
        sharedPreferences = application.getSharedPreferences("kullanici_adi", Context.MODE_PRIVATE);
        sepettekiYemekleriYukle(sharedPreferences.getString("kullanici_adi", ""));
    }


    public void sepeteYemekEkle(String yemek_adi, String yemek_resim_adi,
                                int yemek_fiyat, int yemek_siparis_adet, String kullanici_adi) {
        sepetYemeklerDaoRepository.sepeteYemekEkleKontrol(yemek_adi, yemek_resim_adi, yemek_fiyat,
                yemek_siparis_adet, kullanici_adi);
    }

    public void sepettekiYemekleriYukle(String kullanici_adi) {
        sepetYemeklerDaoRepository.sepettekiYemekleriYukle(kullanici_adi);
    }

}
