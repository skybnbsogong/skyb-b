package com.example.myapplication;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savvi.rangedatepicker.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class DateFragment extends Fragment {
    CalendarPickerView calendar;
    Date first_day;
    Date last_day;
    int days=0;
    int num=0;

    public DateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Calendar pastYear = Calendar.getInstance();
        pastYear.add(Calendar.DATE, -1);
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        return inflater.inflate(R.layout.fragment_date, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setCalendar();
        super.onActivityCreated(savedInstanceState);
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
