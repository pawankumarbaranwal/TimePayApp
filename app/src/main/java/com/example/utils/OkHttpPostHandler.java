package com.example.utils;

/**
 * Created by Pawan on 10/29/2015.
 */
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.utils.Http;
import com.example.utils.OnTaskCompleted;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class OkHttpPostHandler extends AsyncTask<String, Void, Boolean> {
    private Context context;
    private String responseString;
    OkHttpClient client = new OkHttpClient();
    private Http http = null;
    private OnTaskCompleted onTaskCompletedResponse ;
    String urlType;
    Dialog dialog;
    public OkHttpPostHandler(OnTaskCompleted onTaskCompletedReturn,Context context, String url,String type){
        this.context = context;
        this.onTaskCompletedResponse = onTaskCompletedReturn;
        urlType=type;
        REQUEST_URL =url;
    }
    // TODO: get it from the string.xml
    private String REQUEST_URL;

    public static final MediaType JSON
            = MediaType.parse("application/json");

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog=ShowError.displayProgressBar(context);
    }
    @Override
    protected Boolean doInBackground(String... params) {

        http = Http.instance();
        String parameters ="";
        if ((urlType).equals("register"))
        {
            Map<String ,String> param=new LinkedHashMap<String, String>();
            param.put("emil",params[0]);
            param.put("mobileNumber", params[1]);
            JSONObject jsonObject=new JSONObject(param);
            parameters=jsonObject.toString();
        }else if ((urlType).equals("register"))
        {
        }
        try
        {
            http.requestBody(JSON, parameters);
            http.requestPOSTBuilder(REQUEST_URL);
            try
            {
                Response response = http.responseExecute();
                if(response != null ){
                    responseString = response.body().string();
                    return true;
                }else{
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e)
        {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean s) {
        super.onPostExecute(s);
        dialog.hide();
        this.onTaskCompletedResponse.onTaskCompleted(responseString);
    }
}
