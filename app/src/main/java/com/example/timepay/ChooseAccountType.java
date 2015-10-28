package com.example.timepay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class ChooseAccountType extends ActionBarActivity {

    //Spinner spAccountType;
   // private String[] Account_Type = {"General Public Registration", "Vendor Registration", "Privilege Vendor Registration"};
    Button bAccountType;
    CheckBox cbIAgree;
    Integer position;
    WebView wvIAccpet;
    TextView tvIAgree;
    RadioButton rbGPReistration,rbVReistration,rbPVReistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type);
        initializeView();



        tvIAgree.setClickable(true);
        tvIAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseAccountType.this, Webview.class);
                intent.putExtra("URL", getString(R.string.wvIAree));
                intent.putExtra("Text",getString(R.string.Text_view_IAgree));
                startActivity(intent);

            }
        });

//        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Account_Type);
//        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spAccountType.setAdapter(adapter_state);
//        spAccountType.setOnItemSelectedListener(this);

        bAccountType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbIAgree.isChecked()) {
                    //Toast.makeText(ChooseAccountType.this, "check box is checked", Toast.LENGTH_LONG).show();
                    Intent i= null;

                    if(rbGPReistration.isChecked())
                    {
                        i = new Intent(ChooseAccountType.this,GeneralPublicRegistration.class);

                    }
                    if(rbVReistration.isChecked())
                    {
                        i = new Intent(ChooseAccountType.this,VendorRegistration.class);
                    }
                    if (rbPVReistration.isChecked())
                    {
                        i = new Intent(ChooseAccountType.this,PrivilageVendorRegistration.class);
                    }
                    startActivity(i);
                } else {
                    Toast.makeText(ChooseAccountType.this, "Please accept terms and conditions", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initializeView() {
        bAccountType = (Button) findViewById(R.id.bContinue);
        cbIAgree = (CheckBox) findViewById(R.id.cbIAgree);
        wvIAccpet = (WebView)findViewById(R.id.webview);
        tvIAgree= (TextView)findViewById(R.id.tvIAgree);
        rbGPReistration = (RadioButton)findViewById(R.id.rbGPReistration);
        rbVReistration = (RadioButton)findViewById(R.id.rbVReistration);
        rbPVReistration = (RadioButton)findViewById(R.id.rbPVReistration);
    }



//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        spAccountType.setSelection(i);
//        if(cbIAgree.isChecked())
//        {
//            cbIAgree.toggle();
//        }
//        position = i;
//    }

//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_account_type, menu);
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
}
