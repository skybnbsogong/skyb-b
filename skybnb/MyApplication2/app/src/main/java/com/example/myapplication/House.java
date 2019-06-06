package com.example.myapplication;

import java.io.Serializable;

public class House implements Serializable {
    private int houseimage;
    private String houseName;
    private String houseScore;
    private int housePrice;
    private String houseLocation;
    private String houseInfo;
    private String houseDetailInfo;

    private Util util;

    public House(int image, String name, String score, int price, String location, String info, String detailInfo){
        this.houseimage = image;
        this.houseName = name;
        this.houseScore = score;
        this.housePrice = price;
        this.houseLocation = location;
        this.houseInfo = info;
        this.houseDetailInfo = detailInfo;
        util = new Util();
    }

    public int getHouseimage() {
        return houseimage;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public String getHouseName() {
        return houseName;
    }

    public String getHouseScore() {
        return houseScore;
    }
    public String getPrintPrice(){
        return "￦" + util.comma(housePrice) +  " /박";
    }

    public String getHouseDetailInfo() {
        return houseDetailInfo;
    }

    public String getHouseInfo() {
        return houseInfo;
    }

    public String getHouseLocation() {
        return houseLocation;
    }
}
