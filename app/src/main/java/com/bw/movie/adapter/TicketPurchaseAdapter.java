package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;

public class TicketPurchaseAdapter extends RecyclerView.Adapter<TicketPurchaseAdapter.ViewHolder> {
    private Context context;

    @NonNull
    @Override
    public TicketPurchaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cinema_recycle_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TicketPurchaseAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
