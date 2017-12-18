package com.test.common.questions.mapper;

import com.test.common.questions.model.Questions;
import com.test.utils.BaseMapper;
import java.util.List;
import java.util.Map;

public interface QuestionsMapper extends BaseMapper {


    int updateByPrimaryKey(Questions record);

    int insert(Questions record);

    Questions selectByPrimaryKey(Long id);

    int deleteByPrimaryKey(Long id);

    List<Map> selectAll();

    List<Map> selectAllQuestions();
}