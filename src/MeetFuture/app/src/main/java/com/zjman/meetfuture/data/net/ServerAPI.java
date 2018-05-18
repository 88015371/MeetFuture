package com.zjman.meetfuture.data.net;

import com.zjman.meetfuture.data.bean.HttpResult;
import com.zjman.meetfuture.data.body.GetVerifyCodeBody;
import com.zjman.meetfuture.pref.C;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;


/**
 * Created by zchu on 16-11-16.
 */

public interface ServerAPI {

    String BASE_URL = C.URL_SERVER_API;
    String UPLOAD_URL = C.UPLOAD_SERVER;


//    /**
//     * 登录
//     */
//
//    @POST("/signin")
//    Observable<HttpResult<LoginRd>> login(@Body LoginBody body);
//
//    /**
//     * 注册
//     */
//    @POST("/signup")
//    Observable<HttpResult> register(@Body RegisterBody body);
//
//    /**
//     * 微信登录
//     * @param weChatBody
//     * @return
//     */
//    @POST("/signin/wechat")
//    Observable<HttpResult<WeChatBean>> weChatLogin(@Body WeChatBody weChatBody);
//
//    /**
//     * 修改密码
//     */
//    @PUT("/password_reset")
//    Observable<HttpResult> updatePassword(@Body UpdatePasswordBody body);
//
//
//    /**
//     * 忘记密码
//     *//*
//    @PUT("/v1/forgotten/{mobile}")
//    Observable<HttpResult> forgottenPassword(@Path("mobile") String mobile,
//                                             @Body ForgottenPasswordBody body);
//
//    /**
//     * 修改用户信息
//     */
//    @PUT("/users/{id}")
//    Observable<HttpResult> updateUserInfo(@Path("id") long id,
//                                          @Body UpdateUserBody body);
//
//    /**
//     * 验证手机号是否注册-注册
//     */
//    @GET("/users/mobiles/{mobile}/valid/register")
//    Observable<HttpResult> verifyPhoneByRegister(@Path("mobile") String mobile);
//
//
//
//    /**
//     * 验证手机号是否注册-找回密码
//     */
//    @GET("/users/mobiles/{mobile}/valid/forgetpassword")
//    Observable<HttpResult> verifyPhoneByForgotten(@Path("mobile") String mobile);
//
   /**
     * 获取短信验证码-注册
    */
     @POST("/sms/users/register")
    Observable<HttpResult> getVerifyCodeByRegister(@Body GetVerifyCodeBody body);
//
//    /**
//     * 获取短信验证码-找回密码
//     */
//    @POST(" /sms/users/forgetpassword")
//    Observable<HttpResult> getVerifyCodeByUpdate(@Body MobileBody body);
//
//
//    /**
//     * 获取新闻列表
//     */
//
//    @GET("/news") //?page=1&per_page=10&order_key=deleted_at&order_value=desc
//    Observable<HttpResult<NewsListInfoResult>> getNewsList(@Query("page") int page, @Query("per_page") int perPage,
//                                                           @QueryMap Map<String, String> map);
//
//    /**
//     * 获取新闻详情
//     */
//
//    @GET("/news-detail/{code}")
//    Observable<HttpResult<NewsCommentNum>> getNewsCommentNum(@Path("code") String code);
//
//
//    /**
//     * 获取新闻评论列表
//     */
//    @GET("/news-comments")  //?page=1&per_page=10&resource_id={resource_id}&user_id=1
//    Observable<HttpResult<NewsCommentList>> getNewsCommentList(@Query("resource_id") long resourceId, @Query("page") int page, @Query("per_page") int perPage, @Query("per_page") long userId);
//
//    /**
//     * 创建新闻评论
//     */
//    @POST("/news/comments")
//    Observable<HttpResult> createNewsComment(@Body CreateReplyCommentBody body);
//
//    /**
//     * 创建回复新闻评论
//     */
//    @POST("/news/comments")
//    Observable<HttpResult> createNewsReplyComment(@Body CreateReplyCommentBody body);
//
//
//    /**
//     * 创建新闻评论
//     */
//    @DELETE("/news/users/{user_id}/comments/{id}")
//    Observable<HttpResult> delNewsComment(@Path("user_id") long user_id, @Path("id") long id);
//
//    /**
//     * 获取活动列表
//     */
//    @GET("/activities")
//    Observable<HttpResult<ActiveListInfo>> getActiveList(@Query("page") int page, @Query("per_page") int perPage);
//
//    //getActiveListDetails
//
//    /**
//     * 获取活动列表详情
//     * @param id
//     */
//    @GET("/activities/{id}")
//    Observable<HttpResult<ActiveDetailsInfo>> getActiveListDetails(@Path("id") long id);
//
//    /**
//     * 获取举报类目
//     * @return
//     */
//    @GET("/complaints/catalogs/{type}")
//    Observable<HttpResult<ReportBean>> getReportInfo(@Path("type") String type);
//
//    /**
//     * 提交举报信息
//     */
//    @POST("/complaints")
//    Observable<HttpResult> createReport(@Body ReportBody reportBody);
//
//    /**
//     * 获取赛区信息
//     * @param id
//     * @return
//     */
//    @GET("/activity-areas/{id}")
//    Observable<HttpResult<List<CompetitionAreaInfo>>> getCompetitionAreaInfo(@Path("id") long id);
//
//
//    /**
//     * 报名列表上传图片
//     * @param
//     */
//
//    @Multipart
//    @POST("/upload/picture")
//   Observable<HttpResult<String>> upLoadImg(@Part MultipartBody.Part file);
//
//
//    /**
//     * 报名列表上传视频
//     * @param
//     */
//
//    @Multipart
//    @POST("/upload/video")
//    Observable<HttpResult<UploadVideoInfo>> upLoadVideo(@Part MultipartBody.Part file);
//
//    @GET("/users-registrable/{userId}/activities/{activeId}")
//    Observable<HttpResult<RegistrationResult>> checkRegistrationState(@Path("userId") long userId, @Path("activeId") long activeId);
//
//
//    /**
//     * 微信支付
//     * @param
//     */
//    @POST("/activities/wechat/pay")
//    Observable<HttpResult<WxPayInfo>> getWxPay(@Body WxPayApplyBody wxPayBody);
//
//    /**
//     * alipay支付
//     * @param
//     */
//    @POST("/activities/ali/pay")
//    Observable<AliPayInfo> getAliPay(@Body WxPayApplyBody wxPayBody);
//
//    @GET("/activities-questions/{activeId}")
//    Observable<HttpResult<List<ActiveQuestionResult>>> getActivityQuestions(@Path("activeId") long activeId);
//
//    /**
//     * 获取个人详情
//     */
//    @GET("/users-info/{id}")
//    Observable<HttpResult<UserDetailsInfo>> getUserDetails(@Path("id") long id);
//
//   /**
//     *获取粉丝列表
//     */
//    @GET("/users-followed/{userId}")
//    Observable<HttpResult<FansListInfo>> getFansListInfo(@Path("userId") long userId, @Query("page") int page, @Query("per_page") int perPage);
//
//	/**
//     * 获取关注列表
//     */
//    @GET("/users-following/{userId}")
//    Observable<HttpResult<AttentionListInfo>> getAttentionListInfo(@Path("userId") long userId, @Query("page") int page, @Query("per_page") int perPage);
//
//
//    /**
//     * 提交活动参赛者资料
//     * */
//    @PUT("/activities/competitor/register")
//    Observable<HttpResult> submitApplyInfo(@Body ApplyActiveBody applyActiveBody);
//
//    /**
//     * 查询用户参与的活动
//     * */
//    @GET("/activities-attended/users/{userId}")
//    Observable<HttpResult<MyActiveInfo>> getAttendedActivity(@Path("userId") long userId, @Query("page") int page, @Query("per_page") int perPage);
//
//    /**
//     * 查询用户参与活动填写过的信息
//     * */
//    @GET("/activity/competitors/{userId}")
//    Observable<HttpResult<CompetitorInfoResult>> getCompetitorInfo(@Path("userId") long userId, @Query("activity_id") long activeId);
//
//    /**
//     * 查询活动跟进信息
//     * */
//    @GET("/users-activity/{userId}/activities/{activityId}")
//    Observable<HttpResult<ActivityStateResult>> getActivityState(@Path("userId") long userId, @Path("activityId") long activeId);
//
//    /**
//     * 关注
//     * */
//    @POST("/follow")
//    Observable<HttpResult> addAttention(@Body AttentionBody attentionBody);
//
//    /**
//     * 取消关注
//     * */
//    @PUT("/unfollow")
//    Observable<HttpResult> cancelAttention(@Body AttentionBody attentionBody);
//
//    /**
//     * 用户的会员信息
//     * */
//    @GET("/memberships/users/{userId}")
//    Observable<HttpResult<MemberResult>> getMemberInfo(@Path("userId") long userId);
//
//    /**
//     * 会员套餐
//     * */
//    @GET("/memberships/scheme")
//    Observable<HttpResult<List<MemberShipResult>>> getMemberShip();
//
//    /**
//     * 会员套餐支付
//     * */
//    @POST("/users/membership/pay")
//    Observable<HttpResult<WxPayInfo>> getWxPayMember(@Body WxPayMemberBody wxPayMemberBody);
//
//    @POST("/users/membership/ali/pay")
//    Observable<AliPayInfo> getAliPayMember(@Body WxPayMemberBody wxPayMemberBody);
//
//    /**
//     * 活动排行榜
//     * */
//    @GET("/activities-leaderboards/{activityId}")
//    Observable<HttpResult<RankResult>> getActivityRank(@Path("activityId") long activityId, @Query("area_id") long areaId, @Query("page") int page);
//
//    /**
//     * 投票
//     * */
//    @POST("/activities-leaderboard-votes")
//    Observable<HttpResult<VoteResult>> voteToCompetitor(@Body VoteBody voteBody);
//
//    /**
//     * 搜索活动选手
//     * */
//    @GET("/activities-leaderboard-search/{activityId}")
//    Observable<HttpResult<List<SearchPlayerInfo>>> getActivityPlayer(@Path("activityId") long activityId, @Query("q") String text);
//
//    /**
//     * 我的修改昵称
//     * */
//
//    @PUT("/users/{id}")
//    Observable<HttpResult> updatePersonal(@Path("id") long userId, @Body UpdatePersonalBody updatePersonalBody);
//
//    /**
//     * 验证手机是或被绑定
//     * */
//    @GET("/users/mobiles/{mobile}/valid/bind")
//    Observable<HttpResult> isBindUserPhone(@Path("mobile") String mobile);
//
//    @POST("/sms/users/bind")
//    Observable<HttpResult> bindUserSendSms(@Body MobileBody mobileBody);
//
//    @PUT("/users/{userId}/binding")
//    Observable<HttpResult> bindUserPhone(@Path("userId") long userId, @Body BindPhoneBody bindPhoneBody);
//
//    /**
//     * 个人的动态圈
//     * */
//    @GET("/moments-list/{targetId}")
//    Observable<HttpResult<DynamicResult>> getUserDynamic(@Path("targetId") long targetId, @Query("actor_id") long userId, @Query("page") int page, @Query("per_page") int perPage);
//
//    /**
//     * 个人及关注的人的动态圈
//     * */
//    @GET("/moments-index/{targetId}")
//    Observable<HttpResult<DynamicResult>> getAttentionDynamic(@Path("targetId") long targetId, @Query("page") int page, @Query("per_page") int perPage);
//
//    /**
//     * 获取他人的详情及与自己的关注关系
//     * */
//    @GET("/users-info/{targetId}")
//    Observable<HttpResult<UserDetailsInfo>> getTargetUserDetails(@Path("targetId") long targetId, @Query("user_id") long userId);
//
//    /**
//     * 点赞，取消点赞
//     * */
//    @POST("/tweet/like")
//    Observable<HttpResult> doLike(@Body LikeBody likeBody);
//
//    /**
//     * 系统设置密码
//     * */
//    @PUT("/password-update")
//    Observable<HttpResult> changePwd(@Body ChangePwdBody changePwdBody);
//
//    /**
//     * 获取我的视频
//     * */
//    @GET("/users-resources/{id}/timeline")
//    Observable<HttpResult<PersonalVideoInfo>> getPersonalVideo(@Path("id") long id);
//
//
//    /**
//     * 搜索视频(昵称)
//     * */
//    @GET("/resources-search")
//    Observable<HttpResult<SearchVideoInfo>> getSearchNameVideo(@Query("nick_name") String name);
//
//    /**
//     * 搜索视频(title)
//     * */
//    @GET("/resources-search")
//    Observable<HttpResult<SearchVideoInfo>> getSearchTitleVideo(@Query("title") String title, @Query("page") int page, @Query("per_page") int perPage);
//
//    /**
//     *  视频详情
//     * */
//    @GET("/resources/{videoId}")
//    Observable<HttpResult<VideoDetailsInfo>> getVideoDetails(@Path("videoId") long videoId);
//    /**
//     * 上传文件
//     */
///*    @Multipart
//    @POST("/upload/picture")
//    Observable<HttpResult<List<UploadRd>>> upLoad(@Body UploadBody body);*/
//
//
//  /*
//     // 友盟推送
//
//    @DELETE("/notifications/upload")
//    Observable<HttpResult> uPush(@Body UPushBody uPushBody);*//*
//*/
//    /**
//     * 首页Banner
//     */
//    @GET("/banners/index")
//    Observable<HttpResult<List<BannerInfo>>> getBannerInfo();
//
//    /**
//     * 动态圈评论
//     */
//    @GET("/moments-comments")
//    Observable<HttpResult<NewsCommentList>> getDynamicCommentList(@Query("resource_id") long resourceId, @Query("page") int page, @Query("per_page") int perPage, @Query("user_id") long userId);
//
//    /**
//     * 创建/回复动态圈评论
//     */
//    @POST("/moments/comments")
//    Observable<HttpResult> createReplyDynamicComment(@Body CreateReplyCommentBody createReplyCommentBody);
//
//    /**
//     * 删除动态圈评论
//     */
//    @DELETE("/moments/users/{userId}/comments/{id}")
//    Observable<HttpResult> deleteDynamicComment(@Path("userId") long userId, @Path("id") int id);
//
//    /**
//     * 动态圈评论
//     */
//    @GET("/moments/{id}")
//    Observable<HttpResult<DynamicInfo>> getDynamicInfo(@Path("id") long id, @Query("actor_id") long userId);
//
//    /**
//     * 删除自己的动态圈
//     */
//    @DELETE("/moments-delete/{id}")
//    Observable<HttpResult> deleteMyDynamic(@Path("id") long id);
//
//    /**
//     * 收藏动态圈
//     */
//    @POST("/favorite")
//    Observable<HttpResult> doCollect(@Body CollectBody collectBody);
//
//    /**
//     * 取消收藏动态圈
//     */
//    @DELETE("/favorite")
//    Observable<HttpResult> cancelCollect(@Query("resource_type") int resourceType,
//                                         @Query("resource_id") long resourceId, @Query("user_id") long userId);
//
//    /**
//     * 创建视频评论
//     */
//    @POST("/videos/comments")
//    Observable<HttpResult> createVideoComment(@Body CreateReplyCommentBody createReplyCommentBody);
//
//    /**
//     * 视频评论
//     */
//    @GET("/videos-comments")
//    Observable<HttpResult<VideoCommentInfo>> getVideoComment(@Query("resource_id") long resourceId, @Query("page") int page, @Query("per_page") int perPage, @Query("user_id") long userId);
//
//
//    /**
//     * 删除视频评论
//     */
//    @DELETE("/moments/users/{userId}/comments/{commentId}")
//    Observable<HttpResult> delVideoComment(@Path("userId") long userId, @Path("commentId") long commentId);
//
//   /**
//    *   发文字动态圈
//    */
//    @FormUrlEncoded
//    @POST("/moments")
//    Observable<HttpResult> publishTextDynamic(@Field("user_id") long userId, @Field("type") int type, @Field("desc") String content);
//
//    /**
//     * 发文字,图片动态圈
//     */
//    @FormUrlEncoded
//    @POST("/moments_v2")
//    Observable<HttpResult> publishImageTextDynamic(@Field("images_url") String urls, @Field("user_id") long userId,
//                                                   @Field("type") int type, @Field("desc") String content);
//    /**
//     * 发文字,图片动态圈
//     */
//    @Multipart
//    @POST("/moments")
//    Observable<HttpResult> publishImageTextDynamic(@PartMap Map<String, RequestBody> paramsMap, @Part("user_id") RequestBody userIdBody,
//                                                   @Part("type") RequestBody typeBody, @Part("desc") RequestBody contentBody);
//
//    /**
//     * 发视频动态圈
//     */
//    @Multipart
//    @POST("/moments")
//    Observable<HttpResult> publishVideoDynamic(@Part MultipartBody.Part video, @Part MultipartBody.Part image, @Part("duration") RequestBody videoDurationBody,
//                                               @Part("user_id") RequestBody userIdBody, @Part("type") RequestBody typeBody, @Part("desc") RequestBody contentBody);
//     /**
//     * 获取萌娃列表信息
//     */
//    @GET("/moes")
//    Observable<HttpResult<MengStarInfo>> getMengStarInfo(@Query("page") int page, @Query("per_page") int perPage);
//
//    /**
//     * 视频--精选推荐
//     */
//    @GET("/resources-selection")
//    Observable<HttpResult<VideoListInfo>> getRecommendVideoInfo(@Query("page") int page, @Query("per_page") int perPage);
//
//    /**
//     * 视频--本日最新
//     */
//    @GET("/resources-latest")
//    Observable<HttpResult<VideoListInfo>> getNewestVideoInfo(@Query("page") int page, @Query("per_page") int perPage);
//
//    /**
//     * 视频--官方独播
//     */
//    @GET("/resources-official")
//    Observable<HttpResult<VideoListInfo>> getOfficialVideoInfo(@Query("page") int page, @Query("per_page") int perPage);
//
//    /**
//     * 是否可以发视频
//     */
//    @GET("/moments-validate/{userId}")
//    Observable<IsMemberResult> checkIsMember(@Path("userId") long userId);
//
//    /**
//     * 选手投票页
//     */
//    @GET("/competitors-page/{playerId}")
//    Observable<HttpResult<PlayerResult>> getPlayerInfo(@Path("playerId") long playerId, @Query("activity_id") long activityId, @Query("actor_id") long userId);
//
//
//    /**
//     * 评论通知
//     */
//    @GET("/users-comment/{userId}/notifications")//?page=1&per_page=20&state=unread
//    Observable<HttpResult<CommentNotifictionInfo>> getCommentNotifition(@Path("userId") long userId, @Query("page") int page, @Query("per_page") int perPage);
//
//
//    /**
//     * 系统通知
//     */
//    @GET("/users-system/{userId}/notifications")//?page=1&per_page=20&state=unread
//    Observable<HttpResult<SystemNotifictionInfo>> getSystemNotifition(@Path("userId") long userId, @Query("page") int page, @Query("per_page") int perPage);
//
//    /**
//     *   发分享动态圈
//     */
//    @FormUrlEncoded
//    @POST("/moments")
//    Observable<HttpResult> publishShareDynamic(@Field("user_id") long userId, @Field("type") int type, @Field("share_type") int shareType,
//                                               @Field("share_id") long shareId, @Field("share_user_id") long shareUserId, @Field("desc") String content);
//    /**
//     * 我的-----收藏活动选手
//     */
//    @GET("/favorite/{userId}")
//    Observable<HttpResult<CollectionPlayerInfo>> getCollectionPlayerInfo(@Path("userId") long userId, @Query("resource_type") int resource_type, @Query("page") int page, @Query("per_page") int perPage);
//
//    /**
//     * 获取晋级证书
//     */
//    @GET("/activities-certificate/{activityId}/users/{userId}")
//    Observable<HttpResult<CertificationResult>> getPromotionCertification(@Path("activityId") long activityId, @Path("userId") long userId);
//
//    /**
//     * 微信支付晋级费用
//     */
//    @POST("/activities/promotion/wechat/pay")
//    Observable<HttpResult<WxPayInfo>> getWxPayPromotion(@Body WxPayPromotionBody wxPayPromotionBody);
//
//    /**
//     * ali支付晋级费用
//     */
//    @POST("/activities/promotion/ali/pay")
//    Observable<AliPayInfo> getAliPayPromotion(@Body WxPayPromotionBody wxPayPromotionBody);
//
//    /**
//     * 删除收藏的活动选手
//     */
//    @DELETE("/favorite")
//    Observable<HttpResult> delFavoritePlayer(@Query("user_id") long user_id, @Query("resource_type") int resource_type, @Query("resource_id") long resource_id);
//
//    /**
//     * 我的--收藏用户动态
//     */
//    @GET("/favorite/{userId}")
//    Observable<HttpResult<DynamicResult>> getCollectionDynamicInfo(@Path("userId") long userId, @Query("resource_type") int resource_type, @Query("page") int page, @Query("per_page") int perPage);
//
//    /**
//     * 提交活动参赛者资料
//     * */
//    @Multipart
//    @POST("/activities/competitor/update_v1")
//    Observable<HttpResult> submitApplyInfo(@Part MultipartBody.Part video, @Part MultipartBody.Part avatarImage, @Part MultipartBody.Part videoImage, @Part MultipartBody.Part activityId, @Part MultipartBody.Part userId,
//                                           @Part MultipartBody.Part gender, @Part MultipartBody.Part name, @Part MultipartBody.Part height, @Part MultipartBody.Part birthDate, @Part MultipartBody.Part household, @Part MultipartBody.Part nation,
//                                           @Part MultipartBody.Part weight, @Part MultipartBody.Part idCard, @Part MultipartBody.Part schoolName, @Part MultipartBody.Part address, @Part MultipartBody.Part parentName, @Part MultipartBody.Part mobile,
//                                           @Part MultipartBody.Part email, @Part MultipartBody.Part code, @Part MultipartBody.Part videoTitle, @Part MultipartBody.Part videoDuration, @Part MultipartBody.Part questions, @Part MultipartBody.Part careers);
//
//
//
//    /**
//     *  更新设备
//     */
//    @PUT("/devices")
//    Observable<HttpResult> updateDeviceToken(@Body UpdateDeviceBody updateDeviceBody);
//
//    /**
//     *  未读消息
//     */
//    @GET("/users-unread/{userId}")
//    Observable<HttpResult<UnReadMsgInfo>> unReadMsg(@Path("userId") long userId);
//
//
//    /**
//     * 推荐人
//     * */
//    @GET("/moes")
//    Observable<HttpResult<RecommendUserResult>> getRecommendUser(@Query("user_id") long userId);
//
//
//    /**
//     * 搜索用户
//     * */
//    @GET("/users-search") ///users-search?nick_name=
//    Observable<HttpResult<SearchUserInfo>> getSearchUser(@Query("nick_name") String nick_name, @Query("page") int page, @Query("per_page") int perPage);
//
//    @Multipart
//    @POST("/activities/competitor/upload")
//    Observable<HttpResult<CertificationUrl>> uploadCertification(@Part MultipartBody.Part image,
//                                                                 @Part MultipartBody.Part activityIdPart,
//                                                                 @Part MultipartBody.Part userIdPart);
//
//    @GET("/version-last")
//    Observable<HttpResult<VersionState>> checkAppVersion(@Query("device") String device, @Query("version") String appVersionName);
//
//    @Multipart
//    @POST("/upload/video")
//    Observable<HttpResult<UploadVideoInfo>> upLoadVideo(@Part MultipartBody.Part video, @Part MultipartBody.Part image);
//
//    /**
//     * 发视频动态圈
//     */
//    @FormUrlEncoded
//    @POST("/moments_v2")
//    Observable<HttpResult> publishVideoDynamic(@Field("video_url") String videoUrl, @Field("video_image") String videoImageUrl,
//                                               @Field("duration") String videoDuration, @Field("user_id") long userId,
//                                               @Field("type") int type, @Field("desc") String content);
//
//    @GET("/qiniu/token")
//    Observable<HttpResult<QiniuToken>> getQiniuToken();
//
//    /**
//     * 清除后台通知
//     */
//    @PUT("/signout/{userId}")
//    Observable<HttpResult> clearNotifiction(@Path("userId") long userId);
//
//    /**
//     * 获取随机视频
//     */
//    @GET("/resources-recommended")
//    Observable<HttpResult<List<VideoListInfo.DataBean>>> getRecommendedVideo(@Query("user_id") long userId);

}

