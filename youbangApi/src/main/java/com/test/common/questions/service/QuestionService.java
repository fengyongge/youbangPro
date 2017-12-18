package com.test.common.questions.service;

import com.test.common.questions.model.Questions;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import javax.ws.rs.QueryParam;

/**
 * Created by Administrator on 2017/9/15 0015.
 */
public interface QuestionService {

    int insert(Long user_id, String title,String content);

    int delete(Long id);

    String selectAllQuestions( int page, int per_page);

    Questions selectByPrimaryKey(Long id);


}
