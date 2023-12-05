package com.karacamehmet.dinedashr.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.karacamehmet.dinedashr.data.entity.YemekListeleme;
import com.karacamehmet.dinedashr.data.entity.Yemekler;
import com.karacamehmet.dinedashr.data.repo.YemekListelemeDaoRepository;
import com.karacamehmet.dinedashr.data.repo.YemeklerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    public YemeklerDaoRepository yemeklerDaoRepository;
    public MutableLiveData<List<Yemekler>> yemeklerListesi;
    public YemekListelemeDaoRepository yemekListelemeDaoRepository;
    public MutableLiveData<List<YemekListeleme>> yemekListelemeListesi;

    @Inject
    public HomeViewModel(YemeklerDaoRepository yemeklerDaoRepository, YemekListelemeDaoRepository yemekListelemeDaoRepository) {
        this.yemeklerDaoRepository = yemeklerDaoRepository;
        this.yemekListelemeDaoRepository = yemekListelemeDaoRepository;
        yemekleriYukle();
        yemekListelemeYukle();
        yemeklerListesi = yemeklerDaoRepository.yemeklerListesi;
        yemekListelemeListesi = yemekListelemeDaoRepository.yemekListelemeListesi;
    }

    public void yemekleriYukle() {
        yemeklerDaoRepository.yemekleriYukle();
    }

    public void yemekListelemeYukle() {
        yemekListelemeDaoRepository.yemekListelemeYukle();
    }

    public void yemekListelemeGuncelle(int id, int tur, int favori) {
        yemekListelemeDaoRepository.yemekListelemeGuncelle(id, tur, favori);
    }
}
