package com.example.jivenlanstabien.projecthitch1;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class AddSchedule extends AppCompatActivity {

    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3;
    RadioButton rb1, rb2;
    CheckBox cbx;
    Button btnAddSched;
    EditText etPickUp, etDropOff, etTime, etNoDays, etNoWeeks;
    Spinner spnPreference, etLuggage;
    TextView tvScheduleID, tvDriverID, tvVehicleID;
    RadioButton rbPattern;
    RadioGroup rbGroup;
    CheckBox cbMonday, cbTuesday, cbWednesday, cbThursday, cbFriday, cbSaturday, cbSunday, cbRecurrence, cbSeat1,
            cbSeat2, cbSeat3, cbSeat4, cbSeat5, cbSeat6, cbSeat7;
    String scheduleid, driverid, vehicleid, pickup, dropoff, date, time, preference, luggage, nodays, enddateD, noweeks,
            enddateW, monday, tuesday, wednesday, thursday, friday, saturday, sunday, pattern, seat1, seat2, seat3, seat4, seat5
            , seat6, seat7;
    String trSeat1,trSeat2,trSeat3,trSeat4,trSeat5,trSeat6,trSeat7,recurrence_stat, recurDaily_stat, recurWeekly_stat;

    DatePicker datePicker;
    Calendar c, new_c;
    Button datePick, endPickWeek, endPickDay;
    EditText timePick;
    int mYear, mMonth, mDay, mHour, mMinute, day1, day2, day3, day4, day5, day6, day7, noOfCheckedDays, countCheckedDays, countWeeks, dayStatCounter;
    SharedPreferences sharedPreferences1;
    String driveridgetter, driveridget;

    SimpleDateFormat sdf;
    ArrayList<Integer> checkedDaysList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        cbRecurrence = (CheckBox) findViewById(R.id.expandableButton1);
        cbRecurrence.setChecked(true);

        datePick = (Button) findViewById(R.id.DatePick);
        endPickDay = (Button) findViewById(R.id.endPickDay);
        endPickWeek = (Button) findViewById(R.id.endPickWeek);
        timePick = (EditText) findViewById(R.id.txtTime);
        tvScheduleID = (TextView) findViewById(R.id.txtScheduleID);

        tvDriverID = (TextView) findViewById(R.id.txtDriverID);
        tvVehicleID = (TextView) findViewById(R.id.txtVehicleID);
        btnAddSched = (Button) findViewById(R.id.btnAdd);

        etPickUp = (EditText) findViewById(R.id.txtPickUp);
        etDropOff = (EditText) findViewById(R.id.txtDropOff);
        etTime = (EditText) findViewById(R.id.txtTime);
        etLuggage = (Spinner) findViewById(R.id.luggage_provision);
        etNoDays = (EditText) findViewById(R.id.txtNoDays);
        etNoWeeks = (EditText) findViewById(R.id.txtNoWeeks);

        spnPreference = (Spinner) findViewById(R.id.passenger_preference);

        rbGroup = (RadioGroup) findViewById(R.id.RecurPattern);
        rbPattern = (RadioButton) findViewById(R.id.radioDaily);

        cbMonday = (CheckBox) findViewById(R.id.monday);
        cbTuesday = (CheckBox) findViewById(R.id.tuesday);
        cbWednesday = (CheckBox) findViewById(R.id.wednesday);
        cbThursday = (CheckBox) findViewById(R.id.thursday);
        cbFriday = (CheckBox) findViewById(R.id.friday);
        cbSaturday = (CheckBox) findViewById(R.id.saturday);
        cbSunday = (CheckBox) findViewById(R.id.sunday);
        cbSeat1 = (CheckBox) findViewById(R.id.Seat1);
        cbSeat2 = (CheckBox) findViewById(R.id.Seat2);
        cbSeat3 = (CheckBox) findViewById(R.id.Seat3);
        cbSeat4 = (CheckBox) findViewById(R.id.Seat4);

        cbx = (CheckBox)findViewById(R.id.expandableButton1);
        recurrence_stat = "true";
        recurDaily_stat = "false";
        recurWeekly_stat = "true";

        sdf = new SimpleDateFormat("yyyy-MM-dd");
        new_c = Calendar.getInstance();
        noOfCheckedDays = 0;
        dayStatCounter = 0;

        etNoDays.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    endPickDay.setEnabled(true);
                } else {
                    endPickDay.setEnabled(false);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        etNoWeeks.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    endPickWeek.setEnabled(true);
                } else {
                    endPickWeek.setEnabled(false);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        endPickDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    etNoDays.setEnabled(true);
                } else {
                    etNoDays.setEnabled(false);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        endPickWeek.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    etNoWeeks.setEnabled(true);
                } else {
                    etNoWeeks.setEnabled(false);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        getTrips();
    }

    private void getTrips(){
        class GetTrips extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddSchedule.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showTrips(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                String email_login = sharedPreferences.getString("email_login", "");
                String s = rh.sendGetRequestParam(DriverConfig.URL_GET_LOGINID,email_login);
                return s;
            }
        }
        GetTrips ge = new GetTrips();
        ge.execute();
    }
    private void showTrips(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(DriverConfig.TAG_TR_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String driveridgetter = c.getString(DriverConfig.TAG_USER_DRIVERID);

            tvDriverID.setText(driveridgetter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setDate(View v) {

        if (v == datePick) {
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            if (year < mYear)
                                view.updateDate(mYear, mMonth, mDay);

                            if (monthOfYear < mMonth && year == mYear)
                                view.updateDate(mYear, mMonth, mDay);

                            if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                                view.updateDate(mYear, mMonth, mDay);

                            datePick.setText(year + "-"
                                    + (monthOfYear + 1) + "-" + dayOfMonth);
                        }
                    }, mYear, mMonth, mDay);
            dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            dpd.show();
        }
    }

    public void setEndDay(View v) {

        if (v == endPickDay) {
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            if (year < mYear)
                                view.updateDate(mYear, mMonth, mDay);

                            if (monthOfYear < mMonth && year == mYear)
                                view.updateDate(mYear, mMonth, mDay);

                            if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                                view.updateDate(mYear, mMonth, mDay);

                            endPickDay.setText(year + "-"
                                    + (monthOfYear + 1) + "-" + dayOfMonth);
                        }
                    }, mYear, mMonth, mDay);
            dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            dpd.show();
        }
    }

    public void setEndWeek(View v) {

        if (v == endPickWeek) {
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            if (year < mYear)
                                view.updateDate(mYear, mMonth, mDay);

                            if (monthOfYear < mMonth && year == mYear)
                                view.updateDate(mYear, mMonth, mDay);

                            if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                                view.updateDate(mYear, mMonth, mDay);

                            endPickWeek.setText(year + "-"
                                    + (monthOfYear + 1) + "-" + dayOfMonth);
                        }
                    }, mYear, mMonth, mDay);
            dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            dpd.show();
        }
    }

    public void setTime(View v) {
        if (v == timePick) {

            // Process to get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog tpd = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            timePick.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            tpd.show();
        }
    }

    public void expandableButton1(View view) {
        expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout1);

        if(cbx.isChecked())
        {
            expandableLayout1.expand(); // toggle expand and collapse
            recurrence_stat = "true";
            Toast.makeText(AddSchedule.this, recurrence_stat, Toast.LENGTH_LONG).show();
        }
        else
        {
            expandableLayout1.collapse();
            recurrence_stat = "false";
            Toast.makeText(AddSchedule.this, recurrence_stat, Toast.LENGTH_LONG).show();
            cbMonday.setChecked(false);
            cbTuesday.setChecked(false);
            cbWednesday.setChecked(false);
            cbThursday.setChecked(false);
            cbFriday.setChecked(false);
            cbSaturday.setChecked(false);
            cbSunday.setChecked(false);
            endPickWeek.setText("");
            etNoWeeks.setText("");
        }
    }

    public void RecurDaily(View view){

        rb2 = (RadioButton) findViewById(R.id.radioWeekly);
        rb2.setChecked(false);

        expandableLayout2 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout2);
        expandableLayout2.expand();

        expandableLayout3 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout3);
        expandableLayout3.collapse();

        cbMonday.setChecked(false);
        cbTuesday.setChecked(false);
        cbWednesday.setChecked(false);
        cbThursday.setChecked(false);
        cbFriday.setChecked(false);
        cbSaturday.setChecked(false);
        cbSunday.setChecked(false);
        endPickWeek.setText("");
        etNoWeeks.setText("");

        cbMonday = (CheckBox) findViewById(R.id.monday);
        cbMonday.setEnabled(false);
        cbTuesday = (CheckBox) findViewById(R.id.tuesday);
        cbTuesday.setEnabled(false);
        cbWednesday = (CheckBox) findViewById(R.id.wednesday);
        cbWednesday.setEnabled(false);
        cbThursday = (CheckBox) findViewById(R.id.thursday);
        cbThursday.setEnabled(false);
        cbFriday = (CheckBox) findViewById(R.id.friday);
        cbFriday.setEnabled(false);
        cbSaturday = (CheckBox) findViewById(R.id.saturday);
        cbSaturday.setEnabled(false);
        cbSunday = (CheckBox) findViewById(R.id.sunday);
        cbSunday.setEnabled(false);

        recurDaily_stat = "true";
        recurWeekly_stat = "false";

