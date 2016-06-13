package com.example.jivenlanstabien.projecthitch1.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.Random;


public class forgot_password extends AppCompatActivity {
    Button Submit;
    private EditText Email;
    TextView EmailLabel;
    String emailadd, output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Email = (EditText) findViewById(R.id.EmailAddress);
        EmailLabel = (TextView) findViewById(R.id.EmailLabel);
        Submit = (Button) findViewById(R.id.Submit);

        //Adding click listener
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    getUserLogin();
                }
                catch (Exception e){
                    e.printStackTrace();
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
        output = sb.toString();

        //send code to database
        String addSecurityCode = "addSecurityCode";
        forgotpasswordBackgroundTask securitycodebackgroundTask = new forgotpasswordBackgroundTask(this.getApplicationContext());
        securitycodebackgroundTask.execute(addSecurityCode,output);

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
            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                //mobile
                NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
                //wifi
                NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
                progressDialog = ProgressDialog.show(forgot_password.this,"Validating account","Please wait...",true);
                if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
                }
                    else if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
                    }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(forgot_password.this,"Sending failed. No internet connection.",Toast.LENGTH_SHORT).show();
                        }
            }

            @Override
            protected void onPostExecute(String json) {
                super.onPostExecute(json);
                try {
                    String email = Email.getText().toString().trim();

                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray result = jsonObject.getJSONArray(Config.TAG_LOGINID_JSON_ARRAY);
                    JSONObject c = result.getJSONObject(0);
                    String loginid = c.getString(Config.KEY_LOGIN_ID);

                    if (email.equals("")) {
                        progressDialog.dismiss();
                        Email.setError("Email Address required!");
                        Email.requestFocus();
                    }
                    else {
                        if (loginid.equals("") || (loginid.equals(null)|| (loginid.equals("null")))) {
                            progressDialog.dismiss();
                            Email.setError("User Email Address doesn't exist!");
                            } else {
                            progressDialog.dismiss();
                                sendEmail();
                                Intent intent = new Intent(forgot_password.this, SecurityCodeChecking.class);
                            intent.putExtra("output",output);
                            startActivity(intent);
                            }
                    }
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

