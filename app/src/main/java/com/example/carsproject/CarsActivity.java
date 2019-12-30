package com.example.carsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CarsActivity extends AppCompatActivity {

    ListView listView;
    List<Car> carList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
        listView = (ListView) findViewById(R.id.list_prov);
        carList = new ArrayList<>();
        showList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CarsActivity.this,CarModelsActivity.class);
                int pos = position+1;
                intent.putExtra("carId", pos);
                startActivity(intent);
            }
        });
    }

    private void showList(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://172.20.10.5/carApp/cars.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("arabalar");
                            for(int i = 0; i<array.length(); i++){
                                JSONObject carObj = array.getJSONObject(i);
                                Car car = new Car(carObj.getString("id"),
                                        carObj.getString("carBrand"),
                                        carObj.getString("carImageUrl"),
                                        carObj.getString("carDescription"));
                                carList.add(car);
                            }
                            ProvinsiAdapter adapter = new ProvinsiAdapter(carList,getApplicationContext());
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

        };
        Handler.getInstance(getApplicationContext()).addToRequestQue(stringRequest);

    }
}
