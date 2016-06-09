package com.example.jivenlanstabien.projecthitch1.Passenger;

/**
 * Created by JivenlansTabien on 5/19/2016.
 */
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jivenlanstabien.projecthitch1.DriverConfig;
import com.example.jivenlanstabien.projecthitch1.DriverTripList;
import com.example.jivenlanstabien.projecthitch1.Login.Login;
import com.example.jivenlanstabien.projecthitch1.R;
import com.example.jivenlanstabien.projecthitch1.RequestHandler;
import com.example.jivenlanstabien.projecthitch1.driver_requirements;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.Map;

public class home_screen_registration extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button DriverButton, PassengerButton;
    TextView Email, driver_id, passenger_id;
    Spinner maritalstatus;
    EditText address1, address2, city, country, contactnumber, alternatecontactnumber, emergencycontactperson, emergencycontactnumber;
    private int PICK_IMAGE_REQUEST = 1;
    private String JSON_STRING;
    String firstname, lastname, username;

    private String UPLOAD_URL = "http://angkas.net23.net/upload.php";

    private String KEY_IMAGE = "image";

    private String KEY_NAME = "name";
    private Bitmap bitmap;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_registration_navigation_menu);
        new BackgroundTask().execute();

        imageView = (ImageView) findViewById(R.id.Photo);
        DriverButton = (Button) findViewById(R.id.DriverButton);
        PassengerButton = (Button) findViewById(R.id.PassengerButton);

        maritalstatus = (Spinner) findViewById(R.id.maritalstatus);
        address1 = (EditText) findViewById(R.id.address1);
        address2 = (EditText) findViewById(R.id.address2);
        city = (EditText) findViewById(R.id.city);
        country = (EditText) findViewById(R.id.country);
        contactnumber = (EditText) findViewById(R.id.contactnumber);
        alternatecontactnumber = (EditText) findViewById(R.id.alternatecontactnumber);
        emergencycontactperson = (EditText) findViewById(R.id.emergencycontactperson);
        emergencycontactnumber = (EditText) findViewById(R.id.emergencycontactnumber);


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

    class BackgroundTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String reg_url = "http://angkas.net23.net/addingyes.php";

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
                OS.close();
                bufferedWriter.close();

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

        }
    }

    public void showFileChooser(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void submit(View view) {
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(home_screen_registration.this, s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        //   Toast.makeText(home_screen_registration.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                String name = sharedPreferences.getString("email_login", "");

                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, name);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);



        String getmaritalstatus = maritalstatus.getSelectedItem().toString().trim();
        String getaddress1 = address1.getText().toString().trim();
        String getaddress2 = address2.getText().toString().trim();
        String getcity = city.getText().toString().trim();
        String getcountry = country.getText().toString().trim();
        String getcontactnumber = contactnumber.getText().toString().trim();
        String getalternatecontactnumber = alternatecontactnumber.getText().toString().trim();
        String getemergencycontactperson = emergencycontactperson.getText().toString().trim();
        String getemergencycontactnumber = emergencycontactnumber.getText().toString().trim();

        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        String getemail = sharedPreferences.getString("email_login", "");
        String method = "register";
        updateinfo_BackgroundTask backgroundTask = new updateinfo_BackgroundTask(this);
        backgroundTask.execute(method,getemail,getmaritalstatus,getaddress1,getaddress2,getcity,getcountry,getcontactnumber,getalternatecontactnumber,getemergencycontactperson,getemergencycontactnumber);
    }
    public void driver(View view)
    {
        PassengerButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
        DriverButton.setBackgroundColor(Color.parseColor("#A2F7A5"));
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        String getemail = sharedPreferences.getString("email_login", "");
        String method = "register";
        home_screen_BackgroundTask backgroundTask = new home_screen_BackgroundTask(this);
        backgroundTask.execute(method,getemail);

    }
    public void passenger(View view)
    {
        PassengerButton.setBackgroundColor(Color.parseColor("#A2F7A5"));
        DriverButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        String getemail = sharedPreferences.getString("email_login", "");
        String method = "register";
        passenger_home_screen_BackgroundTask backgroundTask = new passenger_home_screen_BackgroundTask(this);
        backgroundTask.execute(method,getemail);

    }
    @Override
    public void onBackPressed()
    {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain); // super.onBackPressed(); // Comment this super call to avoid calling finish()
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

        } else if (id == R.id.nav_gps
                ) {


        } else if (id == R.id.nav_requirements) {

            Intent intent = new Intent(this,driver_requirements.class);
            startActivity(intent);

        } else if (id == R.id.nav_driver_schedule) {

            Intent intent = new Intent(this, DriverTripList.class);
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
                    Toast.makeText(home_screen_registration.this, "Successfully Logged Out!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(home_screen_registration.this, Login.class);
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
            jsonObject = new JSONObject(JSON_STRING);
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
                loading = ProgressDialog.show(home_screen_registration.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
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
