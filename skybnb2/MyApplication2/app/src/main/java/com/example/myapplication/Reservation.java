package com.example.myapplication;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable {
    private House house;
    private Date checkin;
    private Date checkout;
    private int totalprice;
    private int id;
    public Reservation(House house, Date checkin, Date checkout, int totalprice, int id){
        this.house = house;
        this.checkin = checkin;
        this.checkout = checkout;
        this.totalprice = totalprice;
        this.id = id;
    }
    public Date getCheckin() {
        return checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public House getHouse() {
        return house;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public int getId() {
        return id;
    }
}
