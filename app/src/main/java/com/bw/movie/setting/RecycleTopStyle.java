package com.bw.movie.setting;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/26 20:03<p>
 * <p>更改时间：2019/1/26 20:03<p>
 * <p>版本号：1<p>
 */
public class RecycleTopStyle extends RecyclerView.ItemDecoration {

    private int space;

    public RecycleTopStyle(int space) {
        this.space = space;
    }
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(parent.getChildPosition(view) != 0)
            outRect.top = space;
    }
}
