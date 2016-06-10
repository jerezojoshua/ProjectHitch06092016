package com.example.jivenlanstabien.projecthitch1.Passenger;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jivenlanstabien.projecthitch1.DriverConfig;
import com.example.jivenlanstabien.projecthitch1.Login.Login;
import com.example.jivenlanstabien.projecthitch1.R;
import com.example.jivenlanstabien.projecthitch1.RequestHandler;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class passenger_home_screen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String JSON_STRING2;
    private String JSON_STRING3;
    private String JSON_STRING4;
    String username;
    TextView passengerid2, txtName, txtGender, txtBirthdate, txtAddress1, txtAddress2, txtCity, txtCountry,txtContactNumber, txtAlterContactNumber, txtEmergeContact, txtEmergeContactNo, discountid, school;
    Spinner maritalstatus, passengertype;
    String passengerid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger_home_screen_navigation_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SharedPreferences sharedPreferences2 = getSharedPreferences("login", MODE_PRIVATE);
        username = sharedPreferences2.getString("email_login", "");
        passengerid2 = (TextView)findViewById(R.id.txtpass);
        txtName = (TextView)findViewById(R.id.txtName);
        txtGender = (TextView)findViewById(R.id.txtGender);
        txtBirthdate = (TextView)findViewById(R.id.txtBirthDate);
        txtAddress1 = (TextView)findViewById(R.id.txtAddress1);
        txtAddress2 = (TextView)findViewById(R.id.txtAddress2);
        txtCity = (TextView)findViewById(R.id.txtCity);
        txtCountry = (TextView)findViewById(R.id.txtCountry);
        txtContactNumber = (TextView)findViewById(R.id.txtContactNumber);
        txtAlterContactNumber = (TextView)findViewById(R.id.txtAlterContactNumber);
        txtEmergeContact = (TextView)findViewById(R.id.txtEmergeContact);
        txtEmergeContactNo = (TextView)findViewById(R.id.txtEmergeContactNo);
        discountid = (TextView) findViewById(R.id.discountid);
        school = (TextView) findViewById(R.id.school);
        maritalstatus = (Spinner) findViewById(R.id.maritalstatus);
        passengertype = (Spinner)findViewById(R.id.Passenger_type_spinner);

        //spnGender = (Spinner)findViewById(R.id.spnGender);



        getJSON2();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void save(View view)
    {
        String getpassengerid = passengerid2.getText().toString().trim();
        String getaddress1 = txtAddress1.getText().toString().trim();
        String getaddress2 = txtAddress2.getText().toString().trim();
        String getcity = txtCity.getText().toString().trim();
        String getcountry = txtCountry.getText().toString().trim();
        String getcontactnumber = txtContactNumber.getText().toString().trim();
        String getalternatecontactnumber = txtAlterContactNumber.getText().toString().trim();
        String getemergencycontact = txtEmergeContact.getText().toString().trim();
        String getemergencycontactnumber = txtEmergeContactNo.getText().toString().trim();
        String getmaritalstatus = maritalstatus.getSelectedItem().toString().trim();
        String getpassengertype = passengertype.getSelectedItem().toString().trim();
        String getdiscountid = discountid.getText().toString().trim();
        String getschool = school.getText().toString().trim();






        String method = "register";
        personalBackgroundTask2 backgroundTask = new personalBackgroundTask2(this);
        backgroundTask.execute(method, getpassengerid, getaddress1, getaddress2, getcity, getcountry, getcontactnumber, getalternatecontactnumber, getemergencycontact, getemergencycontactnumber, getmaritalstatus, getpassengertype, getdiscountid,getschool);
        Toast.makeText(this,"Saved Successfully",Toast.LENGTH_LONG).show();
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
        getMenuInflater().inflate(R.menu.passenger_navigation_menu, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();


        if (id == R.id.nav_profile) {

            Intent intent = new Intent(this, passenger_home_screen.class);
            startActivity(intent);

        } else if (id == R.id.nav_booking) {

            Intent intent = new Intent(this, passenger_booking.class);
            startActivity(intent);


        }  else if (id == R.id.nav_logout) {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(passenger_home_screen.this);

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
                    Toast.makeText(passenger_home_screen.this, "Successfully Logged Out!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(passenger_home_screen.this, Login.class);
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
                passengerid = jo.getString(DriverConfig.passengerid);
                String birthdate = jo.getString(DriverConfig.birthdate);
                String gender = jo.getString(DriverConfig.gender);
                String maritalstatus2 = jo.getString(DriverConfig.maritalstatus);
                String address1 = jo.getString(DriverConfig.address1);
                String city = jo.getString(DriverConfig.city);
                String address2 = jo.getString(DriverConfig.address2);
                String country = jo.getString(DriverConfig.country);
                String mainphone = jo.getString(DriverConfig.mainphone);
                String alternatephone = jo.getString(DriverConfig.alternatephone);
                String emergencycontact = jo.getString(DriverConfig.emergencycontact);
                String emergencycontacttel = jo.getString(DriverConfig.emergencycontacttel);
                String maritalstatus3 = maritalstatus2.trim();

                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);



                View hview = navigationView.inflateHeaderView(R.layout.nav_header_passenger_navigation_menu);
                TextView email = (TextView)hview.findViewById(R.id.email_header);
                TextView name = (TextView)hview.findViewById(R.id.name_header);
                name.setText(firstname+" "+lastname);
                email.setText(username);
                passengerid2.setText(passengerid);
                txtName.setText(firstname+" "+lastname);
                txtGender.setText(gender);
                txtBirthdate.setText(birthdate);
                txtAddress1.setText(address1);
                txtAddress2.setText(address2);
                txtCity.setText(city);
                txtCountry.setText(country);
                txtContactNumber.setText(mainphone);
                txtAlterContactNumber.setText(alternatephone);
                txtEmergeContact.setText(emergencycontact);
                txtEmergeContactNo.setText(emergencycontacttel);


                if (maritalstatus3.equals("Single"))
                {
                    maritalstatus.setSelection(0);
                }
                else if(maritalstatus3.equals("Married"))
                {
                    maritalstatus.setSelection(1);
                }
                else if(maritalstatus3.equals("Widowed"))
                {
                    maritalstatus.setSelection(2);
                }
                else if(maritalstatus3.equals("Divorced"))
                {
                    maritalstatus.setSelection(3);
                }
                else if(maritalstatus3.equals("Separated"))
                {
                    maritalstatus.setSelection(4);
                }

                getJSON3();


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
                loading = ProgressDialog.show(passenger_home_screen.this,"Fetching Data","Wait...",false,false);
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
    private void getJSON3(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                JSON_STRING3 = s;
                showpassengerinfo();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(DriverConfig.URL_GET_PASSENGERINFO,passengerid);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
    private void showpassengerinfo() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(JSON_STRING3);
            JSONArray result = jsonObject.getJSONArray(DriverConfig.TAG_TR_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String discountid2 = jo.getString(DriverConfig.discountid);
                String school2 = jo.getString(DriverConfig.school);
                String passengertype2 = jo.getString(DriverConfig.passengertype);


                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);

                discountid.setText(discountid2);
                school.setText(school2);

                if (passengertype2.equals("Regular"))
                {
                    passengertype.setSelection(0);
                }
                else if(passengertype2.equals("Student"))
                {
                    passengertype.setSelection(1);
                }
                else if(passengertype2.equals("Senior"))
                {
                    passengertype.setSelection(2);
                }
                else if(passengertype2.equals("PWD"))
                {
                    passengertype.setSelection(3);
                }
                else if(passengertype2.equals("Infant"))
                {
                    passengertype.setSelection(4);
                }
                else if(passengertype2.equals("Child"))
                {
                    passengertype.setSelection(5);
                }





            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {

            Intent intent = new Intent(this, home_screen.class);
            startActivity(intent);

        }    return true;
        }


}
