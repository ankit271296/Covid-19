package com.example.trackcovid.ui.country;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trackcovid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CountryFragment extends Fragment {
    RecyclerView rvCovidCountry;
    ProgressBar progressBar;
    private static final String TAG="CountryFragment";
    ArrayList<CovidCountry> CovidCountries;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_country,container,false);


        //call view
        rvCovidCountry=root.findViewById(R.id.rvCovidCountry);
//        progressBar=root.findViewById(R.id.progress_circular_Country);
        rvCovidCountry.setLayoutManager(new LinearLayoutManager(getActivity()));


        // call volley method
        getDatafromServer();



        return root;
    }
    private void showRecyclerView(){

        CovidCountryAdapter covidCountryAdapter= new CovidCountryAdapter(CovidCountries);
        rvCovidCountry.setAdapter(covidCountryAdapter);
    }
    private void getDatafromServer(){

        Toast.makeText(getContext(), "Calling API", Toast.LENGTH_SHORT).show();
        String url="https://api.rootnet.in/covid19-in/unofficial/covid19india.org/statewise";
        CovidCountries=new ArrayList<>();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            String name;
                            CovidCountries.add(new CovidCountry(data.getString("Country"),
                            data.getString("cases")));
                        } showRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();

                }

            }}},

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Log.e(TAG, "onResponse: " + error);
                    }

                });


        Volley.newRequestQueue(getActivity()).add(stringRequest);
            }
        }