//        Toast.makeText(AddSchedule.this, "RECURDAILY = "+recurDaily_stat+" / RECURWEEKLY = "+recurWeekly_stat, Toast.LENGTH_LONG).show();

    }

    public void RecurWeekly(View view){

        rb1 = (RadioButton) findViewById(R.id.radioDaily);

        rb1.setChecked(false);
        expandableLayout3 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout3);
        expandableLayout3.expand();

        expandableLayout2 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout2);
        expandableLayout2.collapse();

        endPickDay.setText("");
        etNoDays.setText("");

        cbMonday = (CheckBox) findViewById(R.id.monday);
        cbMonday.setEnabled(true);
        cbTuesday = (CheckBox) findViewById(R.id.tuesday);
        cbTuesday.setEnabled(true);
        cbWednesday = (CheckBox) findViewById(R.id.wednesday);
        cbWednesday.setEnabled(true);
        cbThursday = (CheckBox) findViewById(R.id.thursday);
        cbThursday.setEnabled(true);
        cbFriday = (CheckBox) findViewById(R.id.friday);
        cbFriday.setEnabled(true);
        cbSaturday = (CheckBox) findViewById(R.id.saturday);
        cbSaturday.setEnabled(true);
        cbSunday = (CheckBox) findViewById(R.id.sunday);
        cbSunday.setEnabled(true);

        recurWeekly_stat = "true";
        recurDaily_stat = "false";

