package com.bw.movie.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.MovieFilmAdapter;
import com.bw.movie.avtivity.MovieListActivity;
import com.bw.movie.bean.MovieFilmData;
import com.bw.movie.bean.RegisterData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.setting.RecycleTopStyle;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.MyGlideUtil;
import com.bw.movie.utils.SpBase;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * 影评
 */
public class MovieFilmReviewFragment extends Fragment implements Contract.View, View.OnClickListener {

    private RecyclerView film_review_recycle;
    private ImageView film_review_write;
    private Contract.Presenter presenter;
    private Map<String, Object> headmap;
    private Map<String, Object> map;
    private EditText film_recycle_edit;
    private TextView film_recycle_send;
    private LinearLayout film_recycle_linear;

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
        map.put("page", "1");
        map.put("count", "100");
        presenter.get(Interfaces.SearchForFilmReviews, headmap, map, MovieFilmData.class);
    }

    private void initView(final View view) {
        film_review_recycle = (RecyclerView) view.findViewById(R.id.film_review_recycle);
        film_review_write = (ImageView) view.findViewById(R.id.film_review_write);
        film_review_recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        film_review_recycle.addItemDecoration(new RecycleTopStyle(20));
        film_review_write.setOnClickListener(this);
        film_recycle_edit = (EditText) view.findViewById(R.id.film_recycle_edit);
        film_recycle_send = (TextView) view.findViewById(R.id.film_recycle_send);
        film_recycle_send.setOnClickListener(this);
        film_recycle_linear = (LinearLayout) view.findViewById(R.id.film_recycle_linear);
        film_recycle_linear.setOnClickListener(this);
    }


    @Override
    public void success(Object success) {
        if (success instanceof MovieFilmData) {
            final MovieFilmData data = (MovieFilmData) success;
            MovieFilmAdapter adapter = new MovieFilmAdapter(R.layout.movie_detail_item, data.getResult());
            film_review_recycle.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    Map<String, Object> mapthree = new HashMap<>();
                    mapthree.put("commentId",data.getResult().get(position).getCommentId());
                    ImageView image =(ImageView)adapter.getViewByPosition(film_review_recycle,position, R.id.Ping_Huifu_love);
                    if(data.getResult().get(position).getIsGreat()==0){
                        data.getResult().get(position).setIsGreat(1);
                        presenter.post(Interfaces.CommentsOnTheFilm,headmap,mapthree,RegisterData.class);
                        MyGlideUtil.setRoundImage(getActivity(),R.mipmap.com_icon_praise_selected,image);
                        TextView view1 = (TextView) adapter.getViewByPosition(film_review_recycle,position, R.id.Ping_Huifu_DianZaiShu);
                        view1.setText((data.getResult().get(position).getGreatNum()+1)+"");
                    }
                }
            });
        }else if(success instanceof RegisterData){
            RegisterData data = (RegisterData) success;
            if(data.getMessage().equals("评论成功")){
                /**
                 * 重新拉一下数据
                 */
                presenter.get(Interfaces.SearchForFilmReviews, headmap, map, MovieFilmData.class);
            } else if(data.getMessage().equals("点赞成功")){

//                presenter.get(Interfaces.SearchForFilmReviews, headmap, map, MovieFilmData.class);
            }
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    private boolean submit() {
        // validate
        String edit = film_recycle_edit.getText().toString().trim();
        if (TextUtils.isEmpty(edit)) {
            Toast.makeText(getContext(), "写评论", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.film_recycle_send:
                film_recycle_linear.setVisibility(View.GONE);
                film_review_write.setVisibility(View.VISIBLE);
                if(submit()){
                    String edit = film_recycle_edit.getText().toString().trim();
                    Map<String,Object> maptwo =new HashMap<>();
                    maptwo.put("movieId",SpBase.getString(getActivity(),"movieId",""));
                    maptwo.put("commentContent",edit);
                    presenter.post(Interfaces.AddUserCommentsOnTheFilm,headmap,maptwo,RegisterData.class);
                }
                break;
            case R.id.film_review_write:
                film_recycle_linear.setVisibility(View.VISIBLE);
                film_review_write.setVisibility(View.GONE);
                break;
        }
    }
}