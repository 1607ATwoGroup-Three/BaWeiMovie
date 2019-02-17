package com.bw.movie.avtivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.ui.SeatTable;

/**
 * 选座 Activity
 */
public class SeatSelectionActivity extends BaseActivity {

    private TextView seat_selection_name;
    private TextView seat_selection_address;
    private TextView seat_selection_movie_name;
    private TextView seat_selection_address_ting;
    private TextView seat_selection_price;
    private ImageView seat_selection_pay;
    private ImageView seat_selection_esc;
    private SeatTable seat_selection_seatTable;
    private ImageView seat_selection_down;
    private RadioButton seat_selection_weixinbutton;
    private RadioButton seat_selection_zhifubaobutton;
    private RelativeLayout select_sit_pay;
    private TextView seat_selection_weixin_paytext;

    @Override
    protected void initView() {
        BaseActivity.doublekeydown(false);
        BaseActivity.fullScreen(SeatSelectionActivity.this, false);
        setContentView(R.layout.activity_seat_selection);
        seat_selection_name =findViewById(R.id.seat_selection_name);
        seat_selection_address =findViewById(R.id.seat_selection_address);
        seat_selection_movie_name =findViewById(R.id.seat_selection_movie_name);
        seat_selection_address_ting =findViewById(R.id.seat_selection_address_ting);
        seat_selection_price =findViewById(R.id.seat_selection_price);
        seat_selection_pay =findViewById(R.id.seat_selection_pay);
        seat_selection_esc =findViewById(R.id.seat_selection_esc);
        seat_selection_seatTable =findViewById(R.id.seat_selection_seatTable);
        seat_selection_down =findViewById(R.id.seat_selection_down);
        seat_selection_weixinbutton =findViewById(R.id.seat_selection_weixinbutton);
        seat_selection_zhifubaobutton =findViewById(R.id.seat_selection_zhifubaobutton);
        select_sit_pay =findViewById(R.id.select_sit_pay);
        seat_selection_weixin_paytext =findViewById(R.id.seat_selection_weixin_paytext);

        seat_selection_seatTable.setData(8,10);
        seat_selection_seatTable.setMaxSelected(4);//设置最多选中

    }

    @Override
    protected void initData() {
        seat_selection_seatTable.setSeatChecker(new SeatTable.SeatChecker() {
            //第san次
            @Override
            public boolean isValidSeat(int row, int column) {
                /**
                 * 这里的column 是列数
                 */
                /*if (column == 2||column == 7) {
                    return false;
                }*/
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if (row == 6 && column == 6) {
                    return true;
                }
                if (row == 5 && column == 2) {
                    return true;
                }
                if (row == 2 && column == 4) {
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {
            }

            @Override
            public void unCheck(int row, int column) {
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
    }

    @Override
    protected void present() {

    }
}
