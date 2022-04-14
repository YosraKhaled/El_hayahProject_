package com.yosra.el_hayahproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(() -> {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null ) {
                //check user is register or no
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            } else {
                //start RegisterActivity
                startActivity(new Intent(SplashActivity.this, RegistrActivity.class));
                finish();
            }
        }, 3000);
    }
}