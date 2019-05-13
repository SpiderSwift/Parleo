package com.leathersoft.parleo.util;

import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class TouchUtils {
    private TouchUtils() {
    }

    public static void setEditTextMultilineScrolling(EditText editText){
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_SCROLL:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            return true;
                    }
                }
                return false;
            }
        });
    }
}
