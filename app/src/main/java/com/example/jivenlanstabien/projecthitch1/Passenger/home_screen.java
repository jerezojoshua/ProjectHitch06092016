package com.example.jivenlanstabien.projecthitch1.Passenger;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jivenlanstabien.projecthitch1.BackgroundTask;
import com.example.jivenlanstabien.projecthitch1.DriverConfig;
import com.example.jivenlanstabien.projecthitch1.DriverScheduleList;
import com.example.jivenlanstabien.projecthitch1.RequestHandler;
import com.example.jivenlanstabien.projecthitch1.driver_requirements;
import com.example.jivenlanstabien.projecthitch1.Login.Login;
import com.example.jivenlanstabien.projecthitch1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class home_screen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String JSON_STRING;
    Button DriverButton, PassengerButton;
    String result1, result2;
    private String JSON_STRING2;
    String firstname, lastname, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_navigation_menu);
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        username = sharedPreferences.getString("email_login", "");
        getJSON();
        new BackgroundTask().execute();
        new BackgroundTask1().execute();


        if(result1 != null) {
            if (result1.equals("No")) {
                Intent intent = new Intent(this, home_screen_registration.class);
                startActivity(intent);
            }
        }


        DriverButton = (Button) findViewById(R.id.DriverButton);
        PassengerButton = (Button) findViewById(R.id.PassengerButton);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    class BackgroundTask1 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String reg_url = "http://angkas.net23.net/getPassId.php";

            SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
            String email_login = sharedPreferences.getString("email_login", "");

            String username = email_login;
            // end of declaration of params

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("username2", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                String response = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            result2 = result.trim();

        }
    }
    class BackgroundTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String reg_url = "http://angkas.net23.net/home_screen_registration.php";

            SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
            String email_login = sharedPreferences.getString("email_login", "");

            String username = email_login;
            // end of declaration of params

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("username2", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                String response = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            result1 = result.trim();

        }
    }
    public void driver(View view)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        String getemail = sharedPreferences.getString("email_login", "");
        if (result1.equals("")) {
            String method = "register";
            home_screen_BackgroundTask backgroundTask = new home_screen_BackgroundTask(this);
            backgroundTask.execute(method, getemail);
        } else {
            SharedPreferences driversharedPreferences = getSharedPreferences("driver", MODE_PRIVATE);
            SharedPreferences.Editor editor = driversharedPreferences.edit();
            editor.putString("driverid", result1);
            editor.commit();
            String method = "register";
            home_screen_BackgroundTask backgroundTask = new home_screen_BackgroundTask(this);
            backgroundTask.execute(method, getemail);
        }
    }
    public void passenger(View view)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        String getemail = sharedPreferences.getString("email_login", "");
        if (result2.equals("")) {
            String method1 = "register";
            passenger_home_screen_BackgroundTask backgroundTask = new passenger_home_screen_BackgroundTask(this);
            backgroundTask.execute(method1, getemail);
        } else {
            SharedPreferences passengerSharedPreferences = getSharedPreferences("passenger", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = passengerSharedPreferences.edit();
            editor1.putString("passengerid", result2);
            editor1.commit();
            String method1 = "register";
            passenger_home_screen_BackgroundTask passengerbackgroundTask = new passenger_home_screen_BackgroundTask(this);
            passengerbackgroundTask.execute(method1, getemail);
        }

    }

    @Override
    public void onBackPressed()
    {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);;// super.onBackPressed(); // Comment this super call to avoid calling finish()
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen_navigation_menu, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_gps) {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            //  Intent intent = new Intent(this,EmergencyContacts.class);
            //  startActivity(intent);
        } else if (id == R.id.nav_requirements) {

            Intent intent = new Intent(this,driver_requirements.class);
            startActivity(intent);

        } else if (id == R.id.nav_driver_schedule) {

            Intent intent = new Intent(this, DriverScheduleList.class);
            startActivity(intent);

        } else if (id == R.id.nav_home_screen_logout) {

            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

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
                    Toast.makeText(home_screen.this, "Successfully Logged Out!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(home_screen.this, Login.class);
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
                firstname = jo.getString(DriverConfig.Firstname);
                lastname = jo.getString(DriverConfig.Lastname);

                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);



                View hview = navigationView.inflateHeaderView(R.layout.nav_header_home_screen_navigation_menu);
                TextView email = (TextView)hview.findViewById(R.id.email_header);
                TextView name = (TextView)hview.findViewById(R.id.name_header);

                name.setText(firstname+" "+lastname);
                email.setText(username);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(home_screen.this,"Fetching Data","Wait...",false,false);
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

}
