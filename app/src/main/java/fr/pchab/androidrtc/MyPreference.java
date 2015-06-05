package fr.pchab.androidrtc;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by USER on 2015/6/5.
 */
public class MyPreference {

    public static String getSeverAddress(Context context){
        SharedPreferences settings = context.getSharedPreferences(Param.SHARED_PREF_NAME, 0);
        String address = settings.getString(Param.FIELD_SERVER_ADDRESS, Param.DEFAULT_IP);
        return address;
    }
    public static int getServerPort(Context context){
        SharedPreferences settings = context.getSharedPreferences(Param.SHARED_PREF_NAME, 0);
        int port = settings.getInt(Param.FIELD_SERVER_PORT, Param.DEFAULT_PORT);
        return port;
    }
    public static String getDeviceName(Context context){
        SharedPreferences settings = context.getSharedPreferences(Param.SHARED_PREF_NAME, 0);
        String deviceName = settings.getString(Param.FIELD_DEVICE_NAME, Param.DEFAULT_DEVICE_NAME);
        return deviceName;
    }

    public static void setSeverAddress(Context context, String address){
        SharedPreferences settings = context.getSharedPreferences(Param.SHARED_PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Param.FIELD_SERVER_ADDRESS, address);
        editor.commit();
    }
    public static void setServerPort(Context context, int port){
        SharedPreferences settings = context.getSharedPreferences(Param.SHARED_PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(Param.FIELD_SERVER_PORT, port);
        editor.commit();
    }
    public static void setDeviceName(Context context, String deviceName){
        SharedPreferences settings = context.getSharedPreferences(Param.SHARED_PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Param.FIELD_DEVICE_NAME, deviceName);
        editor.commit();
    }
}

