package com.yosra.el_hayahproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.yosra.el_hayahproject.Adapter.HomeAdapter;
import com.yosra.el_hayahproject.pojo.HomeModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeAdapter.OnClickCar {
    BottomNavigationView bottomNavigationView;
    List<HomeModel> mList;
    RecyclerView mHomeRecycler;
    HomeAdapter mHomeAdapter;
    FirebaseFirestore mFireStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        iniView();
        loadDataFromFireBase();
    }

    private void iniView() {

        mHomeRecycler = findViewById(R.id.home_rec);
        mList = new ArrayList<>();
        mHomeAdapter = new HomeAdapter(this, mList, this);
        mFireStore = FirebaseFirestore.getInstance();

    }

    private void loadDataFromFireBase() {

        mFireStore.collection("HomeRecycler").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        Log.d("getData", "DON");
                        for (DocumentSnapshot snapshot : list) {

                            String name = snapshot.getString("name");
                            String specialty = snapshot.getString("specialty");
                            String img = snapshot.getString("img");
                            String id = snapshot.getString("id");
                            HomeModel homeModel = new HomeModel(name, img, id, specialty);
                            mList.add(homeModel);
                        }
                        mHomeAdapter.notifyDataSetChanged();
                    }
                });


        mHomeAdapter = new HomeAdapter(HomeActivity.this, mList, this);
        mHomeRecycler.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        mHomeRecycler.setAdapter(mHomeAdapter);
        mHomeAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(String id) {


        Intent intent = new Intent(HomeActivity.this, OrderDoctorActivity.class);
        intent.putExtra("idHome", id);
        startActivity(intent);

    }
}