package com.bw.movie.bean;

import java.util.List;

/**
 * <p>文件描述：根据电影ID和影院ID查询电影排期列表<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/2/13 20:30<p>
 * <p>更改时间：2019/2/13 20:30<p>
 * <p>版本号：1<p>
 */
public class MovieIdCinemaId {

    /**
     * result : [{"beginTime":"17:05","duration":"118分钟","endTime":"19:03","id":3,"price":0.13,"screeningHall":"3厅","seatsTotal":180,"seatsUseCount":10,"status":2},{"beginTime":"19:20","duration":"118分钟","endTime":"21:18","id":136,"price":0.13,"screeningHall":"2号厅","seatsTotal":150,"seatsUseCount":50,"status":2},{"beginTime":"20:30","duration":"118分钟","endTime":"22:28","id":137,"price":0.13,"screeningHall":"1号厅","seatsTotal":66,"seatsUseCount":15,"status":1},{"beginTime":"11:50","duration":"118分钟","endTime":"14:02","id":138,"price":0.13,"screeningHall":"4厅","seatsTotal":21,"seatsUseCount":19,"status":2}]
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
         * beginTime : 17:05
         * duration : 118分钟
         * endTime : 19:03
         * id : 3
         * price : 0.13
         * screeningHall : 3厅
         * seatsTotal : 180
         * seatsUseCount : 10
         * status : 2
         */

        private String beginTime;
        private String duration;
        private String endTime;
        private int id;
        private double price;
        private String screeningHall;
        private int seatsTotal;
        private int seatsUseCount;
        private int status;

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getScreeningHall() {
            return screeningHall;
        }

        public void setScreeningHall(String screeningHall) {
            this.screeningHall = screeningHall;
        }

        public int getSeatsTotal() {
            return seatsTotal;
        }

        public void setSeatsTotal(int seatsTotal) {
            this.seatsTotal = seatsTotal;
        }

        public int getSeatsUseCount() {
            return seatsUseCount;
        }

        public void setSeatsUseCount(int seatsUseCount) {
            this.seatsUseCount = seatsUseCount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
