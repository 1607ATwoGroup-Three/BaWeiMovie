package com.bw.movie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;

/**
 * 影评
 */
public class MovieFilmReviewFragment extends Fragment {

    private RecyclerView film_review_recycle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_movie_film_review, null);

        initView(view);
        return view;
    }

    private void initView(View view) {
        film_review_recycle = (RecyclerView) view.findViewById(R.id.film_review_recycle);
        
    }
}
