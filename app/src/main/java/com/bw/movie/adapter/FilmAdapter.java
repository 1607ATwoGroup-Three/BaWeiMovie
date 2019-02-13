package com.bw.movie.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.FilmRecycleItemBean;
import com.bw.movie.bean.FilmTypeBean;
import com.bw.movie.bean.IsShowingUpBean;
import com.bw.movie.bean.PopularCinemaBean;
import com.bw.movie.bean.ToBeShownSoonBean;
import com.bw.movie.setting.RecycleLeftStyle;
import com.bw.movie.setting.RecycleLeft_DownStyle;
import com.bw.movie.setting.animation.DecelerateAccelerateInterpolator;

import java.util.ArrayList;
import java.util.List;

import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/25 19:33<p>
 * <p>更改时间：2019/1/25 19:33<p>
 * <p>版本号：1<p>
 */
public class FilmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<FilmTypeBean> typeBeanList;

    private int jindu = 2;
    private float flag =150;
    //    热门电影
    private List<PopularCinemaBean.ResultBean> Poplist;
    //    正在热映
    private List<IsShowingUpBean.ResultBean> IsSlist;
    //    即将上映
    private List<ToBeShownSoonBean.ResultBean> ToBlist;

    public FilmAdapter(Context context, List<FilmTypeBean> typeBeanList, List<PopularCinemaBean.ResultBean> poplist,
                       List<IsShowingUpBean.ResultBean> isSlist, List<ToBeShownSoonBean.ResultBean> toBlist) {
        this.context = context;
        this.typeBeanList = typeBeanList;
        Poplist = poplist;
        IsSlist = isSlist;
        ToBlist = toBlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder holder = null;
        if (i == 1000) {
            View view = View.inflate(context, R.layout.film_recycle_banner, null);
            holder = new ViewbannerHolder(view);
        } else if (i == 1001) {
            View view = View.inflate(context, R.layout.film_recycle_one, null);
            holder = new ViewReHolder(view);
        } else if (i == 1002) {
            View view = View.inflate(context, R.layout.film_recycle_one, null);
            holder = new ViewReHolder(view);
        } else if (i == 1003) {
            View view = View.inflate(context, R.layout.film_recycle_one, null);
            holder = new ViewReHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        if (type == 1000) {
            if (viewHolder instanceof ViewbannerHolder) {
                final List<FilmRecycleItemBean> list = new ArrayList<>();
                for (int j = 0; j < IsSlist.size(); j++) {
                    list.add(new FilmRecycleItemBean(IsSlist.get(j).getId(), IsSlist.get(j).getImageUrl(), IsSlist.get(j).getName()));
                }
                FilmeRecycleAdapter filmeRecycleAdapter = new FilmeRecycleAdapter(context, list);
                ((ViewbannerHolder) viewHolder).film_recycle_banner.setAdapter(filmeRecycleAdapter);
                ((ViewbannerHolder) viewHolder).film_recycle_banner.smoothScrollToPosition(2);
                filmeRecycleAdapter.getonclick(new FilmeRecycleAdapter.onclicked() {
                    @Override
                    public void movie(int id, int i) {
                        ((ViewbannerHolder) viewHolder).film_recycle_banner.smoothScrollToPosition(i);
                    }
                });
                /*((ViewbannerHolder) viewHolder).film_recycle_banner.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
                    @Override
                    public void onItemSelected(int i) {
//                        得到当前显示的是哪一个
                        int pos = ((ViewbannerHolder) viewHolder).film_recycle_banner.getSelectedPos();
                        int i1 = 100 / list.size();
                        ((ViewbannerHolder) viewHolder).film_recycle_bar.setProgress(i1*(pos+1));
                    }
                });*/
                ((ViewbannerHolder) viewHolder).film_recycle_banner.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
                    @Override
                    public void onItemSelected(int position) {
                        if(jindu>position)
                        {
                            ObjectAnimator animator = ObjectAnimator.ofFloat(((ViewbannerHolder) viewHolder).linearLayout, "translationX",flag, flag-=50*(jindu-position));
                            animator.setDuration(300);
                            animator.start();
                            jindu=position;
                        }
                        else
                        {
                            ObjectAnimator animator = ObjectAnimator.ofFloat(((ViewbannerHolder) viewHolder).linearLayout, "translationX",flag,flag+=50*(position-jindu));
                            animator.setDuration(300);
                            animator.start();
                            jindu=position;
                        }
                    }
                });

                filmeRecycleAdapter.notifyDataSetChanged();
            }
        } else if (type == 1001) {
            if (viewHolder instanceof ViewReHolder) {
                ((ViewReHolder) viewHolder).film_one_text.setText("热门电影");
                float x = ((ViewReHolder) viewHolder).film_one_img_ani.getTranslationX();
                ObjectAnimator animator = ObjectAnimator.ofFloat(((ViewReHolder) viewHolder).film_one_img_ani, "translationX",x,120,x);
                // 创建动画对象 & 设置动画
                // 表示的是:
                // 动画作用对象是mButton
                // 动画作用的对象的属性是X轴平移
                // 动画效果是:从当前位置平移到 x=1500再平移到初始位置
                animator.setDuration(1000);
                animator.setRepeatCount(-1);
                animator.setInterpolator(new DecelerateAccelerateInterpolator());
                // 设置插值器
                animator.start();
                ((ViewReHolder) viewHolder).film_one_recycle.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                List<FilmRecycleItemBean> list = new ArrayList<>();
                for (int j = 0; j < Poplist.size(); j++) {
                    list.add(new FilmRecycleItemBean(Poplist.get(j).getId(), Poplist.get(j).getImageUrl(), Poplist.get(j).getName()));
                }
                FilmeRecycleAdapter filmeRecycleAdapter = new FilmeRecycleAdapter(context, list);
                ((ViewReHolder) viewHolder).film_one_recycle.setAdapter(filmeRecycleAdapter);
                ((ViewReHolder) viewHolder).film_one_recycle.addItemDecoration(new RecycleLeftStyle(20));
                filmeRecycleAdapter.notifyDataSetChanged();
                ((ViewReHolder) viewHolder).film_one_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onclick != null) {
                            onclick.list(1001);
                        }
                    }
                });
                filmeRecycleAdapter.getonclick(new FilmeRecycleAdapter.onclicked() {
                    @Override
                    public void movie(int id,int i) {
                        if (onclick != null) {
                            onclick.movie(id);
                        }
                    }
                });
            }
        } else if (type == 1002) {
            if (viewHolder instanceof ViewReHolder) {
                ((ViewReHolder) viewHolder).film_one_text.setText("正在热映");

                float x = ((ViewReHolder) viewHolder).film_one_img_ani.getTranslationX();
                ObjectAnimator animator = ObjectAnimator.ofFloat(((ViewReHolder) viewHolder).film_one_img_ani, "translationX",x,120,x);
                // 创建动画对象 & 设置动画
                // 表示的是:
                // 动画作用对象是mButton
                // 动画作用的对象的属性是X轴平移
                // 动画效果是:从当前位置平移到 x=1500再平移到初始位置
                animator.setDuration(1000);
                animator.setRepeatCount(-1);
                animator.setInterpolator(new DecelerateAccelerateInterpolator());
                // 设置插值器
                animator.start();

                ((ViewReHolder) viewHolder).film_one_recycle.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                List<FilmRecycleItemBean> list = new ArrayList<>();
                for (int j = 0; j < IsSlist.size(); j++) {
                    list.add(new FilmRecycleItemBean(IsSlist.get(j).getId(), IsSlist.get(j).getImageUrl(), IsSlist.get(j).getName()));
                }
                FilmeRecycleAdapter adapter = new FilmeRecycleAdapter(context, list);
                ((ViewReHolder) viewHolder).film_one_recycle.setAdapter(adapter);
                ((ViewReHolder) viewHolder).film_one_recycle.addItemDecoration(new RecycleLeftStyle(20));
                adapter.notifyDataSetChanged();
                ((ViewReHolder) viewHolder).film_one_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onclick != null) {
                            onclick.list(1002);
                        }
                    }
                });
                adapter.getonclick(new FilmeRecycleAdapter.onclicked() {
                    @Override
                    public void movie(int id,int i) {
                        if (onclick != null) {
                            onclick.movie(id);
                        }
                    }
                });
            }
        } else if (type == 1003) {
            if (viewHolder instanceof ViewReHolder) {
                ((ViewReHolder) viewHolder).film_one_text.setText("即将上映");
                float x = ((ViewReHolder) viewHolder).film_one_img_ani.getTranslationX();
                ObjectAnimator animator = ObjectAnimator.ofFloat(((ViewReHolder) viewHolder).film_one_img_ani, "translationX",x,120,x);
                // 创建动画对象 & 设置动画
                // 表示的是:
                // 动画作用对象是mButton
                // 动画作用的对象的属性是X轴平移
                // 动画效果是:从当前位置平移到 x=1500再平移到初始位置
                animator.setDuration(1000);
                animator.setRepeatCount(-1);
                animator.setInterpolator(new DecelerateAccelerateInterpolator());
                // 设置插值器
                animator.start();
                ((ViewReHolder) viewHolder).film_one_recycle.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                List<FilmRecycleItemBean> list = new ArrayList<>();
                for (int j = 0; j < ToBlist.size(); j++) {
                    list.add(new FilmRecycleItemBean(ToBlist.get(j).getId(), ToBlist.get(j).getImageUrl(), ToBlist.get(j).getName()));
                }
                FilmeRecycleAdapter adapter = new FilmeRecycleAdapter(context, list);
                ((ViewReHolder) viewHolder).film_one_recycle.setAdapter(adapter);
                ((ViewReHolder) viewHolder).film_one_recycle.addItemDecoration(new RecycleLeft_DownStyle(20));
                adapter.notifyDataSetChanged();
                ((ViewReHolder) viewHolder).film_one_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onclick != null) {
                            onclick.list(1003);
                        }
                    }
                });
                adapter.getonclick(new FilmeRecycleAdapter.onclicked() {
                    @Override
                    public void movie(final int id,int i) {
                        if (onclick != null) {
                            onclick.movie(id);
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return typeBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        int type = typeBeanList.get(position).getType();
        if (type == 0) {
            return 1000;
        } else if (type == 1) {
            return 1001;
        } else if (type == 2) {
            return 1002;
        } else if (type == 3) {
            return 1003;
        }
        return super.getItemViewType(position);
    }


    private class ViewReHolder extends RecyclerView.ViewHolder {
        public TextView film_one_text;
        public ImageView film_one_img;
        public ImageView film_one_img_ani;
        public RecyclerView film_one_recycle;

        public ViewReHolder(@NonNull View itemView) {
            super(itemView);
            this.film_one_text = (TextView) itemView.findViewById(R.id.film_one_text);
            this.film_one_img = (ImageView) itemView.findViewById(R.id.film_one_img);
            this.film_one_img_ani = (ImageView) itemView.findViewById(R.id.film_one_img_ani);
            this.film_one_recycle = (RecyclerView) itemView.findViewById(R.id.film_one_recycle);
        }
    }


    private class ViewbannerHolder extends RecyclerView.ViewHolder {
        public RecyclerCoverFlow film_recycle_banner;
        public ProgressBar film_recycle_bar;
        public RelativeLayout relativeLayout;
        public LinearLayout linearLayout;
        public ViewbannerHolder(@NonNull View itemView) {
            super(itemView);
            this.film_recycle_banner = (RecyclerCoverFlow) itemView.findViewById(R.id.film_recycle_banner);
            this.film_recycle_bar = (ProgressBar) itemView.findViewById(R.id.film_recycle_bar);
            this.relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_prograss_bar);
            this.linearLayout = (LinearLayout) itemView.findViewById(R.id.rl_checked);
        }

    }

    public interface onclick {
        //        查看更多电影
        void list(int id);

        //        点击的是电影
        void movie(int id);
    }

    private onclick onclick;

    public void getonclcked(onclick onclick) {
        this.onclick = onclick;
    }
}
