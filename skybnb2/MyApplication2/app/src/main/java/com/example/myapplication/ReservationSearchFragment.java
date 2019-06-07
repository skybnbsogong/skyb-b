package com.example.myapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationSearchFragment extends Fragment {
    ArrayList<Reservation> reservations;
    DbOpenHelper dbOpenHelper;
    RecyclerView recyclerView;
    ReservationAdapter adapter = null;
    LinearLayoutManager layoutManager = null;

    public ReservationSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dbOpenHelper = new DbOpenHelper(getContext(), "reservation.db", null, 1);
        reservations = dbOpenHelper.getReservationList();
        return inflater.inflate(R.layout.fragment_reservation_search, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView)getView().findViewById(R.id.recyclerview_reservation);
        setRecyclerView();
        super.onActivityCreated(savedInstanceState);
    }
    public void setRecyclerView(){
        recyclerView = (RecyclerView)getView().findViewById(R.id.recyclerview_reservation);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getView().getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        adapter = new ReservationAdapter(reservations);
        recyclerView.setAdapter(adapter);
    }
}
