package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String lat;
    private String lon;
    private RequestQueue requestQueue;
    private String city;
    private String weatherInfo;
    private TextView text;
    private String url0;
    private String url1;
    private EditText cityText;
    public void pridobiKoordinate(View view){
        if (view != null){
            city = cityText.getText().toString().substring(19);
            /*if (city == null){
                text.setText("Niste vnesli lokacije.");
            }
            else {*/
            url0 = "http://api.openweathermap.org/geo/1.0/direct?q="+city+"&appid=946ba6b18924b417a87ba084a4758bde";
            JsonArrayRequest request = new JsonArrayRequest(url0, jsonArrayListener, errorListener);
            requestQueue.add(request);
            text.setText("Pošiljanje 3ahteve na: "+url0);
            //}
        }
    }
    public void koncniPodatki(){
        //if (view != null){
        Integer d = (3-1);
        Integer p = (6-1);
        String v = d.toString()+"."+p.toString();
        url1 = "https://api.openweathermap.org/data/"+v+"/weather?lat="+lat+"&lon="+lon+"&appid=946ba6b18924b417a87ba084a4758bde";
        JsonArrayRequest r = new JsonArrayRequest(url1,jsonArrayListener,errorListener);
        requestQueue.add(r);
    }


    public Response.Listener<JSONArray> jsonArrayListener = new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            text.setText("Od3iv na 3ahtevo: ");
            ArrayList<String> data = new ArrayList<String>();
            boolean check = false;
            String mesto = "";
            try {
                JSONObject o = response.getJSONObject(0);
                mesto = o.getString("name");
                /*String c = o.getString("coord");
                if (c != null){
                    weatherInfo = o.getString("weather");
                    check = true;
                    text.setText(weatherInfo);
                }*/
            } catch (JSONException e) {
                e.printStackTrace();
            }
            text.setText("Od3iv na 3ahtevo: "+mesto);
            /*if (check == false) {
                text.setText("Dol3ina: "+response.length());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        text.setText(i+": "+object.getString("name"));
                        String l0 = object.getString("lon");
                        String l1 = object.getString("lat");
                        data.add(l0+";"+l1);
                        text.setText("LAT: "+l0+"LON: "+l1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //koncniPodatki();
            }*/
        }
    };

    public Response.ErrorListener errorListener = new Response.ErrorListener(){
        @Override
        public void onErrorResponse(VolleyError error){
            Log.d("REST error",error.getMessage());
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //city = (String) findViewById(R.id.city).toString();
        cityText = (EditText) findViewById(R.id.city);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        text = (TextView) findViewById(R.id.text);
    }
}
