package com.example.myapplication;


import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
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
public class ReservationDialog extends DialogFragment {
    private String result;
    private CustomDialogResult dialogResult;
    private Fragment fragment;

    Reservation data;
    TextView nameTv;
    TextView checkin;
    TextView checkout;
    TextView totalPrice;

    Util util;

    public ReservationDialog() {
        // Required empty public constructor
    }

    public interface CustomDialogResult {
        void finish(String result);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setWindowAnimations(R.style.DialogAnimation);
        getDialog().getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);

        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("OpenDialog");
        data = (Reservation)getArguments().getSerializable("RESERVATION");
        util = new Util();
        return inflater.inflate(R.layout.reservation_custom_dialog, container, false);
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
        nameTv = getView().findViewById(R.id.name_detail);
        nameTv.setText(data.getHouse().getHouseName());
        checkin = getView().findViewById(R.id.checkin_detail);
        checkin.setText(util.dateForm(data.getCheckin()));
        checkout = getView().findViewById(R.id.checkout_detail);
        checkout.setText(util.dateForm(data.getCheckout()));
        totalPrice = getView().findViewById(R.id.totalPrice_detail);
        totalPrice.setText(util.comma(data.getTotalprice()));

        final TextView okTextView = (TextView) getView().findViewById(R.id.cancel_reservation);
        okTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogResult.finish("cancelR");
                DialogFragment dialogFragment = (DialogFragment) fragment;
                dialogFragment.dismiss();
            }
        });
        final TextView cancelTextView = (TextView) getView().findViewById(R.id.cancel_detail);
        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogResult.finish("cancel");
                DialogFragment dialogFragment = (DialogFragment) fragment;
                dialogFragment.dismiss();
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    public void setDialogResult(CustomDialogResult cdr) {
        dialogResult = cdr;
    }
}

