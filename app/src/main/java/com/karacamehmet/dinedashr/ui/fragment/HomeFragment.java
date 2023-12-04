package com.karacamehmet.dinedashr.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.karacamehmet.dinedashr.R;
import com.karacamehmet.dinedashr.databinding.FragmentHomeBinding;
import com.karacamehmet.dinedashr.ui.adapter.YemekRVAdapter;
import com.karacamehmet.dinedashr.ui.viewmodel.HomeViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.recyclerViewYemekler.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


        viewModel.yemeklerListesi.observe(getViewLifecycleOwner(), yemeklers -> {
            YemekRVAdapter adapter = new YemekRVAdapter(yemeklers, requireContext(), viewModel);
            binding.recyclerViewYemekler.setAdapter(adapter);
        });
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }

}