package com.example.jivenlanstabien.projecthitch1;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jivenlanstabien.projecthitch1.Login.Login;
import com.example.jivenlanstabien.projecthitch1.Passenger.home_screen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class vehicle_records extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txtVehicleID, txtBody, txtMake, txtSeries, txtYear, txtColor, txtFuel,
            txtEngine, txtChassis, txtPlate, txtStatus;

    private String JSON_STRING2;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_vehicle_navigation_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        username = sharedPreferences.getString("email_login", "");
        getJSON2();

        txtVehicleID = (TextView) findViewById(R.id.txtVehicleID);
        txtBody = (TextView) findViewById(R.id.txtBody);
        txtMake = (TextView) findViewById(R.id.txtModel);
        txtSeries = (TextView) findViewById(R.id.txtBrand);
        txtYear = (TextView) findViewById(R.id.txtYear);
        txtColor = (TextView) findViewById(R.id.txtColor);
        txtChassis = (TextView) findViewById(R.id.txtChassis);
        txtEngine = (TextView) findViewById(R.id.txtEngine);
        txtFuel = (TextView) findViewById(R.id.txtFuel);
        txtPlate = (TextView) findViewById(R.id.txtPlate);
        txtStatus = (TextView) findViewById(R.id.txtStatus);

        getVehicle();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void getVehicle(){
        class GetVehicle extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(vehicle_records.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showVehicle(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                SharedPreferences sharedPreferences = getSharedPreferences("driver", MODE_PRIVATE);
                String driverid = sharedPreferences.getString("driverid", "");
                String s = rh.sendGetRequestParam(VehicleConfig.URL_GET_VEHICLE,driverid);
                return s;
            }
        }
        GetVehicle gv = new GetVehicle();
        gv.execute();
    }
    private void showVehicle(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(VehicleConfig.TAG_VEHICLE_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String driverid = c.getString(VehicleConfig.TAG_VEHICLE_DRID);
            String vehicleid = c.getString(VehicleConfig.TAG_VEHICLE_VID);
            String bodytype = c.getString(VehicleConfig.TAG_VEHICLE_BODY);
            String make = c.getString(VehicleConfig.TAG_VEHICLE_MAKE);
            String series = c.getString(VehicleConfig.TAG_VEHICLE_SERIES);
            String year = c.getString(VehicleConfig.TAG_VEHICLE_YEAR);
            String color = c.getString(VehicleConfig.TAG_VEHICLE_COLOR);
            String fuel = c.getString(VehicleConfig.TAG_VEHICLE_FUEL);
            String engine = c.getString(VehicleConfig.TAG_VEHICLE_ENGINE);
            String chassis = c.getString(VehicleConfig.TAG_VEHICLE_CHASSIS);
            String plate = c.getString(VehicleConfig.TAG_VEHICLE_PLATE);
            String status = c.getString(VehicleConfig.TAG_VEHICLE_STATUS);

            txtVehicleID.setText(vehicleid);
            txtBody.setText(bodytype);
            txtMake.setText(make);
            txtSeries.setText(series);
            txtYear.setText(year);
            txtColor.setText(color);
            txtFuel.setText(fuel);
            txtEngine.setText(engine);
            txtChassis.setText(chassis);
            txtPlate.setText(plate);
            txtStatus.setText(status);

        } catch (JSONException e) {
            e.printStackTrace();
        }
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
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(vehicle_records.this);

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
                    Toast.makeText(vehicle_records.this, "Successfully Logged Out!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(vehicle_records.this, Login.class);
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
                loading = ProgressDialog.show(vehicle_records.this,"Fetching Data","Wait...",false,false);
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




