package com.bw.movie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.MovieNoticeAdapter;
import com.bw.movie.adapter.MovieStilsAdapter;
import com.bw.movie.bean.MovieDetailBean;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.SpBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 剧照
 */
public class MoviceStillsFragment extends Fragment implements Contract.View {

    private RecyclerView film_stils_recycle;
    private Map<String, Object> headmap;
    private Map<String, Object> map;
    private Contract.Presenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movice_stills, container, false);
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
        film_stils_recycle = (RecyclerView) view.findViewById(R.id.film_stils_recycle);
    }

    @Override
    public void success(Object success) {
        if (success instanceof MovieDetailBean) {
            MovieDetailBean bean = (MovieDetailBean) success;
            List<String> list =new ArrayList<>();
            list.addAll(bean.getResult().getPosterList());
            MovieStilsAdapter adapter =new MovieStilsAdapter(R.layout.stills_image,list);
            film_stils_recycle.setLayoutManager(new StaggeredGridLayoutManager(2,OrientationHelper.VERTICAL));
            film_stils_recycle.setAdapter(adapter);
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}
