package com.example.chicken_tinder;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CustomSwipeItemAdapter extends ArrayAdapter<Result> {
    private Context context;
    public CustomSwipeItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Result> objects) {
        super(context, resource, objects);
    }
}
