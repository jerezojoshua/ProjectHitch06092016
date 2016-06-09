package com.example.jivenlanstabien.projecthitch1.Login;

import android.content.Context;
import android.content.SharedPreferences;
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
 * Created by JivenlansTabien on 5/10/2016.
 */
public class user_registration_background_task extends AsyncTask<String, Void, String>
{
    String insert_email_address;
    Context ctx;
    user_registration_background_task(Context ctx)
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
        String reg_url = "http://angkas.net23.net/user_registration.php";

        String method = params[0];
        if(method.equals("register"))
        {
            String insert_firstname = params[1];
            String insert_lastname = params[2];
            insert_email_address = params[3];
            String insert_password = params[4];
            String insert_phone_number = params[5];

            try
            {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("firstname", "UTF-8") +"="+URLEncoder.encode(insert_firstname, "UTF-8")+"&"+
                        URLEncoder.encode("lastname", "UTF-8") +"="+URLEncoder.encode(insert_lastname, "UTF-8")+"&"+
                        URLEncoder.encode("email_address", "UTF-8") +"="+URLEncoder.encode(insert_email_address, "UTF-8")+"&"+
                        URLEncoder.encode("password", "UTF-8") +"="+URLEncoder.encode(insert_password, "UTF-8")+"&"+
                        URLEncoder.encode("phone_number", "UTF-8") +"="+URLEncoder.encode(insert_phone_number, "UTF-8");
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
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("login", ctx.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email_login", "8@yahoo.com");
        editor.commit();
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }
}
