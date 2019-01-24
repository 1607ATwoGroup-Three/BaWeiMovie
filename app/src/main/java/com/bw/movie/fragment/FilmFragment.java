package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.model.LatLng;
import com.bw.movie.R;
import com.bw.movie.utils.LocationUtil;

/**
 * 这是一个影片的fragment
 */

public class FilmFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =View.inflate(getContext(),R.layout.fragment_film,null);
        return view;
    }
}