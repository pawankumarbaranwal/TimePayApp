package com.example.timepay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Sahil on 20-06-2015.
 */
public class HomePage extends AppCompatActivity {

    Button BSignIn;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        BSignIn=(Button)findViewById(R.id.bSignIn);
        BSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,Accounts.class);
                startActivity(intent);
            }
        });
    }
}
