package com.leathersoft.parleo.listener;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;

import com.leathersoft.parleo.util.DateUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static java.security.AccessController.getContext;

public class DateButtonOnClickListener implements View.OnClickListener {

    DatePickerDialog.OnDateSetListener mDateSetListener;

    public DateButtonOnClickListener(DatePickerDialog.OnDateSetListener dateSetListener) {
//        todo check for null
        mDateSetListener = dateSetListener;
    }
    @Override
    public void onClick(View view) {
        if(view.getContext() == null){
            return;
        }

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(new Date());

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                view.getContext(),
                mDateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }

}
