package com.example.trackcovid.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trackcovid.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.net.URL;

public class HomeFragment extends Fragment {
    private TextView tvTotalConfirmed,tvTotalDeaths,tvTotalRecovered;
    private ProgressBar progressBar;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        boolean attachToRoot;
        View root = inflater.inflate(R.layout.fragment_home,container,  false);
        //call view
        tvTotalConfirmed=root.findViewById(R.id.tvTotalConfirmed);
        tvTotalDeaths=root.findViewById(R.id.tvTotalDeath);
        tvTotalRecovered=root.findViewById(R.id.tvTotalRecovered);
//        progressBar=root.findViewById(R.id.progress_circular_home);
        //call volley
        getDate();

        return root;


    }

    private void getDate() {
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        String url ="https://corona.lmao.ninja/v2/all";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            private String name;

            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());


                    tvTotalConfirmed.setText(jsonObject.getString( "cases"));
                    tvTotalDeaths.setText(jsonObject.getString( "deaths"));
                    tvTotalRecovered.setText(jsonObject.getString( "Recovered"));

                } catch (JSONException e) {
                    e.printStackTrace();

                }}
            },new Response.ErrorListener(){
                @Override
                        public void  onErrorResponse(VolleyError error){

                }
            });

        }



}