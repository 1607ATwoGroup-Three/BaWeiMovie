package com.bw.movie.setting;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/26 20:05<p>
 * <p>更改时间：2019/1/26 20:05<p>
 * <p>版本号：1<p>
 */
public class RecycleLeft_DownStyle extends RecyclerView.ItemDecoration {
    private int space;

    public RecycleLeft_DownStyle(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if(parent.getChildPosition(view) != 0)
            outRect.left = space;
            outRect.bottom = space+50;
    }
}
