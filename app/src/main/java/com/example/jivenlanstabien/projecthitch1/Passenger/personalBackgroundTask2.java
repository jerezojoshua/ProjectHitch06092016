package com.example.jivenlanstabien.projecthitch1.Passenger;

/**
 * Created by jivenlans on 6/9/2016.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

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
 * Created by jivenlans on 6/6/2016.
 */
public class personalBackgroundTask2 extends AsyncTask<String, Void, String>
{
    Context ctx;
    personalBackgroundTask2(Context ctx)
    {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params)
    {
        String reg_url = "http://angkas.net23.net/personalinfoupdate2.php";

        String method = params[0];
        if(method.equals("register"))
        {

            String getpassengerid = params[1];
            String getaddress1 = params[2];
            String getaddress2 = params[3];
            String getcity = params[4];
            String getcountry = params[5];
            String getcontactnumber = params[6];
            String getalternatecontactnumber = params[7];
            String getemergencycontact = params[8];
            String getemergencycontactnumber = params[9];
            String getmaritalstatus = params[10];
            String getpassengertype = params[11];
            String getdiscountid = params[12];
            String getschool = params[13];


            try
            {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("getpassengerid", "UTF-8") +"="+URLEncoder.encode(getpassengerid, "UTF-8")+"&"+
                        URLEncoder.encode("getaddress1", "UTF-8") +"="+URLEncoder.encode(getaddress1, "UTF-8")+"&"+
                        URLEncoder.encode("getaddress2", "UTF-8") +"="+URLEncoder.encode(getaddress2, "UTF-8")+"&"+
                        URLEncoder.encode("getcity", "UTF-8") +"="+URLEncoder.encode(getcity, "UTF-8")+"&"+
                        URLEncoder.encode("getcountry", "UTF-8") +"="+URLEncoder.encode(getcountry, "UTF-8")+"&"+
                        URLEncoder.encode("getcontactnumber", "UTF-8") +"="+URLEncoder.encode(getcontactnumber, "UTF-8")+"&"+
                        URLEncoder.encode("getalternatecontactnumber", "UTF-8") +"="+URLEncoder.encode(getalternatecontactnumber, "UTF-8")+"&"+
                        URLEncoder.encode("getemergencycontact", "UTF-8") +"="+URLEncoder.encode(getemergencycontact, "UTF-8")+"&"+
                        URLEncoder.encode("getemergencycontactnumber", "UTF-8") +"="+URLEncoder.encode(getemergencycontactnumber, "UTF-8")+"&"+
                        URLEncoder.encode("getmaritalstatus", "UTF-8") +"="+URLEncoder.encode(getmaritalstatus, "UTF-8")+"&"+
                        URLEncoder.encode("getpassengertype", "UTF-8") +"="+URLEncoder.encode(getpassengertype, "UTF-8")+"&"+
                        URLEncoder.encode("getdiscountid", "UTF-8") +"="+URLEncoder.encode(getdiscountid, "UTF-8")+"&"+
                        URLEncoder.encode("getschool", "UTF-8") +"="+URLEncoder.encode(getschool, "UTF-8");
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
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result)
    {

    }
}
