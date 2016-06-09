package com.example.jivenlanstabien.projecthitch1.Passenger;

/**
 * Created by JivenlansTabien on 5/19/2016.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.widget.Toast;

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

/**
 * Created by JivenlansTabien on 5/7/2016.
 */
public class updateinfo_BackgroundTask extends AsyncTask<String, Void, String> {
    Context ctx;
    String username;
    private ProgressDialog dialog;
    updateinfo_BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://angkas.net23.net/updateinfo.php";

        String method = params[0];
        if (method.equals("register")) {

            username = params[1];
            String getmaritalstatus = params[2];
            String getaddress1 = params[3];
            String getaddress2 = params[4];
            String getcity = params[5];
            String getcountry = params[6];
            String getcontactnumber = params[7];
            String getalternatecontactnumber = params[8];
            String getemergencycontactperson = params[9];
            String getemergencycontactnumber = params[10];




            // end of declaration of params

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("username2", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8")+"&"+
                URLEncoder.encode("getmaritalstatus", "UTF-8") +"="+URLEncoder.encode(getmaritalstatus, "UTF-8")+"&"+
                        URLEncoder.encode("getaddress1", "UTF-8") +"="+URLEncoder.encode(getaddress1, "UTF-8")+"&"+
                        URLEncoder.encode("getaddress2", "UTF-8") +"="+URLEncoder.encode(getaddress2, "UTF-8")+"&"+
                        URLEncoder.encode("getcity", "UTF-8") +"="+URLEncoder.encode(getcity, "UTF-8")+"&"+
                        URLEncoder.encode("getcountry", "UTF-8") +"="+URLEncoder.encode(getcountry, "UTF-8")+"&"+
                        URLEncoder.encode("getcontactnumber", "UTF-8") +"="+URLEncoder.encode(getcontactnumber, "UTF-8")+"&"+
                        URLEncoder.encode("getalternatecontactnumber", "UTF-8") +"="+URLEncoder.encode(getalternatecontactnumber, "UTF-8")+"&"+
                        URLEncoder.encode("getemergencycontactperson", "UTF-8") +"="+URLEncoder.encode(getemergencycontactperson, "UTF-8")+"&"+
                        URLEncoder.encode("getemergencycontactnumber", "UTF-8") +"="+URLEncoder.encode(getemergencycontactnumber, "UTF-8");




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
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
            Toast.makeText(ctx, "Saved Successfully", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(ctx,home_screen.class);
        ctx.startActivity(intent);

    }
}


