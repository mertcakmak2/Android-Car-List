package com.example.carsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.Display;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarModelsActivity extends AppCompatActivity {

    ListView listView;
    List<carModel> carModelList;
    int carid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_models);

        listView = (ListView) findViewById(R.id.list_prov2);
        carModelList = new ArrayList<>();
        showList();

        Intent intent = getIntent();
        carid = intent.getIntExtra("carId", 61);
    }
    private void showList(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://172.20.10.5/carApp/models.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("arabaModel");
                            for(int i = 0; i<array.length(); i++){
                               JSONObject carModel = array.getJSONObject(i);
                                carModel c = new carModel(carModel.getString("id"),
                                        carModel.getString("carid"),
                                        carModel.getString("carmodel"),
                                        carModel.getString("carImageUrl"));
                                carModelList.add(c);
                            }
                            ModelAdapter adapter = new ModelAdapter(carModelList,getApplicationContext());
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("carid", String.valueOf(carid));
                return params;
            }
        };
        Handler.getInstance(getApplicationContext()).addToRequestQue(stringRequest);

    }
}
