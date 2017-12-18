package com.test.utils;



/**
 * @author
 */
public class ClientConstant {


    /**************************全局返回码********************************/

    /**********************************************************/
    //数据返回成功 begin 服务器成功返回用户请求数据
    public static final String ERROR_0_STATUS = "200";
    public static final String ERROR_0_STATUSNAME = "服务器成功返回用户请求数据";
    //数据返回成功 over
    /**********************************************************/
    /**********************************************************/
    //数据返回成功 begin
    public static final String ERROR_1_STATUS = "404";
    public static final String ERROR_1_STATUSNAME = "请求的数据不存在";
    //数据返回成功 over
    /**********************************************************/
    /**********************************************************/
    //数据返回成功 begin
    public static final String ERROR_401_STATUS = "401";
    public static final String ERROR_401_STATUS_MSG = "授权验证失败";
    //数据返回成功 over
    /**********************************************************/

    /**********************************************************/
    //Filter Error Begin
    public static final String PERMISSION_ERROR_900001_STATUS = "900001";
    public static final String PERMISSION_ERROR_900001_STATUS_MSG = "非法的请求类型";
    public static final String PERMISSION_ERROR_900002_STATUS = "900002";
    public static final String PERMISSION_ERROR_900002_STATUS_MSG = "没有检测到有效的商户信息";
    public static final String PERMISSION_ERROR_900003_STATUS = "900003";
    public static final String PERMISSION_ERROR_900003_STATUS_MSG = "没有检测到有效的参数";
    public static final String PERMISSION_ERROR_900004_STATUS = "900004";
    public static final String PERMISSION_ERROR_900004_STATUS_MSG = "解析后的参数列表为空";
    public static final String PERMISSION_ERROR_900005_STATUS = "900005";
    public static final String PERMISSION_ERROR_900005_STATUS_MSG = "无效的公匙参数";
    public static final String PERMISSION_ERROR_900006_STATUS = "900006";
    public static final String PERMISSION_ERROR_900006_STATUS_MSG = "无效的签名参数";
    public static final String PERMISSION_ERROR_900007_STATUS = "900007";
    public static final String PERMISSION_ERROR_900007_STATUS_MSG = "无效的时间戳参数";
    public static final String PERMISSION_ERROR_900008_STATUS = "900008";
    public static final String PERMISSION_ERROR_900008_STATUS_MSG = "必须参数不存在或者不完整";
    public static final String PERMISSION_ERROR_900009_STATUS = "900009";
    public static final String PERMISSION_ERROR_900009_STATUS_MSG = "签名验证失败";
    //Filter Error Over
    /**********************************************************/

    /***************************增删改查成功返回码*******************************/
    public static final String QUERY_SUCCESS = "200";
    public static final String NEWUPDATE_SUCCESS = "201";
    public static final String DELETE_SUCCESS = "204";


    /***************************全局通用返回码*******************************/
    public static final String Search_Execption_CODE = "00001";
    public static final String Search_Execption_CODE_MSG = "查询代码中存在异常";

    public static final String SupplierId_Execption_CODE = "00002";
    public static final String SupplierId_Execption_CODE_MSG = "商户id非法";

    /******************************************************************/
    public static final String SUCCESS = "\"" + "success" + "\"";
    public static final String FAIL = "\"" + "fail" + "\"";
    public static final String EMPTY = "\"" + "\"";
    public static final String EMPTYLIST = "[]";
    public static final String EXIST = "exist";

    /**
     * 全局返回码  demo begin
     **/

    //查询失败
    public static final String QUERY_ERROR = "10001";
    public static final String QUERY_ERROR_MSG = "查询失败";
    //插入失败
    public static final String INSERT_ERROR = "10002";
    public static final String INSERT_ERROR_MSG = "新增失败";
    //更新失败
    public static final String UPDATE_ERROR = "10003";
    public static final String UPDATE_ERROR_MSG = "修改失败";
    //删除失败
    public static final String DELETE_ERROR = "10004";
    public static final String DELETE_ERROR_MSG = "删除失败";

    /**
     * 全局返回码  demo end
     **/

    public static final int VISIT_SOURCE_APP = 1;
    public static final int VISIT_SOURCE_PC = 2;
    public static final int VISIT_SOURCE_H5 = 3;
    public static final int VISIT_SOURCE_DK = 4;

