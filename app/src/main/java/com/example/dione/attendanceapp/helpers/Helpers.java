package com.example.dione.attendanceapp.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import com.example.dione.attendanceapp.R;

/**
 * Created by dione on 29 Sep 2016.
 */

public class Helpers {
    private static final String SHARED_PREFERENCE_TAG = "shared_preference";
    public static boolean isInputValid(EditText editText, Context context){
        boolean isValid = true;
        if (editText.getText().toString().isEmpty()){
            isValid = false;
            editText.setError(context.getString(R.string.error_required_field));
        }else{
            editText.setError(null);
        }
        return isValid;
    }

    public static void saveStringSharedPreference(Context context, String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_TAG, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).apply();
    }
    public static String getStringPreference(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_TAG, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(key, "");
    }
    public static void clearPreferences(Context context){
        SharedPreferences settings = context.getSharedPreferences(SHARED_PREFERENCE_TAG, Context.MODE_PRIVATE);
        settings.edit().clear().apply();
    }

}
