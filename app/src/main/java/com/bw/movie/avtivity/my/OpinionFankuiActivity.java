package com.bw.movie.avtivity.my;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.utils.SpBase;

public class OpinionFankuiActivity extends BaseActivity {

    private ImageView OpinionFK_Back;


    protected void initView() {
        BaseActivity.doublekeydown(false);
        BaseActivity.fullScreen(OpinionFankuiActivity.this,true);
        setContentView(R.layout.activity_opinion_fankui);

    }

    @Override
    protected void initData() {
        OpinionFK_Back = (ImageView) findViewById(R.id.OpinionFK_Back);
        OpinionFK_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void present() {

    }
}
