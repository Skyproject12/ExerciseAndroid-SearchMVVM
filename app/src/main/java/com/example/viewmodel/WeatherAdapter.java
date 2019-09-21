package com.example.viewmodel;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private ArrayList<WeatherItems> items= new ArrayList<>();

    // membuat adapter selalu terperbarui jika terdapat item baru
    public void setItems(ArrayList<WeatherItems> item){
        items.clear();
        items.addAll(item);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weather_items, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder viewHolder, int i) {
        // call bind from class viewholder
        viewHolder.bind(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textKota;
        TextView textTemperature;
        TextView textDeskription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textKota= itemView.findViewById(R.id.textKota);
            textTemperature= itemView.findViewById(R.id.textTemp);
            textDeskription= itemView.findViewById(R.id.textDeskripsi);
        }

        // definition in bind
        void bind(WeatherItems weatherItems){
            textKota.setText(weatherItems.getName());
            textTemperature.setText(weatherItems.getTemperature());
            textDeskription.setText(weatherItems.getDeskription());
        }

    }
}
