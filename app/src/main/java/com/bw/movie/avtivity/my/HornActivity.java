package com.bw.movie.avtivity.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.SystemMessageAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.HornBean;
import com.bw.movie.bean.OpinionBean;
import com.bw.movie.bean.WdMessageData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.SpBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HornActivity extends BaseActivity implements Contract.View {

    private ImageView System_Back;
    private TextView System_messageW;
    private Presenter presenter;
    private List<HornBean.ResultBean> mlist=new ArrayList<>();
    private RecyclerView recy;
    private String sessionid;
    private String userid;
    private SystemMessageAdapter adapter;

    protected void initView() {
        BaseActivity.doublekeydown(false);
        BaseActivity.fullScreen(HornActivity.this,true);
        setContentView(R.layout.activity_horn);
    }

    @Override
    protected void initData() {
        System_Back = (ImageView) findViewById(R.id.System_Back);
        System_messageW = (TextView) findViewById(R.id.System_messageW);
        recy = findViewById(R.id.Recy);

        System_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        recy.setLayoutManager(manager1);
        adapter = new SystemMessageAdapter(mlist,this);
        recy.setAdapter(adapter);
    }

    @Override
    protected void present() {
        presenter = new Presenter(this);
        sessionid = SpBase.getString(HornActivity.this, "sessionId", "");
        userid = SpBase.getString(HornActivity.this, "userId", "");

        Map<String, Object> headmap = new HashMap<>();
        headmap.put("userId", userid + "");
        headmap.put("sessionId", sessionid + "");
        Map<String, Object> map = new HashMap<>();
        presenter.get(Interfaces.QueryTheNumberOfUnreadMessagesTheUserCurrentlyHas, headmap, map, WdMessageData.class);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("page",1);
        map2.put("count",5);
        presenter.get(Interfaces.QuerySystemMessageList, headmap, map2, HornBean.class);

    }

    @Override
    public void success(Object success) {
        if(success instanceof WdMessageData){

            WdMessageData wdMessageData = (WdMessageData) success;
            String message = wdMessageData.getMessage();
            if(wdMessageData.getStatus().equals("0000")){
                Toast.makeText(HornActivity.this, message, Toast.LENGTH_SHORT).show();
                int count = wdMessageData.getCount();
                System_messageW.setText("("+count+"条未读)");
            }
        }else if(success instanceof HornBean){
            HornBean hornBean= (HornBean) success;
            String message = hornBean.getMessage();
            List<HornBean.ResultBean> result = hornBean.getResult();
            mlist.addAll(result);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void error(String error) {

    }
}
