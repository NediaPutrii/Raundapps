package com.example.raund.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raund.R;
import com.example.raund.model.ChooseTravel;

import java.util.ArrayList;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {

    ArrayList<ChooseTravel> ListChooseTravel = new ArrayList<ChooseTravel>();

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textItemTravel;
        TextView textTanggal;
        TextView textPukul;
        TextView textHarga;
        TextView textJumlah;
        TextView textKapasitas;
        CardView TravelChooseCv;


        ViewHolder(View v) {

            super(v);

            textItemTravel = v.findViewById(R.id.textItemTravel);
            textTanggal = v.findViewById(R.id.textTanggal);
            textPukul = v.findViewById(R.id.textPukul);
            textHarga = v.findViewById(R.id.textHarga);
            textJumlah = v.findViewById(R.id.textJumlah);
            textKapasitas = v.findViewById(R.id.textKapasitas);
            TravelChooseCv = v.findViewById(R.id.TravelChooseCv);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null){
                listener.onClick(view, ListChooseTravel.get(getAdapterPosition()));
            }
        }
    }

    public void setListChooseTravel(ArrayList<ChooseTravel> listChooseTravel) {
        ListChooseTravel = listChooseTravel;
    }

    //Click Listener
    OnChooseTravelClickListener listener = null;
    public interface OnChooseTravelClickListener {
        void onClick(View view, ChooseTravel chooseTravel);
    }

    public void setListener(OnChooseTravelClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_choose_travel, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChooseTravel chooseTravel = ListChooseTravel.get(position);

        holder.textItemTravel.setText(chooseTravel.jenisMobil);
//        holder.textTanggal.setText(chooseTravel.tanggal);
        holder.textPukul.setText(chooseTravel.jam_departure);
        holder.textHarga.setText(chooseTravel.harga);
//        holder.textJumlah.setText(chooseTravel.jumlah);
        holder.textKapasitas.setText(chooseTravel.kapasitas);
    }


    @Override
    public int getItemCount() {

        return ListChooseTravel.size();
    }
}