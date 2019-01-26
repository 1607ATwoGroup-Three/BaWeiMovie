package com.bw.movie.avtivity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;

public class OpinionActivity extends BaseActivity {

    private Button Opinion_tijiao;
    private ImageView Opinion_Back;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_opinion);
    }

    @Override
    protected void initData() {
        Opinion_tijiao = findViewById(R.id.Opinion_tijiao);
        Opinion_Back=findViewById(R.id.Opinion_Back);
        //提交
        Opinion_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(OpinionActivity.this, OpinionFankuiActivity.class);
                startActivity(in);
            }
        });
        //返回
        Opinion_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void present() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }
}
