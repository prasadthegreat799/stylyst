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
import com.nappdeveloper.salon.Adapters.exploreAdapter;
import com.nappdeveloper.salon.Adapters.homeFilterResultAdapter;
import com.nappdeveloper.salon.Model.Model;
import com.nappdeveloper.salon.R;

public class exploreFragment extends Fragment {

    RecyclerView exploreRecyclerView;
    exploreAdapter exploreAdapter;
    DatabaseReference exploreDatabaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_explore, container, false);

        //RecyclerView Code For Food Filters
        exploreDatabaseReference = FirebaseDatabase.getInstance().getReference().child("filterSolonList");
        exploreRecyclerView = (RecyclerView) view.findViewById(R.id.exploreRecyclerView);
        exploreRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        exploreRecyclerView.getRecycledViewPool().clear();

        //Firebase Recycler Options for salon filters
        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(exploreDatabaseReference, Model.class)
                        .build();
        exploreAdapter = new exploreAdapter(options);
        exploreRecyclerView.setAdapter(exploreAdapter);

        return view;
    }

    @Override
    public void onStart() {

        exploreAdapter.startListening();
        super.onStart();
    }

    @Override
    public void onStop() {

        exploreAdapter.stopListening();
        super.onStop();
    }
}