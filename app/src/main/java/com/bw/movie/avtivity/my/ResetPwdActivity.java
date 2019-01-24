package com.bw.movie.avtivity.my;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;

public class ResetPwdActivity extends AppCompatActivity {

    private ImageView Reset_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);
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
}
