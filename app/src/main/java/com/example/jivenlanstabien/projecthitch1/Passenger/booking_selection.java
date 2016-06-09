package com.example.jivenlanstabien.projecthitch1.Passenger;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jivenlanstabien.projecthitch1.AdditionalPassBGTask;
import com.example.jivenlanstabien.projecthitch1.DriverConfig;
import com.example.jivenlanstabien.projecthitch1.R;
import com.example.jivenlanstabien.projecthitch1.RequestHandler;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class booking_selection extends AppCompatActivity
{
    RadioButton AddPassYes,AddPassNo;
    CheckBox expandableButton2;
    CheckBox x1, x2, x3, x4, x5, x6, x7;
    TextView lblPUP2DOFF, lblDateAndTime, txtDID, txtDName, txtVehicle, txtPassPref, txtLuggage;
    TextView tvSeat1,tvSeat2,tvSeat3,tvSeat4,tvSeat5,tvSeat6,tvSeat7;
    TextView firstname, lastname, middlename, email, birthdate;
    Spinner gender;
    String passfirstname, passmiddlename, passlastname, passemail, passbirthdate, passgender;
    private String tripid,driverid;
    Button Book;
    ExpandableRelativeLayout  expandableLayoutAddPass,expandableLayoutAddPassNo,expandableLayout3,expandableLayout2;
    String r = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_booking_selection);

        Intent intent = getIntent();
        tripid = intent.getStringExtra(DriverConfig.TRP_ID);

        Button btn = (Button) findViewById(R.id.btnPassReady);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updateStatusReady1();
            }
        });

        Book = (Button) findViewById(R.id.book);
        txtDID = (TextView) findViewById(R.id.txtDID);
        lblPUP2DOFF = (TextView) findViewById(R.id.lblPUP2DOFF);
        lblDateAndTime = (TextView) findViewById(R.id.lblDateAndTime);
        firstname = (TextView) findViewById(R.id.firstname);
        middlename = (TextView) findViewById(R.id.middlename);
        lastname = (TextView) findViewById(R.id.lastname);
        email = (TextView) findViewById(R.id.email);
        birthdate = (TextView) findViewById(R.id.birthdate);
        gender = (Spinner) findViewById(R.id.Gender);
        txtDName = (TextView) findViewById(R.id.txtDName);
        txtVehicle = (TextView) findViewById(R.id.txtVehicle);
        txtPassPref = (TextView) findViewById(R.id.txtPassPref);
        txtLuggage = (TextView) findViewById(R.id.txtLuggage) ;

        expandableLayout2 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout2);
        expandableLayout3 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout3);
        expandableLayoutAddPass = (ExpandableRelativeLayout) findViewById(R.id.expandableLayoutAddPass);
        expandableLayoutAddPassNo = (ExpandableRelativeLayout) findViewById(R.id.expandableLayoutAddPassNo);

        AddPassYes = (RadioButton) findViewById(R.id.AddPassYes);
        AddPassNo = (RadioButton) findViewById(R.id.AddPassNo);
        expandableButton2 = (CheckBox) findViewById(R.id.expandableButton2);

        tvSeat1 = (TextView) findViewById(R.id.tvseat1);
        tvSeat2 = (TextView) findViewById(R.id.tvseat2);
        tvSeat3 = (TextView) findViewById(R.id.tvseat3);
        tvSeat4 = (TextView) findViewById(R.id.tvseat4);

        x1 = (CheckBox) findViewById(R.id.x1);
        x2 = (CheckBox) findViewById(R.id.x2);
        x3 = (CheckBox) findViewById(R.id.x3);
        x4 = (CheckBox) findViewById(R.id.x4);

        expandableButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expandableButton2.isChecked()) {
                    expandableLayout2.expand();
                } else {
                    expandableLayoutAddPass.collapse();
                    expandableLayout2.collapse();
                    expandableLayoutAddPassNo.collapse();
                }
            }
        });

        AddPassYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AddPassYes.isChecked()) {
                    expandableLayoutAddPassNo.collapse();
                    expandableLayoutAddPass.toggle();
                }
            }
        });
        getTrips();

        x1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (x2.isChecked() || x3.isChecked() || x4.isChecked()){
                    AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(booking_selection.this);
                    alertdialogbuilder.setMessage("You're a front sitter, you are not allowed to have extra seats...")
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    x1.setChecked(false);
                                    dialog.dismiss();
                                }
                            }).create();
                    alertdialogbuilder.show();
                }
            }
        });

        x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (x1.isChecked()){
                    AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(booking_selection.this);
                    alertdialogbuilder.setMessage("You're a front sitter, you are not allowed to have extra seats...")
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    x2.setChecked(false);
                                    dialog.dismiss();
                                }
                            }).create();
                    alertdialogbuilder.show();
                } else if (x3.isChecked()) {
                    AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(booking_selection.this);
                    alertdialogbuilder.setMessage("Meron ka ng upuan, Do you want this as your additional seat?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    x2.setChecked(true);
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    x2.setChecked(false);
                                    dialog.dismiss();
                                }
                            }).create();
                    alertdialogbuilder.show();
                } else if ( x4.isChecked()){
                    AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(booking_selection.this);
                    alertdialogbuilder.setMessage("Di mo pwede ibook ang hindi mo katabing upuan...")
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    x2.setChecked(false);
                                    dialog.dismiss();
                                }
                            }).create();
                    alertdialogbuilder.show();
                }
            }
        });

        x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (x1.isChecked()){
                    AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(booking_selection.this);
                    alertdialogbuilder.setMessage("You're a front sitter, you are not allowed to have extra seats...")
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    x3.setChecked(false);
                                    dialog.dismiss();
                                }
                            }).create();
                    alertdialogbuilder.show();
                } else if (x2.isChecked() || x4.isChecked()) {
                    AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(booking_selection.this);
                    alertdialogbuilder.setMessage("Meron ka ng upuan, Do you want this as your additional seat?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    x3.setChecked(true);
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    x3.setChecked(false);
                                    dialog.dismiss();
                                }
                            }).create();
                    alertdialogbuilder.show();
                }
            }
        });

        x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (x1.isChecked()){
                    AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(booking_selection.this);
                    alertdialogbuilder.setMessage("You're a front sitter, you are not allowed to have extra seats...")
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    x4.setChecked(false);
                                    dialog.dismiss();
                                }
                            }).create();
                    alertdialogbuilder.show();
                } else if (x3.isChecked()) {
                    AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(booking_selection.this);
                    alertdialogbuilder.setMessage("Meron ka ng upuan, Do you want this as your additional seat?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    x4.setChecked(true);
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    x4.setChecked(false);
                                    dialog.dismiss();
                                }
                            }).create();
                    alertdialogbuilder.show();
                } else if ( x2.isChecked()){
                    AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(booking_selection.this);
                    alertdialogbuilder.setMessage("Di mo pwede ibook ang hindi mo katabing upuan...")
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    x4.setChecked(false);
                                    dialog.dismiss();
                                }
                            }).create();
                    alertdialogbuilder.show();
                }
            }
        });

        Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(booking_selection.this);
                alertdialogbuilder.setMessage("Do you want to reserve this schedule?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (x1.isChecked()) {
                                    r = "For Confirmation";
                                    updateSeat1();
                                }
                                if (x2.isChecked()) {
                                    r = "For Confirmation";
                                    updateSeat2();
                                }
                                if (x3.isChecked()) {
                                    r = "For Confirmation";
                                    updateSeat3();
                                }
                                if (x4.isChecked()) {
                                    r = "For Confirmation";
                                    updateSeat4();
                                }
                                /*if (x5.isChecked()) {
                                    r = "For Confirmation";
                                    updateSeat5();
                                }
                                if (x6.isChecked()) {
                                    r = "For Confirmation";
                                    updateSeat6();
                                }
                                if (x7.isChecked()) {
                                    r = "For Confirmation";
                                    updateSeat7();
                                }*/
                                Intent intent = new Intent (booking_selection.this,PassengerViewTripList.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                alertdialogbuilder.show();
            }
        });
    }

    private void getTrips(){
        class GetTrips extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                showTrips(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(DriverConfig.URL_GET_TRIP,tripid);
                return s;
            }
        }
        GetTrips ge = new GetTrips();
        ge.execute();
    }
    private void showTrips(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(DriverConfig.TAG_TR_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String drid = c.getString(DriverConfig.TAG_TR_DRID);
            String pickup = c.getString(DriverConfig.TAG_TR_PUP);
            String dropoff = c.getString(DriverConfig.TAG_TR_DOFF);
            String date = c.getString(DriverConfig.TAG_TR_DATE);
            String time = c.getString(DriverConfig.TAG_TR_TIME);
            String seat1 = c.getString(DriverConfig.TAG_TR_SEAT1);
            String seat2 = c.getString(DriverConfig.TAG_TR_SEAT2);
            String seat3 = c.getString(DriverConfig.TAG_TR_SEAT3);
            String seat4 = c.getString(DriverConfig.TAG_TR_SEAT4);

            txtDID.setText(drid);
            lblPUP2DOFF.setText(pickup + " to " + dropoff);
            lblDateAndTime.setText(date + " " + time);
            tvSeat1.setText(seat1);
            tvSeat2.setText(seat2);
            tvSeat3.setText(seat3);
            tvSeat4.setText(seat4);
            driverid = drid;
            getUser();
            getProfile();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void getUser(){
        class GetUser extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                showUser(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(DriverConfig.URL_GET_USER,driverid);
                return s;
            }
        }
        GetUser gu = new GetUser();
        gu.execute();
    }
    private void showUser(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(DriverConfig.TAG_TR_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String lastname = c.getString(DriverConfig.TAG_USER_LASTNAME);
            String middlename = c.getString(DriverConfig.TAG_USER_MIDDLENAME);
            String firstname = c.getString(DriverConfig.TAG_USER_FIRSTNAME);

            txtDName.setText(firstname + " " + middlename + " " + lastname);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void getProfile(){
        class GetProfile extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(booking_selection.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showProfile(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(DriverConfig.URL_GET_DRPROFILE,driverid);
                return s;
            }
        }
        GetProfile gp = new GetProfile();
        gp.execute();
    }
    private void showProfile(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(DriverConfig.TAG_TR_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String passpref = c.getString(DriverConfig.TAG_DRPROFILE_PASSPREF);
            String luggage = c.getString(DriverConfig.TAG_DRPROFILE_LUGGAGE);

            txtPassPref.setText(passpref);
            txtLuggage.setText(luggage);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void updateSeat1(){
        final String seat1 = r;
        class UpdateSeat1 extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(booking_selection.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(booking_selection.this,
                        "",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(DriverConfig.KEY_TRP_TRPID,tripid);
                hashMap.put(DriverConfig.KEY_SEAT1_STATUS,seat1);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(DriverConfig.URL_UPDATE_SEAT1,hashMap);
                return s;
            }
        }
        UpdateSeat1 usi = new UpdateSeat1();
        usi.execute();
    }
    private void updateSeat2(){
        final String seat2 = r;
        class UpdateSeat2 extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(booking_selection.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(booking_selection.this,
                        "",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(DriverConfig.KEY_TRP_TRPID,tripid);
                hashMap.put(DriverConfig.KEY_SEAT2_STATUS,seat2);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(DriverConfig.URL_UPDATE_SEAT2,hashMap);
                return s;
            }
        }
        UpdateSeat2 usi = new UpdateSeat2();
        usi.execute();
    }
    private void updateSeat3(){
        final String seat3 = r;
        class UpdateSeat3 extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(booking_selection.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(booking_selection.this,
                        "",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(DriverConfig.KEY_TRP_TRPID,tripid);
                hashMap.put(DriverConfig.KEY_SEAT3_STATUS,seat3);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(DriverConfig.URL_UPDATE_SEAT3,hashMap);
                return s;
            }
        }
        UpdateSeat3 usi = new UpdateSeat3();
        usi.execute();
    }
    private void updateSeat4(){
        final String seat4 = r;
        class UpdateSeat4 extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(booking_selection.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(booking_selection.this,
                        "",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(DriverConfig.KEY_TRP_TRPID,tripid);
                hashMap.put(DriverConfig.KEY_SEAT4_STATUS,seat4);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(DriverConfig.URL_UPDATE_SEAT4,hashMap);
                return s;
            }
        }
        UpdateSeat4 usi = new UpdateSeat4();
        usi.execute();
    }
    private void updateStatusReady1() {
        final String seat1 = r;
        class UpdateSeat1 extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(booking_selection.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                TextView tv1 = (TextView) findViewById(R.id.tvpassStatus);
                tv1.setText("Ready For Pick-up");
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(DriverConfig.KEY_TRP_TRPID, tripid);
                hashMap.put(DriverConfig.KEY_SEAT1_STATUS, seat1);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(DriverConfig.URL_UPDATE_SEAT1, hashMap);
                return s;
            }
        }
        UpdateSeat1 usi = new UpdateSeat1();
        usi.execute();
    }
    public void Home(View view) {
        Intent intent = new Intent(booking_selection.this,PassengerViewTripList.class);
        startActivity(intent);
    }
    public void addPassenger(View view){
        passfirstname = firstname.getText().toString();
        passmiddlename = middlename.getText().toString();
        passlastname = lastname.getText().toString();
        passemail = email.getText().toString();
        passbirthdate = birthdate.getText().toString();
        passgender = gender.getSelectedItem().toString();

        String addPassMethod = "addPassenger";
        AdditionalPassBGTask additionalPassBGTask = new AdditionalPassBGTask(this.getApplicationContext());
        additionalPassBGTask.execute(addPassMethod,passfirstname,passmiddlename,passlastname,passemail,passbirthdate,passgender);
    }
    public void addAnotherPass(View view){
        firstname.setText("");
        middlename.setText("");
        lastname.setText("");
        email.setText("");
        birthdate.setText("");
    }
    public void expandableButton2(View view) {
        ExpandableRelativeLayout expandableLayout2 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout2);

        AddPassYes = (RadioButton) findViewById(R.id.AddPassYes);
        AddPassYes.setChecked(false);

        AddPassNo = (RadioButton) findViewById(R.id.AddPassNo);
        AddPassNo.setChecked(false);

        expandableLayout2.toggle(); // toggle expand and collapse

        ExpandableRelativeLayout expandableLayoutAddPass = (ExpandableRelativeLayout) findViewById(R.id.expandableLayoutAddPass);
        expandableLayoutAddPass.collapse(); // toggle expand and collapse
        expandableLayoutAddPassNo.collapse();

    }
    public void expandableButton3(View view) {
        ExpandableRelativeLayout expandableLayout3 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayoutLuggage);

        expandableLayout3.toggle(); // toggle expand and collapse
    }
    public void AddPassNo(View view) {
        if (AddPassNo.isChecked()) {
            AddPassYes.setChecked(false);
            expandableLayoutAddPass.collapse();
            expandableLayoutAddPassNo.toggle(); // toggle expand and collapse
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home) {
            finish();
        }
        return true;
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
