package com.bw.movie.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.MovieDetailBean;
import com.bw.movie.contract.Contract;
import com.bw.movie.fragment.MoviceStillsFragment;
import com.bw.movie.fragment.MovieDetailFragment;
import com.bw.movie.fragment.MovieFilmReviewFragment;
import com.bw.movie.fragment.MovieNoticeFragment;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.MyGlideUtil;
import com.bw.movie.utils.SpBase;

import java.util.HashMap;
import java.util.Map;

public class MovieDetailsActivity extends BaseActivity implements Contract.View, View.OnClickListener {

    private ImageView details_image_back;
    private TextView details_movie;
    private ImageView details_movie_image_love;
    private TextView details_movie_text_name;
    private ImageView details_movie_image_big;
    private ImageView details_visibility_img;
    private ImageView details_visibility_img_top;
    private TextView details_lin_movie_text_details;
    private TextView details_lin_movie_text_notice;
    private TextView details_lin_movie_text_photo;
    private TextView details_lin_movie_text_review;
    private TextView details_movie_button_back;
    private TextView details_movie_button_buy;
    private FrameLayout details_frg;

    private String movieId;
    private String islove;
    private Map<String, Object> headmap;
    private Map<String, Object> map;
    private Contract.Presenter presenter;
    private FragmentManager manager;

    @Override
    protected void initView() {
        BaseActivity.doublekeydown(false);
        BaseActivity.fullScreen(MovieDetailsActivity.this, false);
        setContentView(R.layout.activity_movie_details);
        details_image_back = findViewById(R.id.details_image_back);
        details_visibility_img = findViewById(R.id.details_visibility_img);
        details_visibility_img_top = findViewById(R.id.details_visibility_img_top);
        details_movie = findViewById(R.id.details_movie);
        details_movie_image_love = findViewById(R.id.details_movie_image_love);
        details_movie_text_name = findViewById(R.id.details_movie_text_name);
        details_movie_image_big = findViewById(R.id.details_movie_image_big);
        details_lin_movie_text_details = findViewById(R.id.details_lin_movie_text_details);
        details_lin_movie_text_notice = findViewById(R.id.details_lin_movie_text_notice);
        details_lin_movie_text_photo = findViewById(R.id.details_lin_movie_text_photo);
        details_lin_movie_text_review = findViewById(R.id.details_lin_movie_text_review);
        details_movie_button_back = findViewById(R.id.details_movie_button_back);
        details_movie_button_buy = findViewById(R.id.details_movie_button_buy);
        details_frg = findViewById(R.id.details_frg);
        details_lin_movie_text_details.setOnClickListener(this);
        details_lin_movie_text_notice.setOnClickListener(this);
        details_lin_movie_text_photo.setOnClickListener(this);
        details_lin_movie_text_review.setOnClickListener(this);
        details_movie_button_back.setOnClickListener(this);
        details_visibility_img.setOnClickListener(this);
        details_visibility_img_top.setOnClickListener(this);
        details_movie_button_buy.setOnClickListener(this);
        Intent intent = getIntent();
        movieId = intent.getStringExtra("movieId");
        islove = intent.getStringExtra("islove");
    }

    @Override
    protected void initData() {
        if(islove!=null)
        if (islove.equals("2")) {
            MyGlideUtil.setRoundImage(MovieDetailsActivity.this, R.mipmap.com_icon_collection_default, details_movie_image_love);
        } else if(islove.equals("1")){
            MyGlideUtil.setRoundImage(MovieDetailsActivity.this, R.mipmap.com_icon_collection_selected, details_movie_image_love);
        }
        if(movieId!=null){
            SpBase.save(MovieDetailsActivity.this,"movieId",movieId);
        }
        headmap = new HashMap<>();
        map = new HashMap<>();
        headmap.put("userId", SpBase.getString(MovieDetailsActivity.this, "userId", ""));
        headmap.put("sessionId", SpBase.getString(MovieDetailsActivity.this, "sessionId", ""));
        map.put("movieId", SpBase.getString(MovieDetailsActivity.this, "movieId", ""));
        manager = getSupportFragmentManager();
    }

    @Override
    protected void present() {
        presenter = new Presenter(this);
        presenter.get(Interfaces.CheckOutTheDetailsOfTheMovie, headmap, map, MovieDetailBean.class);
    }

    @Override
    public void success(Object success) {
        if (success instanceof MovieDetailBean) {
            MovieDetailBean bean = (MovieDetailBean) success;
            MovieDetailBean.ResultBean resultBean = bean.getResult();
            details_movie_text_name.setText(resultBean.getName());
            SpBase.save(MovieDetailsActivity.this,"moviename",resultBean.getName());
            MyGlideUtil.setRoundImage(ctx, resultBean.getImageUrl(), details_movie_image_big);
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(ctx, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.details_lin_movie_text_details:
                manager.beginTransaction().replace(R.id.details_frg,new MovieDetailFragment()).commit();
                details_frg.setVisibility(View.VISIBLE);
                details_visibility_img.setVisibility(View.VISIBLE);
                details_visibility_img_top.setVisibility(View.VISIBLE);
                break;
            case R.id.details_lin_movie_text_notice:
                manager.beginTransaction().replace(R.id.details_frg,new MovieNoticeFragment()).commit();
                details_frg.setVisibility(View.VISIBLE);
                details_visibility_img.setVisibility(View.VISIBLE);
                details_visibility_img_top.setVisibility(View.VISIBLE);
                break;
            case R.id.details_lin_movie_text_photo:
                manager.beginTransaction().replace(R.id.details_frg,new MoviceStillsFragment()).commit();
                details_frg.setVisibility(View.VISIBLE);
                details_visibility_img.setVisibility(View.VISIBLE);
                details_visibility_img_top.setVisibility(View.VISIBLE);
                break;
            case R.id.details_lin_movie_text_review:
                manager.beginTransaction().replace(R.id.details_frg,new MovieFilmReviewFragment()).commit();
                details_frg.setVisibility(View.VISIBLE);
                details_visibility_img.setVisibility(View.VISIBLE);
                details_visibility_img_top.setVisibility(View.VISIBLE);
                break;
            case R.id.details_movie_button_back:
//                返回
                finish();
                break;
            case R.id.details_visibility_img:
                //这是拦截一个点击事件的
                break;
            case R.id.details_visibility_img_top:
//                点击其他地方关闭fragment
                details_frg.setVisibility(View.GONE);
                details_visibility_img.setVisibility(View.GONE);
                details_visibility_img_top.setVisibility(View.GONE);
                break;
            case R.id.details_movie_button_buy:
                Intent intent =new Intent(MovieDetailsActivity.this,TicketPurchaseActivity.class);
                startActivity(intent);
                break;
        }
    }
}
