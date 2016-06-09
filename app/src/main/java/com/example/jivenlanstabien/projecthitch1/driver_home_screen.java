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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jivenlanstabien.projecthitch1.Login.Login;
import com.example.jivenlanstabien.projecthitch1.Passenger.home_screen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class driver_home_screen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txtName, txtGender, txtBirthDate, txtDriverID;
    Spinner spnMaritalStats;
    EditText txtAddress1, txtAddress2, txtCity, txtCountry, txtContactNumber, txtAlternateNumber, txtEmerPerson, txtEmerPhone;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_home_screen_navigation_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        username = sharedPreferences.getString("email_login", "");


        txtDriverID = (TextView) findViewById(R.id.txtDriverID);
        txtName = (TextView) findViewById(R.id.txtName);
        txtGender = (TextView) findViewById(R.id.txtGender);
        txtBirthDate = (TextView) findViewById(R.id.txtBirthDate);
        spnMaritalStats = (Spinner) findViewById(R.id.spnMaritalStats);
        txtAddress1 = (EditText) findViewById(R.id.txtAddress1);
        txtAddress2 = (EditText) findViewById(R.id.txtAddress2);
        txtCity = (EditText) findViewById(R.id.txtCity);
        txtCountry = (EditText) findViewById(R.id.txtCountry);
        txtContactNumber = (EditText) findViewById(R.id.txtContactNumber);
        txtAlternateNumber = (EditText) findViewById(R.id.txtAlterContactNumber);
        txtEmerPerson = (EditText) findViewById(R.id.txtEmergeContact);
        txtEmerPhone = (EditText) findViewById(R.id.txtEmergeContactNo);

        getUser();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void getUser() {
        class GetUser extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(driver_home_screen.this, "Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showUser(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                SharedPreferences sharedPreferences = getSharedPreferences("driver", MODE_PRIVATE);
                String driverid = sharedPreferences.getString("driverid", "");
                String s = rh.sendGetRequestParam(DriverConfig.URL_GET_USER, driverid);
                return s;
            }
        }
        GetUser gu = new GetUser();
        gu.execute();
    }

    private void showUser(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(DriverConfig.TAG_TR_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String driverid = c.getString(DriverConfig.TAG_USER_DRIVERID);
            String lastname = c.getString(DriverConfig.TAG_USER_LASTNAME);
            String middlename = c.getString(DriverConfig.TAG_USER_MIDDLENAME);
            String firstname = c.getString(DriverConfig.TAG_USER_FIRSTNAME);
            String birthdate = c.getString(DriverConfig.TAG_USER_BIRTHDATE);
            String gender = c.getString(DriverConfig.TAG_USER_GENDER);
            String maritalstatus = c.getString(DriverConfig.TAG_USER_MARITALSTATUS);
            String address1 = c.getString(DriverConfig.TAG_USER_ADDRESS1);
            String address2 = c.getString(DriverConfig.TAG_USER_ADDRESS2);
            String city = c.getString(DriverConfig.TAG_USER_CITY);
            String country = c.getString(DriverConfig.TAG_USER_COUNTRY);
            String mainphone = c.getString(DriverConfig.TAG_USER_MAINPHONE);
            String alterphone = c.getString(DriverConfig.TAG_USER_ALTERNATEPHONE);
            String emercontact = c.getString(DriverConfig.TAG_USER_EMERGENCYCONTACT);
            String emerphone = c.getString(DriverConfig.TAG_USER_CONTACTPHONE);


            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);


            View hview = navigationView.inflateHeaderView(R.layout.nav_header_driver_navigation_menu);
            TextView email = (TextView) hview.findViewById(R.id.email_header);
            TextView name = (TextView) hview.findViewById(R.id.name_header);
            name.setText(firstname + " " + lastname);
            email.setText(username);

            txtDriverID.setText(driverid);
            txtName.setText(firstname + " " + middlename + " " + lastname);
            //txtBirthDate.setText(birthdate);
            txtGender.setText(gender);
            spnMaritalStats.setSelection(((ArrayAdapter) spnMaritalStats.getAdapter()).getPosition(maritalstatus));
            txtAddress1.setText(address1);
            txtAddress2.setText(address2);
            txtCity.setText(city);
            txtCountry.setText(country);
            txtContactNumber.setText(mainphone);
            txtAlternateNumber.setText(alterphone);
            txtEmerPerson.setText(emercontact);
            txtEmerPhone.setText(emerphone);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateTrip() {
        final String maritalstats = spnMaritalStats.getSelectedItem().toString();
        final String address1 = txtAddress1.getText().toString().trim();
        final String address2 = txtAddress2.getText().toString().trim();
        final String city = txtCity.getText().toString().trim();
        final String country = txtCountry.getText().toString().trim();
        final String mainphone = txtContactNumber.getText().toString().trim();
        final String alterphone = txtAlternateNumber.getText().toString().trim();
        final String emercontact = txtEmerPerson.getText().toString().trim();
        final String emerphone = txtEmerPhone.getText().toString().trim();

        class UpdateTrip extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(driver_home_screen.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(driver_home_screen.this, "Driver Information Successfully Updated!", Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {

                SharedPreferences sharedPreferences = getSharedPreferences("driver", MODE_PRIVATE);
                String driverid = sharedPreferences.getString("driverid", "");

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(DriverConfig.KEY_USER_DRIVERID, driverid);
                hashMap.put(DriverConfig.KEY_USER_MARITALSTATUS, maritalstats);
                hashMap.put(DriverConfig.KEY_USER_ADDRESS1, address1);
                hashMap.put(DriverConfig.KEY_USER_ADDRESS2, address2);
                hashMap.put(DriverConfig.KEY_USER_CITY, city);
                hashMap.put(DriverConfig.KEY_USER_COUNTRY, country);
                hashMap.put(DriverConfig.KEY_USER_MAINPHONE, mainphone);
                hashMap.put(DriverConfig.KEY_USER_ALTERNATEPHONE, alterphone);
                hashMap.put(DriverConfig.KEY_USER_EMERGENCYCONTACT, emercontact);
                hashMap.put(DriverConfig.KEY_USER_CONTACTPHONE, emerphone);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(DriverConfig.URL_UPDATE_USER, hashMap);

                return s;
            }
        }
        UpdateTrip ut = new UpdateTrip();
        ut.execute();
    }

    public void save(View view) {
        updateTrip();
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
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(driver_home_screen.this);

            // Setting Dialog Title
            alertDialog.setTitle("Logout...");

            // Setting Dialog Message
            alertDialog.setMessage("Are you sure you want to logout?");


            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    // Showing Alert Message

                    SharedPreferences settings = getSharedPreferences("login", MODE_PRIVATE);
                    settings.edit().clear().commit();
                    Toast.makeText(driver_home_screen.this, "Successfully Logged Out!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(driver_home_screen.this, Login.class);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {

            Intent intent = new Intent(this, home_screen.class);
            startActivity(intent);
        }
            return true;
        }

}

