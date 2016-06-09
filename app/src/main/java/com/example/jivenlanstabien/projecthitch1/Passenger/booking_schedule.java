package com.example.jivenlanstabien.projecthitch1.Passenger;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jivenlanstabien.projecthitch1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class booking_schedule extends AppCompatActivity {


    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    SchedDataAdapter schedDataAdapter;
    ListView listView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_booking_schedule);

        listView =(ListView) findViewById(R.id.listview);
        schedDataAdapter = new SchedDataAdapter(this,R.layout.row_layout);
        listView.setAdapter(schedDataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(booking_schedule.this,booking_selection.class);
                startActivity(intent);
            }
        });

        json_string = getIntent().getExtras().getString("json_data");
        try
        {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("Response");

            int count=0;
            String pickup,dropoff,date,time;

            while(count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                pickup = JO.getString("PickUp");
                dropoff = JO.getString("DropOff");
                date = JO.getString("Date");
                time = JO.getString("Time");

                SchedData schedData = new SchedData(pickup,dropoff,date,time);
                schedDataAdapter.add(schedData);
                count++;
            }
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id==android.R.id.home) {
            finish();
        }
        return true;
    }
}
