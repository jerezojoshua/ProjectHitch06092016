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
 * Created by Eugene on 4/28/2016.
 */
public class BackgroundTask extends AsyncTask<String,Void,String> {

    Context ctx;

    BackgroundTask(Context ctx){
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String sched_url = "http://angkas.net23.net/addschedule.php";
        String schedAddMethod = params[0];
        if (schedAddMethod.equals("addSchedule"))
        {
            String scheduleid = params[1];
            String driverid = params[2];
            String vehicleid = params[3];
            String pickup = params[4];
            String dropoff = params[5];
            String date = params[6];
            String time = params[7];
            String preference = params[8];
            String luggage = params[9];
            String pattern = params[10];
            String monday = params[11];
            String tuesday = params[12];
            String wednesday = params[13];
            String thursday = params[14];
            String friday = params[15];
            String saturday = params[16];
            String sunday = params[17];
            String noweeks = params[18];
            String enddateW = params[19];
            String seat1 = params[20];
            String seat2 = params[21];
            String seat3 = params[22];
            String seat4 = params[23];

            try {
                URL url = new URL(sched_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("scheduleid","UTF-8") + "=" + URLEncoder.encode(scheduleid, "UTF-8") + " & " +
                        URLEncoder.encode("driverid","UTF-8") + "=" + URLEncoder.encode(driverid, "UTF-8") + " & " +
                        URLEncoder.encode("vehicleid","UTF-8") + "=" + URLEncoder.encode(vehicleid, "UTF-8") + " & " +
                        URLEncoder.encode("pickup","UTF-8") + "=" + URLEncoder.encode(pickup, "UTF-8") + " & " +
                        URLEncoder.encode("dropoff","UTF-8") + "=" + URLEncoder.encode(dropoff, "UTF-8") + " & " +
                        URLEncoder.encode("date","UTF-8") + "=" + URLEncoder.encode(date, "UTF-8") + " & " +
                        URLEncoder.encode("time","UTF-8") + "=" + URLEncoder.encode(time, "UTF-8") + " & " +
                        URLEncoder.encode("preference","UTF-8") + "=" + URLEncoder.encode(preference, "UTF-8") + " & " +
                        URLEncoder.encode("luggage","UTF-8") + "=" + URLEncoder.encode(luggage, "UTF-8") + " & " +
                        URLEncoder.encode("pattern","UTF-8") + "=" + URLEncoder.encode(pattern, "UTF-8") + " & " +
                        URLEncoder.encode("monday","UTF-8") + "=" + URLEncoder.encode(monday, "UTF-8") + " & " +
                        URLEncoder.encode("tuesday","UTF-8") + "=" + URLEncoder.encode(tuesday, "UTF-8") + " & " +
                        URLEncoder.encode("wednesday","UTF-8") + "=" + URLEncoder.encode(wednesday, "UTF-8") + " & " +
                        URLEncoder.encode("thursday","UTF-8") + "=" + URLEncoder.encode(thursday, "UTF-8") + " & " +
                        URLEncoder.encode("friday","UTF-8") + "=" + URLEncoder.encode(friday, "UTF-8") + " & " +
                        URLEncoder.encode("saturday","UTF-8") + "=" + URLEncoder.encode(saturday, "UTF-8") + " & " +
                        URLEncoder.encode("sunday","UTF-8") + "=" + URLEncoder.encode(sunday, "UTF-8") + " & " +
                        URLEncoder.encode("noweeks","UTF-8") + "=" + URLEncoder.encode(noweeks, "UTF-8") + " & " +
                        URLEncoder.encode("enddateW","UTF-8") + "=" + URLEncoder.encode(enddateW, "UTF-8") + " & " +
                        URLEncoder.encode("seat1","UTF-8") + "=" + URLEncoder.encode(seat1, "UTF-8") + " & " +
                        URLEncoder.encode("seat2","UTF-8") + "=" + URLEncoder.encode(seat2, "UTF-8") + " & " +
                        URLEncoder.encode("seat3","UTF-8") + "=" + URLEncoder.encode(seat3, "UTF-8") + " & " +
                        URLEncoder.encode("seat4","UTF-8") + "=" + URLEncoder.encode(seat4, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Adding Schedule Success...";
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
