package com.nappdeveloper.salon.Adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.nappdeveloper.salon.Activities.shopServiceActivity;
import com.nappdeveloper.salon.Fragments.homeFilterResultFragment;
import com.nappdeveloper.salon.Model.Model;
import com.nappdeveloper.salon.R;

public class exploreAdapter extends FirebaseRecyclerAdapter<Model, exploreAdapter.Viewholder> {

    private int selected_position = 0;

    public exploreAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {

        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull exploreAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position, @NonNull Model model) {

        String name = model.getFilterName().toString();
        holder.filterNameTxt.setText(name);
        holder.exploreCategoryName.setText(model.getShopName());
        Glide.with(holder.imageView).load(model.getShopImage()).into(holder.imageView);


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), shopServiceActivity.class);
                v.getContext().startActivity(intent);

                int previousItem = selected_position;
                selected_position = position;

                notifyItemChanged(previousItem);
                notifyItemChanged(position);

            }
        });
    }

    @Override
    public void onDataChanged() {
        notifyDataSetChanged();
        super.onDataChanged();
    }

    @NonNull
    @Override
    public exploreAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //the data objects are inflated into the xml file single_data_item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_shops_layout, parent, false);
        return new exploreAdapter.Viewholder(view);

    }

    //we need view holder to hold each objet form recyclerview and to show it in recyclerview
    class Viewholder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        TextView filterNameTxt;
        TextView exploreCategoryName;
        ImageView imageView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.explore_shops_card);
            imageView = (ImageView) itemView.findViewById(R.id.exploreShopImage);
            filterNameTxt = (TextView) itemView.findViewById(R.id.exploreShopName);
            exploreCategoryName = (TextView) itemView.findViewById(R.id.exploreCategoryName);

        }
    }


}
