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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegistrActivity extends AppCompatActivity {
    EditText mName, mEmail, mPassword;
    Button btn_register;
    FirebaseAuth mAuth;

    FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);

        mName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        btn_register = findViewById(R.id.btn_register);
        mAuth = FirebaseAuth.getInstance();

        firestore = FirebaseFirestore.getInstance();


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = mName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (name.isEmpty() && email.isEmpty() & password.isEmpty()) {
                    Toast.makeText(RegistrActivity.this, "Enter Your Data", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(RegistrActivity.this, LoginActcivity.class));
                                        String id = task.getResult().getUser().getUid();
                                        uploadData(name, email, password ,id);
                                    } else {
                                        Toast.makeText(RegistrActivity.this, "check your internet", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                }
            }
        });
    }

    private void uploadData(String name, String email, String password,String id) {


        HashMap<String, String> userData = new HashMap<>();
        userData.put("name", name);
        userData.put("email", email);
        userData.put("password",password);
        userData.put("id",id);

        firestore.collection("UserInf").document(id).set(userData);


    }
}