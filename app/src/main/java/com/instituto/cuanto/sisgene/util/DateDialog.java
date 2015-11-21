package com.instituto.cuanto.sisgene.util;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.instituto.cuanto.sisgene.R;

import java.util.Calendar;

/**
 * Created by Jesus on 18/10/2015.
 */
public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    EditText txtDate;
    Bundle args = new Bundle();
    /*
    public DateDialog(View view){
        txtDate = (EditText) view;
    }*/

    public DateDialog(){}

    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this,year,month,day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day){

        String sDia="",sMes="";

        if(day<10){
            sDia = "0"+day;
        }else{
            sDia = ""+day;
        }

        if(month<10){
            sMes = "0"+month;
        }else{
            sMes = ""+month;
        }

        String date = sDia+"/"+(sMes)+"/"+year;

        args.putString("hora", date);
        this.setArguments(args);
        txtDate.setText(date);
    }

}
