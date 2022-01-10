package com.example.raund.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
//import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raund.R;
import com.example.raund.itemhome;
import com.example.raund.model.History;
import com.example.raund.model.TravelList;

import java.util.ArrayList;

public class AdapterRecyclerView3 extends RecyclerView.Adapter<AdapterRecyclerView3.ViewHolder> {

    private ArrayList<TravelList> dataItem = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageIcon1, imageIcon2, imageIcon3 ;
      ImageView imageIcon;

        public ViewHolder(@NonNull View v) {
            super(v);
            imageIcon1 =(ImageView) v.findViewById(R.id.imageViewahya);
            imageIcon2 =(ImageView) v.findViewById(R.id.imageView14);
            imageIcon3 =(ImageView) v.findViewById(R.id.imageView13);

//            imageIcon = v.findViewById(R.id.image);
        }
    }

    AdapterRecyclerView3(ArrayList<itemhome> data) {
//        this.dataItem = data;
    }

    public void setListdata(ArrayList<History> listdata) {
        this.dataItem = dataItem;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterRecyclerView3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home, parent, false);
        //myonClickListener
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        ImageView imageIcon = holder.imageIcon1;
//        imageIcon.setImageResource(dataItem.get(position).getImage());
//        TravelList travelList = listda
//        Glide.with(holder.itemView.getContext())
//                .load(R.drawable.ahya)
//                .into(holder.imageIcon1);
    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }
}