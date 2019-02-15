package com.bw.movie.avtivity;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.ui.SeatTable;

/**
 * 选座 Activity
 */
public class SeatSelectionActivity extends BaseActivity {

    private SeatTable seat_view;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_seat_selection);
        seat_view = (SeatTable) findViewById(R.id.seat_view);

        seat_view.setData(8,10);
        seat_view.setMaxSelected(5);//设置最多选中

    }

    @Override
    protected void initData() {
        seat_view.setSeatChecker(new SeatTable.SeatChecker() {
            //第san次
            @Override
            public boolean isValidSeat(int row, int column) {
                if (column == 2||column == 7) {
                    return false;
                }
                if (row==4)
                    return false;
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if (row == 6 && column == 6) {
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
