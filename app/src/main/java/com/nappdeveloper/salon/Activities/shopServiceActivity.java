package com.nappdeveloper.salon.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nappdeveloper.salon.Adapters.exploreAdapter;
import com.nappdeveloper.salon.Adapters.shopServiceAdapter;
import com.nappdeveloper.salon.Model.Model;
import com.nappdeveloper.salon.R;

public class shopServiceActivity extends AppCompatActivity {


    RecyclerView exploreRecyclerView;
    shopServiceAdapter serviceAdapter;
    DatabaseReference exploreDatabaseReference;

    Button viewCartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_service);


        viewCartBtn=(Button) findViewById(R.id.viewCartBtn);

        //RecyclerView Code For Food Filters
        exploreDatabaseReference = FirebaseDatabase.getInstance().getReference().child("filterSolonList");
        exploreRecyclerView = (RecyclerView) findViewById(R.id.shopServiceRecyclerView);
        exploreRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        exploreRecyclerView.getRecycledViewPool().clear();


        viewCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),cartActivity.class);
                startActivity(intent);
            }
        });

        //Firebase Recycler Options for salon filters
        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(exploreDatabaseReference, Model.class)
                        .build();
        serviceAdapter = new shopServiceAdapter(options);
        exploreRecyclerView.setAdapter(serviceAdapter);
    }


    @Override
    public void onStart() {

        serviceAdapter.startListening();
        super.onStart();
    }

    @Override
    public void onStop() {

        serviceAdapter.stopListening();
        super.onStop();
    }
}