package com.example.jivenlanstabien.projecthitch1.Login;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jivenlanstabien.projecthitch1.R;

import java.util.Calendar;

public class sign_up extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    Context context = this;
    EditText tbFname, tbMname, tbLname, tbEmail, tbEmailAgain, tbPassword, tbPasswordAgain;
    Spinner spnGender;
    Button signup, tbBirthDate;
    int mYear, mMonth, mDay;
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        tbFname = (EditText) findViewById(R.id.tbFname);
        tbMname = (EditText) findViewById(R.id.tbMname);
        tbLname = (EditText) findViewById(R.id.tbLname);
        tbBirthDate = (Button) findViewById(R.id.tbBirthdate);
        tbEmail = (EditText) findViewById(R.id.tbEmail);
        tbEmailAgain = (EditText) findViewById(R.id.tbEmailAgain);
        spnGender = (Spinner) findViewById(R.id.spnGender);
        tbPassword = (EditText) findViewById(R.id.tbPassword);
        tbPasswordAgain = (EditText) findViewById(R.id.tbPasswordAgain);
        signup = (Button) findViewById(R.id.Signup);
        tbFname.requestFocus();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

    }
       /* SharedPreferences sharedPreferences = getSharedPreferences("MyData",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ID", DriverID.getText().toString());

        editor.commit();
        */
       public void setBDate(View v) {

           if (v == tbBirthDate) {
               DatePickerDialog dpd = new DatePickerDialog(this,
                       new DatePickerDialog.OnDateSetListener() {

                           @Override
                           public void onDateSet(DatePicker view, int year,
                                                 int monthOfYear, int dayOfMonth) {
                              /* if (year < mYear)
                                   view.updateDate(mYear, mMonth, mDay);

                               if (monthOfYear < mMonth && year == mYear)
                                   view.updateDate(mYear, mMonth, mDay);

                               if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                                   view.updateDate(mYear, mMonth, mDay);
*/
                               tbBirthDate.setText(year + "-"
                                       + (monthOfYear + 1) + "-" + dayOfMonth);
                           }
                       }, mYear, mMonth, mDay);
               //dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
               dpd.show();
           }
       }

    public void signup(){

        String addFname = tbFname.getText().toString().trim();
        String addMname = tbMname.getText().toString().trim();
        String addLname = tbLname.getText().toString().trim();
        String addEmail = tbEmail.getText().toString().trim();
        String addGender = spnGender.getSelectedItem().toString().trim();
        String addBirthdate = tbBirthDate.getText().toString().trim();
        String addPassword = tbPassword.getText().toString().trim();
        String PasswordAgain = tbPasswordAgain.getText().toString().trim();
        String EmailAgain = tbEmailAgain.getText().toString().trim();


        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (addFname.equals("") || addFname == null || addMname.equals("") || addMname.length() == 0 ||
                addLname.equals("") || addLname == null || addEmail.equals("") || addEmail.length() == 0 ||
                addGender.equals("") || addGender == null || addBirthdate.equals("") || addBirthdate.length() == 0 ||
                addPassword.equals("") || addPassword == null || PasswordAgain.equals("") || PasswordAgain.length() == 0 ||
                EmailAgain.equals("") || EmailAgain == null) {
            Toast.makeText(getApplicationContext(), "Please complete your Registration details.", Toast.LENGTH_LONG).show();
            return;
        }
        else if (!addEmail.equals(EmailAgain)) {
            //Toast.makeText(getApplicationContext(), "Password does not match.", Toast.LENGTH_LONG).show();
            tbEmailAgain.setError("Email does not match.");
            tbEmailAgain.requestFocus();
            return;
        }

        // onClick of button perform this simplest code.
        else if (!addEmail.matches(emailPattern)){
            tbEmail.setError("Invalid Email Address!");
            tbEmail.requestFocus();
        }
        else if (!addPassword.equals(PasswordAgain)) {
            //Toast.makeText(getApplicationContext(), "Password does not match.", Toast.LENGTH_LONG).show();
            tbPasswordAgain.setError("Password does not match.");
            tbPasswordAgain.requestFocus();
            return;
        } else {

            String method = "register";
            loginBackgroundTask backgroundTask = new loginBackgroundTask(this);
            backgroundTask.execute(method,addFname,addMname,addLname,addEmail,addGender,addBirthdate,addPassword);
        }
    }
}