package com.test.common.user.service;

import com.test.common.user.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/9/14 0014.
 */
public interface UserService {

    int insert(String mobile,String password);

    User selectByPrimaryKey(Long id);

    User selectByparameter(String mobile,String password);

    int updatePassword(String newPassword);

    int updateByparameter(Long id,String name, Long gender, String des, String pic);


}
