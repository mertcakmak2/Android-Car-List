package com.example.carsproject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Handler {
    public static Handler mInstance;
    private RequestQueue requestQueue;
    private static Context mContext;

    private Handler(Context context){
        mContext = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized Handler getInstance(Context context){
        if(mInstance == null){
            mInstance =  new Handler(context);
        }
        return mInstance;
    }
    public <T> void  addToRequestQue(Request<T> request){
        requestQueue.add(request);
    }
}
