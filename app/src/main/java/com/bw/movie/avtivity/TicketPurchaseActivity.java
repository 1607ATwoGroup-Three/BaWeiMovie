package com.bw.movie.avtivity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;

public class TicketPurchaseActivity extends BaseActivity implements Contract.View {

    private TextView movie_ticket_name;
    private RecyclerView movie_ticket_recycle;
    private ImageView movie_ticket_back;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ticket_purchase);
        movie_ticket_name =findViewById(R.id.movie_ticket_name);
        movie_ticket_recycle =findViewById(R.id.movie_ticket_recycle);
        movie_ticket_back = findViewById(R.id.movie_ticket_back);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void present() {
        Presenter presenter=new Presenter(this);
    }

    @Override
    public void success(Object success) {

    }

    @Override
    public void error(String error) {

    }
}
