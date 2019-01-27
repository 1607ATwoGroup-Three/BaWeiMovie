package com.bw.movie.avtivity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.ResetPwdData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.SpBase;

import java.util.HashMap;
import java.util.Map;

public class ResetPwdActivity extends BaseActivity implements Contract.View {

    private ImageView Reset_Back;
    private EditText newPwd;
    private EditText resetPwd;
    private TextView nowPwd;
    private String pwd;
    private Button Update_save;
    private Presenter presenter;
    private String encryptn;


    protected void initView() {
        setContentView(R.layout.activity_reset_pwd);
    }

    @Override
    protected void initData() {
        Reset_Back = (ImageView) findViewById(R.id.Reset_Back);
        newPwd = (EditText) findViewById(R.id.newPwd);
        resetPwd = (EditText) findViewById(R.id.resetPwd);
        nowPwd = (TextView) findViewById(R.id.nowPwd);
        Update_save = (Button) findViewById(R.id.Update_save);

        pwd = SpBase.getString(ResetPwdActivity.this, "pwd", "");
        nowPwd.setText(pwd + "");
        encryptn = EncryptUtil.encrypt(pwd);


        Reset_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Update_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sessionid = SpBase.getString(ResetPwdActivity.this, "sessionId", "");
                String userid = SpBase.getString(ResetPwdActivity.this, "userId", "");
                Map<String, Object> headmap = new HashMap<>();
                headmap.put("userId", userid + "");
                headmap.put("sessionId", sessionid + "");
                //老 新 重复密码
                Map<String, Object> map = new HashMap<>();
                map.put("oldPwd", encryptn);
                String newpwd = newPwd.getText().toString();
                String resetpwd = resetPwd.getText().toString();
                String encrypt = EncryptUtil.encrypt(newpwd);
                String encrypt1 = EncryptUtil.encrypt(resetpwd);
                map.put("newPwd", encrypt);
                map.put("newPwd2", encrypt1);
                presenter.post(Interfaces.ChangePassword, headmap, map, ResetPwdData.class);
            }
        });
    }

    @Override
    protected void present() {
        presenter = new Presenter(this);
    }

    @Override
    public void success(Object success) {
        if (success instanceof ResetPwdData) {
            ResetPwdData resetPwdData = (ResetPwdData) success;
            String message = resetPwdData.getMessage();
            if (resetPwdData.getStatus().equals("0000")) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                finish();
            }

        }
    }

    @Override
    public void error(String error) {

    }

}
