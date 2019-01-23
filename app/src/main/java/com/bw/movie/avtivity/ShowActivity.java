package com.bw.movie.avtivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;

public class ShowActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout show_frage;
    private ImageView show_btn_Cinema;
    private ImageView show_btn_My;
    private ImageView show_btn_Film;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_show);
        show_btn_Film = findViewById(R.id.show_btn_Film);
        show_frage = findViewById(R.id.show_frage);
        show_btn_My = findViewById(R.id.show_btn_My);
        show_btn_Cinema = findViewById(R.id.show_btn_Cinema);

        show_btn_Cinema.setOnClickListener(this);
        show_btn_Film.setOnClickListener(this);
        show_btn_My.setOnClickListener(this);
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void present() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_btn_My:
                break;
            case R.id.show_btn_Cinema:
                break;
            case R.id.show_btn_Film:
                break;
        }
    }
}
