package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.NearbyCinemaData;
import com.bw.movie.bean.RecommendCinemaData;
import com.bw.movie.utils.MyGlideUtil;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/25 9:13<p>
 * <p>更改时间：2019/1/25 9:13<p>
 * <p>版本号：1<p>
 */
public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.ViewHolder> {
    private Context context;
    private List<NearbyCinemaData.ResultBean> Nearbylist;
    private List<RecommendCinemaData.ResultBean> Recommendlist;
    private int type=0;

    public CinemaAdapter(Context context, List<NearbyCinemaData.ResultBean> nearbylist, List<RecommendCinemaData.ResultBean> recommendlist) {
        this.context = context;
        this.Nearbylist = nearbylist;
        this.Recommendlist = recommendlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.cinema_recycle_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void setType(int type){
        this.type=type;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (type == 0) {
            viewHolder.recycle_item_name.setText(Recommendlist.get(i).getName());
            viewHolder.recycle_item_address.setText(Recommendlist.get(i).getAddress());
            viewHolder.recycle_item_distance.setText(Recommendlist.get(i).getDistance()+"km");
            MyGlideUtil.setDefaultImage(context,Recommendlist.get(i).getLogo(),viewHolder.recycle_item_image);
            if(Recommendlist.get(i).getFollowCinema()==2){
                Glide.with(context).load(R.mipmap.com_icon_collection_default).into(viewHolder.recycle_item_follow);
            }else{
                Glide.with(context).load(R.mipmap.com_icon_collection_selected).into(viewHolder.recycle_item_follow);
            }
        } else {
            viewHolder.recycle_item_name.setText(Nearbylist.get(i).getName());
            viewHolder.recycle_item_address.setText(Nearbylist.get(i).getAddress());
            int v1 = Nearbylist.get(i).getDistance() / 1000;
            int v = Nearbylist.get(i).getDistance() % 1000;
            viewHolder.recycle_item_distance.setText(v1+"."+v+"km");
            MyGlideUtil.setDefaultImage(context,Nearbylist.get(i).getLogo(),viewHolder.recycle_item_image);
            if(Nearbylist.get(i).getFollowCinema()==2){
                Glide.with(context).load(R.mipmap.com_icon_collection_default).into(viewHolder.recycle_item_follow);
            }else{
                Glide.with(context).load(R.mipmap.com_icon_collection_selected).into(viewHolder.recycle_item_follow);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    @Override
    public int getItemCount() {
        if (type == 0) {
            return Recommendlist.size();
        } else {
            return Nearbylist.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView recycle_item_image;
        public TextView recycle_item_name;
        public TextView recycle_item_address;
        public TextView recycle_item_distance;
        public ImageView recycle_item_follow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.recycle_item_image = (ImageView) itemView.findViewById(R.id.recycle_item_image);
            this.recycle_item_name = (TextView) itemView.findViewById(R.id.recycle_item_name);
            this.recycle_item_address = (TextView) itemView.findViewById(R.id.recycle_item_address);
            this.recycle_item_distance = (TextView) itemView.findViewById(R.id.recycle_item_distance);
            this.recycle_item_follow = (ImageView) itemView.findViewById(R.id.recycle_item_follow);
        }
    }
}
