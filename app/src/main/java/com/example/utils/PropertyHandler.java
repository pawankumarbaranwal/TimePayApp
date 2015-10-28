package com.example.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by nadeem on 16-06-2015.
 * PropertyHandler read/write file from the folder Assets
 */
public class PropertyHandler {
    private Context context;
    private Properties properties;

    public PropertyHandler(Context context){
        this.context=context;
        properties = new Properties();
    }

    public Properties getMyProperties(String file){
        try{
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(file);
            properties.load(inputStream);

        }catch (Exception e){
            System.out.print(e.getMessage());
        }

        return properties;
    }
}
