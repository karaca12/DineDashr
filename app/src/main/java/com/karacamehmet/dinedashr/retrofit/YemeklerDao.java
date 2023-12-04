package com.karacamehmet.dinedashr.retrofit;

import com.karacamehmet.dinedashr.data.entity.YemeklerCevap;

import retrofit2.Call;
import retrofit2.http.GET;

public interface YemeklerDao {
    @GET("yemekler/tumYemekleriGetir.php")
    Call<YemeklerCevap> yemekleriGetir();
}
