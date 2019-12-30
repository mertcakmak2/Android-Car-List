package com.example.carsproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class ProvinsiAdapter extends ArrayAdapter<Car> {
    private List<Car> carList;
    private Context mContext;

    public ProvinsiAdapter(List<Car> P, Context c){
        super(c, R.layout.listprovinsi, P);
        this.carList = P;
        this.mContext = c;
    }
    public long getItemId(int position){
        return position;
    }

    @Nullable
    @Override
    public Car getItem(int position) {
        return carList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.listprovinsi,null,true);
        TextView description = (TextView) view.findViewById(R.id.carDesc);
        TextView name = (TextView) view.findViewById(R.id.carBrand);
        ImageView image = (ImageView) view.findViewById(R.id.carImage);
        Car car = carList.get(position);
        name.setText(car.getName());
        description.setText(car.getDescription());

        Picasso.get().load(car.getImageurl()).into(image);

        return view;
    }

}
