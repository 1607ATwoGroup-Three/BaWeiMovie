package com.bw.movie.bean;

/**
 * <p>文件描述：电影轮播图 用到的ID name imageurl<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/26 15:00<p>
 * <p>更改时间：2019/1/26 15:00<p>
 * <p>版本号：1<p>
 */
public class FilmRecycleItemBean {
    private int id;
    private String imageUrl;
    private String name;

    public FilmRecycleItemBean(int id, String imageUrl, String name) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
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
}
