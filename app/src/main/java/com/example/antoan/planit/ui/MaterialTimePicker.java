package com.example.antoan.planit.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.antoan.planit.view.createEvent.CreateEventView;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

/**
 * Created by Antoan on 3/3/2017.
 */

public class MaterialTimePicker {

    public MaterialTimePicker(){

    }

    public void show (Activity act, TimePickerDialog.OnTimeSetListener timeSetListener){
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(timeSetListener,now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),true);
        tpd.setThemeDark(true);
        tpd.show(act.getFragmentManager(),"Timepickerdialog");
    }
}
