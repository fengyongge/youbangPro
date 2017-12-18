package com.test.api.v1.open.comment;

import com.sun.jersey.api.spring.Autowire;
import com.test.common.comment.model.Comment;
import com.test.common.comment.service.CommentService;
import com.test.utils.ClientConstant;
import com.test.utils.JsonMapper;
import com.test.utils.Response;
import com.test.utils.Validator;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * description:
 *
 * @author fengyongge
 * @date 2017年9月14日 上午11:23:40
 */


@Autowire
@Path("/CommentService")
public class CommentRest {

    @Autowired
    CommentService commentService;

    /**
     * 评论问题
     * @param content
     * @param user_id
     * @param question_id
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/comment/user_id/{user_id}")
    public String comment(@PathParam("user_id") Long user_id, @FormParam("content") String content, @FormParam("question_id") Long question_id){

        if(!Validator.paramsHaveNull(content,user_id,question_id)) {

            try {
                int insert = commentService.insert(content, user_id, question_id);

                if (insert == 1) {
                    return Response.info().setCode(ClientConstant.ERROR_0_STATUS).setMsg(ClientConstant.COMMENT_QUESTIONS_SUCCESS_MSG).setData(ClientConstant.SUCCESS).toJSON();
                }

            } catch (Exception e) {
                e.printStackTrace();


                return Response.info().setCode(ClientConstant.COMMENT_QUESTIONS_CODE).setMsg(ClientConstant.COMMENT_QUESTIONS_FAIL_MSG).setData(ClientConstant.FAIL).toJSON();

            }
        }

        return Response.info().setCode(ClientConstant.COMMENT_QUESTIONS_CODE).setMsg(ClientConstant.COMMENT_QUESTIONS_NULL).setData(ClientConstant.FAIL).toJSON();

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/comment/question_id/{question_id}")

    public String comment(@PathParam("question_id")Long question_id){

        String jsonString = commentService.selectAllComment(question_id);

        return Response.info().setCode(ClientConstant.QUERY_SUCCESS).setMsg(ClientConstant.COMMENT_LIST_QUESTIONS_SUCCESS_MSG).setData(jsonString).toJSON();

    }











}
