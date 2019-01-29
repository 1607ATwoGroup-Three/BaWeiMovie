package com.bw.movie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.MovieFilmAdapter;
import com.bw.movie.bean.MovieFilmData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.setting.RecycleLeftStyle;
import com.bw.movie.setting.RecycleTopStyle;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.SpBase;

import java.util.HashMap;
import java.util.Map;

/**
 * 影评
 */
public class MovieFilmReviewFragment extends Fragment implements Contract.View {

    private RecyclerView film_review_recycle;
    private Contract.Presenter presenter;
    private Map<String,Object> headmap;
    private Map<String,Object> map;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_movie_film_review, null);
        initView(view);
        present();
        return view;
    }

    private void present() {
        presenter = new Presenter(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        headmap.put("userId", SpBase.getString(getContext(), "userId", ""));
        headmap.put("sessionId", SpBase.getString(getContext(), "sessionId", ""));
        map.put("movieId", SpBase.getString(getContext(), "movieId", ""));
        map.put("page","1");
        map.put("count","100");
        presenter.get(Interfaces.SearchForFilmReviews,headmap,map,MovieFilmData.class);
    }

    private void initView(View view) {
        film_review_recycle = (RecyclerView) view.findViewById(R.id.film_review_recycle);
        film_review_recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        film_review_recycle.addItemDecoration(new RecycleTopStyle(20));
    }


    @Override
    public void success(Object success) {
        if(success instanceof MovieFilmData){
            MovieFilmData data = (MovieFilmData) success;
            MovieFilmAdapter adapter =new MovieFilmAdapter(R.layout.movie_detail_item,data.getResult());
            film_review_recycle.setAdapter(adapter);
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}
