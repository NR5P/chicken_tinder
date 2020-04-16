package com.example.chicken_tinder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomSwipeItemAdapter extends ArrayAdapter<Result> {
    private Context context;
    int resource;
    String key = "AIzaSyB8kVd2fjsNcf4t4CwT5nCrM0LNfLGSE5M";
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

        TextView restaurantNameView = convertView.findViewById(R.id.restaurantName);
        ImageView imageView = convertView.findViewById(R.id.restaurantImage);
        restaurantNameView.setText(name);

        String photoUrlPath = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + photo.photo_reference + "&key=" + key;
        //Picasso.get().load(photoUrlPath).into(imageView); //TODO: need to get pic figured out before setting
        Log.d("photo reference: ",photoUrlPath);

        return restaurantNameView;
    }
}
