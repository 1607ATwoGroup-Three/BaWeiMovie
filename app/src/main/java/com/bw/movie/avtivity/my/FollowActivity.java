package com.bw.movie.avtivity.my;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.FollowAdapter;
import com.bw.movie.adapter.MovieRecycleAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.Follow_DianyinData;
import com.bw.movie.bean.Follow_YinyuanData;
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
    private List<Follow_DianyinData.ResultBean> mDYlist = new ArrayList<>();
    private List<Follow_YinyuanData.ResultBean> mYYlist = new ArrayList<>();
    private RecyclerView follow_recycle;
    private FollowAdapter adapter;

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
        follow_recycle=findViewById(R.id.Follow_recycle);


        Follow_back.setOnClickListener(this);
        care_yinPian.setOnClickListener(this);
        care_yinYuan.setOnClickListener(this);

        follow_recycle.setLayoutManager(new LinearLayoutManager(this));
        adapter=new FollowAdapter(this,mDYlist,mYYlist);
        follow_recycle.setAdapter(adapter);
    }

    @Override
    protected void present() {
        presenter = new Presenter(this);

        sessionid = SpBase.getString(this, "sessionId", "");
        userid = SpBase.getString(this, "userId", "");
        Map<String, Object> headmap = new HashMap<>();
        headmap.put("userId", userid + "");
        headmap.put("sessionId", sessionid + "");
        Map<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("count", 100);
        presenter.get(Interfaces.SearchForVideosOfInterestToUsers, headmap, map, Follow_DianyinData.class);
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
                map.put("count", 100);
                presenter.get(Interfaces.SearchForVideosOfInterestToUsers, headmap, map, Follow_DianyinData.class);
                break;
            case R.id.care_YinYuan:
                care_yinYuan.setBackgroundResource(R.drawable.cinema_ed);
                care_yinYuan.setTextColor(Color.WHITE);
                care_yinPian.setBackgroundResource(R.drawable.cinema_noed);
                care_yinPian.setTextColor(Color.BLACK);

                sessionid = SpBase.getString(this, "sessionId", "");
                userid = SpBase.getString(this, "userId", "");
                Map<String, Object> headmap2 = new HashMap<>();
                headmap2.put("userId", userid + "");
                headmap2.put("sessionId", sessionid + "");
                Map<String, Object> map2 = new HashMap<>();
                map2.put("page", 1);
                map2.put("count", 100);
                presenter.get(Interfaces.SearchForCinemaInFormationThatUsersAreInterestedIn, headmap2, map2, Follow_YinyuanData.class);
                break;
        }
    }

    @Override
    public void success(Object success) {
        if (success instanceof Follow_DianyinData) {
            mDYlist.clear();
            Follow_DianyinData follow_dianyinData = (Follow_DianyinData) success;
            List<Follow_DianyinData.ResultBean> result = follow_dianyinData.getResult();
            mDYlist.addAll(result);
            adapter.setType(0);
            adapter.notifyDataSetChanged();
        }else if (success instanceof Follow_YinyuanData){
            mYYlist.clear();
            Follow_YinyuanData follow_yinyuanData= (Follow_YinyuanData) success;
            List<Follow_YinyuanData.ResultBean> result = follow_yinyuanData.getResult();
            mYYlist.addAll(result);
            adapter.setType(1);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

}
