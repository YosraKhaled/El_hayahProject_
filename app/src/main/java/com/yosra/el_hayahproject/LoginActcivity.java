package com.yosra.el_hayahproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActcivity extends AppCompatActivity {
    EditText mName, mEmail, mPassword;
    Button btn_Login;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actcivity);

        mEmail = findViewById(R.id.email_login);
        mPassword = findViewById(R.id.password_login);
        btn_Login = findViewById(R.id.btn_login);
        mAuth = FirebaseAuth.getInstance();
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if ( email.isEmpty() & password.isEmpty()) {
                    Toast.makeText(LoginActcivity.this, "Enter Your Data", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(LoginActcivity.this, HomeActivity.class));
                                    } else {
                                        Toast.makeText(LoginActcivity.this, "check your internet", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });

                }
            }
        });



    }
}