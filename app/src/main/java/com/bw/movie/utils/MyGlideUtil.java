package com.bw.movie.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/25 18:37<p>
 * <p>更改时间：2019/1/25 18:37<p>
 * <p>版本号：1<p>
 */
public enum MyGlideUtil {
    INSTANCE;

    MyGlideUtil() {
    }

    /**
     * 默认加载图片方法
     *
     * @param mContext
     * @param mImageUrl
     * @param mImageView
     */
    public static void setDefaultImage(Context mContext, String mImageUrl, ImageView mImageView) {

        Glide.with (mContext)
                .load (mImageUrl)
                .apply (RequestOptions.errorOf (R.mipmap.error))        //加载失败 默认的加载图片
                .apply (RequestOptions.placeholderOf (R.mipmap.haha_1)) //加载中 默认的加载图片
                .into (mImageView);
    }

    public static void setDefaultImage(Context mContext, Uri mImageUrl, ImageView mImageView) {

        Glide.with (mContext)
                .load (mImageUrl)
                .apply (RequestOptions.errorOf (R.mipmap.error))        //加载失败 默认的加载图片
                .apply (RequestOptions.placeholderOf (R.mipmap.haha_1)) //加载中 默认的加载图片
                .into (mImageView);
    }

    /**
     * 默认加载圆形图
     * @param mContext
     * @param mImageUrl
     * @param mImageView
     */
    public static void setCircleImage(Context mContext, String mImageUrl, ImageView mImageView) {

        Glide.with (mContext)
                .load (mImageUrl)
                .apply (RequestOptions.circleCropTransform ())
                .apply (RequestOptions.errorOf (R.mipmap.error))        //加载失败 默认的加载图片
                .apply (RequestOptions.placeholderOf (R.mipmap.haha_1)) //加载中 默认的加载图片
                .into (mImageView);
    }

    public static void setCircleImage(Context mContext, Uri mImageUrl, ImageView mImageView) {

        Glide.with (mContext)
                .load (mImageUrl)
                .apply (RequestOptions.circleCropTransform ())
                .apply (RequestOptions.errorOf (R.mipmap.error))        //加载失败 默认的加载图片
                .apply (RequestOptions.placeholderOf (R.mipmap.haha_1)) //加载中 默认的加载图片
                .into (mImageView);
    }

    /**
     * 强制加载静态图片方法
     *
     * @param mContext
     * @param mImageUrl
     * @param mImageView
     */
    public static void setStaticImage(Context mContext, String mImageUrl, ImageView mImageView) {

        Glide.with (mContext)
                .load (mImageUrl)
                .apply (RequestOptions.errorOf (R.mipmap.error))        //加载失败 默认的加载图片
                .apply (RequestOptions.placeholderOf (R.mipmap.haha_1)) //加载中 默认的加载图片
                .apply (RequestOptions.noAnimation ())//强制加载图片格式为静态图片   默认情况下都可以加载
                .into (mImageView);
    }

    public static void setStaticImage(Context mContext, Uri mImageUrl, ImageView mImageView) {

        Glide.with (mContext)
                .load (mImageUrl)
                .apply (RequestOptions.errorOf (R.mipmap.error))        //加载失败 默认的加载图片
                .apply (RequestOptions.placeholderOf (R.mipmap.haha_1)) //加载中 默认的加载图片
                .apply (RequestOptions.noAnimation ())//强制加载图片格式为静态图片   默认情况下都可以加载
                .into (mImageView);
    }

    /**
     * 强制加载动态图片方法
     *
     * @param mContext
     * @param mImageUrl
     * @param mImageView
     */
    public void setGifImage(Context mContext, String mImageUrl, ImageView mImageView) {

        Glide.with (mContext)
                .asGif () //强制加载动态图片
                .load (mImageUrl)
                .apply (RequestOptions.errorOf (R.mipmap.error))        //加载失败 默认的加载图片
                .apply (RequestOptions.placeholderOf (R.mipmap.haha_1)) //加载中 默认的加载图片
                .into (mImageView);
    }

    public void setGifImage(Context mContext, Uri mImageUrl, ImageView mImageView) {

        Glide.with (mContext)
                .asGif () //强制加载动态图片
                .load (mImageUrl)
                .apply (RequestOptions.errorOf (R.mipmap.error))        //加载失败 默认的加载图片
                .apply (RequestOptions.placeholderOf (R.mipmap.haha_1)) //加载中 默认的加载图片
                .into (mImageView);
    }
}
