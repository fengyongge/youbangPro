package com.zzti.youbang.bean;

import java.io.Serializable;

/**
 * @author fengyongge
 * @Description
 * @date 2017/9/28 0028 下午 12:34
 */
public class QuestionBean implements Serializable {


    public String created_time;
    public String updated_time;
    public String user_id;
    public String name;
    public String question_user_id;
    public String pic;
    public String title;
    public String question_id;
    public String content;

    QuestionUserInforBean user_infor;


    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion_user_id() {
        return question_user_id;
    }

    public void setQuestion_user_id(String question_user_id) {
        this.question_user_id = question_user_id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public QuestionUserInforBean getUser_infor() {
        return user_infor;
    }

    public void setUser_infor(QuestionUserInforBean user_infor) {
        this.user_infor = user_infor;
    }
}
