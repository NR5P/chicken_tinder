package com.example.chicken_tinder;

import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Restaurants {
    public ArrayList<Result> results;
}

class Result {
    public String id;
    public String place_id;
    public String name;
    public String vicinity;

    public Opening_hours opening_hours;
    public ArrayList<photos> photos;

    public String getName() {
        return name;
    }

    public photos getPhotos() {
        return photos.get(0);
    }
}

class Opening_hours {
    public boolean open_now;
}

class photos {
    String photo_reference;
    int height;
    int width;
}
