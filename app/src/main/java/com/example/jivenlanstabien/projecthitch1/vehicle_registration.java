package com.example.jivenlanstabien.projecthitch1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jivenlanstabien.projecthitch1.R;

public class vehicle_registration extends AppCompatActivity {
    Spinner bodytype;
    Spinner make;
    EditText model;
    Spinner year;
    EditText platenumber;
    EditText engine;
    Spinner fuel_type;
    EditText vehicle_color;
    EditText chassis;

    Context context = this;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_registration);
        bodytype = (Spinner) findViewById(R.id.body_type);
        make = (Spinner) findViewById(R.id.make);
        model = (EditText) findViewById(R.id.model);
        year = (Spinner) findViewById(R.id.year);
        vehicle_color = (EditText) findViewById(R.id.vehicle_color);
        fuel_type = (Spinner) findViewById(R.id.fuel_type);
        platenumber = (EditText) findViewById(R.id.plate_number);
        engine = (EditText) findViewById(R.id.engine);
        chassis = (EditText) findViewById(R.id.chassis);
    }

    public void addInfo (View view) {


    }
}
