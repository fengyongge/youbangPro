package com.test.api.v1.open.questions;

import com.sun.jersey.api.spring.Autowire;
import com.test.common.questions.model.Questions;
import com.test.common.questions.service.QuestionService;
import com.test.utils.ClientConstant;
import com.test.utils.JsonMapper;
import com.test.utils.Response;
import com.test.utils.Validator;
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
@Path("/QuestionService")
public class QuestionsRest {

    @Autowired
    QuestionService questionService;


    /**
     * 发布问题
     * @param user_id
     * @param title
     * @param content
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/question/user_id/{user_id}")
    public String insert(@PathParam("user_id") Long user_id,@FormParam("title")String title,@FormParam("content") String content){

        boolean b = Validator.paramsHaveNull(title, content);
        if(!b){
            int insert = 0;
            try {
                insert = questionService.insert(user_id,title,content);

                if(insert==1){
                    return Response.info().setCode(ClientConstant.QUESTIONS_SUCCESS_MSG).setMsg(ClientConstant.QUERY_USER_INFOR_SUCCESS_MSG).setData(ClientConstant.SUCCESS).toJSON();
                }else{
                    return Response.info().setCode(ClientConstant.QUESTIONS_CODE).setMsg(ClientConstant.QUESTIONS_FAIL_MSG).setData(ClientConstant.FAIL).toJSON();
                }

            } catch (Exception e) {
                e.printStackTrace();
                return Response.info().setCode(ClientConstant.QUESTIONS_CODE).setMsg(ClientConstant.QUESTIONS_FAIL_MSG).setData(ClientConstant.FAIL).toJSON();
            }

        }else{
            return Response.info().setCode(ClientConstant.QUESTIONS_CODE).setMsg(ClientConstant.QUESTIONS_NULL).setData(ClientConstant.FAIL).toJSON();
        }

    }


    /**
     * 根据id查询问题详情
     * @param question_id
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/question/question_id/{question_id}")
    public String selectQuestionById(@PathParam("question_id") Long question_id){


       Questions questions =  questionService.selectByPrimaryKey(question_id);

        if(questions==null){
            return Response.info().setCode(ClientConstant.QUERY_QUESTIONS_CODE).setMsg(ClientConstant.QUERY_QUESTIONS_FAIL_MSG).setData(ClientConstant.FAIL).toJSON();
        }

       String question = JsonMapper.toJson(questions);

       return Response.info().setCode(ClientConstant.ERROR_0_STATUS).setMsg(ClientConstant.QUERY_QUESTIONS_SUCCESS_MSG).setData(question).toJSON();

    }


    /**
     * 查询所有问题
     * @param page
     * @param per_page
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/question")
    public String getAllQuestion( @QueryParam("page")int page, @QueryParam("per_page")int per_page){
        String content =  questionService.selectAllQuestions(page,per_page);
        return content;
    }


}
