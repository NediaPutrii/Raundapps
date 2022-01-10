package com.example.raund.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.raund.HistoryActivity;
import com.example.raund.R;
import com.example.raund.model.History;
import com.example.raund.model.OnGoing;

import java.util.ArrayList;

public class AdapterRecylerView extends RecyclerView.Adapter<AdapterRecylerView.KelasViewHolder> {

    ArrayList<History> listdata = new ArrayList<>();




    public class KelasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nama_travelh,
                destinationDelivery,
                fromDelivery,
                tgl_departure2,
                tgl_arrival2,
                jam_datang2,
                jam_pergi2,
                harga2;
        CardView TravelCv;
        ImageView logoTravel;

        public KelasViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_travelh = itemView.findViewById(R.id.nama_travelh);
            fromDelivery = itemView.findViewById(R.id.fromDelivery);
            destinationDelivery = itemView.findViewById(R.id.destinationDelivery);
            tgl_departure2 = itemView.findViewById(R.id.tgl_departure2);
            tgl_arrival2 = itemView.findViewById(R.id.tgl_arrival2);
            jam_datang2 = itemView.findViewById(R.id.jam_datang2);
            jam_pergi2 = itemView.findViewById(R.id.jam_pergi2);
            harga2 = itemView.findViewById(R.id.harga2);
            TravelCv = itemView.findViewById(R.id.TravelCv);
            logoTravel =(ImageView) itemView.findViewById(R.id.imageView19);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (listener != null){
                listener.onClick(view, listdata.get(getAdapterPosition()));
            }
        }
    }

    public void setListdata(ArrayList<History> listdata) {
        this.listdata = listdata;
        notifyDataSetChanged();
    }

    //Click Listener
    AdapterRecylerView.HistoryClickListener listener = null;
    public interface HistoryClickListener {
        void onClick(View view, History history);
    }
    public void setListener(AdapterRecylerView.HistoryClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override

    public  KelasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_history,parent,false);
        return new KelasViewHolder(view);

    }

    @Override
    public void onBindViewHolder(KelasViewHolder holder, int position){
        History history = listdata.get(position);
        holder.nama_travelh.setText(history.nama_travelh);
        holder.fromDelivery.setText(history.fromDelivery);
        holder.destinationDelivery.setText(history.destinationDelivery);
        holder.tgl_departure2.setText(history.tgl_departure2);
        holder.tgl_arrival2.setText(history.tgl_arrival2);
        holder.jam_datang2.setText(history.jam_datang2);
        holder.jam_pergi2.setText(history.jam_pergi2);
        holder.harga2.setText(Integer.toString(history.harga2));
        if(history.jenis==1) {
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.ic_delivery)
                    .into(holder.logoTravel);
        }else {
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.kotaktravel)
                    .into(holder.logoTravel);
        }

    }

    @Override
    public int getItemCount(){

    return listdata.size();
    }
}


