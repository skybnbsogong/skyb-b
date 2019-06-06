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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Util util = new Util();
    public static class ViewHolder extends RecyclerView.ViewHolder{
        View container;
        ImageView imageView;
        TextView nameTv;
        TextView priceTv;
        TextView scoreTv;
        ViewHolder(View view){
            super(view);
            container = view;
            this.imageView = (ImageView) view.findViewById(R.id.house_image);
            this.nameTv = (TextView)view.findViewById(R.id.house_name);
            this.priceTv = (TextView)view.findViewById(R.id.house_price);
            this.scoreTv = (TextView)view.findViewById(R.id.house_score);
        }
    }
    private ArrayList<House> data ;
    int allPrice = 0;

    RecyclerViewAdapter(ArrayList<House> d){
        this.data = d;
        dataSort();
        getAllPrice();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_house, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.nameTv.setText(data.get(position).getHouseName());
        viewHolder.imageView.setImageResource(data.get(position).getHouseimage());
        viewHolder.scoreTv.setText(data.get(position).getHouseScore());
        viewHolder.priceTv.setText(data.get(position).getPrintPrice());

        //아이템 리스너 설정
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fr = new DetailHouseInfo();
                //새로운 프래그먼트에 전달할 객체
                Bundle args = new Bundle();
                args.putSerializable("DATA", data.get(position));
                fr.setArguments(args);
                FragmentActivity f = (FragmentActivity)v.getContext();
                FragmentManager fm = f.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.dynamic_mainFragment, fr);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
    @Override
    public int getItemCount(){
        return data.size();
    }

    //데이터를 금액순으로 소팅
    public void dataSort(){
        RecyclerViewComparator comp = new RecyclerViewComparator();
        Collections.sort(this.data, comp);
    }
    //모든 데이터의 총금액을 구함
    public void getAllPrice(){
        for(int i = 0; i < data.size(); i++){
            allPrice += data.get(i).getHousePrice();
        }
    }
    //소팅에 필요한 비교자
    public class RecyclerViewComparator implements Comparator<House> {
        @Override
        public int compare(House first, House second){
            int firstValue = first.getHousePrice();
            int secondValue = second.getHousePrice();

            if(firstValue > secondValue) return 1;
            else if(firstValue < secondValue) return -1;
            else return 0;
        }
    }

}
