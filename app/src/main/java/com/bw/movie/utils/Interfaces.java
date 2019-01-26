package com.bw.movie.utils;

/**
 * <p>文件描述：接口文档<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/22 11:39<p>
 * <p>更改时间：2019/1/22 11:39<p>
 * <p>版本号：1<p>
 */
public class Interfaces {
    //    外网环境
//    mobile.bwstudent.com
//    内网环境
//    172.17.8.100
//    总接口
    public static final String Base_Url = "http://mobile.bwstudent.com/";
    //    注册 post
    public static final String Register = "movieApi/user/v1/registerUser";
    //    登陆 post
    public static final String Land = "movieApi/user/v1/login";
    //    查询会员首页信息  我的信息  get  得到当前用户关注的影院  电影
    public static final String MyMessage = "movieApi/user/v1/login";
    //    修改信息 post
    public static final String ModifyInformation = "movieApi/user/v1/verify/modifyUserInfo";
    //    上传头像 post
    public static final String UploadHead = "movieApi/user/v1/verify/uploadHeadPic";
    //    修改密码 post
    public static final String ChangePassword = "movieApi/user/v1/verify/modifyUserPwd";
    //根据用户ID 查询用户信息 get
    public static final String QueryUserInformation = "movieApi/user/v1/verify/getUserInfoByUserId";
    //    用户签到 get
    public static final String UserCheckIn = "movieApi/user/v1/verify/userSignIn";
    //    用户购票记录查询列表 get
    public static final String QueryListOfUserPurchaseRecords = "movieApi/user/v1/verify/findUserBuyTicketRecordList";
    //    微信登陆 post
    public static final String WeChatlanding = "movieApi/user/v1/weChatBindingLogin";
    //    绑定微信账号 post
    public static final String IndingWechatAccount = "movieApi/user/v1/verify/bindWeChat";
    //    是否绑定微信账号 get
    public static final String WhetherToBindWeichatAccount = "movieApi/user/v1/verify/whetherToBindWeChat";
    //    查询热门电影列表 get
    public static final String QueryTheListOfPopularMovies = "movieApi/movie/v1/findHotMovieList";
    //    查询正在上映电影列表 get
    public static final String QueryTheListOfMoviesBeingShown = "movieApi/movie/v1/findReleaseMovieList";
    //    查询即将上映电影列表 GET
    public static final String QueryTheListOfUpcomingMovies = "movieApi/movie/v1/findComingSoonMovieList";
    //    根据电影ID查询电影信息 get
    public static final String SearchFilmInformationBasedOnFilmID = "movieApi/movie/v1/findMoviesById";
    //    查看电影详情 get
    public static final String CheckOutTheDetailsOfTheMovie = "movieApi/movie/v1/findMoviesDetail";
    //    查询附近的影院 get
    public static final String SearchForNearbyCinemas = "movieApi/cinema/v1/findNearbyCinemas";
    //    查询推荐影院 get
    public static final String SearchForRecommendedCinema = "movieApi/cinema/v1/findRecommendCinemas";
    //    查询热门电影 get
    public static final String SearchForHotMovies = "movieApi/movie/v1/findHotMovieList";
    //    修改密码 get
    public static final String UpdataPwd = "movieApi/user1erify/modifyUserPwd";
    //    个人信息 get
    public static final String UserMessage = "movieApi/user1erify/getUserInfoByUserId";
}
