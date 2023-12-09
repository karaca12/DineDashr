package com.karacamehmet.dinedashr.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.karacamehmet.dinedashr.data.entity.Yemekler;
import com.karacamehmet.dinedashr.data.repo.YemeklerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    public YemeklerDaoRepository yemeklerDaoRepository;
    public MutableLiveData<List<Yemekler>> yemeklerListesi;

    @Inject
    public HomeViewModel(YemeklerDaoRepository yemeklerDaoRepository) {
        this.yemeklerDaoRepository = yemeklerDaoRepository;
        yemekleriYukle();
        yemeklerListesi = yemeklerDaoRepository.yemeklerListesi;
    }

    public void yemekleriYukle() {
        yemeklerDaoRepository.yemekleriYukle();
    }

}
