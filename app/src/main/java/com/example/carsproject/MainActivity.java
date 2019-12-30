package com.example.carsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    Button loginButton;
    TextView usernameInput, passwordInput;
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.login_btn);
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        imageview = findViewById(R.id.imageView);
        String url = "https://www.teslarati.com/wp-content/uploads/2019/11/Tesla-Cybertruck-unveiling.jpg";
        Picasso.get().load(url).into(imageview);
    }

    public void Login(View view) {
        final Intent intent = new Intent(MainActivity.this,CarsActivity.class);
        StringRequest request = new StringRequest(Request.Method.POST, "http://172.20.10.5/carApp/login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.length()>0){
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext() ,"Yanlış şifre yada kullanıcı adı",Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username", usernameInput.getText().toString());
                params.put("password", passwordInput.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }


}

