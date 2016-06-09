package com.example.jivenlanstabien.projecthitch1;

/**
 * Created by JivenlansTabien on 5/10/2016.
 */
import android.app.ProgressDialog;
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
 * Created by JivenlansTabien on 5/7/2016.
 */
public class driverBackgroundTask1 extends AsyncTask<String, Void, String>
{
    Context ctx;
    private ProgressDialog dialog;
    driverBackgroundTask1(Context ctx)
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
        String reg_url = "http://angkas.net23.net/driverinsert.php";

        String method = params[0];
        if(method.equals("register"))
        {
            //for vehicle
            String sbodytype = params[1];
            String smake = params[2];
            String smodel = params[3];
            String syear = params[4];
            String svehicle_color = params[5];
            String sfuel_type = params[6];
            String splatenumber = params[7];
            String sengine = params[8];
            String schassis = params[9];


            //for alternate driver

            String salternateDriverEmail = params[10];
            String salternateDriverID = params[11];

            //for requirements information

            String stbLicenseNo = params[12];
            String stbLicenseExpiry = params[13];

            String sgetemail = params[14];

            // end of declaration of params

            try
            {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("sbodytype", "UTF-8") +"="+URLEncoder.encode(sbodytype, "UTF-8")+"&"+
                        URLEncoder.encode("smake", "UTF-8") +"="+URLEncoder.encode(smake, "UTF-8")+"&"+
                        URLEncoder.encode("smodel", "UTF-8") +"="+URLEncoder.encode(smodel, "UTF-8")+"&"+
                        URLEncoder.encode("syear", "UTF-8") +"="+URLEncoder.encode(syear, "UTF-8")+"&"+
                        URLEncoder.encode("svehicle_color", "UTF-8") +"="+URLEncoder.encode(svehicle_color, "UTF-8")+"&"+
                        URLEncoder.encode("sfuel_type", "UTF-8") +"="+URLEncoder.encode(sfuel_type, "UTF-8")+"&"+
                        URLEncoder.encode("splatenumber", "UTF-8") +"="+URLEncoder.encode(splatenumber, "UTF-8")+"&"+
                        URLEncoder.encode("sengine", "UTF-8") +"="+URLEncoder.encode(sengine, "UTF-8")+"&"+
                        URLEncoder.encode("schassis", "UTF-8") +"="+URLEncoder.encode(schassis, "UTF-8")+"&"+
                        URLEncoder.encode("salternateDriverEmail", "UTF-8") +"="+URLEncoder.encode(salternateDriverEmail, "UTF-8")+"&"+
                        URLEncoder.encode("salternateDriverID", "UTF-8") +"="+URLEncoder.encode(salternateDriverID, "UTF-8")+"&"+
                        URLEncoder.encode("stbLicenseNo", "UTF-8") +"="+URLEncoder.encode(stbLicenseNo, "UTF-8")+"&"+
                        URLEncoder.encode("stbLicenseExpiry", "UTF-8") +"="+URLEncoder.encode(stbLicenseExpiry, "UTF-8")+"&"+
                        URLEncoder.encode("sgetemail", "UTF-8") +"="+URLEncoder.encode(sgetemail, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Registration Success";
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
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
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }
}
