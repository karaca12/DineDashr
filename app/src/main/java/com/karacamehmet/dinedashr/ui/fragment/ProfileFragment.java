package com.karacamehmet.dinedashr.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karacamehmet.dinedashr.R;
import com.karacamehmet.dinedashr.databinding.FragmentProfileBinding;
import com.karacamehmet.dinedashr.ui.activity.LoginActivity;
import com.karacamehmet.dinedashr.ui.activity.MainActivity;
import com.karacamehmet.dinedashr.ui.viewmodel.BasketViewModel;
import com.karacamehmet.dinedashr.ui.viewmodel.HomeViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);


        SharedPreferences kullanici_adi = getActivity().getSharedPreferences(
                "kullanici_adi", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = kullanici_adi.edit();

        binding.textViewKullaniciAdi.setText(kullanici_adi.getString("kullanici_adi", ""));
        binding.buttonCikis.setOnClickListener(v -> {
            editor.clear();
            editor.apply();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });


        return binding.getRoot();
    }

}