package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ReservationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Util util = new Util();
    DbOpenHelper dbOpenHelper;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        View container;
        TextView labelTv;
        TextView nameTv;
        TextView checkinTv;
        TextView checkoutTv;
        ViewHolder(View view){
            super(view);
            container = view;
            this.labelTv = (TextView) view.findViewById(R.id.list_number);
            this.nameTv = (TextView)view.findViewById(R.id.list_name);
            this.checkinTv = (TextView)view.findViewById(R.id.list_checkin
            );
            this.checkoutTv = (TextView)view.findViewById(R.id.list_checkout);
        }
    }
    private ArrayList<Reservation> data ;
    int allPrice = 0;

    ReservationAdapter(ArrayList<Reservation> d){
        this.data = d;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reservation, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.labelTv.setText(Integer.toString(position + 1));
        viewHolder.nameTv.setText(data.get(position).getHouse().getHouseName());
        viewHolder.checkinTv.setText(util.dateForm(data.get(position).getCheckin()));
        viewHolder.checkoutTv.setText(util.dateForm(data.get(position).getCheckout()));
        //아이템 리스너 설정
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ReservationDialog cd = new ReservationDialog();

                Bundle arg = new Bundle();
                arg.putSerializable("RESERVATION", data.get(position));
                cd.setArguments(arg);
                FragmentActivity f = (FragmentActivity) v.getContext();
                FragmentManager fm = f.getSupportFragmentManager();
                cd.show(fm, "OpenDialog");
                //다이얼로그로 부터 결과값을 받아오고 그 후 처리
                cd.setDialogResult(new ReservationDialog.CustomDialogResult(){
                    @Override
                    public void finish(String result) {
                        if(result.equals("cancelR")){
                            dbOpenHelper = new DbOpenHelper(v.getContext(), "reservation.db", null, 1);
                            dbOpenHelper.delete(data.get(position).getId());
                            Fragment fr = new ReservationSearchFragment();
                            FragmentManager fm = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fm.beginTransaction();
                            fragmentTransaction.replace(R.id.dynamic_mainFragment, fr);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }else if(result.equals("cancel")){}
                    }
                });
            }
        });
    }
    @Override
    public int getItemCount(){
        return data.size();
    }


}
