package com.bw.movie.avtivity.my;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.MovieRecycleAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.Follow_DianyinData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.SpBase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FollowActivity extends BaseActivity implements View.OnClickListener, Contract.View {

    private ImageView Follow_back;
    private TextView care_yinPian;
    private TextView care_yinYuan;
    private Presenter presenter;
    private String sessionid;
    private String userid;
    private List<Follow_DianyinData.ResultBean> mlist = new ArrayList<>();
    private RecyclerView movie_list_lin_recycle;

    @Override
    protected void initView() {
        BaseActivity.doublekeydown(false);
        BaseActivity.fullScreen(FollowActivity.this, true);
        setContentView(R.layout.activity_follow);
    }

    @Override
    protected void initData() {
        Follow_back = findViewById(R.id.Follow_back);
        care_yinPian = findViewById(R.id.care_YinPian);
        care_yinYuan = findViewById(R.id.care_YinYuan);
        movie_list_lin_recycle=findViewById(R.id.Follow_recycle);


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
        switch (v.getId()) {
            case R.id.Follow_back:
                finish();
                break;
            case R.id.care_YinPian:
                care_yinPian.setBackgroundResource(R.drawable.cinema_ed);
                care_yinPian.setTextColor(Color.WHITE);
                care_yinYuan.setBackgroundResource(R.drawable.cinema_noed);
                care_yinYuan.setTextColor(Color.BLACK);

                sessionid = SpBase.getString(this, "sessionId", "");
                userid = SpBase.getString(this, "userId", "");
                Map<String, Object> headmap = new HashMap<>();
                headmap.put("userId", userid + "");
                headmap.put("sessionId", sessionid + "");
                Map<String, Object> map = new HashMap<>();
                map.put("page", 1);
                map.put("count", 4);
                presenter.get(Interfaces.SearchForVideosOfInterestToUsers, headmap, map, Follow_DianyinData.class);
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
        if (success instanceof Follow_DianyinData) {
            Follow_DianyinData follow_dianyinData = (Follow_DianyinData) success;
            List<Follow_DianyinData.ResultBean> result = follow_dianyinData.getResult();
            mlist.clear();
            

        }
    }

    @Override
    public void error(String error) {

    }

}
