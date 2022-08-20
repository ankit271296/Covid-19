package com.example.trackcovid.ui.country;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackcovid.R;

import java.util.ArrayList;

public class CovidCountryAdapter extends RecyclerView.Adapter<CovidCountryAdapter.ViewHolder> {

    ArrayList<CovidCountry> CovidCountries;

    public CovidCountryAdapter(ArrayList<CovidCountry>covidCountries){
        this.CovidCountries=covidCountries;
    }

    @NonNull
    @Override
    public CovidCountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_covid_country, parent, false);
        return new ViewHolder(view);





    }

    @Override
    public void onBindViewHolder(@NonNull CovidCountryAdapter.ViewHolder holder, int position) {
       CovidCountry covidCountry=CovidCountries.get(position);
       holder.tvTotalCases.setText(covidCountry.getmCases());
       holder.tvCountryName.setText(covidCountry.getmCovidCountry());

    }

    @Override
    public int getItemCount() {
        return CovidCountries.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTotalCases,tvCountryName;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            tvTotalCases=itemView.findViewById(R.id.tvTotalcases);
            tvCountryName=itemView.findViewById(R.id.tvCountryName);
        }
    }
}
