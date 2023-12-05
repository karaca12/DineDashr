package com.karacamehmet.dinedashr.data.repo;

import androidx.lifecycle.MutableLiveData;

import com.karacamehmet.dinedashr.data.entity.YemekListeleme;
import com.karacamehmet.dinedashr.room.YemekListelemeDao;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class YemekListelemeDaoRepository {
    public MutableLiveData<List<YemekListeleme>> yemekListelemeListesi = new MutableLiveData<>();
    private YemekListelemeDao yemekListelemeDao;

    public YemekListelemeDaoRepository(YemekListelemeDao yemekListelemeDao) {
        this.yemekListelemeDao = yemekListelemeDao;
    }

    public void yemekListelemeYukle(){
        yemekListelemeDao.yemekListelemeYukle().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<YemekListeleme>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<YemekListeleme> yemekListelemes) {
                        yemekListelemeListesi.setValue(yemekListelemes);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void yemekListelemeGuncelle(int id,int tur,int favori){
        YemekListeleme guncellenenYemekListeleme = new YemekListeleme(id,tur,favori);
        yemekListelemeDao.yemekListelemeGuncelle(guncellenenYemekListeleme)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
