package com.zzti.youbang.bean;

import java.io.Serializable;

/**
 * @author fengyongge
 * @Description
 * @date 2017/9/30 0030 下午 1:12
 */
public class CommentBean implements Serializable{

    private String created_time;
    private String updated_time;
    private String content;
    private String des;
    private String user_id;
    private String name;
    private String mobile;
    private String id;
    private String pic;
    private String question_id;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }
}
