package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.PopularData;
import com.bw.movie.utils.MyGlideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/25 19:33<p>
 * <p>更改时间：2019/1/25 19:33<p>
 * <p>版本号：1<p>
 */
public class FilmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private int type =0;
    private List<PopularData.ResultBean> populist;

    public FilmAdapter(Context context, List<PopularData.ResultBean> populist) {
        this.context = context;
        this.populist = populist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder holder = null;
        View view;
        switch (i) {
            case 1:
                view = View.inflate(context, R.layout.film_recycle_banner, null);
                holder = new headerbannerHolder(view);
                break;
            case 2:
                view = View.inflate(context, R.layout.film_recycle_picture_item, null);
                holder = new ViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof headerbannerHolder) {
            ((headerbannerHolder) viewHolder).film_recycle_banner.setText("1111111111111111");
        }else if(viewHolder instanceof ViewHolder){
            MyGlideUtil.setDefaultImage(context,populist.get(i).getImageUrl(),((ViewHolder) viewHolder).film_recycle_item_img);
            ((ViewHolder) viewHolder).film_recycle_item_name.setText(populist.get(i).getName());
        }
    }

    public void settype(int type) {
        this.type = type;
    }

    @Override
    public int getItemViewType(int position) {
        if(type!=0){
            return type;
        }else{
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        switch (type) {
            case 1:
                return 1;
            case 2:
                return populist.size();
            default:
                return 0;
        }
    }


    public static class headerbannerHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView film_recycle_banner;

        public headerbannerHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.film_recycle_banner = (TextView) rootView.findViewById(R.id.film_recycle_banner);
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView film_recycle_item_img;
        public TextView film_recycle_item_name;

        public ViewHolder(View rootView) {
            super(rootView);
            this.film_recycle_item_img = (ImageView) rootView.findViewById(R.id.film_recycle_item_img);
            this.film_recycle_item_name = (TextView) rootView.findViewById(R.id.film_recycle_item_name);
        }

    }
}
