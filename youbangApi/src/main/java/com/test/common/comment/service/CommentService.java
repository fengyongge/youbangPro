package com.test.common.comment.service;

import com.test.common.comment.model.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/27 0027.
 */
public interface CommentService {

    int insert(String content,Long user_id,Long question_id);


    String selectAllComment(Long question_id);




}
