package com.example.jivenlanstabien.projecthitch1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

@SuppressWarnings("ALL")
public class ViewTrip extends AppCompatActivity implements View.OnClickListener {

    private EditText etTRID, etSID, etDID, etVID, etPUP, etDOFF, etDATE, etTIME, etSTATUS;

    private Button btnIn, btnReady, btnScan, btnStart;
    Button btnUpdate, buttonDelete, btnConfirm1, btnConfirm2,
            btnConfirm3, btnConfirm4, btnConfirm5, btnConfirm6, btnConfirm7,
            btnBoarded1, btnBoarded2, btnBoarded3, btnBoarded4;
    TextView tvSeat1,tvSeat2,tvSeat3,tvSeat4,tvSeat5,tvSeat6,tvSeat7;
    private String tripid, tripStatus;
    private boolean stats = true;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_view_trip);

        Intent intent = getIntent();
        tripid = intent.getStringExtra(DriverConfig.TRP_ID);

        etTRID = (EditText) findViewById(R.id.etTRID);
        etSID = (EditText) findViewById(R.id.etSID);
        etDID = (EditText) findViewById(R.id.etDID);
        etVID = (EditText) findViewById(R.id.etVID);
        etPUP = (EditText) findViewById(R.id.etPUP);
        etDOFF = (EditText) findViewById(R.id.etDOFF);
        etDATE = (EditText) findViewById(R.id.etDATE);
        etTIME = (EditText) findViewById(R.id.etTime);
        etSTATUS = (EditText) findViewById(R.id.etStatus);

        btnIn = (Button) findViewById(R.id.btnIn);
        btnReady = (Button) findViewById(R.id.btnReady);
        btnScan = (Button) findViewById(R.id.btnScan);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnIn.setEnabled(true);
        btnReady.setEnabled(false);
        btnScan.setEnabled(false);
        btnStart.setEnabled(false);

        tvSeat1 = (TextView) findViewById(R.id.tvseat1);
        tvSeat2 = (TextView) findViewById(R.id.tvseat2);
        tvSeat3 = (TextView) findViewById(R.id.tvseat3);
        tvSeat4 = (TextView) findViewById(R.id.tvseat4);

        if(btnIn.isEnabled()){tripStatus="Open";}

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        btnConfirm1 = (Button) findViewById(R.id.btnConfirm1);
        btnConfirm1.setOnClickListener(this);
        btnConfirm2 = (Button) findViewById(R.id.btnConfirm2);
        btnConfirm2.setOnClickListener(this);
        btnConfirm3 = (Button) findViewById(R.id.btnConfirm3);
        btnConfirm3.setOnClickListener(this);
        btnConfirm4 = (Button) findViewById(R.id.btnConfirm4);
        btnConfirm4.setOnClickListener(this);
        btnBoarded1 = (Button) findViewById(R.id.btnBoarded1);
        btnBoarded1.setOnClickListener(this);
        btnBoarded2 = (Button) findViewById(R.id.btnBoarded2);
        btnBoarded2.setOnClickListener(this);
        btnBoarded3 = (Button) findViewById(R.id.btnBoarded3);
        btnBoarded3.setOnClickListener(this);
        btnBoarded4 = (Button) findViewById(R.id.btnBoarded4);
        btnBoarded4.setOnClickListener(this);

        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnIn.setEnabled(false);
                btnReady.setEnabled(true);
                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){

                }else{
                    showGPSDisabledAlertToUser();
                }
                tripStatus = "In Transit";
                updateStatusIn();
            }
        });

        btnReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnReady.setEnabled(false);
                btnScan.setEnabled(true);
                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){

                }else{
                    showGPSDisabledAlertToUser();
                }
                tripStatus = "Ready for Pick-up";
                updateStatusIn();
            }
        });

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewTrip.this,QRReader.class);
                btnScan.setEnabled(false);
                btnStart.setEnabled(true);
                startActivity(intent);
            }
        });

        final boolean[] showingFirst = {true};
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showingFirst[0] == true){
                    LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                    if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){

                    }else{
                        showGPSDisabledAlertToUser();
                    }
                    StartTrip();
                    tripStatus = "On Trip";
                    showingFirst[0] = false;
                } else {
                    EndTrip();
                    tripStatus = "Trip Ended";
                    showingFirst[0] = false;
                }
            }
        });
        etTRID.setText(tripid);
        getTrips();
    }

    public void EndTrip(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You are in the destination to end your trip?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(ViewTrip.this,"Your trip has ended!",Toast.LENGTH_LONG).show();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        alertDialogBuilder.show();
    }
    public void StartTrip(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You want to start your trip?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                btnStart.setText("End Trip");
                Toast.makeText(ViewTrip.this,"Your trip has started!",Toast.LENGTH_LONG).show();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        alertDialogBuilder.show();
    }
    private void getTrips(){
        class GetTrips extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewTrip.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
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
            String scheduleid = c.getString(DriverConfig.TAG_TR_SCHEDID);
            String driverid = c.getString(DriverConfig.TAG_TR_DRID);
            String vehicleid = c.getString(DriverConfig.TAG_TR_VEHID);
            String pickup = c.getString(DriverConfig.TAG_TR_PUP);
            String dropoff = c.getString(DriverConfig.TAG_TR_DOFF);
            String date = c.getString(DriverConfig.TAG_TR_DATE);
            String time = c.getString(DriverConfig.TAG_TR_TIME);
            String seat1 = c.getString(DriverConfig.TAG_TR_SEAT1);
            String seat2 = c.getString(DriverConfig.TAG_TR_SEAT2);
            String seat3 = c.getString(DriverConfig.TAG_TR_SEAT3);
            String seat4 = c.getString(DriverConfig.TAG_TR_SEAT4);
            final String status = c.getString(DriverConfig.TAG_TR_STATUS);

            etSID.setText(scheduleid);
            etDID.setText(driverid);
            etVID.setText(vehicleid);
            etPUP.setText(pickup);
            etDOFF.setText(dropoff);
            etDATE.setText(date);
            etTIME.setText(time);
            tvSeat1.setText(seat1);
            tvSeat2.setText(seat2);
            tvSeat3.setText(seat3);
            tvSeat4.setText(seat4);
            etSTATUS.setText(status);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void updateTrip(){
        final String pickup = etPUP.getText().toString().trim();
        final String dropoff = etDOFF.getText().toString().trim();
        final String date = etDATE.getText().toString().trim();
        final String time = etTIME.getText().toString().trim();

        class UpdateTrip extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewTrip.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewTrip.this,"Trip Successfully Updated!",Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(DriverConfig.KEY_TRP_TRPID,tripid);
                hashMap.put(DriverConfig.KEY_TRP_PUP,pickup);
                hashMap.put(DriverConfig.KEY_TRP_DOFF,dropoff);
                hashMap.put(DriverConfig.KEY_TRP_DATE,date);
                hashMap.put(DriverConfig.KEY_TRP_TIME,time);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(DriverConfig.URL_UPDATE_TRIP,hashMap);

                return s;
            }
        }
        UpdateTrip ut = new UpdateTrip();
        ut.execute();
    }
    private void updateStatusIn(){
        final String status = tripStatus;

        class UpdateStatusIn extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewTrip.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewTrip.this,"Your Trip Status is Now " + status + "...",Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(DriverConfig.KEY_TRP_TRPID,tripid);
                hashMap.put(DriverConfig.KEY_TRP_STATUS,status);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(DriverConfig.URL_UPDATE_TRIPSTATUS_IN,hashMap);

                return s;
            }
        }
        UpdateStatusIn usi = new UpdateStatusIn();
        usi.execute();
    }
    private void updateSeat1(){
        final String seat1 = "Confirmed";
        class UpdateSeat1 extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewTrip.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                tvSeat1.setText("Confirmed");
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
        final String seat2 = "Confirmed";
        class UpdateSeat2 extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewTrip.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                tvSeat2.setText("Confirmed");
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
        final String seat3 = "Confirmed";
        class UpdateSeat3 extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewTrip.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                tvSeat3.setText("Confirmed");
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
        final String seat4 = "Confirmed";
        class UpdateSeat4 extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewTrip.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                tvSeat4.setText("Confirmed");
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
    private void boardedSeat1(){
        final String seat1 = "Boarded";
        class BoardedSeat1 extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewTrip.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                tvSeat1.setText("Boarded");
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
        BoardedSeat1 usi = new BoardedSeat1();
        usi.execute();
    }
    private void boardedSeat2(){
        final String seat2 = "Boarded";
        class BoardedSeat2 extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewTrip.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                tvSeat2.setText("Boarded");
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
        BoardedSeat2 usi = new BoardedSeat2();
        usi.execute();
    }
    private void boardedSeat3(){
        final String seat3 = "Boarded";
        class BoardedSeat3 extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewTrip.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                tvSeat3.setText("Boarded");
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
        BoardedSeat3 usi = new BoardedSeat3();
        usi.execute();
    }
    private void boardedSeat4(){
        final String seat4 = "Boarded";
        class BoardedSeat4 extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewTrip.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                tvSeat4.setText("Boarded");
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
        BoardedSeat4 usi = new BoardedSeat4();
        usi.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == btnUpdate){
            updateTrip();
        }
        if(v == buttonDelete){
            // confirmDeleteEmployee();
        }
        if(v == btnConfirm1){
            updateSeat1();
        }
        if(v == btnConfirm2){
            updateSeat2();
        }
        if(v == btnConfirm3){
            updateSeat3();
        }
        if(v == btnConfirm4){
            updateSeat4();
        }
        /*if(v == btnConfirm5){
            updateSeat5();
        }
        if(v == btnConfirm6){
            updateSeat6();
        }
        if(v == btnConfirm7){
            updateSeat7();
        }*/
        if(v == btnBoarded1){
            boardedSeat1();
        }
        if(v == btnBoarded2){
            boardedSeat2();
        }
        if(v == btnBoarded3){
            boardedSeat3();
        }
        if(v == btnBoarded4){
            boardedSeat4();
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
    private void showGPSDisabledAlertToUser(){
        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Go to Settings",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
        android.support.v7.app.AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}
