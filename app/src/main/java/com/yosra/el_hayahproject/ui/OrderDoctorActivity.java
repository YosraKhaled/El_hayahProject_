package com.yosra.el_hayahproject.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.yosra.el_hayahproject.R;

import org.jetbrains.annotations.NotNull;

public class OrderDoctorActivity extends AppCompatActivity {
    FirebaseFirestore mFirestore;
    String idDr;
    TextView firstDay, lastDay, specialty, name, timeOneF, timeOneT, timeTowF, timeTowT;
    Button mBtn_OrderDoctor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_doctor);


        idDr = getIntent().getStringExtra("idHome");
        Toast.makeText(this, idDr, Toast.LENGTH_SHORT).show();

        iniView();
        getDataFromFireBase();
        orderDoctor();

    }


    private void iniView() {
        mFirestore=FirebaseFirestore.getInstance();

        firstDay = findViewById(R.id.day1);
        lastDay = findViewById(R.id.day2);
        specialty = findViewById(R.id.dr_spc);
        name = findViewById(R.id.dr_name);
        timeOneT = findViewById(R.id.fromtime1);
        timeOneF = findViewById(R.id.totime1);
        timeTowF = findViewById(R.id.fromtime2);
        timeTowT = findViewById(R.id.totime2);
        mBtn_OrderDoctor=findViewById(R.id.OrderDoctor);

    }

    private void getDataFromFireBase() {

        mFirestore.collection("DoctorInf")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshots : task.getResult()) {
                                String id = documentSnapshots.getString("drId");
                                Toast.makeText(OrderDoctorActivity.this, id, Toast.LENGTH_SHORT).show();
                                String drName = documentSnapshots.getString("name");

                                if (idDr.equalsIgnoreCase(id)) {
                                    Toast.makeText(OrderDoctorActivity.this, drName, Toast.LENGTH_SHORT).show();

                                    name.setText(drName);
                                }
                                Log.d("taskTag", documentSnapshots.getId());
                            }
                        } else {
                            Log.d("taskTag", String.valueOf(task.getException()));

                        }

                    }
                });
    }


    private void orderDoctor() {
        mBtn_OrderDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDoctorActivity.this , SentUserDataActivity.class));
            }
        });
    }
}