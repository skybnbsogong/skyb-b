package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class PayFragment extends Fragment {
    ArrayList<Date> dates;
    House house;
    TextView houseNameTv;
    TextView priceTv;
    TextView scoreTv;
    ImageView img;

    TextView checkInTv;
    TextView checkOutTv;
    TextView usersTv;

    TextView originPriceTitleTv;
    TextView originPriceTv;
    TextView cleanPriceTv;
    TextView servicePriceTv;

    TextView finalPriceTv;
    TextView reservationBtn;
    Util util;
    public PayFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dates = (ArrayList<Date>)getArguments().getSerializable("DATES");
        house = (House)getArguments().getSerializable("HOUSE");
        util = new Util();
        return inflater.inflate(R.layout.pay_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        houseNameTv = (TextView)getView().findViewById(R.id.txt1);
        houseNameTv.setText(house.getHouseName());
        priceTv = (TextView)getView().findViewById(R.id.txt2);
        priceTv.setText(house.getPrintPrice());
        scoreTv = (TextView)getView().findViewById(R.id.txt3);
        scoreTv.setText(house.getHouseScore());
        img = (ImageView) getView().findViewById(R.id.main_img);
        img.setImageResource(house.getHouseimage());
        checkInTv = (TextView)getView().findViewById(R.id.checkinTxt);
        checkInTv.setText(util.dateForm(dates.get(0)));
        checkOutTv = (TextView)getView().findViewById(R.id.checkoutTxt);
        checkOutTv.setText(util.dateForm(dates.get(dates.size() - 1)));
        usersTv = (TextView)getView().findViewById(R.id.userTextView);
        usersTv.setText(house.getHouseUsers());
        originPriceTitleTv = (TextView)getView().findViewById(R.id.money_txt);
        originPriceTitleTv.setText(util.comma(house.getHousePrice()) + " × " + (dates.size() - 1) + "박");
        originPriceTv = (TextView)getView().findViewById(R.id.moneyResult_txt);

        int originPrice = house.getHousePrice() * (dates.size() - 1);
        originPriceTv.setText("￦" + util.comma(originPrice));
        cleanPriceTv = (TextView)getView().findViewById(R.id.cleanPrice);
        cleanPriceTv.setText("￦" + util.comma(originPrice * 4 / 100));
        servicePriceTv = (TextView)getView().findViewById(R.id.servicePrice);
        servicePriceTv.setText("￦" + util.comma(originPrice * 2 / 100));
        finalPriceTv = (TextView)getView().findViewById(R.id.totalMoney_txt);
        finalPriceTv.setText("￦" + util.comma(util.getfinalPrice(originPrice)));

        reservationBtn = (TextView)getView().findViewById(R.id.reservation_btn);
        reservationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fr = new HomeFragment();
                FragmentActivity f = (FragmentActivity)v.getContext();
                FragmentManager fm = f.getSupportFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i){
                    fm.popBackStack();
                }
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.dynamic_mainFragment, fr);
                fragmentTransaction.commit();
            }
        });
        super.onActivityCreated(savedInstanceState);
    }
}
