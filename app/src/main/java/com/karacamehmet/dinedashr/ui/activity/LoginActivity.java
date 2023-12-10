package com.karacamehmet.dinedashr.ui.activity;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
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
            String kullaniciAdi = binding.editTextKullaniciAdi.getText().toString();

            if (isOnlyEnglishAndSymbols(kullaniciAdi)) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("kullanici_adi", kullaniciAdi);
                startActivity(intent);
            } else {
                Snackbar.make(v, "Lütfen yalnızca İngilizce karakterler ve semboller kullanın!",
                        Snackbar.LENGTH_SHORT).show();
            }
        });

        OnBackPressedCallback backPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                setEnabled(true);
                finishAffinity();
            }
        };
        getOnBackPressedDispatcher().addCallback(backPressedCallback);
    }

    private boolean isOnlyEnglishAndSymbols(String text) {
        return text.matches("[a-zA-Z0-9!@#$%^&*()_+=\\[\\]{};':\"\\\\|,.<>/?]*");
    }
}