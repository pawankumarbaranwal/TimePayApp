package com.example.timepay;

/**
 * Created by Pawan on 10/29/2015.
 */
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.utils.Http;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Response;

public class  OkHttpHandler extends AsyncTask<String, Void, String> {
    private Context context;
    private String responseString;
    private Http http = null;
    private String REQUEST_URL;

    public OkHttpHandler(Context context){
        this.context = context;
        REQUEST_URL =context.getString(R.string.getOrgType);
    }

    public static final MediaType JSON
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
/*
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }*/

    @Override
    protected String doInBackground(String... params) {

        http = Http.instance();
        http.requestGETBuilder(REQUEST_URL);

        try
        {
            Response response = http.responseExecute();
            if(response != null &&  response.isSuccessful()) {
                responseString = response.body().string();
                return responseString;
            }else{
                return "";
            }
        } catch (Exception e)
        {
            return "";
        }
    }
/*
    @Override
    protected void onPostExecute(Boolean s) {
        super.onPostExecute(s);
    }*/
}
