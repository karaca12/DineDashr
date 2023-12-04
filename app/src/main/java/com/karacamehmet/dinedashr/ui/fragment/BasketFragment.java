package com.karacamehmet.dinedashr.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karacamehmet.dinedashr.R;
import com.karacamehmet.dinedashr.databinding.FragmentBasketBinding;
import com.karacamehmet.dinedashr.ui.adapter.SepetRVAdapter;
import com.karacamehmet.dinedashr.ui.viewmodel.BasketViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BasketFragment extends Fragment {
    private FragmentBasketBinding binding;
    private BasketViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBasketBinding.inflate(inflater, container, false);

        binding.recyclerViewSepetYemekler.setLayoutManager(new LinearLayoutManager(requireContext()));
        viewModel.sepettekiYemekleriYukle("mehmet_karaca");

        viewModel.sepetYemeklerListesi.observe(getViewLifecycleOwner(), sepetYemeklers -> {
            SepetRVAdapter adapter = new SepetRVAdapter(sepetYemeklers, requireContext(), viewModel);
            binding.recyclerViewSepetYemekler.setAdapter(adapter);
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
        viewModel.sepettekiYemekleriYukle("mehmet_karaca");
    }
}