package com.bw.movie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;

/**
 * 影院的fragment
 */
public class CinemaFragment extends Fragment {

    private ImageView cinema_image_white;
    private TextView cinema_text_white;
    private TextView cinema_btn_Recommend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_cinema, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        cinema_image_white = (ImageView) view.findViewById(R.id.cinema_image_white);
        cinema_text_white = (TextView) view.findViewById(R.id.cinema_text_white);
        cinema_btn_Recommend = (TextView) view.findViewById(R.id.cinema_btn_Recommend);
    }
}
