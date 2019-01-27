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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.avtivity.my.EditionxActivity;
import com.bw.movie.avtivity.my.FollowActivity;
import com.bw.movie.avtivity.my.HornActivity;
import com.bw.movie.avtivity.my.OpinionActivity;
import com.bw.movie.avtivity.my.PayActivity;
import com.bw.movie.avtivity.my.UserActivity;
import com.bw.movie.bean.IDUserData;
import com.bw.movie.bean.UserMessageData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.MyGlideUtil;
import com.bw.movie.utils.SpBase;

import java.util.HashMap;
import java.util.Map;

public class MyFragment extends Fragment implements View.OnClickListener,Contract.View {
    private ImageView my_head_image;
    private TextView my_Name;
    private ImageView my_message;
    private ImageView my_care;
    private ImageView my_pay;
    private ImageView my_opinion;
    private ImageView my_edition;
    private ImageView my_logoff;
    private ImageView my_horn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_my, null);
        initView(view);
        presenter();
        return view;
    }

    private void presenter() {
        String sessionid = SpBase.getString(getContext(), "sessionId", "");
        String userid = SpBase.getString(getContext(), "userId", "");
        Presenter presenter=new Presenter(this);
        Map<String,Object> headmap=new HashMap<>();
        headmap.put("userId",userid+"");
        headmap.put("sessionId",sessionid+"");
        Map<String,Object> map=new HashMap<>();
        presenter.get(Interfaces.QueryUserInformation,headmap,map,IDUserData.class);
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
        my_horn = (ImageView) view.findViewById(R.id.my_horn);

        my_head_image.setOnClickListener(this);
        my_Name.setOnClickListener(this);
        my_message.setOnClickListener(this);
        my_care.setOnClickListener(this);
        my_pay.setOnClickListener(this);
        my_opinion.setOnClickListener(this);
        my_edition.setOnClickListener(this);
        my_logoff.setOnClickListener(this);
        my_horn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_horn:
                Intent inhorn=new Intent(getContext(),HornActivity.class);
                startActivity(inhorn);
                break;
            case R.id.my_head_image:
                break;
            case R.id.my_Name:
                break;
            case R.id.my_message:
                Intent intent=new Intent(getContext(),UserActivity.class);
                startActivity(intent);
                break;
            case R.id.my_care:
                Intent inCare=new Intent(getContext(),FollowActivity.class);
                startActivity(inCare);
                break;
            case R.id.my_pay:
                Intent inPay=new Intent(getContext(),PayActivity.class);
                startActivity(inPay);
                break;
            case R.id.my_opinion:
                Intent in=new Intent(getContext(),OpinionActivity.class);
                startActivity(in);
                break;
            case R.id.my_edition:
                Intent ine=new Intent(getContext(),EditionxActivity.class);
                startActivity(ine);
                break;
            case R.id.my_logoff:
                break;
        }
    }

    @Override
    public void success(Object success) {
        if (success instanceof IDUserData){
            IDUserData idUserData= (IDUserData) success;
            IDUserData.ResultBean result = idUserData.getResult();
            String nickName = result.getNickName();
            String headPic = result.getHeadPic();
            my_Name.setText(nickName);
            MyGlideUtil.setCircleImage(getContext(),headPic,my_head_image);
        }
    }

    @Override
    public void error(String error) {

    }
}
