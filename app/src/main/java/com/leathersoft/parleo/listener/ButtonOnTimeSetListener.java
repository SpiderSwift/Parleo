package com.leathersoft.parleo.listener;

import android.app.TimePickerDialog;
import android.widget.Button;
import android.widget.TimePicker;

import com.leathersoft.parleo.util.DateUtil;

import java.text.SimpleDateFormat;

public class ButtonOnTimeSetListener implements TimePickerDialog.OnTimeSetListener {

    Button mButton;
    SimpleDateFormat mTimeFormat;

    public ButtonOnTimeSetListener(Button button, SimpleDateFormat timeFormat) {
        mButton = button;
        mTimeFormat = timeFormat;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        mButton.setText(mTimeFormat.format(
                DateUtil.getTimeFromTimePicker(timePicker)
        ));
    }
}
