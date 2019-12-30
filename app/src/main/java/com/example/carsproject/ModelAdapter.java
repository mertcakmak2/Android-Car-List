package com.example.carsproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ModelAdapter extends ArrayAdapter<carModel> {
    private List<carModel> carModelList;
    private Context mContext;

    public ModelAdapter(List<carModel> P, Context c){
        super(c, R.layout.listmodel, P);
        this.carModelList = P;
        this.mContext = c;
    }
    public long getItemId(int position){
        return position;
    }

    @Nullable
    @Override
    public carModel getItem(int position) {
        return carModelList.get(position);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.listmodel,null,true);
        TextView modelName = (TextView) view.findViewById(R.id.modelName);
        ImageView image = (ImageView) view.findViewById(R.id.modelImg);
        carModel carModel = carModelList.get(position);
        modelName.setText(carModel.getCarmodel());
        Picasso.get().load(carModel.getCarimageurl()).into(image);

        return view;
    }
}
