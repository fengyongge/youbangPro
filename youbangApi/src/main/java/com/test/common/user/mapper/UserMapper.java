package com.test.common.user.mapper;

import com.test.common.user.model.User;
import com.test.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper {

    List<User> selectAll();
    int deleteByPrimaryKey(Long id);

    User selectByPrimaryKey(Long id);
    //添加用户
    int insert(User record);
    //根据手机号和密码获取个人信息
    User selectByparameter(@Param("mobile") String mobile);
    //修改个人信息
    int updateByPrimaryKey(User record);
    //修改登录密码
    int updatePassword(@Param("password")  String password);





}