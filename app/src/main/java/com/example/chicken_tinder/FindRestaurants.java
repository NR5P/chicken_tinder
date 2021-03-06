package com.example.chicken_tinder;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindRestaurants implements ActivityCompat.OnRequestPermissionsResultCallback, Runnable {
    LocationManager locationManager;
    SwipeActivity swipeActivity;
    Context context;
    double longitude;
    double latitude;
    int mileage;
    Handler handler;
    Restaurants restaurants;

    public FindRestaurants(Context context, int mileage, Handler handler) {
        this.context = context;
        this.mileage = mileage;
        this.handler = handler;
        this.swipeActivity = (SwipeActivity)context;
    }

    public void getRestaurants() {
        int meters = (int)(1609.344 * mileage);
        String response = "";
        String key = "AIzaSyB8kVd2fjsNcf4t4CwT5nCrM0LNfLGSE5M";
        getGpsCoordinance();
        String urlPath = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + latitude + "," + longitude + "&radius=" + meters + "&type=restaurant&key=" + key;
        Log.d("urlPath: ",urlPath);
        //Log.d("Longitud: ",String.valueOf(longitude));
        //Log.d("Latitude: ",String.valueOf(latitude));
        try {
            URLConnection connection = new URL(urlPath).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            response = scanner.useDelimiter("\\A").next();
            //Log.d("JSON: ", response);
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
        }
        restaurants = new Gson().fromJson(response, Restaurants.class);
        for (Result result : restaurants.results) {
            System.out.println(result.name);
        }
    }

    public ArrayList<Result> getRestaurantResults() {
        return restaurants.results;
    }

    private void getGpsCoordinance() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return TODO;
        }

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        longitude = location.getLongitude();
        latitude = location.getLatitude();
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void run() {
        getRestaurants();
        ArrayList<Result> restaurantResults = getRestaurantResults();
        for (Result r : restaurants.results) {
            swipeActivity.addRestaurant(r.name);
            Log.d("FindRestaurants","photo is: "+r.photos.get(0).photo_reference);
            Log.d("FindRestaurants","height is: "+r.photos.get(0).height);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    swipeActivity.refreshAdapter();
                }
            });
        }
    }
}
