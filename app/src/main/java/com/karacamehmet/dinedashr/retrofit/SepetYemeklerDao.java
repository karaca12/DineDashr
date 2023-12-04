package com.karacamehmet.dinedashr.retrofit;

import com.karacamehmet.dinedashr.data.entity.CRUDCevap;
import com.karacamehmet.dinedashr.data.entity.SepetYemeklerCevap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SepetYemeklerDao {
    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    Call<SepetYemeklerCevap> sepettekiYemekleriGetir(@Field("kullanici_adi") String kullanici_adi);

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    Call<CRUDCevap> sepeteYemekEkle(@Field("yemek_adi") String yemek_adi,
                                    @Field("yemek_resim_adi") String yemek_resim_adi,
                                    @Field("yemek_fiyat") int yemek_fiyat,
                                    @Field("yemek_siparis_adet") int yemek_siparis_adet,
                                    @Field("kullanici_adi") String kullanici_adi);

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    Call<CRUDCevap> sepettenYemekSil(@Field("sepet_yemek_id") int sepet_yemek_id,
                                     @Field("kullanici_adi") String kullanici_adi);

}
