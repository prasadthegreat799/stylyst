package com.nappdeveloper.salon.Adapters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.nappdeveloper.salon.Fragments.homeFilterResultFragment;
import com.nappdeveloper.salon.Model.Model;
import com.nappdeveloper.salon.R;

public class homeFilterAdapter extends FirebaseRecyclerAdapter<Model, homeFilterAdapter.Viewholder> {

    private int selected_position = 0;
    public homeFilterAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {

        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull homeFilterAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position, @NonNull Model model) {

        String name=model.getFilterName().toString();
        holder.filterNameTxt.setText(name);

        /*
        if(selected_position==position){
            holder.filterNameTxt.setTextColor(Color.parseColor("White"));
            holder.linearLayout.setBackgroundResource(R.color.pale_pink);
        }else{
            holder.filterNameTxt.setTextColor(Color.parseColor("Black"));
            holder.linearLayout.setBackgroundResource(R.color.white);
        }
         */

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new homeFilterResultFragment();
                FragmentManager fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager(); // this is basically context of the class
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putString("name",name); //key and value
                //set Fragmentclass Arguments
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.homeFilterResultFrameLayout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


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
    public homeFilterAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //the data objects are inflated into the xml file single_data_item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_filter_layout, parent, false);
        return new homeFilterAdapter.Viewholder(view);

    }

    //we need view holder to hold each objet form recyclerview and to show it in recyclerview
    class Viewholder extends RecyclerView.ViewHolder {

        TextView filterNameTxt;
        CardView linearLayout;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            linearLayout = (CardView) itemView.findViewById(R.id.home_text_filter_card);
            filterNameTxt = (TextView) itemView.findViewById(R.id.filterText);

        }
    }


}

