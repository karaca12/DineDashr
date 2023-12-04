package com.karacamehmet.dinedashr.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import com.karacamehmet.dinedashr.data.entity.Yemekler;
import com.karacamehmet.dinedashr.databinding.FragmentDetailBinding;
import com.karacamehmet.dinedashr.ui.viewmodel.DetailViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailFragment extends Fragment {
    private FragmentDetailBinding binding;
    private DetailViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);

        DetailFragmentArgs bundle = DetailFragmentArgs.fromBundle(getArguments());
        Yemekler gelenYemek = bundle.getYemek();

        binding.textViewDetailAd.setText(gelenYemek.getYemek_adi());
        binding.textViewDetailFiyat.setText(Integer.toString(gelenYemek.getYemek_fiyat()) + "₺");

        String URL = "http://kasimadalan.pe.hu/yemekler/resimler/" + gelenYemek.getYemek_resim_adi();

        Glide.with(this).load(URL).override(300).into(binding.imageViewDetail);

        binding.buttonSepeteEkle.setOnClickListener(v -> {
            if (Integer.parseInt(binding.textViewYemekAdet.getText().toString()) > 0) {
                viewModel.sepeteYemekEkle(
                        gelenYemek.getYemek_adi(), gelenYemek.getYemek_resim_adi(),
                        gelenYemek.getYemek_fiyat(),
                        Integer.parseInt(binding.textViewYemekAdet.getText().toString()),
                        "mehmet_karaca");
                Snackbar.make(v, "Sepete Eklendi", Snackbar.LENGTH_SHORT).show();
                binding.textViewYemekAdet.setText("0");

            }

        });

        binding.buttonArttir.setOnClickListener(v -> {
            int yemekAdet = Integer.parseInt(binding.textViewYemekAdet.getText().toString()) + 1;
            binding.textViewYemekAdet.setText(String.valueOf(yemekAdet));
        });

        binding.buttonEksilt.setOnClickListener(v -> {
            int yemekAdet = Integer.parseInt(binding.textViewYemekAdet.getText().toString());
            if (yemekAdet > 0) {
                yemekAdet--;
            }
            binding.textViewYemekAdet.setText(String.valueOf(yemekAdet));
        });

        binding.imageViewGeri.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });


        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);
    }
}