    /**
     * 错误返回码 11000: ;
     */
    /**********************************************************/


    public static final String MOBILE_CODE = "11000";
    public static final String MOBILE_FAIL_MSG = "手机格式不正确";


    public static final String INSERT_USER_CODE = "11001";
    public static final String INSERT_USER_NULL = "注册用户失败，必要字段不能为空";
    public static final String INSERT_USER_FAIL_MSG = "注册用户失败";
    public static final String INSERT_USER_FAIL_MSG2 = "该手机号已经存在";
    public static final String INSERT_USER_SUCCESS_MSG = "注册用户成功";


    public static final String QUERY_USER_CODE = "11002";
    public static final String QUERY_USER_NULL = "用户登录失败，必要字段不能为空";
    public static final String QUERY_USER_FAIL_MSG = "用户登录失败";
    public static final String QUERY_USER_FAIL_MSG2 = "密码错误";
    public static final String QUERY_USER_SUCCESS_MSG = "登录失败";


    public static final String UPDATE_USER_CODE = "11003";
    public static final String UPDATE_USER_NULL = "修改用户信息失败，必要字段不能为空";
    public static final String UPDATE_USER_FAIL_MSG = "修改用户信息失败";
    public static final String UPDATE_USER_SUCCESS_MSG = "修改用户信息成功";

    public static final String UPDATE_USER_PASSWORD_CODE = "11004";
    public static final String UPDATE_USER_PASSWORD_NULL = "修改密码失败，必要字段不能为空";
    public static final String UPDATE_USER_PASSWORD_FAIL_MSG = "修改密码失败";
    public static final String UPDATE_USER_PASSWORD_SUCCESS_MSG = "修改密码成功";

    public static final String QUERY_USER_INFOR_CODE = "11005";
    public static final String QUERY_USER_INFOR_NULL = "查询用户信息失败，必要字段不能为空";
    public static final String QUERY_USER_INFOR_FAIL_MSG = "查询用户信息失败";
    public static final String QUERY_USER_INFOR_FAIL_MSG2 = "用户不存在";
    public static final String QUERY_USER_INFOR_SUCCESS_MSG = "查询用户信息成功";


    public static final String QUESTIONS_CODE = "12001";
    public static final String QUESTIONS_NULL = "发布问题失败，必要字段不能为空";
    public static final String QUESTIONS_FAIL_MSG = "发布问题失败";
    public static final String QUESTIONS_SUCCESS_MSG = "发布问题成功";


    public static final String QUERY_QUESTIONS_CODE = "12002";
    public static final String QUERY_QUESTIONS_SUCCESS_MSG = "查询发布的问题成功";
    public static final String QUERY_QUESTIONS_FAIL_MSG = "发布的问题不存在";


    public static final String QUERY_ALL_QUESTIONS_CODE = "12003";
    public static final String QUERY_ALL_QUESTIONS_NULL = "查询所有问题，必要字段不能为空";
    public static final String QUERY_ALL_QUESTIONS_FAIL_MSG = "查询所有问题失败";
    public static final String QUERY_ALL_QUESTIONS_SUCCESS_MSG = "查询所有问题成功";


    public static final String COMMENT_QUESTIONS_CODE = "13001";
    public static final String COMMENT_QUESTIONS_NULL = "评论失败，必要字段不能为空";
    public static final String COMMENT_QUESTIONS_FAIL_MSG = "评论失败";
    public static final String COMMENT_QUESTIONS_SUCCESS_MSG = "评论成功";

    public static final String COMMENT_LIST_QUESTIONS_CODE = "13002";
    public static final String COMMENT_LIST_QUESTIONS_NULL = "评论列表";
    public static final String COMMENT_LIST_QUESTIONS_FAIL_MSG = "评论列表失败";
    public static final String COMMENT_LIST_QUESTIONS_SUCCESS_MSG = "评论列表获取成功";




    public static final String APP_VERSION_CODE = "14001";
    public static final String APP_VERSION_SUCCESS_MSG = "获取更新信息成功";
    public static final String APP_VERSION_FAIL_MSG = "获取更新信息失败";



}
