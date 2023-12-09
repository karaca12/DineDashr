package com.karacamehmet.dinedashr.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import com.karacamehmet.dinedashr.databinding.FragmentHomeBinding;
import com.karacamehmet.dinedashr.ui.adapter.YemekRVAdapter;
import com.karacamehmet.dinedashr.ui.viewmodel.HomeViewModel;

import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private final List<String> filters = Arrays.asList("İsim: A-Z", "İsim: Z-A",
            "Fiyat: Artan", "Fiyat: Azalan");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        ArrayAdapter<String> filterAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, filters);
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerFiltre.setAdapter(filterAdapter);

        binding.spinnerFiltre.setSelection(0);


        binding.recyclerViewYemekler.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        viewModel.yemeklerListesi.observe(getViewLifecycleOwner(), yemeklers -> {

            YemekRVAdapter adapter = new YemekRVAdapter(yemeklers, requireContext(), viewModel);
            binding.recyclerViewYemekler.setAdapter(adapter);

            binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    adapter.filterSearch(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.filterSearch(newText);
                    return true;
                }
            });

            binding.spinnerFiltre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedFilter = filters.get(position);
                    switch (selectedFilter) {
                        case "İsim: A-Z":
                            adapter.sortByNameAZ();
                            break;
                        case "İsim: Z-A":
                            adapter.sortByNameZA();
                            break;
                        case "Fiyat: Artan":
                            adapter.sortByPriceLowToHigh();
                            break;
                        case "Fiyat: Azalan":
                            adapter.sortByPriceHighToLow();
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        });
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }

}