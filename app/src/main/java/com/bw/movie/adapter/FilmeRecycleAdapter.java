package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.FilmRecycleItemBean;
import com.bw.movie.utils.MyGlideUtil;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/26 14:52<p>
 * <p>更改时间：2019/1/26 14:52<p>
 * <p>版本号：1<p>
 */
public class FilmeRecycleAdapter extends RecyclerView.Adapter<FilmeRecycleAdapter.ViewHolder> {

    private Context context;
    private List<FilmRecycleItemBean> list;

    public FilmeRecycleAdapter(Context context, List<FilmRecycleItemBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.film_recycle_picture_item, null);
        ViewHolder holder =new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.film_recycle_item_name.setText(list.get(i).getName());
        MyGlideUtil.setDefaultImage(context,list.get(i).getImageUrl(),viewHolder.film_recycle_item_img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView film_recycle_item_img;
        public TextView film_recycle_item_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.film_recycle_item_img = (ImageView) itemView.findViewById(R.id.film_recycle_item_img);
            this.film_recycle_item_name = (TextView) itemView.findViewById(R.id.film_recycle_item_name);
        }
    }
}
