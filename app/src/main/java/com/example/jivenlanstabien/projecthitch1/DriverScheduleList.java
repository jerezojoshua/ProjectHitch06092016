package com.example.jivenlanstabien.projecthitch1;


import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jivenlanstabien.projecthitch1.Login.Login;
import com.example.jivenlanstabien.projecthitch1.Passenger.home_screen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class DriverScheduleList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ListView.OnItemClickListener {

    private ListView listView;

    private String JSON_STRING;
    private String JSON_STRING2;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_schedule_navigation_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        username = sharedPreferences.getString("email_login", "");
        getJSON2();

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();

        Button addSched = (Button) findViewById(R.id.add);
        addSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DriverScheduleList.this, AddSchedule.class);
                startActivity(intent);
                finish();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void showSchedule(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(DriverConfig.TAG_SC_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String scheduleid = jo.getString(DriverConfig.TAG_SC_SCHEDID);
                //String driverid = jo.getString(DriverConfig.TAG_SC_DRID);
                //String vehicleid = jo.getString(DriverConfig.TAG_SC_VEHID);
                String pickup = jo.getString(DriverConfig.TAG_SC_PUP);
                String dropoff = jo.getString(DriverConfig.TAG_SC_DOFF);
                String date = jo.getString(DriverConfig.TAG_SC_DATE);
                String time = jo.getString(DriverConfig.TAG_SC_TIME);

                HashMap<String,String> schedule = new HashMap<>();
                schedule.put(DriverConfig.TAG_SC_SCHEDID,scheduleid);
                //schedule.put(DriverConfig.TAG_SC_DRID,driverid);
                //schedule.put(DriverConfig.TAG_SC_VEHID,vehicleid);
                schedule.put(DriverConfig.TAG_SC_PUP,pickup);
                schedule.put(DriverConfig.TAG_SC_DOFF,dropoff);
                schedule.put(DriverConfig.TAG_SC_DATE,date);
                schedule.put(DriverConfig.TAG_SC_TIME,time);
                list.add(schedule);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                DriverScheduleList.this, list, R.layout.schedule_list_item,
                new String[]{DriverConfig.TAG_SC_SCHEDID, DriverConfig.TAG_SC_PUP, DriverConfig.TAG_SC_DOFF, DriverConfig.TAG_SC_DATE, DriverConfig.TAG_SC_TIME},
                new int[]{R.id.scheduleid, R.id.pickup, R.id.dropoff, R.id.date, R.id.time});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DriverScheduleList.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showSchedule();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                SharedPreferences sharedPreferences = getSharedPreferences("driver", MODE_PRIVATE);
                String driverid = sharedPreferences.getString("driverid", "");
                String s = rh.sendGetRequestParam(DriverConfig.URL_GET_ALL_SCHED,driverid);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ViewSchedule.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String scheduleid = map.get(DriverConfig.TAG_SC_SCHEDID);
        intent.putExtra(DriverConfig.SCHED_ID,scheduleid);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.driver_navigation_menu, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_personal_info) {

            Intent intent = new Intent(this, driver_home_screen.class);
            startActivity(intent);

        } else if (id == R.id.nav_driver_home_screen) {

            Intent intent = new Intent(this, driver_profile.class);
            startActivity(intent);

        } else if (id == R.id.nav_requirements) {

            Intent intent = new Intent(this, driver_requirements.class);
            startActivity(intent);


        } else if (id == R.id.nav_driver_schedule) {

            Intent intent = new Intent(this, DriverScheduleList.class);
            startActivity(intent);


        } else if (id == R.id.nav_trip) {

            Intent intent = new Intent(this, DriverTripList.class);
            startActivity(intent);


        } else if (id == R.id.nav_vehicle) {

            Intent intent = new Intent(this, vehicle_records.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(DriverScheduleList.this);

            // Setting Dialog Title
            alertDialog.setTitle("Logout...");

            // Setting Dialog Message
            alertDialog.setMessage("Are you sure you want to logout?");


            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {

                    // Showing Alert Message

                    SharedPreferences settings = getSharedPreferences("login", MODE_PRIVATE);
                    settings.edit().clear().commit();
                    SharedPreferences settings2 = getSharedPreferences("driver", MODE_PRIVATE);
                    settings2.edit().clear().commit();
                    SharedPreferences settings3 = getSharedPreferences("passenger", MODE_PRIVATE);
                    settings3.edit().clear().commit();
                    Toast.makeText(DriverScheduleList.this, "Successfully Logged Out!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(DriverScheduleList.this, Login.class);
                    startActivity(intent);
                }
            });

            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialog.show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void showuser() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(JSON_STRING2);
            JSONArray result = jsonObject.getJSONArray(DriverConfig.TAG_TR_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String firstname = jo.getString(DriverConfig.Firstname);
                String lastname = jo.getString(DriverConfig.Lastname);

                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);



                View hview = navigationView.inflateHeaderView(R.layout.nav_header_driver_navigation_menu);
                TextView email = (TextView)hview.findViewById(R.id.email_header);
                TextView name = (TextView)hview.findViewById(R.id.name_header);
                name.setText(firstname+" "+lastname);
                email.setText(username);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void getJSON2(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DriverScheduleList.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING2 = s;
                showuser();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(DriverConfig.URL_GET_USERINFO,username);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {

            Intent intent = new Intent(this, home_screen.class);
            startActivity(intent);

        } return true;
        }
}