package com.bw.movie.avtivity.my;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.bean.IDUserData;
import com.bw.movie.bean.ResetPwdData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.SpBase;

import java.util.HashMap;
import java.util.Map;

public class ResetPwdActivity extends AppCompatActivity implements Contract.View {

    private ImageView Reset_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);

        String sessionid = SpBase.getString(this, "sessionId", "");
        String userid = SpBase.getString(this, "userId", "");
        Presenter presenter = new Presenter(this);
        Map<String, Object> headmap = new HashMap<>();
        headmap.put("userId", userid + "");
        headmap.put("sessionId", sessionid + "");
        Map<String, Object> map = new HashMap<>();
        presenter.get(Interfaces.UpdataPwd, headmap, map, ResetPwdData.class);

        initView();
    }

    private void initView() {
        Reset_Back = (ImageView) findViewById(R.id.Reset_Back);
        Reset_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void success(Object success) {
        if (success instanceof ResetPwdData){
            ResetPwdData resetPwdData= (ResetPwdData) success;

        }
    }

    @Override
    public void error(String error) {

    }
}
