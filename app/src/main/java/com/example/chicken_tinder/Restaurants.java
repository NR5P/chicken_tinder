package com.example.chicken_tinder;

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
}

class Opening_hours {
    public boolean open_now;
}
