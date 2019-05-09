package com.leathersoft.parleo.listener;

import android.app.DatePickerDialog;
import android.widget.Button;
import android.widget.DatePicker;

import com.leathersoft.parleo.util.DateUtil;

import java.text.SimpleDateFormat;

public class ButtonOnDateSetListener implements DatePickerDialog.OnDateSetListener {

    Button mButton;
    SimpleDateFormat mDateFormat;

    public ButtonOnDateSetListener(Button button, SimpleDateFormat dateFormat) {
        mButton = button;
        mDateFormat = dateFormat;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        mButton.setText(mDateFormat.format(
                DateUtil.getDateFromDatePicker(datePicker)
        ));
    }
}
