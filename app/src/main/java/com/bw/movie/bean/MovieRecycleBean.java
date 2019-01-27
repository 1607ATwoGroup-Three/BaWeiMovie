package com.bw.movie.bean;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/27 14:03<p>
 * <p>更改时间：2019/1/27 14:03<p>
 * <p>版本号：1<p>
 */
public class MovieRecycleBean {

    private int followMovie;
    private int id;
    private String imageUrl;
    private String name;
    private String summary;

    public MovieRecycleBean(int followMovie, int id, String imageUrl, String name, String summary) {
        this.followMovie = followMovie;
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.summary = summary;
    }

    public int getFollowMovie() {
        return followMovie;
    }

    public void setFollowMovie(int followMovie) {
        this.followMovie = followMovie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
