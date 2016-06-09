package com.example.jivenlanstabien.projecthitch1.Passenger;

/**
 * Created by JivenlansTabien on 5/16/2016.
 */
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
public class passengerBackgroundTask extends AsyncTask<String, Void, String>
{
    Context ctx;
    passengerBackgroundTask(Context ctx)
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
        String reg_url = "http://angkas.net23.net/passengerinsert.php";

        String method = params[0];
        if(method.equals("register"))
        {
            //for personal information
            String getpassenger_type = params[1];
            String getpassenger_preference_spinner = params[2];
            String getprefered_seat = params[3];
            String getschool = params[4];
            String gettracking = params[5];
            String getidnumber = params[6];
            String getpaymenttype = params[7];
            String getBank = params[8];
            String getaccountname = params[9];
            String getCard = params[10];
            String getExpiry = params[11];
            String getCode = params[12];
            String sgetemail = params[13];

            // end of declaration of params

            try
            {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("getpassenger_type", "UTF-8") +"="+URLEncoder.encode(getpassenger_type, "UTF-8")+"&"+
                        URLEncoder.encode("getpassenger_preference_spinner", "UTF-8") +"="+URLEncoder.encode(getpassenger_preference_spinner, "UTF-8")+"&"+
                        URLEncoder.encode("getprefered_seat", "UTF-8") +"="+URLEncoder.encode(getprefered_seat, "UTF-8")+"&"+
                        URLEncoder.encode("getschool", "UTF-8") +"="+URLEncoder.encode(getschool, "UTF-8")+"&"+
                        URLEncoder.encode("gettracking", "UTF-8") +"="+URLEncoder.encode(gettracking, "UTF-8")+"&"+
                        URLEncoder.encode("getidnumber", "UTF-8") +"="+URLEncoder.encode(getidnumber, "UTF-8")+"&"+
                        URLEncoder.encode("getpaymenttype", "UTF-8") +"="+URLEncoder.encode(getpaymenttype, "UTF-8")+"&"+
                        URLEncoder.encode("getBank", "UTF-8") +"="+URLEncoder.encode(getBank, "UTF-8")+"&"+
                        URLEncoder.encode("getaccountname", "UTF-8") +"="+URLEncoder.encode(getaccountname, "UTF-8")+"&"+
                        URLEncoder.encode("getCard", "UTF-8") +"="+URLEncoder.encode(getCard, "UTF-8")+"&"+
                        URLEncoder.encode("getExpiry", "UTF-8") +"="+URLEncoder.encode(getExpiry, "UTF-8")+"&"+
                        URLEncoder.encode("getCode", "UTF-8") +"="+URLEncoder.encode(getCode, "UTF-8")+"&"+
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
