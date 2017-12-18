package com.test.common.user.service.impl;

import com.test.common.user.mapper.UserMapper;
import com.test.common.user.model.User;
import com.test.common.user.service.UserService;
import com.test.utils.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/9/14 0014.
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public User selectByPrimaryKey(Long id) {

        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public int insert(String mobile, String password) {
        String defaultName ="友帮";
        Long defaultGender =0l;
        String defaultPic ="http://qiye.wxsdc.ediankai.com/static/img/profile_staff.png";
        String defaultDes ="假装有一个描述";
        User user = new User();
        user.setName(defaultName);
        user.setGender(defaultGender);
        user.setPic(defaultPic);
        user.setDes(defaultDes);
        user.setMobile(mobile);
        user.setIs_delete(Byte.parseByte("0"));
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(hash);
        user.setCreated_time(Timestamp.valueOf(DateUtil.Symdhms()));
        user.setUpdated_time(Timestamp.valueOf(DateUtil.Symdhms()));
        int insert = userMapper.insert(user);
        return insert;
    }

    @Override
    public User selectByparameter(String mobile, String password) {

        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        User user =  userMapper.selectByparameter(mobile);
        return user;
    }



    @Override
    public int updateByparameter(Long id, String name, Long gender, String des, String pic) {

        User user = userMapper.selectByPrimaryKey(id);

        if(StringValidateUtil.isNotEmpty(name)){
            user.setName(name);
        }
        if(StringValidateUtil.isNotEmpty(des)){
            user.setDes(des);
        }

        if(StringValidateUtil.isNotEmpty(pic)){
            user.setPic(pic);
        }

        user.setGender(gender);

        user.setUpdated_time(Timestamp.valueOf(DateUtil.Symdhms()));

        int update = userMapper.updateByPrimaryKey(user);

        return update;
    }




    @Override
    public int updatePassword(String newPassword) {

       int update = userMapper.updatePassword(newPassword);

       return update;
    }


}
