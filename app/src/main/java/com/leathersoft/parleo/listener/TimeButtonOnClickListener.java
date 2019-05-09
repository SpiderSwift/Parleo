package com.leathersoft.parleo.listener;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeButtonOnClickListener implements View.OnClickListener {

    TimePickerDialog.OnTimeSetListener mTimeSetListener;

    public TimeButtonOnClickListener(TimePickerDialog.OnTimeSetListener timeSetListener) {
        mTimeSetListener = timeSetListener;
    }

    @Override
    public void onClick(View view) {

        if(view.getContext() == null){
            return;
        }

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(new Date());

        TimePickerDialog datePickerDialog = new TimePickerDialog(
                view.getContext(),
                mTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );

        datePickerDialog.show();

    }
}
