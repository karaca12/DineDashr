package com.karacamehmet.dinedashr.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karacamehmet.dinedashr.R;
import com.karacamehmet.dinedashr.data.entity.SepetYemekler;
import com.karacamehmet.dinedashr.databinding.FragmentBasketBinding;
import com.karacamehmet.dinedashr.ui.adapter.SepetRVAdapter;
import com.karacamehmet.dinedashr.ui.viewmodel.BasketViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BasketFragment extends Fragment implements SepetRVAdapter.EmptyStateListener {
    private FragmentBasketBinding binding;
    private BasketViewModel viewModel;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBasketBinding.inflate(inflater, container, false);

        binding.recyclerViewSepetYemekler.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel.sepetYemeklerListesi.observe(getViewLifecycleOwner(), sepetYemeklers -> {

            SepetRVAdapter adapter = new SepetRVAdapter(sepetYemeklers, requireContext(), viewModel, this);
            binding.recyclerViewSepetYemekler.setAdapter(adapter);
            int toplamFiyat = 0;
            for (SepetYemekler sepetYemekler : sepetYemeklers) {
                toplamFiyat += (sepetYemekler.getYemek_fiyat() * sepetYemekler.getYemek_siparis_adet());
            }
            binding.textViewToplamFiyat.setText("Toplam Fiyat: " + String.valueOf(toplamFiyat) + "₺");
            binding.buttonSepetOnayla.setOnClickListener(v -> {
                for (SepetYemekler sepetYemek : sepetYemeklers) {
                    viewModel.sepettenYemekSil(sepetYemek.getSepet_yemek_id(), sepetYemek.getKullanici_adi());
                }
                sepetYemeklers.clear();
                onEmptyStateChanged(true);
            });
        });


        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(BasketViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences = getActivity().getSharedPreferences("kullanici_adi", Context.MODE_PRIVATE);
        viewModel.sepettekiYemekleriYukle(sharedPreferences.getString("kullanici_adi", ""));
    }

    @Override
    public void onEmptyStateChanged(boolean isEmpty) {
        if (isEmpty) {
            binding.emptyStateTextView.setVisibility(View.VISIBLE);
            binding.recyclerViewSepetYemekler.setVisibility(View.INVISIBLE);
            binding.textViewToplamFiyat.setVisibility(View.INVISIBLE);
            binding.buttonSepetOnayla.setVisibility(View.INVISIBLE);
        } else {
            binding.emptyStateTextView.setVisibility(View.INVISIBLE);
            binding.recyclerViewSepetYemekler.setVisibility(View.VISIBLE);
            binding.textViewToplamFiyat.setVisibility(View.VISIBLE);
            binding.buttonSepetOnayla.setVisibility(View.VISIBLE);
        }
    }
}