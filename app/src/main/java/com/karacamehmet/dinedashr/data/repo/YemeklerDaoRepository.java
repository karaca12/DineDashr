package com.karacamehmet.dinedashr.data.repo;

import androidx.lifecycle.MutableLiveData;

import com.karacamehmet.dinedashr.data.entity.Yemekler;
import com.karacamehmet.dinedashr.data.entity.YemeklerCevap;
import com.karacamehmet.dinedashr.retrofit.YemeklerDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YemeklerDaoRepository {
    public MutableLiveData<List<Yemekler>> yemeklerListesi = new MutableLiveData<>();
    private YemeklerDao yemeklerDao;

    public YemeklerDaoRepository(YemeklerDao yemeklerDao) {
        this.yemeklerDao = yemeklerDao;
    }

    public void yemekleriYukle() {
        yemeklerDao.yemekleriGetir().enqueue(new Callback<YemeklerCevap>() {
            @Override
            public void onResponse(Call<YemeklerCevap> call, Response<YemeklerCevap> response) {
                List<Yemekler> list = response.body().getYemekler();
                yemeklerListesi.setValue(list);
            }

            @Override
            public void onFailure(Call<YemeklerCevap> call, Throwable t) {

            }
        });
    }

}
