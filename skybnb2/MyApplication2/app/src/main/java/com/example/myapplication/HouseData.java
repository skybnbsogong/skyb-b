package com.example.myapplication;

import java.util.ArrayList;

public class HouseData {
    private ArrayList<House> data;
    public HouseData(){
        data = new ArrayList<House>();
        data.add(new House(R.drawable.house_expensive, "좀 비싼 집", "★★★☆☆ 31", 997150,
                "Log Angeles, America", "인원 2명   침실 1개   침대 1개   욕실1.5개",
                "Drink in panoramic city views from Hollywood to the ocean from the freestanding bathtub at a private guest house nestled in the wooded hillside.", 2));
        data.add(new House(R.drawable.house_jjanggu, "짱구네 집", "★★★★★ 309", 81050,
                "埼玉県春日部市銚子口904", "인원 3명   침실 2개   침대 2개   욕실 1개",
                "짱구네집이다이마", 3));
        data.add(new House(R.drawable.house_shoes, "신발 집", "★★★★☆ 81", 105031,
                "Rome, Italy", "인원 2명   침실 1개   침대 2개  욕실 1개",
                "신발냄새를 맡고싶다고요??", 2));
    }
    public ArrayList<House> getData(){
        return this.data;
    }
}
