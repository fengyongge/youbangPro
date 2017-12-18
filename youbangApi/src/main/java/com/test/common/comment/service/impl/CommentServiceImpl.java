package com.test.common.comment.service.impl;

import com.test.common.comment.mapper.CommentMapper;
import com.test.common.comment.model.Comment;
import com.test.common.comment.service.CommentService;
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
 * Created by Administrator on 2017/9/27 0027.
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapperl;

    @Override
    public int insert(String content, Long user_id, Long question_id) {
        Comment comment =new Comment();
        comment.setContent(content);
        comment.setUser_id(user_id);
        comment.setQuestion_id(question_id);
        comment.setCreated_time(Timestamp.valueOf(DateUtil.Symdhms()));
        comment.setUpdated_time(Timestamp.valueOf(DateUtil.Symdhms()));

        int insert = commentMapperl.insert(comment);
        return insert;
    }

    @Override
    public String selectAllComment(Long question_id) {

        List list = new ArrayList<>();

        List<Map> questionsList = commentMapperl.selectAllComment(question_id);

        for (Map map : questionsList) {
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("id",map.get("id").toString());
            paramsMap.put("user_id",map.get("user_id").toString());
            paramsMap.put("question_id",map.get("question_id").toString());
            paramsMap.put("created_time",map.get("created_time").toString());
            paramsMap.put("updated_time",map.get("updated_time").toString());
            paramsMap.put("content",map.get("content").toString());
            paramsMap.put("name",map.get("name").toString());
            paramsMap.put("mobile",map.get("mobile").toString());
            paramsMap.put("des",map.get("des").toString());
            paramsMap.put("pic",map.get("pic").toString());
            list.add(paramsMap);
        }

        if (list == null || list.isEmpty() || list.size() == 0) {
            return Response.info().setCode(ClientConstant.QUERY_SUCCESS).setMsg(ClientConstant.COMMENT_LIST_QUESTIONS_SUCCESS_MSG).setData(JsonMapper.emptyListToJson()).toJSON();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list",list);

        String jsonString = JsonMapper.toJson(jsonObject);

        return jsonString;
    }
}
