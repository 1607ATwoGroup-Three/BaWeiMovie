package com.bw.movie.avtivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.LoginData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.SpUtil;
import com.xw.repo.XEditText;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity implements Contract.View {

    private Button login_button;
    private TextView login_Zhuce;
    private XEditText login_phone;
    private XEditText login_pwd;
    private CheckBox login_jizhumima;
    private CheckBox login_zidongdenglu;
    private SharedPreferences.Editor edit;
    private TextView youke;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);

        SpUtil.put("cb",false);
        boolean cb =SpUtil.getBoolean("cb", false);
        if (cb){
            String phone = SpUtil.getString("log_phone", "");
            String pwd = SpUtil.getString("log_pwd", "");
            login_phone.setText(phone);
            login_pwd.setText(pwd);
            login_jizhumima.setChecked(true);
        }

    }

    @Override
    protected void initData() {
        login_Zhuce = findViewById(R.id.login_Zhuce);
        login_button = findViewById(R.id.login_button);
        login_phone = findViewById(R.id.login_phone);
        login_pwd = findViewById(R.id.login_pwd);
        login_jizhumima = findViewById(R.id.login_jizhumima);
        login_zidongdenglu = findViewById(R.id.login_zidongdenglu);
        youke = findViewById(R.id.Youke);

        //点击事件
        login_Zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String log_phone = login_phone.getText().toString();
                String log_pwd = login_pwd.getText().toString();
                if (login_jizhumima.isChecked()){
                    edit.putString("log_phone",log_phone);
                    edit.putString("log_pwd",log_pwd);
                    edit.putBoolean("cb",true);
                    edit.commit();
                }else {
                    edit.clear();
                    edit.commit();
                }
            }
        });

        //游客模式
        youke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void present() {
        Presenter presenter=new Presenter(this);
        Map<String,String> map=new HashMap<>();
        map.put("phone",login_phone.getText()+"");
        map.put("pwd",login_pwd.getText()+"");


    }


    @Override
    public void success(Object success) {
        LoginData loginData= (LoginData) success;
        if(loginData.getStatus().equals("0000")){

        }
    }

    @Override
    public void error(String error) {

    }
}
