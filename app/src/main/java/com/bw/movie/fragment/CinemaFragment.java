package com.bw.movie.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaAdapter;
import com.bw.movie.avtivity.CinemaDetailActivity;
import com.bw.movie.bean.NearbyCinemaData;
import com.bw.movie.bean.RecommendCinemaData;
import com.bw.movie.bean.RegisterData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.SpBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private int rootViewLastHeight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_cinema, null);
        initView(view);
        present();
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = getActivity().getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                //在6.0增加了View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR，这个字段就是把状态栏标记为浅色，然后状态栏的字体颜色自动转换为深色
                window.setStatusBarColor(Color.TRANSPARENT);
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                //导航栏颜色也可以正常设置
                //window.setNavigationBarColor(Color.TRANSPARENT);
            }
        }
    }

    private void initView(final View view) {
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
        adapter.getonclcked(new CinemaAdapter.onclick() {
            @Override
            public void cinema(int id) {
                Intent intent =new Intent(getContext(),CinemaDetailActivity.class);
                SpBase.save(getContext(),"cinema_id",id+"");
                startActivity(intent);
            }

            @Override
            public void love(int id,int i) {
                Map<String, Object> map = new HashMap<>();
                map.put("cinemaId",id);
                if(i==2){
                    presenter.get(Interfaces.CinemaAttention,hashmap,map,RegisterData.class);
                }else{
                    presenter.get(Interfaces.CancelAttentionToCinema,hashmap,map,RegisterData.class);
                }
            }
        });

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
        }else if(success instanceof RegisterData){
            RegisterData data = (RegisterData) success;
            Log.e("关注电影院",data.getMessage()+"");
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}
