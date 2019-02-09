package com.mercury1089.scoutingapp2019;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button fieldSideLeft = View.findViewById(R.id.FieldSideLeft);
        fieldSideLeft.setBackgroundColor(getResources().getColor(R.color.light));
        fieldSideLeft.setTextColor(getResources().getColor(R.color.grey));

        Button fieldSideRight = View.findViewById(R.id.FieldSideRight);
        fieldSideRight.setBackgroundColor(getResources().getColor(R.color.light));
        fieldSideRight.setTextColor(getResources().getColor(R.color.grey));
    }
}
