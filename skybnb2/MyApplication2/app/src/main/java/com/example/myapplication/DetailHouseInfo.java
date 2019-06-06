package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailHouseInfo extends Fragment {
    ImageView imageView;
    TextView tv_name;
    TextView tv_comment;
    TextView tv_location;
    TextView tv_money;
    TextView tv_star;

    TextView dateBtn;
    House data;
    public DetailHouseInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        data = (House)getArguments().getSerializable("DATA");
        return inflater.inflate(R.layout.fragment_detail_house_info, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        imageView = (ImageView)getView().findViewById(R.id.main_img);
        tv_name = (TextView)getView().findViewById(R.id.name);
        tv_comment = (TextView)getView().findViewById(R.id.comment2);
        tv_location = (TextView)getView().findViewById(R.id.location);
        tv_money = (TextView)getView().findViewById(R.id.txt2);
        tv_star = (TextView)getView().findViewById(R.id.txt3);

        imageView.setImageResource(data.getHouseimage());
        tv_name.setText(data.getHouseName());
        tv_comment.setText(data.getHouseDetailInfo());
        tv_location.setText(data.getHouseLocation());
        tv_money.setText("요금을 확인하려면 날짜를 \n입력하세요.\n" );
        tv_star.setText(data.getHouseScore());


        dateBtn = (TextView)getView().findViewById(R.id.date_btn);
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                CustomDialog cd = new CustomDialog();

                FragmentActivity f = (FragmentActivity) v.getContext();
                FragmentManager fm = f.getSupportFragmentManager();
                cd.show(fm, "OpenDialog");
                //다이얼로그로 부터 결과값을 받아오고 그 후 처리
                cd.setDialogResult(new CustomDialog.CustomDialogResult(){
                    @Override
                    public void finish(String result) {
                        if(!result.equals("cancel")) {
                            Intent intent = new Intent();
                            intent.setAction("HomeFragment");
                            v.getContext().sendBroadcast(intent);
                        }
                    }
                });
            }
        });

        super.onActivityCreated(savedInstanceState);
    }
}
