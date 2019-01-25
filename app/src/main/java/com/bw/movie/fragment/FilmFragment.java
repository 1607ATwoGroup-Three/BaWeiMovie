package com.bw.movie.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.utils.SpBase;

/**
 * 这是一个影片的fragment
 */

public class FilmFragment extends Fragment implements View.OnClickListener {


    private ImageView film_image_white;
    private TextView film_text_white;
    private EditText film_search_ed;
    private TextView film_search;
    private LinearLayout film_search_lin;
    private TransitionSet mSet;
    boolean isExpand = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_film, null);

        initView(view);
        return view;
    }

    private void initView(View view) {
        film_image_white = (ImageView) view.findViewById(R.id.film_image_white);
        film_text_white = (TextView) view.findViewById(R.id.film_text_white);
        film_search_ed = (EditText) view.findViewById(R.id.film_search_ed);
        film_search = (TextView) view.findViewById(R.id.film_search);
        film_search.setOnClickListener(this);
        film_search_lin = (LinearLayout) view.findViewById(R.id.film_search_lin);
        film_search_lin.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        film_text_white.setText(SpBase.getString("str", "没有定位"));
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
}