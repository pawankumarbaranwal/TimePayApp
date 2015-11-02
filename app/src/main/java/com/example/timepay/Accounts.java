package com.example.timepay;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pojo.UsersRegistrationStatus;
import com.example.utils.OnTaskCompleted;
import com.example.utils.SharedPreferenceHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class Accounts extends AppCompatActivity implements View.OnClickListener,OnTaskCompleted{
    EditText emailAddressET, phoneNumberET;
    RelativeLayout continueAsGPR,continueAsVR,continueAsPVR;
    String syncedMail;
    String onClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        syncedMail= SharedPreferenceHandler.readValue(this, "Synced Mail");
        initializeView();
        openDialogBox();
        continueAsGPR.setOnClickListener(this);
        continueAsPVR.setOnClickListener(this);
        continueAsVR.setOnClickListener(this);


        String code = "+91";
        phoneNumberET.setCompoundDrawablesWithIntrinsicBounds(new TextDrawable(code), null, null, null);
        phoneNumberET.setCompoundDrawablePadding(code.length() * 23);
/*
        continueB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Validator validator=new Validator();
                Log.i("email", emailAddressET.getText() + "" + phoneNumberET.getText() + "");
                String message= validator.validateAccountDetatis(emailAddressET.getText()+"",phoneNumberET.getText()+"");
                Log.i("email", message);
                if (message.equals("Completed")) {
                    Log.i("email","inside account");
                    Intent i = new Intent(Accounts.this, ChooseAccountType.class);
                    startActivity(i);
                }else {
                    Log.i("email","error");
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                }
            }
        });
*/
    }

    private void openDialogBox() {

        if (!syncedMail.isEmpty()) {
            // Create custom dialog object
            final Dialog dialog = new Dialog(Accounts.this);
            // Include dialog.xml file
            dialog.setContentView(R.layout.dialogboxforemail);
            // Set dialog title
            dialog.setTitle("Add your Account with ");
            TextView eId = (TextView) dialog.findViewById(R.id.tv_emailid);
            eId.setText(syncedMail);
            // set values for custom dialog components - text, image and button
            //text.setText("Custom dialog Android example.");
            //image.setImageResource(R.drawable.ic_launcher);
            dialog.show();

            final Button cancel = (Button) dialog.findViewById(R.id.btnCancel);
            final Button ok = (Button) dialog.findViewById(R.id.btnOK);
            // if decline button is clicked, close the custom dialog
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    emailAddressET.setText(syncedMail);
                    dialog.dismiss();
                }
            });
        }
    }
    private void initializeView() {
        emailAddressET = (EditText)findViewById(R.id.etEmailAddress);
        phoneNumberET = (EditText)findViewById(com.example.timepay.R.id.etPhoneNumber);
        continueAsGPR = (RelativeLayout)findViewById(R.id.rvContinueAsGPR);
        continueAsVR = (RelativeLayout)findViewById(R.id.rvContinueAsVendor);
        continueAsPVR = (RelativeLayout)findViewById(R.id.rvContinueAsPVendor);
    }

    @Override
    public void onClick(View v) {
        Validator validator=new Validator();
        Intent intent;
        OkHttpPostHandler handler = new OkHttpPostHandler(this,this,"http://124.153.111.70:8080/project-1.0.0-BUILD-SNAPSHOT/registration","register");
        String message= validator.validateAccountDetatis(emailAddressET.getText() + "", phoneNumberET.getText() + "");
        Log.i("email", message);
        if (message.equals("Completed")) {
            if (v == continueAsGPR) {
                handler.execute(emailAddressET.getText().toString(), phoneNumberET.getText().toString());
                onClick="continueAsGPR";
            } else if (v == continueAsVR) {
                handler.execute(emailAddressET.getText().toString(), phoneNumberET.getText().toString());
                onClick="continueAsVR";
            } else if (v == continueAsPVR) {
                handler.execute(emailAddressET.getText().toString(), phoneNumberET.getText().toString());
                onClick="continueAsPVR";
            }
        }else{
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
        }
    }

    public class TextDrawable extends Drawable {

        private final String text;
        private final Paint paint;

        public TextDrawable(String text) {
            this.text = text;
            this.paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setTextSize(36f);
            paint.setAntiAlias(true);
            paint.setTextAlign(Paint.Align.LEFT);
        }

        @Override
        public void draw(Canvas canvas) {
            canvas.drawText(text, 0, 14, paint);
        }

        @Override
        public void setAlpha(int alpha) {
            paint.setAlpha(alpha);
        }

        @Override
        public void setColorFilter(ColorFilter cf) {
            paint.setColorFilter(cf);
        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSLUCENT;
        }
    }

    @Override
    public void onTaskCompleted(String response) {
        UsersRegistrationStatus usersRegistrationStatus=new UsersRegistrationStatus();
        Intent intent =new Intent();
        try {
            if (response != null && response != "") {
                usersRegistrationStatus = new Gson().fromJson(response, UsersRegistrationStatus.class);
                if (usersRegistrationStatus.getStatusCode().equals("400")) {
                    throw new Exception(usersRegistrationStatus.getStatusMessage() + "");
                } else {
                    if (onClick.equals("continueAsGPR")) {
                        intent = new Intent(this, GeneralPublicRegistration.class);
                    }else if (onClick.equals("continueAsVR")) {
                        intent = new Intent(this, VendorRegistration.class);
                    }else if (onClick.equals("continueAsPVR")) {
                        intent = new Intent(this, PrivilageVendorRegistration.class);
                    }
                        startActivity(intent);
                }
            } else if (response == null) {
                throw new Exception("Technical error");
            }
        } catch (Exception e) {
            if (e.getMessage().contains("PreparedStatementCallback")){
                ShowError.displayError(this, "Email id should be less than 25 characters");
            }else{
            ShowError.displayError(this, e.getMessage());
            }
        }
    }
}
