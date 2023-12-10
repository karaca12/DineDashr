package com.karacamehmet.dinedashr.ui.activity;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karacamehmet.dinedashr.R;
import com.karacamehmet.dinedashr.databinding.ActivityMainBinding;
import com.karacamehmet.dinedashr.ui.fragment.BasketFragment;
import com.karacamehmet.dinedashr.ui.fragment.HomeFragment;
import com.karacamehmet.dinedashr.ui.viewmodel.BasketViewModel;
import com.karacamehmet.dinedashr.ui.viewmodel.HomeViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navHostFragment);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navHostFragment.getNavController());

        SharedPreferences kullanici_adi_sp = getSharedPreferences("kullanici_adi", MODE_PRIVATE);
        SharedPreferences.Editor editor = kullanici_adi_sp.edit();

        if (kullanici_adi_sp.getString("kullanici_adi", "").isEmpty()) {
            String gelenKullanici_adi = getIntent().getStringExtra("kullanici_adi");
            editor.putString("kullanici_adi", gelenKullanici_adi);
            editor.apply();
        }

        String kullaniciAdi = kullanici_adi_sp.getString("kullanici_adi", "");
        if (kullaniciAdi.isEmpty()) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        OnBackPressedCallback backPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                int count = getSupportFragmentManager().getBackStackEntryCount();
                if (count == 0) {
                    finishAffinity();
                } else {
                    getSupportFragmentManager().popBackStack();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(backPressedCallback);

    }
}