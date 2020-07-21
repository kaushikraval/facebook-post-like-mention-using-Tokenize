package com.magitt.facebookpost_design;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayData extends AppCompatActivity {
    private TextView Showdata;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaydata);

        Showdata = findViewById(R.id.ShowData);

        String data = getIntent().getStringExtra("kaushik");
        Showdata.setText(data);
    }
}
