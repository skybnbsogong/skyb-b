package com.example.myapplication;


import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.savvi.rangedatepicker.CalendarPickerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomDialog extends DialogFragment {
    private String result;
    private CustomDialogResult dialogResult;
    private Fragment fragment;
    CalendarPickerView calendar;
    Date first_day;
    Date last_day;
    int days=0;
    int num=0;

    public CustomDialog() {
        // Required empty public constructor
    }
    public interface CustomDialogResult{
        void finish(String result);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setWindowAnimations(R.style.DialogAnimation);
        getDialog().getWindow().setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);

        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("OpenDialog");


        Calendar pastYear = Calendar.getInstance();
        pastYear.add(Calendar.DATE, -1);
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        return inflater.inflate(R.layout.fragment_custom_dialog, container, false);
    }

    @Override
    public void onStart() {
        getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
        super.onStart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        final TextView canceltextView = (TextView) getView().findViewById(R.id.pay_account_stat_cancel_button);
        canceltextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = "cancel";
                dialogResult.finish(result);
                DialogFragment dialogFragment = (DialogFragment)fragment;
                dialogFragment.dismiss();
            }
        });
        setCalendar();
        super.onActivityCreated(savedInstanceState);
    }
    public void setDialogResult(CustomDialogResult cdr){
        dialogResult = cdr;
    }
    public void setCalendar(){
        Calendar pastYear = Calendar.getInstance();
        pastYear.add(Calendar.DATE, -1);
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        calendar = (CalendarPickerView)getView().findViewById(R.id.calendar_view);

        calendar.init(pastYear.getTime(), nextYear.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.RANGE)
                .withSelectedDate(new Date());
        calendar.setTypeface(Typeface.SANS_SERIF);
    }
}
