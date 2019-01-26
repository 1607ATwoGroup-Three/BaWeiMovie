package com.bw.movie.utils;

import org.w3c.dom.Comment;

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
    public static final String MyMessage = "movieApi/user/v1/verify/findUserHomeInfo";
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

//查询用户 关注的影片列表 get

    public static final String SearchForVideosOfInterestToUsers = "movieApi/movie/v1/verify/findMoviePageList";
    //    关注电影 get
    public static final String FocusOnMovies = "movieApi/movie/v1/verify/followMovie";
    //    取消关注电影 get
    public static final String CancelFilmConcern = "movieApi/movie/v1/verify/cancelFollowMovie";
    //    查询影片评论 get
    public static final String SearchForFilmReviews = "movieApi/movie/v1/findAllMovieComment";
    //添加用户对影片的评论 post
    public static final String AddUserCommentsOnTheFilm = "movieApi/movie/v1/verify/movieComment";
    //查询影片评论回复 get
    public static final String InquiryForFilmCommentReply = "movieApi/movie/v1/findCommentReply";

    //    添加用户对评论的回复 post
    public static final String AddUserResponsesToComments = "movieApi/movie/v1/verify/commentReply";
    //    电影评论点赞 post
    public static final String CommentsOnTheFilm = "movieApi/movie/v1/verify/movieCommentGreat";
    //    根据影院ID查询该影院当前排期的电影列表 get
    public static final String QueryCurrentMoviesBasedOnTheaterID = "movieApi/movie/v1/findMovieListByCinemaId";
    //    根据电影ID和影院ID查询电影排期列表 get
    public static final String SearchMovieSchedulesBasedOnMovieIDAndCinemaID = "movieApi/movie/v1/findMovieScheduleList";
    //    根据电影ID查询当前排片该电影的影院列表 get
    public static final String QuerAyTheListOfCinemasCurrentlyInProductionAccordingToTheMovieID = "movieApi/movie/v1/findCinemasListByMovieId";
    //    购票查询 post
    public static final String TicketEnquiry = "movieApi/movie/v1/verify/buyMovieTicket";
    //    支付 post
    public static final String Payment = "movieApi/movie/v1/verify/pay";
    //    根据影院ID查询该影院下即将上映的电影列表 get
    public static final String QueryTheListOfUpcomingMoviesInTheCinemaBasedOnTheCinemaID = "movieApi/movie/v1/findSoonMovieByCinemaId";
    //    查询电影信息明细 GET
    public static final String InquiryFilmInformationDetails = "movieApi/cinema/v1/findCinemaInfo";
    //    根据电影名称模糊查询电影院 GET
    public static final String InquireTheCinemaVaguelyAccordingToTheNameOfTheFilm = "movieApi/cinema/v1/findAllCinemas";
    //    .查询用户关注的影院信息get
    public static final String SearchForCinemaInFormationThatUsersAreInterestedIn = "movieApi/cinema/v1/verify/findCinemaPageList";
    //    关注影院 get
    public static final String CinemaAttention = "movieApi/cinema/v1/verify/followCinema";
    //取消关注影院 GET
    public static final String CancelAttentionToCinema = "movieApi/cinema/v1/verify/cancelFollowCinema";
    //    查询影院用户评论列表 get
    public static final String QueryTheListOfCinemaUserReviews = "movieApi/cinema/v1/findAllCinemaComment";
    //    影院评论 post
    public static final String CinemaReviews = "movieApi/cinema/v1/verify/cinemaComment";
    //    影院评论点赞 post
    public static final String CinemaComments = "movieApi/cinema/v1/verify/cinemaCommentGreat";
    //    意见反馈 post
    public static final String Feedback = "movieApi/tool/v1/verify/recordFeedBack";
    //    查询新版本 get
    public static final String QueryForNewVersion = "movieApi/tool/v1/findNewVersion";
    //    查询系统消息列表 get
    public static final String QuerySystemMessageList = "movieApi/tool/v1/verify/findAllSysMsgList";
    //    系统消息读取状态修改 get
    public static final String SystemMessageReadStatusModification = "movieApi/tool/v1/verify/changeSysMsgStatus";
    //    查询用户当前未读消息数量 get
    public static final String QueryTheNumberOfUnreadMessagesTheUserCurrentlyHas = "movieApi/tool/v1/verify/findUnreadMessageCount";
    //    上传消息推送使用的token post
    public static final String TokenForUploadingMessagePush = "movieApi/tool/v1/verify/uploadPushToken";
    //    微信分享前置接口，获取分享所需参数 get
    public static final String WeChatShare = "movieApi/tool/v1/wxShare";

}
