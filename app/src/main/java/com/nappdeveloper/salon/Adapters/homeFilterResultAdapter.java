package com.nappdeveloper.salon.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.nappdeveloper.salon.Model.Model;
import com.nappdeveloper.salon.R;

public class homeFilterResultAdapter extends FirebaseRecyclerAdapter<Model, homeFilterResultAdapter.Viewholder> {

    private int selected_position = 0;

    public homeFilterResultAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {

        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull homeFilterResultAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position, @NonNull Model model) {

        String name = model.getFilterName().toString();
        holder.filterNameTxt.setText(name);

    }

    @Override
    public void onDataChanged() {
        notifyDataSetChanged();
        super.onDataChanged();
    }

    @NonNull
    @Override
    public homeFilterResultAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //the data objects are inflated into the xml file single_data_item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_filter_result_layout, parent, false);
        return new homeFilterResultAdapter.Viewholder(view);

    }

    //we need view holder to hold each objet form recyclerview and to show it in recyclerview
    class Viewholder extends RecyclerView.ViewHolder {

        TextView filterNameTxt;
        CardView cardView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.homeResultCard);
            filterNameTxt = (TextView) itemView.findViewById(R.id.homeShopNameTxt);

        }
    }


}

