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
 * Created by Eugene on 6/1/2016.
 */
//JOSHUA
public class AdditionalPassBGTask extends AsyncTask<String,Void,String> {
    Context ctx;

    public AdditionalPassBGTask(Context ctx){
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String addpass_url = "http://angkas.net23.net/additionalpass.php";
        String addPassMethod = params[0];
        if (addPassMethod.equals("addPassenger"))
        {
            String firstname = params[1];
            String middlename = params[2];
            String lastname = params[3];
            String email = params[4];
            String birthdate = params[5];
            String gender = params[6];

            try {
                URL url = new URL(addpass_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("firstname","UTF-8") + "=" + URLEncoder.encode(firstname, "UTF-8") + " & " +
                        URLEncoder.encode("middlename","UTF-8") + "=" + URLEncoder.encode(middlename, "UTF-8") + " & " +
                        URLEncoder.encode("lastname","UTF-8") + "=" + URLEncoder.encode(lastname, "UTF-8") + " & " +
                        URLEncoder.encode("email","UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + " & " +
                        URLEncoder.encode("birthdate","UTF-8") + "=" + URLEncoder.encode(birthdate, "UTF-8") + " & " +
                        URLEncoder.encode("gender","UTF-8") + "=" + URLEncoder.encode(gender, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Adding Additional Passenger Success...";
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
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }
}
