package com.bw.movie.bean;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/29 13:58<p>
 * <p>更改时间：2019/1/29 13:58<p>
 * <p>版本号：1<p>
 */
public class PostImageData {

    /**
     * headPath : http://172.17.8.100/images/head_pic/20180720175142.png
     * message : 上传成功
     * status : 0000
     */

    private String headPath;
    private String message;
    private String status;

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
