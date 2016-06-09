package com.example.jivenlanstabien.projecthitch1;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jivenlanstabien.projecthitch1.Login.SendMail;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class driver_sign_up extends AppCompatActivity {


    private int PICK_IMAGE_REQUEST = 1;
    private int PICK_IMAGE_REQUEST2 = 2;
    private int PICK_IMAGE_REQUEST3 = 3;
    private int PICK_IMAGE_REQUEST4 = 4;
    private int PICK_IMAGE_REQUEST5 = 5;
    private int PICK_IMAGE_REQUEST6 = 6;
    private int PICK_IMAGE_REQUEST7 = 7;
    private int PICK_IMAGE_REQUEST8 = 8;
    private int PICK_IMAGE_REQUEST9 = 9;
    private int PICK_IMAGE_REQUEST10 = 10;
    private int PICK_IMAGE_REQUEST11 = 11;
    private int PICK_IMAGE_REQUEST12 = 12;
    private int PICK_IMAGE_REQUEST13 = 13;
    private int PICK_IMAGE_REQUEST14 = 14;

    Bitmap bitmap;
    ProgressDialog pDialog;

    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4;
    //--------Personal Info Declarations---------
    ImageView img;
    ImageView engineimage, chassisimage,photoexteriorfront,photoexteriorback,photoexteriorright,photoexteriorleft,photointeriorfront,photointeriorback;
    TextView txtDriverID, txtFullname;
    Spinner bodytype, make, year, fuel_type;
    EditText model, vehicle_color, platenumber, engine, chassis;
    String sbodytype, smake, smodel, syear, svehicle_color, sfuel_type, splatenumber, sengine, schassis, sTxtAddress1, sTxtAddress2, sTxtCity, sTxtCountry, sTxtMainPhone, sTxtAlterPhone, sTxtEmerContact, sTxtEmerPhone, stbLicenseNo, stbLicenseExpiry, salternateDriverEmail, salternateDriverID, smaritalstatus, getemail, getemail2;



    //--------Alternate Driver Declaration------

    EditText alternateDriverEmail, alternateDriverID;

    //--------Requirements Declarations----------
    Context context = this;
    ImageView ImgNBI, ImgPNP, ImgInsurance, ImgOR, ImgCR, ImgLicense;
    EditText tbLicenseNo, tbLicenseExpiry;

    private DatePickerDialog expirydate;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_sign_up);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        String email_login = sharedPreferences.getString("email_login", "");
        //================== Photo ID =====================

        img = (ImageView) findViewById(R.id.Photo);
        new image_name_retrieve(this).execute();
        TextView email = (TextView) findViewById(R.id.email);
        getemail2 = email_login;

        //=========================PERSONAL INFORMATION=================================
        sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        txtFullname = (TextView) findViewById(R.id.txtName);
        txtDriverID = (TextView) findViewById(R.id.txtDriverID);
        txtDriverID.setText(sharedPreferences.getString("ID", ""));

        //=========================REQUIREMENTS INFORMATION=================================
        tbLicenseNo = (EditText) findViewById(R.id.tbLicenseNo);
        tbLicenseExpiry = (EditText) findViewById(R.id.tbLicenseExpiry);


        ImgNBI = (ImageView) findViewById(R.id.imgNBI);
        ImgPNP = (ImageView) findViewById(R.id.imgPNP);
        ImgInsurance = (ImageView) findViewById(R.id.imgInsurance);
        ImgOR = (ImageView) findViewById(R.id.imgOR);
        ImgCR = (ImageView) findViewById(R.id.imgCR);
        ImgLicense = (ImageView) findViewById(R.id.licensePhoto);


        //=========================REQUIREMENTS INFORMATION=================================

        //=========================Alternate Driver=========================================

        alternateDriverEmail = (EditText) findViewById(R.id.txtAlterDriver);
        alternateDriverID = (EditText) findViewById(R.id.txtAlterDriverID);

        // ==========================Vehicle=======================================
        bodytype = (Spinner) findViewById(R.id.body_type);
        make = (Spinner) findViewById(R.id.make);
        model = (EditText) findViewById(R.id.model);
        year = (Spinner) findViewById(R.id.year);
        vehicle_color = (EditText) findViewById(R.id.vehicle_color);
        fuel_type = (Spinner) findViewById(R.id.fuel_type);
        platenumber = (EditText) findViewById(R.id.plate_number);
        engine = (EditText) findViewById(R.id.engine);
        chassis = (EditText) findViewById(R.id.chassis);

        engineimage = (ImageView)findViewById(R.id.engineimage);
        chassisimage = (ImageView)findViewById(R.id.chassisimage);
        photoexteriorfront = (ImageView)findViewById(R.id.photoexteriorfront);
        photoexteriorback = (ImageView)findViewById(R.id.photoexteriorback);
        photoexteriorright = (ImageView)findViewById(R.id.photoexteriorright);
        photoexteriorleft = (ImageView)findViewById(R.id.photoexteriorleft);
        photointeriorfront = (ImageView)findViewById(R.id.photointeriorfront);
        photointeriorback = (ImageView)findViewById(R.id.photointeriorback);

        // ==========================Vehicle=======================================

        final TextView btnOpenPopup = (TextView) findViewById(R.id.clickhere);
        btnOpenPopup.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater
                        = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.request_driverid_popup, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView, 650, 950, true);

                final EditText email = (EditText) popupView.findViewById(R.id.email_address);
                Button btnDismiss = (Button) popupView.findViewById(R.id.cancelbutton);
                btnDismiss.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        popupWindow.dismiss();
                    }
                });

                Button request = (Button) popupView.findViewById(R.id.send_request);
                request.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub

                        String getemail = email.getText().toString().trim();
                        if (getemail.equals("")) {

                            Toast.makeText(getBaseContext(), "Please put the email address", Toast.LENGTH_LONG).show();
                        } else {
                            SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                            String getemail2 = sharedPreferences.getString("email_login", "");
                            EditText editTextEmail = (EditText) findViewById(R.id.email_address);
                            Toast.makeText(getBaseContext(), "The driver ID of your alternate driver will be send to your email account if he/she accepted your request", Toast.LENGTH_LONG).show();
                            popupWindow.dismiss();


                            String subject = "Project Hitch";
                            String message = getemail2 + " wants to add you as his/her alternate driver. Please send your driver ID to him/her if you want to be his/her alternate driver Thanks";

                            //Creating SendMail object
                            SendMail sm = new SendMail(driver_sign_up.this, getemail, subject, message);

                            //Executing sendmail to send email
                            sm.execute();

                        }


                    }
                });

                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

            }
        });

    }


    public void expandableButton1(View view) {
        expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout1);
        expandableLayout1.toggle(); // toggle expand and collapse
    }

    public void expandableButton2(View view) {
        expandableLayout2 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout2);
        expandableLayout2.toggle(); // toggle expand and collapse
    }

    public void expandableButton3(View view) {
        expandableLayout3 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout3);
        expandableLayout3.toggle(); // toggle expand and collapse
    }

    public void expandableButton4(View view) {
        expandableLayout4 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout4);
        expandableLayout4.toggle(); // toggle expand and collapse
    }

    public void clicklicense(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public void clicknbi(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST2);


    }
    public void clickpnp(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST3);


    }
    public void clickinsurance(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST4);


    }
    public void clickor(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST5);


    }
    public void clickcr(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST6);


    }
    public void clickengine(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST7);
    }
    public void clickchassis(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST8);
    }
    public void photoexteriorfront(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST9);
    }
    public void photoexteriorback(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST10);
    }
    public void photoexteriorright(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST11);
    }
    public void photoexteriorleft(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST12);
    }
    public void photointeriorfront(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST13);
    }
    public void photointeriorback(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST14);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                ImgLicense.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == PICK_IMAGE_REQUEST2 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                ImgNBI.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == PICK_IMAGE_REQUEST3 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                ImgPNP.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == PICK_IMAGE_REQUEST4 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                ImgInsurance.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == PICK_IMAGE_REQUEST5 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                ImgOR.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == PICK_IMAGE_REQUEST6 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                ImgCR.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == PICK_IMAGE_REQUEST7 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                engineimage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == PICK_IMAGE_REQUEST8 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                chassisimage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == PICK_IMAGE_REQUEST9 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                photoexteriorfront.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == PICK_IMAGE_REQUEST10 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                photoexteriorback.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == PICK_IMAGE_REQUEST11 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                photoexteriorright.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == PICK_IMAGE_REQUEST12 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                photoexteriorleft.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == PICK_IMAGE_REQUEST13 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                photointeriorfront.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == PICK_IMAGE_REQUEST14 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                photointeriorback.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }





    public void addAccount(View view)
    {



        //for requirements information

        stbLicenseNo = tbLicenseNo.getText().toString();
        stbLicenseExpiry = tbLicenseExpiry.getText().toString();

        //for alternate driver

        salternateDriverEmail = alternateDriverEmail.getText().toString();
        salternateDriverID = alternateDriverID.getText().toString();


        //for vehicle
        sbodytype = bodytype.getSelectedItem().toString();
        smake = make.getSelectedItem().toString();
        smodel = model.getText().toString();
        syear = year.getSelectedItem().toString();
        svehicle_color = vehicle_color.getText().toString();
        sfuel_type = fuel_type.getSelectedItem().toString();
        splatenumber = platenumber.getText().toString();
        sengine = engine.getText().toString();
        schassis = chassis.getText().toString();


        if (stbLicenseNo.equals("") || stbLicenseNo == null || stbLicenseExpiry.equals("") || stbLicenseExpiry.length() == 0 ||
                svehicle_color.equals("") || svehicle_color == null || sfuel_type.equals("") || sfuel_type.length() == 0 ||
                splatenumber.equals("") || splatenumber == null || sengine.equals("") || sengine.length() == 0 || schassis.equals("") || schassis.length() == 0) {
            Toast.makeText(getApplicationContext(), "Please complete your details.", Toast.LENGTH_LONG).show();
            return;
        }
        else {

            String method = "register";
            driverBackgroundTask1 backgroundTask = new driverBackgroundTask1(this);
            backgroundTask.execute(method, sbodytype, smake, smodel, syear, svehicle_color, sfuel_type, splatenumber, sengine, schassis, salternateDriverEmail, salternateDriverID, stbLicenseNo, stbLicenseExpiry, getemail2);

            Intent intent = new Intent(driver_sign_up.this, DriverTripList.class);
            startActivity(intent);
        }
    }
    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(driver_sign_up.this);
            pDialog.setMessage("Loading Image ....");
            pDialog.show();

        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if(image != null){
                img.setImageBitmap(image);
                pDialog.dismiss();

            }else{

                pDialog.dismiss();
                Toast.makeText(driver_sign_up.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

            }
        }
    }
    class image_name_retrieve extends AsyncTask<String, Void, String> {
        Context ctx;
        String username;
        private ProgressDialog dialog;

        image_name_retrieve(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String reg_url = "http://angkas.net23.net/driver_registration_image_name_retrieval.php";
            SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
            username = sharedPreferences.getString("email_login", "");


            // end of declaration of params

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("username2", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");


                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                String response = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            new LoadImage().execute("http://angkas.net23.net/"+result);


        }

    }
    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        expirydate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tbLicenseExpiry.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    public void clickexpiry(View view)
    {
        expirydate.show();
    }

}
