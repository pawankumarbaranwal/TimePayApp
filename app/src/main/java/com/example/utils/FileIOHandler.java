package com.example.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by nadeem on 16-06-2015.
 * <p/>
 * Read/Write a file into local folder of the App
 */
public class FileIOHandler {

    public static boolean writeValue(Context context, String filename, String value) {
        boolean flag = false;
        FileOutputStream fos;
        try {
            fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            //default mode is PRIVATE, can be APPEND etc.
            fos.write(value.getBytes());
            fos.close();
            flag = true;
            //Toast.makeText(context,filename + " saved", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {e.printStackTrace();  flag = false;}
        catch (IOException e) {e.printStackTrace();  flag = false;}
        return flag;
    }

    public static String readValue(Context context, String fileName) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    context.openFileInput(fileName)));
            String inputString;
            //Reading data line by line and storing it into the stringbuffer
            while ((inputString = inputReader.readLine()) != null) {
                stringBuffer.append(inputString + "\n");
            }
        } catch (Exception e) {
        }
        return stringBuffer.toString();
    }

}
