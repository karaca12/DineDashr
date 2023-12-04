package com.karacamehmet.dinedashr.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.karacamehmet.dinedashr.data.entity.Yemekler;
import com.karacamehmet.dinedashr.databinding.YemekCardDesignBinding;
import com.karacamehmet.dinedashr.ui.fragment.HomeFragmentDirections;
import com.karacamehmet.dinedashr.ui.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class YemekRVAdapter extends RecyclerView.Adapter<YemekRVAdapter.CardDesignHolder> {
    private List<Yemekler> yemekler;
    private Context mContext;
    private HomeViewModel viewModel;


    public YemekRVAdapter(List<Yemekler> yemekler, Context mContext, HomeViewModel viewModel) {
        this.yemekler = yemekler;
        this.mContext = mContext;
        this.viewModel = viewModel;
    }

    public class CardDesignHolder extends RecyclerView.ViewHolder {
        private YemekCardDesignBinding designBinding;

        public CardDesignHolder(YemekCardDesignBinding designBinding) {
            super(designBinding.getRoot());
            this.designBinding = designBinding;
        }
    }


    @NonNull
    @Override
    public CardDesignHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        YemekCardDesignBinding binding = YemekCardDesignBinding
                .inflate(LayoutInflater.from(mContext), parent, false);
        return new CardDesignHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardDesignHolder holder, int position) {
        Yemekler yemek = yemekler.get(position);
        YemekCardDesignBinding designBinding = holder.designBinding;
        String URL = "http://kasimadalan.pe.hu/yemekler/resimler/" + yemek.getYemek_resim_adi();

        designBinding.textViewDesignYemekAdi.setText(yemek.getYemek_adi());
        designBinding.textViewDesignYemekFiyati.setText(Integer.toString(yemek.getYemek_fiyat()) + "₺");

        Glide.with(mContext).load(URL).override(300).into(designBinding.imageViewDesignYemek);

        designBinding.cardViewDesignYemek.setOnClickListener(v -> {
            HomeFragmentDirections.ActionHomeFragmentToDetailFragment navigation =
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(yemek);
            Navigation.findNavController(v).navigate(navigation);
        });

    }

    @Override
    public int getItemCount() {
        return yemekler.size();
    }

}
