package com.example.jivenlanstabien.projecthitch1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.Calendar;

public class AddSchedule extends AppCompatActivity {

    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3;
    RadioButton rb1, rb2;
    CheckBox cbx;
    Button btnAddSched;
    EditText etPickUp, etDropOff, etTime, etNoDays, etNoWeeks;
    Spinner spnPreference, spnLuggage;
    TextView tvScheduleID, tvDriverID, tvVehicleID;
    RadioButton rbPattern;
    RadioGroup rbGroup;
    CheckBox cbMonday, cbTuesday, cbWednesday, cbThursday, cbFriday, cbSaturday, cbSunday, cbRecurrence, cbSeat1,
            cbSeat2, cbSeat3, cbSeat4, cbSeat5, cbSeat6, cbSeat7;
    String scheduleid, driverid, vehicleid, pickup, dropoff, date, time, preference, luggage, nodays, enddateD, noweeks,
            enddateW, monday, tuesday, wednesday, thursday, friday, saturday, sunday, pattern, seat1, seat2, seat3, seat4, seat5
            , seat6, seat7;
    String trSeat1,trSeat2,trSeat3,trSeat4,trSeat5,trSeat6,trSeat7;

    DatePicker datePicker;
    Calendar c;
    Button datePick, endPickWeek, endPickDay;
    EditText timePick;
    int mYear, mMonth, mDay, mHour, mMinute;
    SharedPreferences sharedPreferences;
    String getdriverid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_schedule);

        sharedPreferences = getSharedPreferences("driver", MODE_PRIVATE);
        getdriverid = sharedPreferences.getString("driverid", "");

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
        tvDriverID.setText(getdriverid);
        tvVehicleID = (TextView) findViewById(R.id.txtVehicleID);
        btnAddSched = (Button) findViewById(R.id.btnAdd);

        etPickUp = (EditText) findViewById(R.id.txtPickUp);
        etDropOff = (EditText) findViewById(R.id.txtDropOff);
        etTime = (EditText) findViewById(R.id.txtTime);
        spnLuggage = (Spinner) findViewById(R.id.luggage_provision);
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
        /*cbSeat5 = (CheckBox) findViewById(R.id.Seat5);
        cbSeat6 = (CheckBox) findViewById(R.id.Seat6);
        cbSeat7 = (CheckBox) findViewById(R.id.Seat7);*/

        cbx = (CheckBox)findViewById(R.id.expandableButton1);

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
        }
        else
        {
            expandableLayout1.collapse();
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

    }
    public void clickAddSched(View v) {
        sharedPreferences = getSharedPreferences("driver", MODE_PRIVATE);
        getdriverid = sharedPreferences.getString("driverid", "");
        scheduleid = tvScheduleID.getText().toString();
        driverid = getdriverid;
        vehicleid = tvVehicleID.getText().toString();

        pickup = etPickUp.getText().toString();
        dropoff = etDropOff.getText().toString();
        date = datePick.getText().toString();
        time = timePick.getText().toString();
        preference = spnPreference.getSelectedItem().toString();
        luggage = spnLuggage.getSelectedItem().toString();
        nodays = etNoDays.toString();
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

        if (cbMonday.isChecked()) {monday = "yes";} else {monday = "no";}
        if (cbTuesday.isChecked()) {tuesday = "yes";} else {tuesday = "no";}
        if (cbWednesday.isChecked()) {wednesday = "yes";} else {wednesday = "no";}
        if (cbThursday.isChecked()) {thursday = "yes";} else {thursday = "no";}
        if (cbFriday.isChecked()) {friday = "yes";} else {friday = "no";}
        if (cbSaturday.isChecked()) {saturday = "yes";} else {saturday = "no";}
        if (cbSunday.isChecked()) {sunday = "yes";} else {sunday = "no";}

        String schedAddMethod = "addSchedule";
        DriverBackgroundTask backgroundTask = new DriverBackgroundTask(this.getApplicationContext());
        backgroundTask.execute(schedAddMethod,scheduleid,driverid,vehicleid,pickup,dropoff,date,time,preference,luggage,pattern,
                monday,tuesday,wednesday,thursday,friday,saturday,sunday,noweeks,enddateW,seat1,seat2,seat3,seat4/*,seat5,seat6,seat7*/);

        addTrips();
        finish();
    }

    public void addTrips(){
        sharedPreferences = getSharedPreferences("driver", MODE_PRIVATE);
        getdriverid = sharedPreferences.getString("driverid", "");
        if (cbMonday.isChecked()) {
            if (seat1.equals("yes")) {trSeat1 = "Available";} else {trSeat1 = "Not Available";}
            if (seat2.equals("yes")) {trSeat2 = "Available";} else {trSeat2 = "Not Available";}
            if (seat3.equals("yes")) {trSeat3 = "Available";} else {trSeat3 = "Not Available";}
            if (seat4.equals("yes")) {trSeat4 = "Available";} else {trSeat4 = "Not Available";}
            String tripAddMethod = "addTrips";
            TripTask tripTask = new TripTask(this.getApplicationContext());
            tripTask.execute(tripAddMethod,"null",scheduleid,getdriverid,vehicleid,pickup,dropoff,date,time,
                    trSeat1,"null",trSeat2,"null",trSeat3,"null",trSeat4,"null","Open");
        } else {
            monday = "no";
        }
        if (cbTuesday.isChecked()) {
            if (cbSeat1.isChecked()) {trSeat1 = "Available";} else {trSeat1 = "Not Available";}
            if (cbSeat2.isChecked()) {trSeat2 = "Available";} else {trSeat2 = "Not Available";}
            if (cbSeat3.isChecked()) {trSeat3 = "Available";} else {trSeat3 = "Not Available";}
            if (cbSeat4.isChecked()) {trSeat4 = "Available";} else {trSeat4 = "Not Available";}
            /*if (cbSeat5.isChecked()) {trSeat5 = "Available";} else {trSeat5 = "Not Available";}
            if (cbSeat6.isChecked()) {trSeat6 = "Available";} else {trSeat6 = "Not Available";}
            if (cbSeat7.isChecked()) {trSeat7 = "Available";} else {trSeat7 = "Not Available";}*/
            String tripAddMethod = "addTrips";
            TripTask tripTask = new TripTask(this.getApplicationContext());
            tripTask.execute(tripAddMethod,"null",scheduleid,getdriverid,vehicleid,pickup,dropoff,date,time,
                    trSeat1,"null",trSeat2,"null",trSeat3,"null",trSeat4,"null",/*trSeat5,"null",trSeat6,"null",trSeat7,"null",*/"Open");
        } else {
            tuesday = "no";
        }
        if (cbWednesday.isChecked()) {
            if (cbSeat1.isChecked()) {trSeat1 = "Available";} else {trSeat1 = "Not Available";}
            if (cbSeat2.isChecked()) {trSeat2 = "Available";} else {trSeat2 = "Not Available";}
            if (cbSeat3.isChecked()) {trSeat3 = "Available";} else {trSeat3 = "Not Available";}
            if (cbSeat4.isChecked()) {trSeat4 = "Available";} else {trSeat4 = "Not Available";}
            /*if (cbSeat5.isChecked()) {trSeat5 = "Available";} else {trSeat5 = "Not Available";}
            if (cbSeat6.isChecked()) {trSeat6 = "Available";} else {trSeat6 = "Not Available";}
            if (cbSeat7.isChecked()) {trSeat7 = "Available";} else {trSeat7 = "Not Available";}*/
            String tripAddMethod = "addTrips";
            TripTask tripTask = new TripTask(this.getApplicationContext());
            tripTask.execute(tripAddMethod,"null",scheduleid,getdriverid,vehicleid,pickup,dropoff,date,time,
                    trSeat1,"null",trSeat2,"null",trSeat3,"null",trSeat4,"null",/*trSeat5,"null",trSeat6,"null",trSeat7,"null",*/"Open");
        } else {
            wednesday = "no";
        }
        if (cbThursday.isChecked()) {
            if (cbSeat1.isChecked()) {trSeat1 = "Available";} else {trSeat1 = "Not Available";}
            if (cbSeat2.isChecked()) {trSeat2 = "Available";} else {trSeat2 = "Not Available";}
            if (cbSeat3.isChecked()) {trSeat3 = "Available";} else {trSeat3 = "Not Available";}
            if (cbSeat4.isChecked()) {trSeat4 = "Available";} else {trSeat4 = "Not Available";}
            /*if (cbSeat5.isChecked()) {trSeat5 = "Available";} else {trSeat5 = "Not Available";}
            if (cbSeat6.isChecked()) {trSeat6 = "Available";} else {trSeat6 = "Not Available";}
            if (cbSeat7.isChecked()) {trSeat7 = "Available";} else {trSeat7 = "Not Available";}*/
            String tripAddMethod = "addTrips";
            TripTask tripTask = new TripTask(this.getApplicationContext());
            tripTask.execute(tripAddMethod,"null",scheduleid,driverid,vehicleid,pickup,dropoff,date,time,
                    trSeat1,"null",trSeat2,"null",trSeat3,"null",trSeat4,"null",/*trSeat5,"null",trSeat6,"null",trSeat7,"null",*/"Open");
        } else {
            thursday = "no";
        }
        if (cbFriday.isChecked()) {
            if (cbSeat1.isChecked()) {trSeat1 = "Available";} else {trSeat1 = "Not Available";}
            if (cbSeat2.isChecked()) {trSeat2 = "Available";} else {trSeat2 = "Not Available";}
            if (cbSeat3.isChecked()) {trSeat3 = "Available";} else {trSeat3 = "Not Available";}
            if (cbSeat4.isChecked()) {trSeat4 = "Available";} else {trSeat4 = "Not Available";}
            /*if (cbSeat5.isChecked()) {trSeat5 = "Available";} else {trSeat5 = "Not Available";}
            if (cbSeat6.isChecked()) {trSeat6 = "Available";} else {trSeat6 = "Not Available";}
            if (cbSeat7.isChecked()) {trSeat7 = "Available";} else {trSeat7 = "Not Available";}*/
            String tripAddMethod = "addTrips";
            TripTask tripTask = new TripTask(this.getApplicationContext());
            tripTask.execute(tripAddMethod,"null",scheduleid,driverid,vehicleid,pickup,dropoff,date,time,
                    trSeat1,"null",trSeat2,"null",trSeat3,"null",trSeat4,"null",/*trSeat5,"null",trSeat6,"null",trSeat7,"null",*/"Open");
        } else {
            friday = "no";
        }
        if (cbSaturday.isChecked()) {
            if (cbSeat1.isChecked()) {trSeat1 = "Available";} else {trSeat1 = "Not Available";}
            if (cbSeat2.isChecked()) {trSeat2 = "Available";} else {trSeat2 = "Not Available";}
            if (cbSeat3.isChecked()) {trSeat3 = "Available";} else {trSeat3 = "Not Available";}
            if (cbSeat4.isChecked()) {trSeat4 = "Available";} else {trSeat4 = "Not Available";}
            /*if (cbSeat5.isChecked()) {trSeat5 = "Available";} else {trSeat5 = "Not Available";}
            if (cbSeat6.isChecked()) {trSeat6 = "Available";} else {trSeat6 = "Not Available";}
            if (cbSeat7.isChecked()) {trSeat7 = "Available";} else {trSeat7 = "Not Available";}*/
            String tripAddMethod = "addTrips";
            TripTask tripTask = new TripTask(this.getApplicationContext());
            tripTask.execute(tripAddMethod,"null",scheduleid,driverid,vehicleid,pickup,dropoff,date,time,
                    trSeat1,"null",trSeat2,"null",trSeat3,"null",trSeat4,"null",/*trSeat5,"null",trSeat6,"null",trSeat7,"null",*/"Open");
        } else {
            saturday = "no";
        }
        if (cbSunday.isChecked()) {
            if (cbSeat1.isChecked()) {trSeat1 = "Available";} else {trSeat1 = "Not Available";}
            if (cbSeat2.isChecked()) {trSeat2 = "Available";} else {trSeat2 = "Not Available";}
            if (cbSeat3.isChecked()) {trSeat3 = "Available";} else {trSeat3 = "Not Available";}
            if (cbSeat4.isChecked()) {trSeat4 = "Available";} else {trSeat4 = "Not Available";}
            /*if (cbSeat5.isChecked()) {trSeat5 = "Available";} else {trSeat5 = "Not Available";}
            if (cbSeat6.isChecked()) {trSeat6 = "Available";} else {trSeat6 = "Not Available";}
            if (cbSeat7.isChecked()) {trSeat7 = "Available";} else {trSeat7 = "Not Available";}*/
            String tripAddMethod = "addTrips";
            TripTask tripTask = new TripTask(this.getApplicationContext());
            tripTask.execute(tripAddMethod,"null",scheduleid,driverid,vehicleid,pickup,dropoff,date,time,
                    trSeat1,"null",trSeat2,"null",trSeat3,"null",trSeat4,"null",/*trSeat5,"null",trSeat6,"null",trSeat7,"null",*/"Open");
        } else {
            sunday = "no";
        }
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}

