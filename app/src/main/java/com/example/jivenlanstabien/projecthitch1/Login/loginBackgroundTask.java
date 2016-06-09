package com.example.jivenlanstabien.projecthitch1.Login;

/**
 * Created by JivenlansTabien on 5/10/2016.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jivenlanstabien.projecthitch1.Passenger.home_screen_registration;

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
public class loginBackgroundTask extends AsyncTask<String, Void, String>
{
    Context ctx;
    loginBackgroundTask(Context ctx)
    {
        this.ctx = ctx;
    }
    String email;
    private ProgressDialog dialog;
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
            //for vehicle
            String firstname = params[1];
            String middlename = params[2];
            String lastname = params[3];
            email = params[4];
            String gender = params[5];
            String birthdate = params[6];
            String password = params[7];

            // end of declaration of params

            try
            {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("firstname", "UTF-8") +"="+URLEncoder.encode(firstname, "UTF-8")+"&"+
                        URLEncoder.encode("middlename", "UTF-8") +"="+URLEncoder.encode(middlename, "UTF-8")+"&"+
                        URLEncoder.encode("lastname", "UTF-8") +"="+URLEncoder.encode(lastname, "UTF-8")+"&"+
                        URLEncoder.encode("email_address", "UTF-8") +"="+URLEncoder.encode(email, "UTF-8")+"&"+
                        URLEncoder.encode("password", "UTF-8") +"="+URLEncoder.encode(password, "UTF-8")+"&"+
                        URLEncoder.encode("birthdate", "UTF-8") +"="+URLEncoder.encode(birthdate, "UTF-8")+"&"+
                        URLEncoder.encode("gender", "UTF-8") +"="+URLEncoder.encode(gender, "UTF-8");

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
        String result1 = result.trim();
        if(result1.equals("error"))
        {
            Toast.makeText(ctx,"The email address is already exist", Toast.LENGTH_LONG).show();
        }
        else {

            dialog = new ProgressDialog(ctx);
            dialog.setMessage("Loading...");
            dialog.show();
            SharedPreferences sharedPreferences = ctx.getSharedPreferences("login", ctx.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email_login", email);
            editor.commit();

            Intent intent = new Intent(ctx, home_screen_registration.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("username", email);
            ctx.startActivity(intent);
            Toast.makeText(ctx,"Registered Successfully", Toast.LENGTH_LONG).show();
        }
    }
}
