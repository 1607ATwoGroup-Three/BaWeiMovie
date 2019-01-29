package com.bw.movie.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/29 15:00<p>
 * <p>更改时间：2019/1/29 15:00<p>
 * <p>版本号：1<p>
 */
public class MovieFilmData {

    /**
     * result : [{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":1962,"commentTime":1547970345000,"commentUserId":4046,"commentUserName":"dgjrwkpgw","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":1961,"commentTime":1547970332000,"commentUserId":4046,"commentUserName":"dgjrwkpgw","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":1960,"commentTime":1547970313000,"commentUserId":4046,"commentUserName":"dgjrwkpgw","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":1959,"commentTime":1547970312000,"commentUserId":4050,"commentUserName":"mybbb","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":1957,"commentTime":1547970266000,"commentUserId":4046,"commentUserName":"dgjrwkpgw","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":1956,"commentTime":1547970265000,"commentUserId":4050,"commentUserName":"mybbb","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":1955,"commentTime":1547969787000,"commentUserId":4046,"commentUserName":"dgjrwkpgw","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":1954,"commentTime":1547969786000,"commentUserId":4050,"commentUserName":"mybbb","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":1953,"commentTime":1547969556000,"commentUserId":4046,"commentUserName":"dgjrwkpgw","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":1952,"commentTime":1547969529000,"commentUserId":4046,"commentUserName":"dgjrwkpgw","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commentContent : 电影好看
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/bwjy.jpg
         * commentId : 1962
         * commentTime : 1547970345000
         * commentUserId : 4046
         * commentUserName : dgjrwkpgw
         * greatNum : 0
         * hotComment : 0
         * isGreat : 0
         * replyNum : 0
         */

        private String commentContent;
        private String commentHeadPic;
        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String commentUserName;
        private int greatNum;
        private int hotComment;
        private int isGreat;
        private int replyNum;

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public int getHotComment() {
            return hotComment;
        }

        public void setHotComment(int hotComment) {
            this.hotComment = hotComment;
        }

        public int getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(int isGreat) {
            this.isGreat = isGreat;
        }

        public int getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(int replyNum) {
            this.replyNum = replyNum;
        }
    }
}
