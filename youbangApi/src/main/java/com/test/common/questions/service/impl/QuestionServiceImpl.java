package com.test.common.questions.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.test.common.questions.mapper.QuestionsMapper;
import com.test.common.questions.model.Questions;
import com.test.common.questions.service.QuestionService;
import com.test.common.user.mapper.UserMapper;
import com.test.common.user.model.User;
import com.test.utils.ClientConstant;
import com.test.utils.DateUtil;
import com.test.utils.JsonMapper;
import com.test.utils.Response;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionsMapper questionsMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public int insert(Long user_id, String title, String content) {
        Questions questions =new Questions();
        questions.setUser_id(user_id);
        questions.setTitle(title);
        questions.setContent(content);
        questions.setCreated_time(Timestamp.valueOf(DateUtil.Symdhms()));
        questions.setUpdated_time(Timestamp.valueOf(DateUtil.Symdhms()));

        int insert = questionsMapper.insert(questions);
        return insert;
    }

    @Override
    public int delete(Long id) {

        int delete = questionsMapper.deleteByPrimaryKey(id);

        return delete;
    }

    @Override
    public String selectAllQuestions(int page, int per_page) {

        Page<Map> coupons = null;
        String couponList = "";
        JSONObject jsonObject;

        //如果没有传当前页码和每页显示的数量 ,默认一次20条
        if (per_page == 0 && page == 0) {
            per_page = 20;
            page = 1;
        }
        //每页显示的数据的条数最多50条
        if (page != 0 && per_page > 50) {
            per_page = 50;
        }

        //防止用户只传page不传per_page，给一个默认，插件默认的全部
        if (page == 1 && per_page == 0) {
            per_page = 20;
            page = 1;
        }

        PageHelper.startPage(page, per_page, true);//通用分页拦截器：第一个参数为第几页 第二个参数为每页显示的条数 第三个参数为true得到总条数
        coupons = (Page<Map>) questionsMapper.selectAllQuestions();

        //拼接用户信息
        for (int i = 0; i <coupons.getResult().size() ; i++) {
            Long user_id = (Long) coupons.getResult().get(i).get("user_id");
            User user = userMapper.selectByPrimaryKey(user_id);

            if(user!=null){
                coupons.getResult().get(i).put("user_infor",user);
            }else{
                coupons.getResult().get(i).put("user_infor","");
            }
        }


        if (coupons.getResult() == null || coupons.getResult().isEmpty() || coupons.getResult().size() == 0) {
            return Response.info().setCode(ClientConstant.QUERY_SUCCESS).setMsg(ClientConstant.QUERY_ALL_QUESTIONS_SUCCESS_MSG).setData(JsonMapper.emptyListToJson()).toJSON();
        }

        couponList = JsonMapper.toJson(coupons.getResult());
        jsonObject = JsonMapper.pageListToJson(coupons, couponList);
        return Response.info().setCode(ClientConstant.QUERY_SUCCESS).setMsg(ClientConstant.QUERY_ALL_QUESTIONS_SUCCESS_MSG).setData(jsonObject).toJSON();
    }


    @Override
    public Questions selectByPrimaryKey(Long id) {
        Questions questions = questionsMapper.selectByPrimaryKey(id);
        return questions;
    }
}
