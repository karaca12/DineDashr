package com.karacamehmet.dinedashr.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.karacamehmet.dinedashr.R;
import com.karacamehmet.dinedashr.databinding.ActivityLoginBinding;
import com.karacamehmet.dinedashr.ui.viewmodel.BasketViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.buttonGiris.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("kullanici_adi", binding.editTextKullaniciAdi.getText().toString());
            startActivity(intent);
        });


    }
}