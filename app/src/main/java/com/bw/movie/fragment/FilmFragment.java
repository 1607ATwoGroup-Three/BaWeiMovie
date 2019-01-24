package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.utils.SpBase;

/**
 * 这是一个影片的fragment
 */

public class FilmFragment extends Fragment {


    private ImageView film_image_white;
    private TextView film_text_white;

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
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("111111","111111111111");
        film_text_white.setText(SpBase.getString("str","没有定位"));
    }
}