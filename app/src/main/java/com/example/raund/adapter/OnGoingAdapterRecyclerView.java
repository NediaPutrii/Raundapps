package com.example.raund.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.raund.R;
import com.example.raund.model.OnGoing;

import java.util.ArrayList;

public class OnGoingAdapterRecyclerView extends RecyclerView.Adapter<OnGoingAdapterRecyclerView.KelasViewHolder2>  {

    ArrayList<OnGoing> listdata = new ArrayList<>();

    public class KelasViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView namaTravel, destination, currentloc, departure, arrive,jam_arrive, jam_departure, total;
    CardView DeliveryCv;
    ImageView logoDelivery,logoTravel;
    Button BtnReschedule;
    public KelasViewHolder2(@NonNull View itemView) {
        super(itemView);
        namaTravel = itemView.findViewById(R.id.nama_travel_og);
        currentloc = itemView.findViewById(R.id.fromDelivery_og);
        destination = itemView.findViewById(R.id.destinationDelivery_og);
        departure = itemView.findViewById(R.id.tgl_departure_og);
        arrive = itemView.findViewById(R.id.tgl_arrival_og);
        jam_arrive = itemView.findViewById(R.id.jam_datang_og);
        jam_departure = itemView.findViewById(R.id.jam_pergi_og);
        total = itemView.findViewById(R.id.harga_og);
        BtnReschedule = itemView.findViewById(R.id.BtnReschedule);
        DeliveryCv = itemView.findViewById(R.id.DeliveryCv);
        logoDelivery =(ImageView) itemView.findViewById(R.id.logodelivery);
        logoTravel =(ImageView) itemView.findViewById(R.id.logotravel);
//        gambar_travel =(ImageView) itemView.findViewById(R.id.gambar_travel);
        itemView.setOnClickListener(this);

    }
        @Override
        public void onClick(View view) {
            if (listener != null){
                listener.onClick(view, listdata.get(getAdapterPosition()));
            }
        }

    }

    public void setListdata(ArrayList<OnGoing> listdata) {

        this.listdata = listdata;
        notifyDataSetChanged();
    }
    //Click Listener
    OnGoingAdapterRecyclerView.OnGoingClickListener listener = null;
    public interface OnGoingClickListener {
        void onClick(View view, OnGoing onGoing);
    }
    public void setListener(OnGoingAdapterRecyclerView.OnGoingClickListener listener) {
        this.listener = listener;
    }



    @NonNull
    @Override

    public OnGoingAdapterRecyclerView.KelasViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_ongoing,parent,false);
        return new OnGoingAdapterRecyclerView.KelasViewHolder2(view);

    }

    @Override
    public void onBindViewHolder(@NonNull KelasViewHolder2 holder, int position) {
        OnGoing ongoing = listdata.get(position);


        holder.namaTravel.setText(ongoing.nama_travel_og);
        holder.currentloc.setText(ongoing.fromDelivery_og);
        holder.destination.setText(ongoing.destinationDelivery_og);
        holder.departure.setText(ongoing.tgl_departure_og);
        holder.arrive.setText(ongoing.tgl_arrival_og);
        holder.jam_arrive.setText(ongoing.jam_datang_og);
        holder.jam_departure.setText(ongoing.jam_pergi_og);
        holder.total.setText(Integer.toString(ongoing.harga_og));
        if(ongoing.jenis==1) {
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