//        Toast.makeText(AddSchedule.this, "RECURWEEKLY = "+recurWeekly_stat+" / RECURDAILY = "+recurDaily_stat, Toast.LENGTH_LONG).show();
    }

    public void clickAddSched(View v) {

        scheduleid = tvScheduleID.getText().toString();
        driverid = tvDriverID.getText().toString();
        vehicleid = tvVehicleID.getText().toString();

        pickup = etPickUp.getText().toString();
        dropoff = etDropOff.getText().toString();
        date = datePick.getText().toString();
        time = timePick.getText().toString();
        preference = spnPreference.getSelectedItem().toString();
        luggage = etLuggage.getSelectedItem().toString();
        nodays = etNoDays.getText().toString();
        enddateD = endPickDay.getText().toString();
        noweeks = etNoWeeks.getText().toString();
        enddateW = endPickWeek.getText().toString();

        int selecteditem = rbGroup.getCheckedRadioButtonId();
        rbPattern = (RadioButton)findViewById(selecteditem);

        pattern = rbPattern.getText().toString();

        if (cbSeat1.isChecked()) {seat1 = "yes";} else {seat1 = "no";}
        if (cbSeat2.isChecked()) {seat2 = "yes";} else {seat2 = "no";}
        if (cbSeat3.isChecked()) {seat3 = "yes";} else {seat3 = "no";}
        if (cbSeat4.isChecked()) {seat4 = "yes";} else {seat4 = "no";}
        /*if (cbSeat5.isChecked()) {seat5 = "yes";} else {seat5 = "no";}
        if (cbSeat6.isChecked()) {seat6 = "yes";} else {seat6 = "no";}
        if (cbSeat7.isChecked()) {seat7 = "yes";} else {seat7 = "no";}*/

        if (cbSunday.isChecked()) {sunday = "yes";  noOfCheckedDays++; checkedDaysList.add(1);} else {sunday = "no";}
        if (cbMonday.isChecked()) {monday = "yes"; noOfCheckedDays++; checkedDaysList.add(2);} else {monday = "no";}
        if (cbTuesday.isChecked()) {tuesday = "yes"; noOfCheckedDays++; checkedDaysList.add(3);} else {tuesday = "no";}
        if (cbWednesday.isChecked()) {wednesday = "yes"; noOfCheckedDays++; checkedDaysList.add(4);} else {wednesday = "no";}
        if (cbThursday.isChecked()) {thursday = "yes";  noOfCheckedDays++; checkedDaysList.add(5);} else {thursday = "no";}
        if (cbFriday.isChecked()) {friday = "yes"; noOfCheckedDays++; checkedDaysList.add(6);} else {friday = "no";}
        if (cbSaturday.isChecked()) {saturday = "yes"; noOfCheckedDays++; checkedDaysList.add(7);} else {saturday = "no";}

        String schedAddMethod = "addSchedule";
        if(recurrence_stat.equals("true"))
        {
            if(recurDaily_stat.equals("true"))
            {
                DriverBackgroundTask backgroundTask = new DriverBackgroundTask(this.getApplicationContext());
                backgroundTask.execute(schedAddMethod, scheduleid, driverid, vehicleid, pickup, dropoff, date, time, preference, luggage, pattern,
                        monday, tuesday, wednesday, thursday, friday, saturday, sunday, nodays, enddateD, seat1, seat2, seat3, seat4/*,seat5,seat6,seat7*/);

                addTripsRecurDaily();
            }
            else
            {
                DriverBackgroundTask backgroundTask = new DriverBackgroundTask(this.getApplicationContext());
                backgroundTask.execute(schedAddMethod,scheduleid,driverid,vehicleid,pickup,dropoff,date,time,preference,luggage,pattern,
                        monday,tuesday,wednesday,thursday,friday,saturday,sunday,noweeks,enddateW,seat1,seat2,seat3,seat4/*,seat5,seat6,seat7*/);

                addTripsWeekly();
            }
        }
        else
        {
            DriverBackgroundTask backgroundTask = new DriverBackgroundTask(this.getApplicationContext());
            backgroundTask.execute(schedAddMethod,scheduleid,driverid,vehicleid,pickup,dropoff,date,time,preference,luggage,pattern,
                    monday,tuesday,wednesday,thursday,friday,saturday,sunday,noweeks,enddateW,seat1,seat2,seat3,seat4/*,seat5,seat6,seat7*/);

            addTrips();
        }
        finish();
    }

    public void addTrips(){
        String tripAddMethod = "addTrips";
        TripTask tripTask = new TripTask(this.getApplicationContext());
        tripTask.execute(tripAddMethod, "null", scheduleid, driverid, vehicleid, pickup, dropoff, date, time,
                seat1, "null", seat2, "null", seat3, "null", seat4, "null",/*trSeat5,"null",trSeat6,"null",trSeat7,"null",*/"Open");
    }

    public void addTripsRecurDaily()
    {
        String tripAddMethod = "addTrips";
        String dt = date,new_date, from_date = date, to_date = enddateD;
        int diffDays;

        if (nodays.length() != 0)
        {
            for (int counter = 1; counter <= Integer.parseInt(nodays); counter++) {
                try {
                    c.setTime(sdf.parse(dt));
                } catch (ParseException e) {
                }
                c.add(Calendar.DATE, 1);
                dt = sdf.format(c.getTime());
                try{
                    new_c.setTime(sdf.parse(dt));
                }catch(ParseException e){}
                new_c.add(Calendar.DATE,-1);
                new_date = sdf.format(new_c.getTime());
                TripTask tripTask = new TripTask(this.getApplicationContext());
                tripTask.execute(tripAddMethod, "null", scheduleid, driverid, vehicleid, pickup, dropoff, new_date, time,
                        seat1, "null", seat2, "null", seat3, "null", seat4, "null",/*trSeat5,"null",trSeat6,"null",trSeat7,"null",*/"Open");
            }
        }
        else
        {
            try{
                c.setTime(sdf.parse(from_date));
                new_c.setTime(sdf.parse(to_date));
            }catch(ParseException e){}

            long millis1 = c.getTimeInMillis();
            long millis2 = new_c.getTimeInMillis();
            long diffmillis = millis2 - millis1;

            long diffdays = (diffmillis / (24 * 60 * 60 * 1000)) + 1;

            for (int counter = 1; counter <= (int)(long) diffdays; counter++) {
                try {
                    c.setTime(sdf.parse(dt));
                } catch (ParseException e) {
                }
                c.add(Calendar.DATE, 1);
                dt = sdf.format(c.getTime());
                try{
                    new_c.setTime(sdf.parse(dt));
                }catch(ParseException e){}
                new_c.add(Calendar.DATE,-1);
                new_date = sdf.format(new_c.getTime());
                TripTask tripTask = new TripTask(this.getApplicationContext());
                tripTask.execute(tripAddMethod, "null", scheduleid, driverid, vehicleid, pickup, dropoff, new_date, time,
                        seat1, "null", seat2, "null", seat3, "null", seat4, "null",/*trSeat5,"null",trSeat6,"null",trSeat7,"null",*/"Open");
            }
        }
    }

    public void addTripsWeekly() {

        String currentDate = date, new_date = "";
        int dOfTheWeek = 0;
        try {
            c.setTime(sdf.parse(currentDate));
        } catch (ParseException e) {
        }
        dOfTheWeek = c.get(Calendar.DAY_OF_WEEK);

        if (hasBeenChecked(dOfTheWeek).equals("true")) {   //if start_date is part of the checked days
            addTripsWeeklyMethod(dOfTheWeek, currentDate);
        }
        else
        {
            //if start date is not one of the checked days
            String checkedStat = "true";
            while(checkedStat.equals("true"))
            {   //get how many days from the picked start day to the next checked day
                dOfTheWeek++;
                if(dOfTheWeek==7)
                    dOfTheWeek = 1;
                if(hasBeenChecked(dOfTheWeek).equals("true"))
                {
                    checkedStat = "false";
                    dayStatCounter+=1;
                }
                else
                    dayStatCounter+=1;
            }

            try {
                c.setTime(sdf.parse(currentDate));
            } catch (ParseException e) {}
            c.add(Calendar.DATE, dayStatCounter);
            dOfTheWeek = c.get(Calendar.DAY_OF_WEEK);
            currentDate = sdf.format(c.getTime());
            date = currentDate;
            addTripsWeeklyMethod(dOfTheWeek, date);
        }
    }

    public void addTripsWeeklyMethod(int day_of_the_week, String curr_Date)
    {
        int dayOfTheWeekMethod = day_of_the_week, num_of_weeks = 0, remainder = 0;
        String current_date_method = curr_Date, original_start_date = date, end_date_weekly = enddateW;
//        Log.d("ENTER HERE DAY OF THE WEEK AND CURRENT DATE", String.valueOf(dayOfTheWeek)+current_date);

        if (noweeks.length()!=0) {   //if number of weeks is given
            num_of_weeks = Integer.parseInt(noweeks);
            addTripsWeeklyProcess(num_of_weeks,dayOfTheWeekMethod, remainder, current_date_method);
        }
        else
        {
            try{
                c.setTime(sdf.parse(original_start_date));
                new_c.setTime(sdf.parse(end_date_weekly));
            }catch(ParseException e){}

            long millis1 = c.getTimeInMillis();
            long millis2 = new_c.getTimeInMillis();
            long diffmillis = millis2 - millis1;

            long diffdays = (diffmillis / (24 * 60 * 60 * 1000)) + 1;
            num_of_weeks = (int)(long)diffdays/7;
            remainder = (int)(long)diffdays-(7*num_of_weeks);

//            Log.d("DATE DIFFERENCE WEEKLY: ", String.valueOf((int) (long) diffdays));
//            Log.d("DATE NUM OF WEEKS AND REMAINDER : ", String.valueOf(num_of_weeks) + " " + String.valueOf(remainder));

            addTripsWeeklyProcess(num_of_weeks, dayOfTheWeekMethod, remainder, current_date_method);
        }
    }

    public void addTripsWeeklyProcess(int numWeeks, int day_of_the_week_from_method, int remainder_from_method, String current_date_from_method)
    {
        String tripAddMethod = "addTrips";
        String current_date_process = current_date_from_method, original_start_date = date, end_date = enddateW;
        int numOfWeeks = numWeeks, dayOfTheWeekProcess = day_of_the_week_from_method, valOfNextCheckedDay = 0, diffOfTheDays = 0, dayToAdd = 0, remainder = remainder_from_method;
        for (countWeeks = 1; countWeeks <= numOfWeeks; countWeeks++) {   //loop by the number of weeks given
            for (countCheckedDays = 1; countCheckedDays <= noOfCheckedDays; countCheckedDays++) {   //loop by the how many days are checked
                int loop = 0;
                try {
                    c.setTime(sdf.parse(current_date_process));
                } catch (ParseException e) {
                }
                dayOfTheWeekProcess = c.get(Calendar.DAY_OF_WEEK);

                if (countCheckedDays != noOfCheckedDays || countCheckedDays!=1) {
//                        Log.d("ENTER HERE WHILE COUNTER!=NO. OF CHECKED DAYS", "Hello World!");
//                        Log.d("ENTER HERE VALUE OF COUNTER FOR CHECKED DAYS", String.valueOf(countCheckedDays));
                    valOfNextCheckedDay = checkedDaysList.get(loop); //get the value of next checked day from the current day
                    if(valOfNextCheckedDay!=dayOfTheWeekProcess || dayStatCounter>0)
                    {
                        while(dayOfTheWeekProcess!=checkedDaysList.get(loop))
                        {
//                                Log.d("ENTER HERE VALUE OF LOOP", String.valueOf(loop));
                            if(loop==(noOfCheckedDays-1))
                                loop = 0;
                            else
                                loop+=1;
                        }
//                            Log.d("ENTER HERE VALUE OF LOOP AFTER LOOP", String.valueOf(loop));
                        valOfNextCheckedDay = checkedDaysList.get(loop); //get the value of next checked day from the current day
                    }
                    if (valOfNextCheckedDay == dayOfTheWeekProcess) {
                        loop += 1;
                        if (loop != checkedDaysList.size()) {
                            valOfNextCheckedDay = checkedDaysList.get(loop);
                        } else {
                            loop = 0;
                            valOfNextCheckedDay = checkedDaysList.get(loop);
                        }
                    }
//                        Log.d("ENTER HERE VALUE OF NEXT CHECKED DAY AND DAY OF THE WEEK", String.valueOf(valOfNextCheckedDay) + String.valueOf(dayOfTheWeek));
//                        Log.d("ENTER HERE VALUE OF VAL OF NEXT CHECKED", String.valueOf(valOfNextCheckedDay));
                    diffOfTheDays = (valOfNextCheckedDay - dayOfTheWeekProcess); //get the days difference of current day with the next day
                    if (!(diffOfTheDays > 0)) {diffOfTheDays += 7;}
                }
                if(countCheckedDays==1)
                {
                    dayToAdd = 7 * (countWeeks-1);
                    current_date_process = addDays(original_start_date, dayToAdd);
                    TripTask tripTask = new TripTask(this.getApplicationContext());
                    tripTask.execute(tripAddMethod, "null", scheduleid, driverid, vehicleid, pickup, dropoff, current_date_process, time,
                            seat1, "null", seat2, "null", seat3, "null", seat4, "null",/*trSeat5,"null",trSeat6,"null",trSeat7,"null",*/"Open");
                }
                else
                {
                    current_date_process = addDays(current_date_process, diffOfTheDays);
                    TripTask tripTask = new TripTask(this.getApplicationContext());
                    tripTask.execute(tripAddMethod, "null", scheduleid, driverid, vehicleid, pickup, dropoff, current_date_process, time,
                            seat1, "null", seat2, "null", seat3, "null", seat4, "null",/*trSeat5,"null",trSeat6,"null",trSeat7,"null",*/"Open");
                }
            }
        }
        if(remainder>0)
        {
            String temp_current_date;
            try{
                c.setTime(sdf.parse(end_date));
            }catch(ParseException e){}
            end_date = sdf.format(c.getTime());
            try {
                c.setTime(sdf.parse(current_date_process));
            } catch (ParseException e) {}
            c.add(Calendar.DATE,1);
            current_date_process = sdf.format(c.getTime());
            while(!current_date_process.equals(end_date))
            {
                try {
                    c.setTime(sdf.parse(current_date_process));
                } catch (ParseException e) {
                }
                dayOfTheWeekProcess = c.get(Calendar.DAY_OF_WEEK);
                if(hasBeenChecked(dayOfTheWeekProcess).equals("true"))
                {
                    temp_current_date = current_date_process;
//                    Log.d("ENTER HERE VALUE OF NEW CURRENT DATE IN REMAINDER", temp_current_date);
                    try {
                        c.setTime(sdf.parse(current_date_process));
                    } catch (ParseException e) {}
                    c.add(Calendar.DATE,1);
                    current_date_process = sdf.format(c.getTime());

                    TripTask tripTask = new TripTask(this.getApplicationContext());
                    tripTask.execute(tripAddMethod, "null", scheduleid, driverid, vehicleid, pickup, dropoff, temp_current_date, time,
                            seat1, "null", seat2, "null", seat3, "null", seat4, "null",/*trSeat5,"null",trSeat6,"null",trSeat7,"null",*/"Open");
                }
                else
                {
                    try {
                        c.setTime(sdf.parse(current_date_process));
                    } catch (ParseException e) {}
                    c.add(Calendar.DATE,1);
                    current_date_process = sdf.format(c.getTime());
                }

            }
            if(current_date_process.equals(end_date))
            {
                try {
                    c.setTime(sdf.parse(current_date_process));
                } catch (ParseException e) {
                }
                dayOfTheWeekProcess = c.get(Calendar.DAY_OF_WEEK);
                if(hasBeenChecked(dayOfTheWeekProcess).equals("true"))
                {
//                    Log.d("ENTER HERE VALUE OF NEW CURRENT DATE WHEN CURRENT DATE == END DATE", current_date_process);
                    TripTask tripTask = new TripTask(this.getApplicationContext());
                    tripTask.execute(tripAddMethod, "null", scheduleid, driverid, vehicleid, pickup, dropoff, current_date_process, time,
                            seat1, "null", seat2, "null", seat3, "null", seat4, "null",/*trSeat5,"null",trSeat6,"null",trSeat7,"null",*/"Open");
                }
            }
        }
    }

    public String addDays(String currentDate, int dateDiff)
    {
        String current_start = "";
        current_start = currentDate;
        int dateDifference = dateDiff;
        try {
            c.setTime(sdf.parse(current_start));
        } catch (ParseException e) {}
        c.add(Calendar.DATE,dateDifference);
        current_start = sdf.format(c.getTime());
//        Log.d("ENTER HERE VALUE OF NEW CURRENT DATE", current_start);
        return current_start;
    }

    public String hasBeenChecked(int dWeek) {
        int day_of_the_week = dWeek;
        String stat ="";
        switch (day_of_the_week)
        {
            case 1:
                if(sunday.equals("yes"))
                    stat = "true";
                else
                    stat = "false";
                break;
            case 2:
                if(monday.equals("yes"))
                    stat = "true";
                else
                    stat = "false";
                break;
            case 3:
                if(tuesday.equals("yes"))
                    stat = "true";
                else
                    stat = "false";
                break;
            case 4:
                if(wednesday.equals("yes"))
                    stat = "true";
                else
                    stat = "false";
                break;
            case 5:
                if(thursday.equals("yes"))
                    stat = "true";
                else
                    stat = "false";
                break;
            case 6:
                if(friday.equals("yes"))
                    stat = "true";
                else
                    stat = "false";
                break;
            case 7:
                if(saturday.equals("yes"))
                    stat = "true";
                else
                    stat = "false";
                break;
        }
        return stat;
    }
}

