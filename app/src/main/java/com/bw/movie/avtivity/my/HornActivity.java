package com.bw.movie.avtivity.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.HornBean;
import com.bw.movie.bean.OpinionBean;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.SpBase;

import java.util.HashMap;
import java.util.Map;

public class HornActivity extends BaseActivity implements Contract.View {

    private ImageView System_Back;
    private TextView Message1;
    private TextView Message2;
    private TextView time1;
    private TextView Message3;
    private TextView time2;
    private Presenter presenter;

    protected void initView() {

        setContentView(R.layout.activity_horn);

    }

    @Override
    protected void initData() {
        System_Back = (ImageView) findViewById(R.id.System_Back);
        Message1 = (TextView) findViewById(R.id.Message1);
        Message2 = (TextView) findViewById(R.id.Message2);
        time1 = (TextView) findViewById(R.id.time1);
        Message3 = (TextView) findViewById(R.id.Message3);
        time2 = (TextView) findViewById(R.id.time2);

        System_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void present() {
        presenter = new Presenter(this);
    }

    @Override
    public void success(Object success) {
        if(success instanceof HornBean){
            String sessionid = SpBase.getString(HornActivity.this, "sessionId", "");
            String userid = SpBase.getString(HornActivity.this, "userId", "");

            Map<String, Object> headmap = new HashMap<>();
            headmap.put("userId", userid + "");
            headmap.put("sessionId", sessionid + "");
            Map<String, Object> map = new HashMap<>();
            map.put("page",1);
            map.put("count",5);
            presenter.get(Interfaces.SystemMessageReadStatusModification, headmap, map, HornBean.class);

            HornBean hornBean = (HornBean) success;
            String message = hornBean.getMessage();
            if(hornBean.getStatus().equals("0000")){
                Toast.makeText(HornActivity.this, message, Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void error(String error) {

    }
}
