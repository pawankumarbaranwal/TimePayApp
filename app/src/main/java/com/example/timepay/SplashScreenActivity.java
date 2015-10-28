package com.example.timepay;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.example.utils.FileIOHandler;
import com.example.utils.PropertyHandler;
import com.example.componentservices.ComponentBaseServices;

import java.util.Properties;
import java.util.Timer;


public class SplashScreenActivity extends Activity {
 
	// Set Duration of the Splash Screen
	long Delay = 8000;
	final Context context=this;
    PropertyHandler propReader;
 	OkHttpHandler okHttpHandler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Properties myProperty;
        ComponentBaseServices baseServices = new ComponentBaseServices();
		okHttpHandler =new OkHttpHandler(context);


		baseServices.getIMEI(this.context);
		baseServices.getMacAddress(this.context);
		baseServices.getEmail(this.context);
        //Log.i("Outputs","IMEI  " + SharedPreferenceHandler.readValue(this.context,"IMEI")+" MAC  "+SharedPreferenceHandler.readValue(this.context,"Mac Address")+"Email  " + SharedPreferenceHandler.readValue(this.context,"Synced Mail"));
		Log.i("Outputs", "IMEI  " + FileIOHandler.readValue(this.context, "IMEI") + " MAC  " + FileIOHandler.readValue(this.context, "Mac Address") + "Email  " + FileIOHandler.readValue(this.context,"Synced Mail"));
		try {
			String s = okHttpHandler.execute().get();
			Log.i("ppppppppppp",s);
		}catch (Exception e){

		}
        propReader = new PropertyHandler(context);
        myProperty = propReader.getMyProperties("url.properties");
//        Log.i("Sign in url :",myProperty.getProperty("signin"));
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Get the view from splash_screen.xml
		setContentView(R.layout.splash_screen);
 
		// Create a Timer
		Timer RunSplash = new Timer();
 
		// Task to do when the timer ends
		//TimerTask ShowSplash = new TimerTask() {
			Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
                                            /*
                                             * We are creating this new thread because we don’t
                                             * want our main thread to stop working for that
                                             * time as our android stop working and some time
                                             * application will crashes
                                             */
					e.printStackTrace();
				}
				finally {
					Intent i = new Intent(SplashScreenActivity.this,
							HomePage.class);
					startActivity(i);
					finish();
				}

			}
		});
 
		// Start the timer
		//RunSplash.schedule(ShowSplash, Delay);
		th.start(); // start the thread
	}
}