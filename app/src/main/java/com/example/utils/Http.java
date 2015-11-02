package com.example.utils;

/**
 * Created by Pawan on 10/29/2015.
 */
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by nadeem on 27-09-2015.
 */
public class Http {

    private static OkHttpClient httpClient;
    private static Http http;
    private RequestBody requestBody = null;
    private Request.Builder builder = null;
    Request request = null;
    Response response = null;
    private Http(){

    }

    public static Http instance(){
        if(http == null){
            http = new Http();
            httpClient = new OkHttpClient();
        }
        return http;
    }

    private void httpClient(){

    }

    private static void defaultHeader(){
        //TODO Add all the headers here generally used
    }

    public void requestBody(MediaType mediaType, String parameters) {
        requestBody = RequestBody.create(mediaType, parameters);
        Log.i("requestBody",requestBody.toString());
    }

    public void requestGETBuilder(String URL){
        builder = new Request.Builder();
        builder.url(URL);
        request = builder.build();
    }
    public void requestPOSTBuilder(String URL){
        builder = new Request.Builder();
        builder
        .addHeader("Content-Type", "application/json")
        .url(URL).post(requestBody);
        request = builder.build();
    }

    public Response responseExecute() {
        try {
            return httpClient.newCall(request).execute();
        } catch (IOException e) {
            //TODO Change this from null to a custom response class
            return null;
        }
    }
    public Response responsePostExecute() {
        try {
            Log.i("ExceptionMessage1","1");
            return httpClient.newCall(request).execute();
        } catch (Exception e) {
            Log.i("ExceptionMessage",e.getMessage()+"");
            //TODO Change this from null to a custom response class
            return null;
        }
    }
}
