package com.bw.movie.avtivity.my;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;

public class FollowActivity extends BaseActivity implements View.OnClickListener,Contract.View {

    private ImageView Follow_back;
    private TextView care_yinPian;
    private TextView care_yinYuan;
    private Presenter presenter;


    @Override
    protected void initView() {
        BaseActivity.doublekeydown(false);
        BaseActivity.fullScreen(FollowActivity.this,true);
        setContentView(R.layout.activity_follow);
    }

    @Override
    protected void initData() {
        Follow_back = findViewById(R.id.Follow_back);
        care_yinPian = findViewById(R.id.care_YinPian);
        care_yinYuan = findViewById(R.id.care_YinYuan);

        Follow_back.setOnClickListener(this);
        care_yinPian.setOnClickListener(this);
        care_yinYuan.setOnClickListener(this);
    }

    @Override
    protected void present() {
        presenter = new Presenter(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Follow_back:
                finish();
                break;
            case R.id.care_YinPian:
                care_yinPian.setBackgroundResource(R.drawable.cinema_ed);
                care_yinPian.setTextColor(Color.WHITE);
                care_yinYuan.setBackgroundResource(R.drawable.cinema_noed);
                care_yinYuan.setTextColor(Color.BLACK);
                break;
            case R.id.care_YinYuan:
                care_yinYuan.setBackgroundResource(R.drawable.cinema_ed);
                care_yinYuan.setTextColor(Color.WHITE);
                care_yinPian.setBackgroundResource(R.drawable.cinema_noed);
                care_yinPian.setTextColor(Color.BLACK);
                break;
        }
    }

    @Override
    public void success(Object success) {

    }

    @Override
    public void error(String error) {

    }
}
