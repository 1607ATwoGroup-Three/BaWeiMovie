package com.bw.movie.avtivity.my;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private EditText newPwd;
    private EditText resetPwd;
    private TextView nowPwd;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);
        initView();
        presenter();
    }

    private void presenter() {
        String sessionid = SpBase.getString(this, "sessionId", "");
        String userid = SpBase.getString(this, "userId", "");
        Presenter presenter = new Presenter(this);
        Map<String, Object> headmap = new HashMap<>();
        headmap.put("userId", userid + "");
        headmap.put("sessionId", sessionid + "");
        //老 新 重复密码
        Map<String, Object> map = new HashMap<>();
        pwd = SpBase.getString(ResetPwdActivity.this, "pwd", "");
        Toast.makeText(this, pwd, Toast.LENGTH_SHORT).show();
        map.put("oldPwd", pwd);
        //map.put("newPwd",);
        //map.put("newPwd2",);
        presenter.post(Interfaces.ChangePassword, headmap, map, ResetPwdData.class);
    }

    private void initView() {
        Reset_Back = (ImageView) findViewById(R.id.Reset_Back);
        Reset_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        newPwd = (EditText) findViewById(R.id.newPwd);
        resetPwd = (EditText) findViewById(R.id.resetPwd);
        nowPwd = (TextView) findViewById(R.id.nowPwd);
    }

    @Override
    public void success(Object success) {
        if (success instanceof ResetPwdData) {
            ResetPwdData resetPwdData = (ResetPwdData) success;

        }
    }

    @Override
    public void error(String error) {

    }
}
