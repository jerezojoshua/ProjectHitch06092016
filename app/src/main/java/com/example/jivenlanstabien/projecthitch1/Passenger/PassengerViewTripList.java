package com.example.jivenlanstabien.projecthitch1.Passenger;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.jivenlanstabien.projecthitch1.DriverConfig;
import com.example.jivenlanstabien.projecthitch1.R;
import com.example.jivenlanstabien.projecthitch1.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class PassengerViewTripList extends AppCompatActivity
        implements ListView.OnItemClickListener{

    private ListView listView;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_view_trip_list);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }

    private void showTrips(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(DriverConfig.TAG_TR_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String tripid = jo.getString(DriverConfig.TAG_TR_TRPID);
                //String scheduleid = jo.getString(DriverConfig.TAG_TR_SCHEDID);
                //String driverid = jo.getString(DriverConfig.TAG_DRID);
                //String vehicleid = jo.getString(DriverConfig.TAG_VEHID);
                String pickup = jo.getString(DriverConfig.TAG_TR_PUP);
                String dropoff = jo.getString(DriverConfig.TAG_TR_DOFF);
                String date = jo.getString(DriverConfig.TAG_TR_DATE);
                String time = jo.getString(DriverConfig.TAG_TR_TIME);

                HashMap<String,String> trip = new HashMap<>();
                trip.put(DriverConfig.TAG_TR_TRPID,tripid);
                //trip.put(DriverConfig.TAG_TR_SCHEDID,scheduleid);
                //trip.put(DriverConfig.TAG_DRID,driverid);
                //trip.put(DriverConfig.TAG_VEHID,vehicleid);
                trip.put(DriverConfig.TAG_TR_PUP,pickup);
                trip.put(DriverConfig.TAG_TR_DOFF,dropoff);
                trip.put(DriverConfig.TAG_TR_DATE,date);
                trip.put(DriverConfig.TAG_TR_TIME,time);
                list.add(trip);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                PassengerViewTripList.this, list, R.layout.trip_list_item,
                new String[]{DriverConfig.TAG_TR_TRPID, /*DriverConfig.TAG_TR_SCHEDID, DriverConfig.TAG_DRID, DriverConfig.TAG_VEHID,*/
                        DriverConfig.TAG_TR_PUP, DriverConfig.TAG_TR_DOFF, DriverConfig.TAG_TR_DATE, DriverConfig.TAG_TR_TIME},
                new int[]{R.id.tripid, /*R.id.scheduleid, R.id.driverid, R.id.vehicleid,*/ R.id.pickup, R.id.dropoff
                        , R.id.date, R.id.time});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(PassengerViewTripList.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showTrips();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(DriverConfig.URL_GET_ALL_TRIP2);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, booking_selection.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String tripid = map.get(DriverConfig.TAG_TR_TRPID);
        intent.putExtra(DriverConfig.TRP_ID,tripid);
        startActivity(intent);
    }
}
