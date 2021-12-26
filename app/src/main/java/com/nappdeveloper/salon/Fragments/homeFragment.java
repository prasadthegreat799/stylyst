package com.nappdeveloper.salon.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nappdeveloper.salon.Adapters.homeFilterAdapter;
import com.nappdeveloper.salon.Model.Model;
import com.nappdeveloper.salon.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class homeFragment extends Fragment {

    RecyclerView filterRecyclerView;
    homeFilterAdapter filterAdapter;
    CarouselView carouselView;
    DatabaseReference filterDatabaseReference;

    int[] sampleImages = {R.drawable.saloon1, R.drawable.saloon2, R.drawable.saloon3};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);


        //Fragment to show the results of food filters
        Fragment fragment = new homeFilterResultFragment();
        FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeFilterResultFrameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        //RecyclerView Code For Food Filters
        filterDatabaseReference = FirebaseDatabase.getInstance().getReference().child("salonData");
        filterRecyclerView = (RecyclerView) view.findViewById(R.id.homeFilterRecyclerView);
        filterRecyclerView.hasFixedSize();
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

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

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

    @Override
    public void onResume() {
        filterAdapter.startListening();
        super.onResume();
    }
}