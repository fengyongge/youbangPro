package com.test.api.v1.open.user;

import com.sun.jersey.api.spring.Autowire;
import com.test.common.user.model.User;
import com.test.common.user.service.UserService;
import com.test.utils.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * description:
 *
 * @author fengyongge
 * @date 2017年9月14日 上午11:23:40
 */

@Autowire
@Path("/UserService")
public class UserRest {

    @Autowired
    UserService userService;

    /**
     * 注册
     * @param mobile
     * @param password
     * @param code
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users/rigister")
    public String insert(@FormParam("mobile") String mobile, @FormParam("password") String password,@FormParam("code") String code){

        if(StringValidateUtil.isNotEmpty(mobile)&&StringValidateUtil.isNotEmpty(password)
                &&StringValidateUtil.isNotEmpty(code)){

            boolean mobile1 = Validator.isMobile(mobile);
            if (!mobile1) {
                return Response.info().setCode(ClientConstant.MOBILE_CODE).setMsg(ClientConstant.MOBILE_FAIL_MSG).setData(ClientConstant.FAIL).toJSON();
            }

            User user = userService.selectByparameter(mobile,password);
            if(user!=null){
                return Response.info().setCode(ClientConstant.INSERT_USER_CODE).setMsg(ClientConstant.INSERT_USER_FAIL_MSG2).setData(ClientConstant.FAIL).toJSON();
            }

            int insert = 0;
            try {
                insert = userService.insert(mobile,password);
            } catch (Exception e) {
                e.printStackTrace();
                return Response.info().setCode(ClientConstant.INSERT_USER_CODE).setMsg(ClientConstant.INSERT_USER_FAIL_MSG).setData(ClientConstant.FAIL).toJSON();
            }


            if(insert!=1){
                return Response.info().setCode(ClientConstant.INSERT_USER_CODE).setMsg(ClientConstant.INSERT_USER_FAIL_MSG).setData(ClientConstant.FAIL).toJSON();

            }else{
                return Response.info().setCode(ClientConstant.ERROR_0_STATUS).setMsg(ClientConstant.ERROR_0_STATUSNAME).setData(ClientConstant.SUCCESS).toJSON();
            }

        }else{
            return Response.info().setCode(ClientConstant.INSERT_USER_CODE).setMsg(ClientConstant.INSERT_USER_NULL).setData(ClientConstant.FAIL).toJSON();
        }

    }


    /**
     * 登录
     * @param mobile
     * @param password
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users/login")
    public String login(@FormParam("mobile") String mobile, @FormParam("password") String password){


        if(StringValidateUtil.isNotEmpty(mobile)&&StringValidateUtil.isNotEmpty(password)) {

            String jsonObject =null;

            User user = userService.selectByparameter(mobile,password);
            if(user==null){
                return Response.info().setCode(ClientConstant.QUERY_USER_CODE).setMsg(ClientConstant.QUERY_USER_FAIL_MSG).setData(ClientConstant.FAIL).toJSON();
            }

            if (!BCrypt.checkpw(password,user.getPassword())) {
                return Response.info().setCode(ClientConstant.QUERY_USER_CODE).setMsg(ClientConstant.QUERY_USER_FAIL_MSG2).setData(ClientConstant.FAIL).toJSON();
            }

            jsonObject= JsonMapper.toJson(user);
            return Response.info().setCode(ClientConstant.ERROR_0_STATUS).setMsg(ClientConstant.ERROR_0_STATUSNAME).setData(jsonObject).toJSON();

        }else{

            return Response.info().setCode(ClientConstant.QUERY_USER_CODE).setMsg(ClientConstant.QUERY_USER_NULL).setData(ClientConstant.FAIL).toJSON();
        }

    }


    /**
     * 获取个人信息
     * @param id
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users/infor/id/{id}")
    public String login(@PathParam("id")Long id){

        String jsonObject="";

        User user =  userService.selectByPrimaryKey(id);
        if(user==null){
            return Response.info().setCode(ClientConstant.QUERY_USER_INFOR_CODE).setMsg(ClientConstant.QUERY_USER_INFOR_FAIL_MSG2).setData(ClientConstant.FAIL).toJSON();
        }

        jsonObject = JsonMapper.toJson(user);

        return Response.info().setCode(ClientConstant.QUERY_SUCCESS).setMsg(ClientConstant.QUERY_USER_INFOR_SUCCESS_MSG).setData(jsonObject).toJSON();

    }




    /**
     * 修改个人信息
     * @param id
     * @param name
     * @param des
     * @param gender
     * @param pic
     * @return
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users/id/{id}")
    public String login(@PathParam("id")Long id,@FormParam("name") String name,
                        @FormParam("des") String des,@FormParam("gender") Long gender, @FormParam("pic") String pic){

        User user =  userService.selectByPrimaryKey(id);
        if(user==null){
            return Response.info().setCode(ClientConstant.QUERY_USER_INFOR_CODE).setMsg(ClientConstant.QUERY_USER_INFOR_FAIL_MSG2).setData(ClientConstant.FAIL).toJSON();
        }

        int update = userService.updateByparameter(id,name,gender,des,pic);

        if(update!=1){
            return Response.info().setCode(ClientConstant.UPDATE_USER_CODE).setMsg(ClientConstant.UPDATE_USER_FAIL_MSG).setData(ClientConstant.FAIL).toJSON();
        }

        return Response.info().setCode(ClientConstant.NEWUPDATE_SUCCESS).setMsg(ClientConstant.UPDATE_USER_SUCCESS_MSG).setData(ClientConstant.SUCCESS).toJSON();

    }


    /**
     * 修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users/password/id/{id}")
    public String login(@PathParam("id")Long id,@PathParam("oldPassword")Long oldPassword,@FormParam("newPassword") String newPassword){


        boolean b = Validator.paramsHaveNull(oldPassword, newPassword);
        if(b){
            User user = userService.selectByPrimaryKey(id);

            String old = user.getPassword();

            if(!old.equals(oldPassword)){
                return Response.info().setCode(ClientConstant.UPDATE_USER_PASSWORD_CODE).setMsg(ClientConstant.UPDATE_USER_SUCCESS_MSG).setData(ClientConstant.SUCCESS).toJSON();
            }

            int update = userService.updatePassword(newPassword);
            if(update!=1){
                return Response.info().setCode(ClientConstant.UPDATE_USER_PASSWORD_CODE).setMsg(ClientConstant.UPDATE_USER_SUCCESS_MSG).setData(ClientConstant.SUCCESS).toJSON();
            }

            return Response.info().setCode(ClientConstant.NEWUPDATE_SUCCESS).setMsg(ClientConstant.UPDATE_USER_SUCCESS_MSG).setData(ClientConstant.SUCCESS).toJSON();

        }else{

            return Response.info().setCode(ClientConstant.UPDATE_USER_PASSWORD_CODE).setMsg(ClientConstant.UPDATE_USER_PASSWORD_NULL).setData(ClientConstant.FAIL).toJSON();

        }


    }









}
