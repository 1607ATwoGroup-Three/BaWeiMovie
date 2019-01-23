package com.bw.movie.avtivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        Button zc = findViewById(R.id.login_btn_register);
        zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void present() {

    }
}
