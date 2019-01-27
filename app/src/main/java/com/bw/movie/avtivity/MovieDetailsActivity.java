package com.bw.movie.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;

import java.util.Map;

public class MovieDetailsActivity extends BaseActivity implements Contract.View {

    private ImageView details_image_back;
    private TextView details_movie;
    private ImageView details_movie_image_love;
    private TextView details_movie_text_name;
    private ImageView details_movie_image_big;
    private TextView details_lin_movie_text_details;
    private TextView details_lin_movie_text_notice;
    private TextView details_lin_movie_text_photo;
    private TextView details_lin_movie_text_review;
    private Button details_movie_button_back;
    private Button details_movie_button_buy;
    private FrameLayout details_frg;

    private Map<String, Object> headmap;
    private Map<String, Object> map;
    private Contract.Presenter presenter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_movie_details);
        details_image_back =findViewById(R.id.details_image_back);
        details_movie =findViewById(R.id.details_movie);
        details_movie_image_love =findViewById(R.id.details_movie_image_love);
        details_movie_text_name =findViewById(R.id.details_movie_text_name);
        details_movie_image_big =findViewById(R.id.details_movie_image_big);
        details_lin_movie_text_details =findViewById(R.id.details_lin_movie_text_details);
        details_lin_movie_text_notice =findViewById(R.id.details_lin_movie_text_notice);
        details_lin_movie_text_photo =findViewById(R.id.details_lin_movie_text_photo);
        details_lin_movie_text_review =findViewById(R.id.details_lin_movie_text_review);
        details_movie_button_back =findViewById(R.id.details_movie_button_back);
        details_movie_button_buy =findViewById(R.id.details_movie_button_buy);
        details_frg =findViewById(R.id.details_frg);
        Intent intent = getIntent();
        String movieid = intent.getStringExtra("movieid");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void present() {
        presenter =new Presenter(this);
//        presenter.get(Interfaces.SearchFilmInformationBasedOnFilmID,headmap,map,);
    }

    @Override
    public void success(Object success) {

    }

    @Override
    public void error(String error) {

    }
}
