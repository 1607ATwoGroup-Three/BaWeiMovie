package com.bw.movie.avtivity.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.IDUserData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.MyGlideUtil;
import com.bw.movie.utils.SpBase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserActivity extends BaseActivity implements View.OnClickListener, Contract.View {

    private ImageView my_message_reset;
    private ImageView my_message;
    private TextView user_name;
    private TextView user_sex;
    private TextView user_birthday;
    private TextView user_phone;
    private TextView user_emil;
    private ImageView User_Back;


    protected void initView() {

        setContentView(R.layout.activity_user);

    }

    @Override
    protected void initData() {
        my_message_reset = (ImageView) findViewById(R.id.my_message_reset);
        my_message = (ImageView) findViewById(R.id.my_message);
        user_name = (TextView) findViewById(R.id.user_name);
        user_sex = (TextView) findViewById(R.id.user_sex);
        user_birthday = (TextView) findViewById(R.id.user_birthday);
        user_phone = (TextView) findViewById(R.id.user_phone);
        user_emil = (TextView) findViewById(R.id.user_emil);

        my_message_reset.setOnClickListener(this);
        User_Back = (ImageView) findViewById(R.id.User_Back);
        User_Back.setOnClickListener(this);
    }

    @Override
    protected void present() {
        String sessionid = SpBase.getString(this, "sessionId", "");
        String userid = SpBase.getString(this, "userId", "");
        Presenter presenter = new Presenter(this);
        Map<String, Object> headmap = new HashMap<>();
        headmap.put("userId", userid + "");
        headmap.put("sessionId", sessionid + "");
        Map<String, Object> map = new HashMap<>();
        presenter.get(Interfaces.QueryUserInformation, headmap, map, IDUserData.class);
    }

    @Override
    public void onClick(View v) {
        my_message_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, ResetPwdActivity.class);
                startActivity(intent);
            }
        });
        User_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void success(Object success) {
        if (success instanceof IDUserData) {
            IDUserData idUserData = (IDUserData) success;
            IDUserData.ResultBean result = idUserData.getResult();
            String nickName = result.getNickName();
            String headPic = result.getHeadPic();
            String phone = result.getPhone();
            //性别
            if (result.getSex() == 1) {
                user_sex.setText("男");
            } else {
                user_sex.setText("女");
            }
            //出生日期
            long currentTimeMillis = result.getBirthday();
            Date d = new Date(currentTimeMillis);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String format = f.format(d);
            user_birthday.setText(format);
            user_name.setText(nickName);
            user_phone.setText(phone);
            MyGlideUtil.setCircleImage(UserActivity.this,headPic,my_message);
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(UserActivity.this, error, Toast.LENGTH_SHORT).show();
    }
}
