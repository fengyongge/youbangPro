package com.test.common.comment.mapper;

import com.test.common.comment.model.Comment;
import com.test.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommentMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    Comment selectByPrimaryKey(Long id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);


    List<Map> selectAllComment(@Param("question_id") Long question_id);
}