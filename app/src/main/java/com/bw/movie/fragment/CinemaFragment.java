package com.bw.movie.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaAdapter;
import com.bw.movie.avtivity.ShowActivity;
import com.bw.movie.bean.NearbyCinemaData;
import com.bw.movie.bean.RecommendCinemaData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.SpBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bw.movie.R.drawable.cinema_noed;

/**
 * 影院的fragment
 */
public class CinemaFragment extends Fragment implements View.OnClickListener, Contract.View {

    private ImageView cinema_image_white;
    private TextView cinema_text_white;
    private TextView cinema_btn_Recommend;
    private TextView cinema_btn_nearby;
    private RecyclerView cinema_recycle;
    private Presenter presenter;
    private Map<String, Object> hashmap = new HashMap<>();
    private List<RecommendCinemaData.ResultBean> recommendlist = new ArrayList<>();
    private List<NearbyCinemaData.ResultBean> Nearbylist = new ArrayList<>();
    private CinemaAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_cinema, null);
        initView(view);
        present();
        return view;
    }

    private void initView(View view) {
        cinema_image_white = (ImageView) view.findViewById(R.id.cinema_image_white);
        cinema_text_white = (TextView) view.findViewById(R.id.cinema_text_white);
        cinema_btn_Recommend = (TextView) view.findViewById(R.id.cinema_btn_Recommend);
        cinema_recycle = (RecyclerView) view.findViewById(R.id.cinema_recycle);
        cinema_btn_nearby = (TextView) view.findViewById(R.id.cinema_btn_nearby);
        cinema_btn_nearby.setOnClickListener(this);
        cinema_btn_Recommend.setOnClickListener(this);
        cinema_text_white.setText(SpBase.getString(getContext(),"str", "没有定位"));

        adapter = new CinemaAdapter(getContext(), Nearbylist, recommendlist);
        adapter.setType(0);
        cinema_recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        cinema_recycle.setAdapter(adapter);

        hashmap.put("userId", SpBase.getString(getContext(),"userId", "0"));
        hashmap.put("sessionId", SpBase.getString(getContext(),"sessionId", ""));

    }

    private void present() {
        presenter = new Presenter(this);
        Map<String, Object> map = new HashMap<>();
//        接口访问只有不到10个数据
        map.put("page", 1);
        map.put("count", 10);
        presenter.get(Interfaces.SearchForRecommendedCinema, hashmap, map, RecommendCinemaData.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cinema_btn_Recommend:
                adapter.setType(0);
                Map<String, Object> map = new HashMap<>();
//        接口访问只有不到10个数据
                map.put("page", 1);
                map.put("count", 10);
                presenter.get(Interfaces.SearchForRecommendedCinema, hashmap, map, RecommendCinemaData.class);
                cinema_btn_Recommend.setBackgroundResource(R.drawable.cinema_ed);
                cinema_btn_Recommend.setTextColor(Color.WHITE);
                cinema_btn_nearby.setBackgroundResource(R.drawable.cinema_noed);
                cinema_btn_nearby.setTextColor(Color.BLACK);
                break;
            case R.id.cinema_btn_nearby:
                adapter.setType(1);
                Map<String, Object> map2 = new HashMap<>();
                map2.put("longitude",SpBase.getString(getContext(),"lgt",""));
                map2.put("latitude",SpBase.getString(getContext(),"lat",""));
                map2.put("page", 1);
                map2.put("count", 100);
                presenter.get(Interfaces.SearchForNearbyCinemas, hashmap, map2,NearbyCinemaData.class);
                cinema_btn_nearby.setBackgroundResource(R.drawable.cinema_ed);
                cinema_btn_nearby.setTextColor(Color.WHITE);
                cinema_btn_Recommend.setBackgroundResource(R.drawable.cinema_noed);
                cinema_btn_Recommend.setTextColor(Color.BLACK);
                break;
        }
    }

    @Override
    public void success(Object success) {
        if (success instanceof RecommendCinemaData) {
            recommendlist.clear();
            RecommendCinemaData data = (RecommendCinemaData) success;
            recommendlist.addAll(data.getResult());
            adapter.setType(0);
            adapter.notifyDataSetChanged();
        }else if(success instanceof NearbyCinemaData){
            Nearbylist.clear();
            NearbyCinemaData data = (NearbyCinemaData) success;
            Nearbylist.addAll(data.getResult());
            adapter.setType(1);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}
