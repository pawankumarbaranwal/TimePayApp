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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.SharedPreferenceHandler;

public class Accounts extends ActionBarActivity implements View.OnClickListener{
    EditText emailAddressET, phoneNumberET;
    RelativeLayout continueAsGPR,continueAsVR,continueAsPVR;
    String syncedMail;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accounts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Validator validator=new Validator();
        Intent intent;
        String message= validator.validateAccountDetatis(emailAddressET.getText()+"",phoneNumberET.getText()+"");
        Log.i("email", message);
        if (message.equals("Completed")) {
            if (v == continueAsGPR) {
                intent = new Intent(getApplicationContext(), GeneralPublicRegistration.class);
                startActivity(intent);
            } else if (v == continueAsVR) {
                intent = new Intent(getApplicationContext(), VendorRegistration.class);
                startActivity(intent);
            } else if (v == continueAsPVR) {
                intent = new Intent(getApplicationContext(), PrivilageVendorRegistration.class);
                startActivity(intent);
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


}



