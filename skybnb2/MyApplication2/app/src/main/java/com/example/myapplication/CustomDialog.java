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
import android.widget.TextView;
import android.widget.Toast;

import com.savvi.rangedatepicker.CalendarPickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


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
        void finish(ArrayList<Date> result);
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
        final TextView okTextView = (TextView)getView().findViewById(R.id.selectBtn);
        okTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(calendar.getSelectedDates() == null || calendar.getSelectedDates().size() < 2){
                    Toast.makeText(getContext(), "1박 2일 이상으로 선택해주세요", Toast.LENGTH_LONG).show();
                }else{
                    ArrayList<Date> result = (ArrayList<Date>)calendar.getSelectedDates();
                    dialogResult.finish(result);
                    DialogFragment dialogFragment = (DialogFragment)fragment;
                    dialogFragment.dismiss();
                }
            }
        });
        final TextView cancelTextView = (TextView) getView().findViewById(R.id.cancelBtn);
        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogResult.finish(null);
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
