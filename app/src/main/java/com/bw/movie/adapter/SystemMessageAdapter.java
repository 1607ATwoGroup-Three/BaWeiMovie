package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.HornBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SystemMessageAdapter extends RecyclerView.Adapter<SystemMessageAdapter.ViewHolder> {

    private List<HornBean.ResultBean> mlist;
    private Context context;

    public SystemMessageAdapter(List<HornBean.ResultBean> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @NonNull
    @Override
    public SystemMessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.system_message_list, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SystemMessageAdapter.ViewHolder viewHolder, int i) {
        viewHolder.message_title.setText(mlist.get(i).getTitle());
        viewHolder.message_content.setText(mlist.get(i).getContent());
        //
        long currentTimeMillis = mlist.get(i).getPushTime();
        Date d = new Date(currentTimeMillis);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String format = f.format(d);
        viewHolder.message_time.setText(format);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView message_title;
        private final TextView message_content;
        private final TextView message_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            message_title = itemView.findViewById(R.id.Message_Title);
            message_content = itemView.findViewById(R.id.Message_Content);
            message_time = itemView.findViewById(R.id.Message_Time);
        }
    }
}
