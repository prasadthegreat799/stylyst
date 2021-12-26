package com.nappdeveloper.salon.Adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.nappdeveloper.salon.Activities.shopServiceActivity;
import com.nappdeveloper.salon.Model.Model;
import com.nappdeveloper.salon.R;

public class shopServiceAdapter extends FirebaseRecyclerAdapter<Model, shopServiceAdapter.Viewholder> {

    private int selected_position = 0;

    public shopServiceAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {

        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull shopServiceAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position, @NonNull Model model) {

        Glide.with(holder.imageView).load(model.getShopImage()).into(holder.imageView);


    }

    @Override
    public void onDataChanged() {
        notifyDataSetChanged();
        super.onDataChanged();
    }

    @NonNull
    @Override
    public shopServiceAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //the data objects are inflated into the xml file single_data_item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_service_layout, parent, false);
        return new shopServiceAdapter.Viewholder(view);

    }

    //we need view holder to hold each objet form recyclerview and to show it in recyclerview
    class Viewholder extends RecyclerView.ViewHolder {


        ImageView imageView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.serviceImage);

        }
    }


}
