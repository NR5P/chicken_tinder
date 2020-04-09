package com.example.chicken_tinder;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class CustomSwipeItemAdapter extends ArrayAdapter<String> {
    public CustomSwipeItemAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
