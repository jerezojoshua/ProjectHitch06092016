package com.example.jivenlanstabien.projecthitch1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jivenlanstabien.projecthitch1.R;

public class driver_registration_form extends Activity implements View.OnClickListener {

    Context context=this;

    TextView txtDriverID;
    ImageView ImgNBI,ImgPNP,ImgInsurance,ImgOR,ImgCR;
    Button btnImgNBI,btnImgPNP,btnImgInsurance,btnImgOR,btnImgCR;
    EditText tbLicenseNo,tbLicenseExpiry;

    String imgDecodableString;
    private static final int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_registration_form);

        tbLicenseNo = (EditText) findViewById(R.id.tbLicenseNo);
        tbLicenseExpiry = (EditText) findViewById(R.id.tbLicenseExpiry);

       /* SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        txtDriverID = (TextView) findViewById(R.id.txtDriverID);
        txtDriverID.setText(sharedPreferences.getString("ID",""));*/

        ImgNBI = (ImageView) findViewById(R.id.imgNBI);
        ImgPNP = (ImageView) findViewById(R.id.imgPNP);
        ImgInsurance = (ImageView) findViewById(R.id.imgInsurance);
        ImgOR = (ImageView) findViewById(R.id.imgOR);
        ImgCR = (ImageView) findViewById(R.id.imgCR);

        ImgNBI.setOnClickListener(this);
        ImgPNP.setOnClickListener(this);
        ImgInsurance.setOnClickListener(this);
        ImgOR.setOnClickListener(this);
        ImgCR.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgNBI:
                Intent intentNBI = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentNBI, RESULT_LOAD_IMAGE);
            case R.id.imgPNP:
                Intent intentPNP = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentPNP, RESULT_LOAD_IMAGE);
            case R.id.imgInsurance:
                Intent intentInsurance = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentInsurance, RESULT_LOAD_IMAGE);
            case R.id.imgOR:
                Intent intentOR = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentOR, RESULT_LOAD_IMAGE);
            case R.id.imgCR:
                Intent intentCR = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentCR, RESULT_LOAD_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imgNBI);
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
    }


    public void addDriver(View view){



    }
}
