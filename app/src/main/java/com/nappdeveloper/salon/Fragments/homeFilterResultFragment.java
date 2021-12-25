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
import com.nappdeveloper.salon.Adapters.homeFilterResultAdapter;
import com.nappdeveloper.salon.Model.Model;
import com.nappdeveloper.salon.R;

public class homeFilterResultFragment extends Fragment {

    RecyclerView filterResultRecyclerView;
    homeFilterResultAdapter filterResultAdapter;
    DatabaseReference filterResultDatabaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_filter_result, container, false);

        //RecyclerView Code For Food Filters
        filterResultDatabaseReference = FirebaseDatabase.getInstance().getReference().child("filterSolonList");
        filterResultRecyclerView = (RecyclerView) view.findViewById(R.id.filterResultRecyclerView);
        filterResultRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        filterResultRecyclerView.getRecycledViewPool().clear();

        //Firebase Recycler Options for salon filters
        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(filterResultDatabaseReference, Model.class)
                        .build();
        filterResultAdapter = new homeFilterResultAdapter(options);
        filterResultRecyclerView.setAdapter(filterResultAdapter);

        return view;
    }

    @Override
    public void onStart() {

        filterResultAdapter.startListening();
        super.onStart();
    }

    @Override
    public void onStop() {

        filterResultAdapter.stopListening();
        super.onStop();
    }
}