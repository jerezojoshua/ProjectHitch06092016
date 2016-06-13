package com.example.jivenlanstabien.projecthitch1.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.CountDownTimer;
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

public class SecurityCodeChecking extends AppCompatActivity {
    Button SubmitCode;
    EditText Code;
    String seccode;
    TextView TimeLeft,SecurityCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_code_checking);

        SecurityCode = (TextView) findViewById(R.id.SecurityCode);
        Bundle bundle = getIntent().getExtras();

        String output = bundle.getString("output");
        SecurityCode.setText(output);

        TimeLeft = (TextView) findViewById(R.id.TimeLeft);
        Code = (EditText) findViewById(R.id.Code);
        SubmitCode = (Button) findViewById(R.id.SubmitCode);

        new CountDownTimer(300000, 1000) {

            public void onTick(long millisUntilFinished) {
                TimeLeft.setText(millisUntilFinished / 1000 + " SECONDS");
            }

            public void onFinish() {
                Toast.makeText(SecurityCodeChecking.this,"Security code expired!",Toast.LENGTH_SHORT).show();
                deleteSecurityCode();
                Intent intent = new Intent(SecurityCodeChecking.this, forgot_password.class);
                startActivity(intent);
            }
        }.start();
        SubmitCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    getSecurityCode();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    private void getSecurityCode(){

        class GetSecurityCode extends AsyncTask<Void,Void,String> {
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                //mobile
                NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
                //wifi
                NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
                progressDialog = ProgressDialog.show(SecurityCodeChecking.this,"Validating security code","Please wait...",true);
                if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
                }
                else if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(SecurityCodeChecking.this,"Sending failed. No internet connection.",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected void onPostExecute(String json) {
                super.onPostExecute(json);
                try {
                    String code = Code.getText().toString().trim();

                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray result = jsonObject.getJSONArray(Config.TAG_CODE_JSON_ARRAY);
                    JSONObject c = result.getJSONObject(0);
                    String securitycode = c.getString(Config.KEY_SECURITY_CODE);

                    if (code.equals("")) {
                        progressDialog.dismiss();
                        Code.setError("Security Code required!");
                        Code.requestFocus();
                    }
                    else {
                        if (!securitycode.equals(code)) {
                            progressDialog.dismiss();
                            Code.setError("Invalid Security code!");
                            Code.requestFocus();
                        } else {
                            progressDialog.dismiss();
                            deleteSecurityCode();
                            Intent intent = new Intent(SecurityCodeChecking.this,ChangePassword.class);
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
                seccode = Code.getText().toString();
                String s = rh.sendGetRequestParam(Config.URL_GET_SECURITY_CODE,seccode);
                return s;
            }
        }
        GetSecurityCode ge = new GetSecurityCode();
        ge.execute();
    }
    private void deleteSecurityCode(){

        class DeleteSecurityCode extends AsyncTask<Void,Void,String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String json) {
                super.onPostExecute(json);
                try {

                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray result = jsonObject.getJSONArray(Config.TAG_CODE_JSON_ARRAY);
                    JSONObject c = result.getJSONObject(0);
                    String securitycode = c.getString(Config.KEY_SECURITY_CODE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                seccode = SecurityCode.getText().toString();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_SECURITY_CODE,seccode);
                return s;
            }
        }
        DeleteSecurityCode ge = new DeleteSecurityCode();
        ge.execute();
    }

    public void onBackPressed()
    {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        // super.onBackPressed(); // Comment this super call to avoid calling finish()
    }
}
