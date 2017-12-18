package com.zzti.youbang.net.api;


import com.zzti.youbang.bean.CommentBean;
import com.zzti.youbang.bean.CommentManageBean;
import com.zzti.youbang.bean.LoginBean;
import com.zzti.youbang.bean.QuestionBean;
import com.zzti.youbang.bean.QuestionManageBean;
import com.zzti.youbang.bean.VersionBean;
import com.zzti.youbang.bean.WelfareBean;
import com.zzti.youbang.net.BaseResponse;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author fengyongge
 * @Description
 * @date 2017/7/19 0019 上午 11:23
 */
public interface JavaApi {


    /**
     * 注册
     * @param userName
     * @param pwd
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("UserService/users/rigister")
    Observable<BaseResponse<LoginBean>> rigister(@Field("mobile") String userName,
                                                 @Field("password") String pwd, @Field("code") String code);

    /**
     * 登陆
     * @param userName
     * @param pwd
     * @return
     */
    @FormUrlEncoded
    @POST("UserService/users/login")
    Observable<BaseResponse<LoginBean>> login(@Field("mobile") String userName,
                                                 @Field("password") String pwd);


    /**
     * 获取个人信息
     * @param id
     * @return
     */
    @GET("UserService/users/infor/id/{id}")
    Observable<BaseResponse<LoginBean>> getInfor(@Path("id") String id);


    /**
     * 修改登录密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @FormUrlEncoded
    @PUT("UserService/users/password/id/{id}")
    Observable<BaseResponse<LoginBean>> updatePassword(@Path("id") String id,@Field("oldPassword")String oldPassword,
                                                 @Field("newPassword")String newPassword);


    /**
     * 发布问题
     * @param user_id
     * @param title
     * @param content
     * @return
     */
    @FormUrlEncoded
    @POST("QuestionService/question/user_id/{user_id}")
    Observable<BaseResponse<LoginBean>> publishedQuestion(@Path("user_id") String user_id,
                                                          @Field("title")String title,@Field("content")String content);





    /**
     * 获取所有问题
     * @return
     */
    @GET("QuestionService/question")
    Observable<BaseResponse<QuestionManageBean>> getQuestion();


    /**
     * 获取问题内容详情
     * @param question_id
     * @return
     */
    @GET("QuestionService/question/question_id/{question_id}")
    Observable<BaseResponse<QuestionBean>> getQuestionDetail(@Path("question_id")String question_id);


    /**
     * 评论问题
     * @param user_id
     * @param content
     * @param question_id
     * @return
     */
    @FormUrlEncoded
    @POST("CommentService/comment/user_id/{user_id}")
    Observable<BaseResponse> comment(@Path("user_id") String user_id,@Field("content")String content,@Field("question_id")String question_id);


    /**
     * 问题的评论列表
     * @return
     */
    @GET("CommentService/comment/question_id/{question_id}")
    Observable<BaseResponse<CommentManageBean>> getComments(@Path("question_id")String question_id);


    /**
     * 获取更新版本
     * @return
     */
    @GET("appVersionService/androidVersion")
    Observable<BaseResponse<VersionBean>> getVersion();


    /**
     * 获取福利
     * @return
     */
    @GET("data/福利/300/1")
    Observable<BaseResponse> getWelfare();



}
