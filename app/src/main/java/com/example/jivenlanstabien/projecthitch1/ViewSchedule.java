package com.example.jivenlanstabien.projecthitch1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewSchedule extends AppCompatActivity implements View.OnClickListener {

    private EditText etSID, etDID, etVID, etPUP, etDOFF, etDATE, etTIME;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String scheduleid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);

        Intent intent = getIntent();

        scheduleid = intent.getStringExtra(DriverConfig.SCHED_ID);

        etSID = (EditText) findViewById(R.id.etSID);
        etDID = (EditText) findViewById(R.id.etDID);
        etVID = (EditText) findViewById(R.id.etVID);
        etPUP = (EditText) findViewById(R.id.etPUP);
        etDOFF = (EditText) findViewById(R.id.etDOFF);
        etDATE = (EditText) findViewById(R.id.etDATE);
        etTIME = (EditText) findViewById(R.id.etTime);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        //buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
//        buttonDelete.setOnClickListener(this);

        etSID.setText(scheduleid);

        getSchedule();
    }

    private void getSchedule(){
        class GetSchedule extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewSchedule.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showSchedule(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(DriverConfig.URL_GET_SCHED,scheduleid);
                return s;
            }
        }
        GetSchedule ge = new GetSchedule();
        ge.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void showSchedule(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(DriverConfig.TAG_SC_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String driverid = c.getString(DriverConfig.TAG_SC_DRID);
            String vehicleid = c.getString(DriverConfig.TAG_SC_VEHID);
            String pickup = c.getString(DriverConfig.TAG_SC_PUP);
            String dropoff = c.getString(DriverConfig.TAG_SC_DOFF);
            String date = c.getString(DriverConfig.TAG_SC_DATE);
            String time = c.getString(DriverConfig.TAG_SC_TIME);

            etDID.setText(driverid);
            etVID.setText(vehicleid);
            etPUP.setText(pickup);
            etDOFF.setText(dropoff);
            etDATE.setText(date);
            etTIME.setText(time);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            // updateEmployee();
        }

       /* if(v == buttonDelete){
            // confirmDeleteEmployee();
        }*/
    }
}