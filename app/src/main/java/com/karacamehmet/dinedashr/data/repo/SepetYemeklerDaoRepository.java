package com.karacamehmet.dinedashr.data.repo;

import androidx.lifecycle.MutableLiveData;

import com.karacamehmet.dinedashr.data.entity.CRUDCevap;
import com.karacamehmet.dinedashr.data.entity.SepetYemekler;
import com.karacamehmet.dinedashr.data.entity.SepetYemeklerCevap;
import com.karacamehmet.dinedashr.retrofit.SepetYemeklerDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SepetYemeklerDaoRepository {
    public MutableLiveData<List<SepetYemekler>> sepetYemeklerListesi = new MutableLiveData<>();
    private SepetYemeklerDao sepetYemeklerDao;

    public SepetYemeklerDaoRepository(SepetYemeklerDao sepetYemeklerDao) {
        this.sepetYemeklerDao = sepetYemeklerDao;
    }

    public void sepeteYemekEkle(String yemek_adi, String yemek_resim_adi,
                                int yemek_fiyat, int yemek_siparis_adet, String kullanici_adi) {
        sepetYemeklerDao.sepeteYemekEkle(
                        yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
                .enqueue(new Callback<CRUDCevap>() {
                    @Override
                    public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

                    }

                    @Override
                    public void onFailure(Call<CRUDCevap> call, Throwable t) {

                    }
                });
    }

    public void sepettekiYemekleriYukle(String kullanici_adi) {
        sepetYemeklerDao.sepettekiYemekleriGetir(kullanici_adi).enqueue(new Callback<SepetYemeklerCevap>() {
            @Override
            public void onResponse(Call<SepetYemeklerCevap> call, Response<SepetYemeklerCevap> response) {
                List<SepetYemekler> list = response.body().getSepet_yemekler();
                sepetYemeklerListesi.setValue(list);
            }

            @Override
            public void onFailure(Call<SepetYemeklerCevap> call, Throwable t) {

            }
        });
    }

    public void sepettenYemekSil(int sepet_yemek_id, String kullanici_adi) {
        sepetYemeklerDao.sepettenYemekSil(sepet_yemek_id, kullanici_adi).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                sepettekiYemekleriYukle(kullanici_adi);
            }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {

            }
        });
    }

}
