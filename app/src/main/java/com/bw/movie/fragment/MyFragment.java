package com.bw.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.avtivity.my.UserActivity;

public class MyFragment extends Fragment implements View.OnClickListener{
    private ImageView my_head_image;
    private TextView my_Name;
    private ImageView my_message;
    private ImageView my_care;
    private ImageView my_pay;
    private ImageView my_opinion;
    private ImageView my_edition;
    private ImageView my_logoff;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_my, null);
        initView(view);
        return view;
    }

    private void initView(View view) {

        my_head_image = (ImageView) view.findViewById(R.id.my_head_image);
        my_Name = (TextView) view.findViewById(R.id.my_Name);
        my_message = (ImageView) view.findViewById(R.id.my_message);
        my_care = (ImageView) view.findViewById(R.id.my_care);
        my_pay = (ImageView) view.findViewById(R.id.my_pay);
        my_opinion = (ImageView) view.findViewById(R.id.my_opinion);
        my_edition = (ImageView) view.findViewById(R.id.my_edition);
        my_logoff = (ImageView) view.findViewById(R.id.my_logoff);

        my_head_image.setOnClickListener(this);
        my_Name.setOnClickListener(this);
        my_message.setOnClickListener(this);
        my_care.setOnClickListener(this);
        my_pay.setOnClickListener(this);
        my_opinion.setOnClickListener(this);
        my_edition.setOnClickListener(this);
        my_logoff.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_head_image:
                break;
            case R.id.my_Name:
                break;
            case R.id.my_message:
                Intent intent=new Intent(getContext(),UserActivity.class);
                startActivity(intent);
                break;
            case R.id.my_care:
                break;
            case R.id.my_pay:
                break;
            case R.id.my_opinion:
                break;
            case R.id.my_edition:
                break;
            case R.id.my_logoff:
                break;
        }
    }
}
