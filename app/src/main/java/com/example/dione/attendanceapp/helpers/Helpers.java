package com.example.dione.attendanceapp.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.EditText;

import com.example.dione.attendanceapp.R;

/**
 * Created by dione on 29 Sep 2016.
 */

public class Helpers {
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

}
