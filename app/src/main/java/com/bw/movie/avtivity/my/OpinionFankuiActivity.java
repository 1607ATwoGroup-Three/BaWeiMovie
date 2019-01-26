package com.bw.movie.avtivity.my;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;

public class OpinionFankuiActivity extends AppCompatActivity {

    private ImageView OpinionFK_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion_fankui);
        initView();
    }

    private void initView() {
        OpinionFK_Back = (ImageView) findViewById(R.id.OpinionFK_Back);
        OpinionFK_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
