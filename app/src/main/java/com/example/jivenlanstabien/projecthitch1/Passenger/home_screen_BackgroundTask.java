package com.example.jivenlanstabien.projecthitch1.Passenger;

/**
 * Created by JivenlansTabien on 5/16/2016.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jivenlanstabien.projecthitch1.driver_sign_up;
import com.example.jivenlanstabien.projecthitch1.DriverTripList;

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
public class home_screen_BackgroundTask extends AsyncTask<String, Void, String> {
    Context ctx;
    String username;
    private ProgressDialog dialog;
    home_screen_BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://angkas.net23.net/user_home_screen.php";

        String method = params[0];
        if (method.equals("register")) {

            username = params[1];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("username2", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");

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

        String result1 = result.trim();
        if(result1.equals("none"))
        {
            Intent intent = new Intent(ctx, driver_sign_up.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("username", username);
            ctx.startActivity(intent);
        }
        else {
            dialog = new ProgressDialog(ctx);
            dialog.setMessage("Loading...");
            dialog.show();
            Intent intent = new Intent(ctx, DriverTripList.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("username", result1);
            ctx.startActivity(intent);
            Toast.makeText(ctx, "Logged in as Driver", Toast.LENGTH_LONG).show();
        }
    }
}


