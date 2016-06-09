package com.example.jivenlanstabien.projecthitch1.Login;

/**
 * Created by jivenlans on 6/8/2016.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jivenlanstabien.projecthitch1.R;

public class SecurityCodeChecking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_code_checking);
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
