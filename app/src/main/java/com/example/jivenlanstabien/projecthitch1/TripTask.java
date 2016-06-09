package com.example.jivenlanstabien.projecthitch1;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Eugene on 5/13/2016.
 */
public class TripTask extends AsyncTask<String,Void,String> {

    Context ctx;

    TripTask(Context ctx){
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {super.onPreExecute();}

    @Override
    protected String doInBackground(String... params) {

        String trip_url = "http://angkas.net23.net/addtrips.php";
        String tripMethod = params[0];
        if (tripMethod.equals("addTrips"))
        {
            String tripid = params[1];
            String scheduleid = params[2];
            String driverid = params[3];
            String vehicleid = params[4];
            String pickup = params[5];
            String dropoff = params[6];
            String date = params[7];
            String time = params[8];
            String seat1 = params[9];
            String seat1_passenger = params[10];
            String seat2 = params[11];
            String seat2_passenger = params[12];
            String seat3 = params[13];
            String seat3_passenger = params[14];
            String seat4 = params[15];
            String seat4_passenger = params[16];
            String status = params[17];

            try {
                URL url = new URL(trip_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("tripid", "UTF-8") + "=" + URLEncoder.encode(tripid, "UTF-8") + " & " +
                        URLEncoder.encode("scheduleid", "UTF-8") + "=" + URLEncoder.encode(scheduleid, "UTF-8") + " & " +
                        URLEncoder.encode("driverid", "UTF-8") + "=" + URLEncoder.encode(driverid, "UTF-8") + " & " +
                        URLEncoder.encode("vehicleid", "UTF-8") + "=" + URLEncoder.encode(vehicleid, "UTF-8") + " & " +
                        URLEncoder.encode("pickup", "UTF-8") + "=" + URLEncoder.encode(pickup, "UTF-8") + " & " +
                        URLEncoder.encode("dropoff", "UTF-8") + "=" + URLEncoder.encode(dropoff, "UTF-8") + " & " +
                        URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8") + " & " +
                        URLEncoder.encode("time", "UTF-8") + "=" + URLEncoder.encode(time, "UTF-8") + " & " +
                        URLEncoder.encode("seat1", "UTF-8") + "=" + URLEncoder.encode(seat1, "UTF-8") + " & " +
                        URLEncoder.encode("seat1_passenger", "UTF-8") + "=" + URLEncoder.encode(seat1_passenger, "UTF-8") + " & " +
                        URLEncoder.encode("seat2", "UTF-8") + "=" + URLEncoder.encode(seat2, "UTF-8") + " & " +
                        URLEncoder.encode("seat2_passenger", "UTF-8") + "=" + URLEncoder.encode(seat2_passenger, "UTF-8") + " & " +
                        URLEncoder.encode("seat3", "UTF-8") + "=" + URLEncoder.encode(seat3, "UTF-8") + " & " +
                        URLEncoder.encode("seat3_passenger", "UTF-8") + "=" + URLEncoder.encode(seat3_passenger, "UTF-8") + " & " +
                        URLEncoder.encode("seat4", "UTF-8") + "=" + URLEncoder.encode(seat4, "UTF-8") + " & " +
                        URLEncoder.encode("seat4_passenger", "UTF-8") + "=" + URLEncoder.encode(seat4_passenger, "UTF-8") + " & " +
                        URLEncoder.encode("status", "UTF-8") + "=" + URLEncoder.encode(status, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Adding Trips Success...";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
            Toast.makeText(ctx,result,Toast.LENGTH_SHORT).show();
    }
}
