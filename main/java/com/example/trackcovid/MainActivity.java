package com.example.trackcovid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trackcovid.ui.country.CovidCountry;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.trackcovid.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView=findViewById(R.id.nav_view);
        getDatafromServer();
        navView.setItemIconTintList(null);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        NavigationUI.setupWithNavController(navView, navController);
    }

    private void getDatafromServer(){

        Toast.makeText(MainActivity.this, "Calling API", Toast.LENGTH_SHORT).show();
        String url="https://api.rootnet.in/covid19-in/unofficial/covid19india.org/statewise";
//        CovidCountries variable=new ArrayList<>();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                progressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.e("TAG", "onResponse: " + response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        JSONArray jsonArray = jsonObject1.getJSONArray("statewise");
//                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            String name;
//                            CovidCountries.add(new CovidCountry(data.getString("Country"),
//                                    data.getString("cases")));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }

                }}},

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        progressBar.setVisibility(View.GONE);
                        Log.e("TAG", "onResponse: " + error);
                    }

                });


        Volley.newRequestQueue(MainActivity.this).add(stringRequest);
    }

}