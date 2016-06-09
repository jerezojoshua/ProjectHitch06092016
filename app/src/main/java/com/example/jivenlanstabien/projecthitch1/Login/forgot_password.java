package com.example.jivenlanstabien.projecthitch1.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jivenlanstabien.projecthitch1.R;
import com.example.jivenlanstabien.projecthitch1.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.Random;


public class forgot_password extends AppCompatActivity {
    Button Submit;
    private EditText Email;
    TextView EmailLabel, LoginID;
    String emailadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        LoginID = (TextView) findViewById(R.id.LoginID);
        Email = (EditText) findViewById(R.id.EmailAddress);
        EmailLabel = (TextView) findViewById(R.id.EmailLabel);
        Submit = (Button) findViewById(R.id.Submit);

        //Adding click listener
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String loginid = LoginID.getText().toString().trim();

                getUserLogin();

                ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                //mobile
                NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
                //wifi
                NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (email.equals("")) {
                    Email.setError("Email Address required!");
                    Email.requestFocus();
                }
                else if (!email.matches(emailPattern)){
                    Email.setError("Invalid Email Address!");
                    Email.requestFocus();
                }
                else if (!email.equals("") && (email.matches(emailPattern))) {
                    if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
                        if (loginid.equals("") || (loginid.equals(null))) {
                            Toast.makeText(forgot_password.this, "User Email Address doesn't exist!", Toast.LENGTH_SHORT).show();
                        } else {
                            sendEmail();
                            Intent intent = new Intent(forgot_password.this, SecurityCodeChecking.class);
                            startActivity(intent);
                        }
                    }
                    else if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
                        if (loginid.equals("") || (loginid.equals(null))) {
                            Toast.makeText(forgot_password.this, "User Email Address doesn't exist!", Toast.LENGTH_SHORT).show();
                        } else {
                            sendEmail();
                            Intent intent = new Intent(forgot_password.this, SecurityCodeChecking.class);
                            startActivity(intent);
                        }
                    }
                    else
                    {
                        Toast.makeText(forgot_password.this,"Sending failed. Check your internet connection.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void sendEmail() {
        //Produce Security Code
        char[] chars = "1234567890abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();

        //Getting content for email
        String username = Email.getText().toString();
        String subject = "ANGKAS - PASSWORD RESET";
        String message = "Your password reset code is: " + output + "\n\n\nThanks!\nANGKAS.";
        //Creating SendMail object
        SendMail sm = new SendMail(this, username, subject, message);
        //Executing sendmail to send email
        sm.execute();

    }

    private void getUserLogin(){
        class GetUserLogin extends AsyncTask<Void,Void,String> {
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String json) {
                super.onPostExecute(json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray result = jsonObject.getJSONArray(Config.TAG_TR_JSON_ARRAY);
                    JSONObject c = result.getJSONObject(0);
                    String loginid = c.getString(Config.KEY_LOGIN_ID);
                    LoginID.setText(loginid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                emailadd = Email.getText().toString();
                String s = rh.sendGetRequestParam(Config.URL_GET_USER_LOGIN,emailadd);
                return s;
            }
        }
        GetUserLogin ge = new GetUserLogin();
        ge.execute();
    }

}

