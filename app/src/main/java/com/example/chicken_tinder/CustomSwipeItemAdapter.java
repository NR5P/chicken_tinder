package com.example.chicken_tinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomSwipeItemAdapter extends ArrayAdapter<Result> {
    private Context context;
    int resource;
    public CustomSwipeItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Result> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        photos photo = getItem(position).getPhotos();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource,parent, false);

        TextView restaurantNameView = (TextView) convertView.findViewById(R.id.restaurantName);
        restaurantNameView.setText(name);

        return restaurantNameView;
    }
}
