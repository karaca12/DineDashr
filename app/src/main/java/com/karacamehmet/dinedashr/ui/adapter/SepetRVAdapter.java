package com.karacamehmet.dinedashr.ui.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.karacamehmet.dinedashr.data.entity.SepetYemekler;
import com.karacamehmet.dinedashr.databinding.SepetCardDesignBinding;
import com.karacamehmet.dinedashr.ui.viewmodel.BasketViewModel;

import java.util.List;

public class SepetRVAdapter extends RecyclerView.Adapter<SepetRVAdapter.CardDesignHolder> {

    private List<SepetYemekler> sepetYemekler;
    private Context mContext;
    private BasketViewModel viewModel;
    private EmptyStateListener emptyStateListener;
    private SharedPreferences sharedPreferences;

    public interface EmptyStateListener {
        void onEmptyStateChanged(boolean isEmpty);
    }

    public SepetRVAdapter(List<SepetYemekler> sepetYemekler, Context mContext, BasketViewModel viewModel, EmptyStateListener emptyStateListener) {
        this.sepetYemekler = sepetYemekler;
        this.mContext = mContext;
        this.viewModel = viewModel;
        this.emptyStateListener = emptyStateListener;
    }

    public class CardDesignHolder extends RecyclerView.ViewHolder {

        private SepetCardDesignBinding designBinding;

        public CardDesignHolder(SepetCardDesignBinding designBinding) {
            super(designBinding.getRoot());
            this.designBinding = designBinding;
        }
    }

    @NonNull
    @Override
    public CardDesignHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SepetCardDesignBinding binding = SepetCardDesignBinding
                .inflate(LayoutInflater.from(mContext), parent, false);
        return new CardDesignHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardDesignHolder holder, int position) {
        emptyStateListener.onEmptyStateChanged(sepetYemekler.isEmpty());
        SepetYemekler sepetYemek = sepetYemekler.get(position);
        SepetCardDesignBinding designBinding = holder.designBinding;
        String URL = "http://kasimadalan.pe.hu/yemekler/resimler/" + sepetYemek.getYemek_resim_adi();


        designBinding.textViewSepetYemekAdi.setText(sepetYemek.getYemek_adi());
        designBinding.textViewSepetYemekAdet.setText(
                String.valueOf(sepetYemek.getYemek_siparis_adet()) + " adet");
        designBinding.textViewSepetYemekFiyat.setText(
                String.valueOf(sepetYemek.getYemek_fiyat()) + "₺");
        designBinding.textViewSepetToplamFiyat.setText("Toplam: " +
                String.valueOf(sepetYemek.getYemek_siparis_adet() * sepetYemek.getYemek_fiyat()) + "₺");
        Glide.with(mContext).load(URL).override(300).into(designBinding.imageViewSepetYemekResim);

        designBinding.imageViewThrash.setOnClickListener(v -> {
            Snackbar.make(v, "Silinsin mi?", Snackbar.LENGTH_SHORT)
                    .setAction("EVET", v1 -> {
                        sharedPreferences = mContext.getSharedPreferences("kullanici_adi",
                                Context.MODE_PRIVATE);
                        viewModel.sepettenYemekSil(sepetYemek.getSepet_yemek_id(),
                                sharedPreferences.getString("kullanici_adi", ""));
                        sepetYemekler.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, sepetYemekler.size());
                        emptyStateListener.onEmptyStateChanged(sepetYemekler.isEmpty());
                    })
                    .show();
        });


    }

    @Override
    public int getItemCount() {
        return sepetYemekler.size();
    }


}
