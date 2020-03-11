package com.example.chicken_tinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class ChooseSingleOrGroupActivity extends AppCompatActivity {
    private static final String MILEAGE = "MILEAGE";
    private ImageButton btnSubmitMiles;
    private SeekBar seekMileage;
    private TextView txtMileage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_single_or_group);

        btnSubmitMiles = findViewById(R.id.btnSubmitMiles);
        seekMileage = findViewById(R.id.seekMileage);
        txtMileage = findViewById(R.id.txtMileage);

        txtMileage.setText(String.valueOf(seekMileage.getProgress()));

        seekMileage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtMileage.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnSubmitMiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseSingleOrGroupActivity.this, SwipeActivity.class);
                intent.putExtra(ChooseSingleOrGroupActivity.MILEAGE, seekMileage.getProgress());
                startActivity(intent);
            }
        });
    }
}
