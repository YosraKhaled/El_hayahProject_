package com.yosra.el_hayahproject.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.yosra.el_hayahproject.R;

import java.util.HashMap;

public class SentUserDataActivity extends AppCompatActivity {

    Button order ;
    EditText name , phone , age ;
    FirebaseFirestore firebaseFirestore ;
    FirebaseAuth auth ;
    FirebaseUser firebaseUser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_user_data);

        iniView();
        addData();
    }



    private void iniView() {
        order=findViewById(R.id.order);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        age=findViewById(R.id.age);
        auth=FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();

        firebaseFirestore=FirebaseFirestore.getInstance();

    }


    private void addData() {

        order.setOnClickListener(v -> {
            String names =name.getText().toString();
            String phones =phone.getText().toString();
            String ages =age.getText().toString();
            String id =firebaseUser.getUid();

            if(valdation(names,phones,ages))
            {
                HashMap<String , String> patientdata = new HashMap<>();
                patientdata.put("name" , names);
                patientdata.put("phone" , phones);
                patientdata.put("age" ,ages);
                patientdata.put("id" , id);
                firebaseFirestore.collection("patientdata").document()
                        .set(patientdata)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful())
                                {
                                    showDilogOrder();

                                }
                            }
                        });
            }
        });
    }

    private void showDilogOrder() {
        //create dialog
        Dialog dialog = new Dialog(SentUserDataActivity.this);
        dialog.setContentView(R.layout.cstoum_okorder);
        dialog.getWindow().setLayout(1000, 1000);
        dialog.setTitle("Yor Order Don");
        dialog.show();

       new Handler().postDelayed(() -> {
           startActivity(new Intent(SentUserDataActivity.this, HomeActivity.class));
           dialog.dismiss();
       },3000) ;
    }
    private boolean valdation(String names, String phones, String ages) {
        if(names.isEmpty())
        {
            Toast.makeText(this, "Enter Your Name", Toast.LENGTH_SHORT).show();
            return  false ;
        }
        if(phones.isEmpty())
        {
            Toast.makeText(this, "Enter Your phone", Toast.LENGTH_SHORT).show();
            return  false ;
        }
        if(ages.isEmpty())
        {
            Toast.makeText(this, "Enter Your age", Toast.LENGTH_SHORT).show();
            return  false ;
        }
        return true ;
    }
}