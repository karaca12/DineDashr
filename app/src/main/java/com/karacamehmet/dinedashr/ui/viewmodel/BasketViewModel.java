package com.karacamehmet.dinedashr.ui.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.karacamehmet.dinedashr.data.entity.SepetYemekler;
import com.karacamehmet.dinedashr.data.repo.SepetYemeklerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;

@HiltViewModel
public class BasketViewModel extends ViewModel {
    public SepetYemeklerDaoRepository sepetYemeklerDaoRepository;
    public MutableLiveData<List<SepetYemekler>> sepetYemeklerListesi;
    private SharedPreferences sharedPreferences;

    @Inject
    public BasketViewModel(SepetYemeklerDaoRepository sepetYemeklerDaoRepository, Application application) {
        this.sepetYemeklerDaoRepository = sepetYemeklerDaoRepository;
        sharedPreferences = application.getSharedPreferences("kullanici_adi", Context.MODE_PRIVATE);
        sepettekiYemekleriYukle(sharedPreferences.getString("kullanici_adi", ""));
        sepetYemeklerListesi = sepetYemeklerDaoRepository.sepetYemeklerListesi;
    }

    public void sepettekiYemekleriYukle(String kullanici_adi) {
        sepetYemeklerDaoRepository.sepettekiYemekleriYukle(kullanici_adi);
    }

    public void sepettenYemekSil(int sepet_yemek_id, String kullanici_adi) {
        sepetYemeklerDaoRepository.sepettenYemekSil(sepet_yemek_id, kullanici_adi);
    }
}
