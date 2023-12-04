package com.karacamehmet.dinedashr.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.karacamehmet.dinedashr.data.entity.SepetYemekler;
import com.karacamehmet.dinedashr.data.repo.SepetYemeklerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class BasketViewModel extends ViewModel {
    public SepetYemeklerDaoRepository sepetYemeklerDaoRepository;
    public MutableLiveData<List<SepetYemekler>> sepetYemeklerListesi;

    @Inject
    public BasketViewModel(SepetYemeklerDaoRepository sepetYemeklerDaoRepository) {
        this.sepetYemeklerDaoRepository = sepetYemeklerDaoRepository;
        sepettekiYemekleriYukle("mehmet_karaca");
        sepetYemeklerListesi = sepetYemeklerDaoRepository.sepetYemeklerListesi;
    }

    public void sepettekiYemekleriYukle(String kullanici_adi) {
        sepetYemeklerDaoRepository.sepettekiYemekleriYukle(kullanici_adi);
    }

    public void sepettenYemekSil(int sepet_yemek_id, String kullanici_adi) {
        sepetYemeklerDaoRepository.sepettenYemekSil(sepet_yemek_id, kullanici_adi);
    }
}
