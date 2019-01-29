package com.bw.movie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.avtivity.MovieDetailsActivity;
import com.bw.movie.bean.MovieDetailBean;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.MyGlideUtil;
import com.bw.movie.utils.SpBase;

import java.util.HashMap;
import java.util.Map;

/**
 * 电影详情页面
 */
public class MovieDetailFragment extends Fragment implements Contract.View {

    private ImageView film_detail_image_down;
    private TextView detail_type;
    private TextView detail_director;
    private TextView details_long;
    private TextView details_address;
    private TextView detail_count;
    private TextView detail_name1;
    private TextView detail_name2;
    private TextView detail_name3;
    private TextView detail_name4;
    private ImageView details_img;
    private Map<String,Object> headmap;
    private Map<String,Object> map;
    private Contract.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        initView(view);
        present();
        return view;
    }

    private void present() {
        headmap = new HashMap<>();
        map = new HashMap<>();
        headmap.put("userId", SpBase.getString(getContext(), "userId", ""));
        headmap.put("sessionId", SpBase.getString(getContext(), "sessionId", ""));
        map.put("movieId", SpBase.getString(getContext(), "movieId", ""));

        presenter = new Presenter(this);
        presenter.get(Interfaces.CheckOutTheDetailsOfTheMovie, headmap, map, MovieDetailBean.class);
    }

    private void initView(View view) {
        film_detail_image_down = (ImageView) view.findViewById(R.id.film_detail_image_down);
        detail_type = (TextView) view.findViewById(R.id.detail_type);
        detail_director = (TextView) view.findViewById(R.id.detail_director);
        details_long = (TextView) view.findViewById(R.id.details_long);
        details_address = (TextView) view.findViewById(R.id.details_address);
        detail_count = (TextView) view.findViewById(R.id.detail_count);
        detail_name1 = (TextView) view.findViewById(R.id.detail_name1);
        detail_name2 = (TextView) view.findViewById(R.id.detail_name2);
        detail_name3 = (TextView) view.findViewById(R.id.detail_name3);
        detail_name4 = (TextView) view.findViewById(R.id.detail_name4);
        details_img = (ImageView) view.findViewById(R.id.details_img);
    }

    @Override
    public void success(Object success) {
        if (success instanceof MovieDetailBean) {
            MovieDetailBean bean = (MovieDetailBean) success;
            MovieDetailBean.ResultBean resultBean = bean.getResult();
            detail_count.setText(resultBean.getSummary());
            detail_type.setText(resultBean.getMovieTypes());
            detail_director.setText(resultBean.getDirector());
            details_long.setText(resultBean.getDuration());
            details_address.setText(resultBean.getPlaceOrigin());
            String[] split = resultBean.getStarring().split(",");
            detail_name1.setText(split[0]);
            detail_name2.setText(split[1]);
            detail_name3.setText(split[2]);
            if(split.length==3){
                detail_name4.setText(split[0]);
            }else{
                detail_name4.setText(split[3]);
            }
            MyGlideUtil.setRoundImage(getContext(), resultBean.getImageUrl(), details_img);
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}
