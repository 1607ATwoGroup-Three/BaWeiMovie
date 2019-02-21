package com.bw.movie.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.FilmAdapter;
import com.bw.movie.avtivity.MovieDetailsActivity;
import com.bw.movie.avtivity.MovieListActivity;
import com.bw.movie.bean.FilmTypeBean;
import com.bw.movie.bean.IsShowingUpBean;
import com.bw.movie.bean.PopularCinemaBean;
import com.bw.movie.bean.ToBeShownSoonBean;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.SpBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这是一个影片的fragment
 */

public class FilmFragment extends Fragment implements View.OnClickListener, Contract.View {


    private ImageView film_image_white;
    private TextView film_text_white;
    private EditText film_search_ed;
    private TextView film_search;
    private LinearLayout film_search_lin;
    private RecyclerView film_recycle;
    private TransitionSet mSet;
    boolean isExpand = false;
//    热门电影
    private List<PopularCinemaBean.ResultBean> Poplist = new ArrayList<>();
    //    正在热映
    private List<IsShowingUpBean.ResultBean> IsSlist = new ArrayList<>();
    //    即将上映
    private List<ToBeShownSoonBean.ResultBean> ToBlist = new ArrayList<>();
    private Presenter presenter;
    private Map<String, Object> headmap;
    private Map<String, Object> map;
//    Item 的类型
    private List<FilmTypeBean> typeBeanList = new ArrayList<>();
    private FilmAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_film, null);
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
                    int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                    //在6.0增加了View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR，这个字段就是把状态栏标记为浅色，然后状态栏的字体颜色自动转换为深色
                    window.setStatusBarColor(Color.TRANSPARENT);
                    decorView.setSystemUiVisibility(option);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    //导航栏颜色也可以正常设置
                    //window.setNavigationBarColor(Color.TRANSPARENT);
                }
            }
    }

    private void present() {
        presenter = new Presenter(this);
        headmap = new HashMap<>();
        headmap.put("userId",SpBase.getString(getContext(),"userId",""));
        headmap.put("sessionId",SpBase.getString(getContext(),"sessionId",""));
        map = new HashMap<>();
        map.put("page","1");
        map.put("count","100");
//        查询热门电影
        presenter.get(Interfaces.SearchForHotMovies, headmap, map,PopularCinemaBean.class);
    }

    private void initView(View view) {
        film_image_white = (ImageView) view.findViewById(R.id.film_image_white);
        film_text_white = (TextView) view.findViewById(R.id.film_text_white);
        film_search_ed = (EditText) view.findViewById(R.id.film_search_ed);
        film_search = (TextView) view.findViewById(R.id.film_search);
        film_search.setOnClickListener(this);
        film_search_lin = (LinearLayout) view.findViewById(R.id.film_search_lin);
        film_search_lin.setOnClickListener(this);
        film_recycle = (RecyclerView) view.findViewById(R.id.film_recycle);
        film_recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FilmAdapter(getContext(),typeBeanList,Poplist,IsSlist,ToBlist);
        film_recycle.setAdapter(adapter);
        adapter.getonclcked(new FilmAdapter.onclick() {
            @Override
            public void list(int id) {
                Intent intent =new Intent(getContext(),MovieListActivity.class);
                intent.putExtra("whilt",SpBase.getString(getContext(), "str", "没有定位"));
                intent.putExtra("type",id+"");
                startActivity(intent);
            }

            @Override
            public void movie(int id) {
                Intent intent =new Intent(getContext(),MovieDetailsActivity.class);
                intent.putExtra("movieId",id+"");
                startActivity(intent);
//                Toast.makeText(getContext(), id+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        film_text_white.setText(SpBase.getString(getContext(), "str", "没有定位"));
    }

    private void submit() {
        // validate
        String ed = film_search_ed.getText().toString().trim();
        if (TextUtils.isEmpty(ed)) {
            Toast.makeText(getContext(), "请输入要搜索的影片", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.film_search:
                if (!isExpand) {
                    expand();
                    isExpand = true;
                } else if (isExpand) {
                    reduce();
                    isExpand = false;
                }
                break;
            case R.id.film_search_lin:
                if (!isExpand) {
                    expand();
                    isExpand = true;
                } else if (isExpand) {
                    reduce();
                    isExpand = false;
                }
                break;
        }
    }

    private void expand() {
        //设置伸展状态时的布局
        //etSearchContent.setHint("搜索你想搜索的内容");
        film_search_ed.setVisibility(View.VISIBLE);
        film_search.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams) film_search_lin.getLayoutParams();
        LayoutParams.width = dip2px(220);
        LayoutParams.setMargins(dip2px(0), dip2px(32), dip2px(0), dip2px(0));
        film_search_lin.setLayoutParams(LayoutParams);
        //开始动画
        beginDelayedTransition(film_search_lin);
    }

    private void reduce() {
        //设置收缩状态时的布局
        film_search_ed.setText("");
        film_search_ed.setVisibility(View.GONE);
        film_search.setVisibility(View.GONE);
        RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams) film_search_lin.getLayoutParams();
        LayoutParams.width = dip2px(60);
        LayoutParams.setMargins(dip2px(0), dip2px(32), dip2px(0), dip2px(0));
        film_search_lin.setLayoutParams(LayoutParams);
        //开始动画
        beginDelayedTransition(film_search_lin);
    }

    @SuppressLint("NewApi")
    void beginDelayedTransition(ViewGroup view) {
        mSet = new AutoTransition();
        mSet.setDuration(300);
        TransitionManager.beginDelayedTransition(view, mSet);
    }

    private int dip2px(float dpVale) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpVale * scale + 0.5f);
    }

    @Override
    public void success(Object success) {
        if (success instanceof PopularCinemaBean) {
            PopularCinemaBean data = (PopularCinemaBean) success;
            Poplist.addAll(data.getResult());
            typeBeanList.clear();
            FilmTypeBean bean1 =new FilmTypeBean();
            bean1.setType(0);
            typeBeanList.add(bean1);
//            正在上映
            FilmTypeBean bean =new FilmTypeBean();
            bean.setType(1);
            typeBeanList.add(bean);
            presenter.get(Interfaces.QueryTheListOfMoviesBeingShown, headmap, map,IsShowingUpBean.class);
        }else if(success instanceof IsShowingUpBean){
            IsShowingUpBean bean = (IsShowingUpBean) success;
            IsSlist.addAll(bean.getResult());
            //            即将上映
            FilmTypeBean bea =new FilmTypeBean();
            bea.setType(2);
            typeBeanList.add(bea);
            presenter.get(Interfaces.QueryTheListOfUpcomingMovies, headmap, map,ToBeShownSoonBean.class);
        }else if(success instanceof ToBeShownSoonBean){
            ToBeShownSoonBean bean = (ToBeShownSoonBean) success;
            ToBlist.addAll(bean.getResult());
            FilmTypeBean be =new FilmTypeBean();
            be.setType(3);
            typeBeanList.add(be);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void error(String error) {
        Log.e("error",error);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.ontach();
        }
    }
}