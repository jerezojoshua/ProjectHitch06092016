package com.example.jivenlanstabien.projecthitch1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jivenlanstabien.projecthitch1.R;

public class vehicle_seats_availability extends AppCompatActivity {
    TextView sample;
    Spinner passenger_preference;
    EditText luggage_provision;
    Button seat1, seat2, seat3, seat4, seat5, seat6, seat7, save, seat0;
    TextView seat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_seats_availability);
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String passenger_preference_value = sharedPreferences.getString("saved_passenger_preference", "No Value");
        String luggage_provision_value = sharedPreferences.getString("saved_luggage_provision", "No Value");
        String seat1_value = sharedPreferences.getString("saved_seat1", "No Value");
        String seat2_value = sharedPreferences.getString("saved_seat2", "No Value");
        String seat3_value = sharedPreferences.getString("saved_seat3", "No Value");
        String seat4_value = sharedPreferences.getString("saved_seat4", "No Value");
        String seat5_value = sharedPreferences.getString("saved_seat5", "No Value");
        String seat6_value = sharedPreferences.getString("saved_seat6", "No Value");
        String seat7_value = sharedPreferences.getString("saved_seat7", "No Value");

        passenger_preference = (Spinner) findViewById(R.id.passenger_preference);
        luggage_provision = (EditText) findViewById(R.id.luggage_provision);
        seat1 = (Button) findViewById(R.id.Seat1);
        seat2 = (Button) findViewById(R.id.Seat2);
        seat3 = (Button) findViewById(R.id.Seat3);
        seat4 = (Button) findViewById(R.id.Seat4);
        seat5 = (Button) findViewById(R.id.Seat5);
        seat6 = (Button) findViewById(R.id.Seat6);
        seat7 = (Button) findViewById(R.id.Seat7);
        save = (Button) findViewById(R.id.save);
        seat = (TextView) findViewById(R.id.seat);
        seat0 = (Button) findViewById(R.id.Seat0);

        if(luggage_provision != null) {
            luggage_provision.setText(luggage_provision_value);
        }

        if (seat1_value == "Not Available") {
            seat1.setBackgroundColor(Color.RED);
            seat1.setText("Not Available");
        }
        else
        {
            seat1.setBackgroundColor(Color.parseColor("#00FF00"));
            seat1.setText("Available");
        }

        if (seat2_value == "Not Available") {
            seat2.setBackgroundColor(Color.RED);
            seat2.setText("Not Available");
        }
        else
        {
            seat2.setBackgroundColor(Color.parseColor("#00FF00"));
            seat2.setText("Available");
        }

        if (seat3_value == "Not Available") {
            seat3.setBackgroundColor(Color.RED);
            seat3.setText("Not Available");
        }
        else
        {
            seat3.setBackgroundColor(Color.parseColor("#00FF00"));
            seat3.setText("Available");
        }

        if (seat4_value == "Not Available") {
            seat4.setBackgroundColor(Color.RED);
            seat4.setText("Not Available");
        }
        else
        {
            seat4.setBackgroundColor(Color.parseColor("#00FF00"));
            seat4.setText("Available");
        }

        if (seat5_value == "Not Available") {
            seat5.setBackgroundColor(Color.RED);
            seat5.setText("Not Available");
        }
        else
        {
            seat5.setBackgroundColor(Color.parseColor("#00FF00"));
            seat5.setText("Available");
        }

        if (seat6_value == "Not Available") {
            seat6.setBackgroundColor(Color.RED);
            seat6.setText("Not Available");
        }
        else
        {
            seat6.setBackgroundColor(Color.parseColor("#00FF00"));
            seat6.setText("Available");
        }

        if (seat7_value == "Not Available") {
            seat7.setBackgroundColor(Color.RED);
            seat7.setText("Not Available");
        }
        else
        {
            seat7.setBackgroundColor(Color.parseColor("#00FF00"));
            seat7.setText("Available");
        }






        seat0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Driver seat cant avail his/her seat for the passenger", Toast.LENGTH_SHORT).show();
                seat.setText("Driver Seat (Front Right Seat)");
            }
        });


        seat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (seat1.getText() == "Not Available") {
                    seat1.setBackgroundColor(Color.parseColor("#00FF00"));
                    seat1.setText("Available");
                    Toast.makeText(getBaseContext(), "Front Right Seat is set to Available", Toast.LENGTH_SHORT).show();
                    seat.setText("Front Right Seat");

                } else {
                    seat1.setBackgroundColor(Color.RED);
                    seat1.setText("Not Available");
                    Toast.makeText(getBaseContext(), "Front Right Seat is set to not Available", Toast.LENGTH_SHORT).show();
                    seat.setText("Front Right Seat");
                }

            }
        });

        seat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (seat2.getText() == "Not Available") {
                    seat2.setBackgroundColor(Color.parseColor("#00FF00"));
                    seat2.setText("Available");
                    Toast.makeText(getBaseContext(), "Middle Left Seat is set to Available", Toast.LENGTH_SHORT).show();
                    seat.setText("Middle Left Seat");
                } else {
                    seat2.setBackgroundColor(Color.RED);
                    seat2.setText("Not Available");
                    Toast.makeText(getBaseContext(), "Middle Left Seat is set to not Available", Toast.LENGTH_SHORT).show();
                    seat.setText("Middle Left Seat");
                }
            }
        });

        seat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (seat3.getText() == "Not Available") {
                    seat3.setBackgroundColor(Color.parseColor("#00FF00"));
                    seat3.setText("Available");
                    Toast.makeText(getBaseContext(), "Middle Center Seat is set to Available", Toast.LENGTH_SHORT).show();
                    seat.setText("Middle Center Seat");
                } else {
                    seat3.setBackgroundColor(Color.RED);
                    seat3.setText("Not Available");
                    Toast.makeText(getBaseContext(), "Middle Center Seat is set to not Available", Toast.LENGTH_SHORT).show();
                    seat.setText("Middle Center Seat");
                }
            }
        });

        seat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seat4.getText() == "Not Available") {
                    seat4.setBackgroundColor(Color.parseColor("#00FF00"));
                    seat4.setText("Available");
                    Toast.makeText(getBaseContext(), "Middle Right Seat is set to Available", Toast.LENGTH_SHORT).show();
                    seat.setText("Middle Right Seat");
                } else {
                    seat4.setBackgroundColor(Color.RED);
                    seat4.setText("Not Available");
                    Toast.makeText(getBaseContext(), "Middle Right Seat is set to not Available", Toast.LENGTH_SHORT).show();
                    seat.setText("Middle Right Seat");
                }
            }
        });

        seat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (seat5.getText() == "Not Available") {
                    seat5.setBackgroundColor(Color.parseColor("#00FF00"));
                    seat5.setText("Available");
                    Toast.makeText(getBaseContext(), "Back Left Seat is set to Available", Toast.LENGTH_SHORT).show();
                    seat.setText("Back Left Seat");
                } else {
                    seat5.setBackgroundColor(Color.RED);
                    seat5.setText("Not Available");
                    Toast.makeText(getBaseContext(), "Back Left Seat is set to not Available", Toast.LENGTH_SHORT).show();
                    seat.setText("Back Left Seat");
                }
            }
        });

        seat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (seat6.getText() == "Not Available") {
                    seat6.setBackgroundColor(Color.parseColor("#00FF00"));
                    seat6.setText("Available");
                    Toast.makeText(getBaseContext(), "Back Center Seat is set to Available", Toast.LENGTH_SHORT).show();
                    seat.setText("Back Center Seat");
                } else {
                    seat6.setBackgroundColor(Color.RED);
                    seat6.setText("Not Available");
                    Toast.makeText(getBaseContext(), "Back Center Seat is set to not Available", Toast.LENGTH_SHORT).show();
                    seat.setText("Back Center Seat");
                }
            }
        });

        seat7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (seat7.getText() == "Not Available") {
                    seat7.setBackgroundColor(Color.parseColor("#00FF00"));
                    seat7.setText("Available");
                    Toast.makeText(getBaseContext(), "Back Right Seat is set to Available", Toast.LENGTH_SHORT).show();
                    seat.setText("Back Right Seat");
                } else {
                    seat7.setBackgroundColor(Color.RED);
                    seat7.setText("Not Available");
                    Toast.makeText(getBaseContext(), "Back Right Seat is set to not Available", Toast.LENGTH_SHORT).show();
                    seat.setText("Back Right Seat");
                }
            }
        });
    }
}
