package com.example.jivenlanstabien.projecthitch1.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jivenlanstabien.projecthitch1.R;
import com.example.jivenlanstabien.projecthitch1.Passenger.home_screen;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class Login extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    TextView ForgotPass;
    EditText Username, Password;
    String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        String email_login = sharedPreferences.getString("email_login", "");

        if(email_login != "")
        {
            Intent intent = new Intent(this,home_screen.class);
            startActivity(intent);
        }
        Username = (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.Password);

        ForgotPass = (TextView) findViewById(R.id.ForgotPass);
        ForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,forgot_password.class);
                startActivity(intent);
            }
        });


    }

    public void signin(View view) {
        String getusername = Username.getText().toString().trim();
        String getpassword = Password.getText().toString().trim();

        String method = "register";
        accessBackgroundTask backgroundTask = new accessBackgroundTask(this);
        backgroundTask.execute(method,getusername,getpassword);
    }



    public void signup(View view)
    {

        Intent intent = new Intent(Login.this,sign_up.class);
        startActivity(intent);
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
