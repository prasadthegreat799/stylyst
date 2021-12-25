package com.nappdeveloper.salon.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nappdeveloper.salon.Adapters.homeFilterAdapter;
import com.nappdeveloper.salon.Model.Model;
import com.nappdeveloper.salon.R;

public class homeFragment extends Fragment {

    RecyclerView filterRecyclerView;
    homeFilterAdapter filterAdapter;
    DatabaseReference filterDatabaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);


        //RecyclerView Code For Food Filters
        filterDatabaseReference = FirebaseDatabase.getInstance().getReference().child("filterSolonList");
        filterRecyclerView = (RecyclerView) view.findViewById(R.id.homeFilterRecyclerView);
        filterRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        filterRecyclerView.getRecycledViewPool().clear();

        //Firebase Recycler Options for salon filters
        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(filterDatabaseReference, Model.class)
                        .build();
        filterAdapter = new homeFilterAdapter(options);
        filterRecyclerView.setAdapter(filterAdapter);

        return view;
    }

    @Override
    public void onStart() {

        filterAdapter.startListening();
        super.onStart();
    }

    @Override
    public void onStop() {

        filterAdapter.stopListening();
        super.onStop();
    }
}