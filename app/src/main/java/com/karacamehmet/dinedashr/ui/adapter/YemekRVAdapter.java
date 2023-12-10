package com.karacamehmet.dinedashr.ui.adapter;

import android.content.Context;
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


import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class YemekRVAdapter extends RecyclerView.Adapter<YemekRVAdapter.CardDesignHolder> {
    private List<Yemekler> yemekler;
    private Context mContext;
    private List<Yemekler> filteredYemekler;


    public YemekRVAdapter(List<Yemekler> yemekler, Context mContext, HomeViewModel viewModel) {
        this.yemekler = yemekler;
        this.filteredYemekler = new ArrayList<>(yemekler);
        this.mContext = mContext;
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
        Yemekler yemek = filteredYemekler.get(position);
        YemekCardDesignBinding designBinding = holder.designBinding;
        String URL = "http://kasimadalan.pe.hu/yemekler/resimler/" + yemek.getYemek_resim_adi();

        designBinding.textViewDesignYemekAdi.setText(yemek.getYemek_adi());
        designBinding.textViewDesignYemekFiyati.setText("₺"+String.valueOf(yemek.getYemek_fiyat()));

        Glide.with(mContext).load(URL).override(300).into(designBinding.imageViewDesignYemek);

        designBinding.cardViewDesignYemek.setOnClickListener(v -> {
            HomeFragmentDirections.ActionHomeFragmentToDetailFragment navigation =
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(yemek);
            Navigation.findNavController(v).navigate(navigation);
        });
    }

    public void sortByNameAZ() {
        filteredYemekler.sort(Comparator.comparing(Yemekler::getYemek_adi));
        notifyDataSetChanged();
    }

    public void sortByNameZA() {
        filteredYemekler.sort((yemek1, yemek2) -> yemek2.getYemek_adi().compareTo(yemek1.getYemek_adi()));
        notifyDataSetChanged();
    }

    public void sortByPriceLowToHigh() {
        filteredYemekler.sort(Comparator.comparingInt(Yemekler::getYemek_fiyat));
        notifyDataSetChanged();
    }

    public void sortByPriceHighToLow() {
        filteredYemekler.sort((yemek1, yemek2) ->
                Integer.compare(yemek2.getYemek_fiyat(), yemek1.getYemek_fiyat()));
        notifyDataSetChanged();
    }

    public void filterSearch(String query) {
        filteredYemekler.clear();
        if (query.trim().isEmpty()) {
            filteredYemekler.addAll(yemekler);
        } else {
            query = query.toLowerCase(new Locale("tr", "TR"));
            for (Yemekler yemek : yemekler) {
                if (normalizeString(yemek.getYemek_adi()).contains(normalizeString(query))) {
                    filteredYemekler.add(yemek);
                }
            }
        }
        notifyDataSetChanged();
    }

    private String normalizeString(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase(new Locale("tr", "TR"));
    }

    @Override
    public int getItemCount() {
        return filteredYemekler.size();
    }

}
