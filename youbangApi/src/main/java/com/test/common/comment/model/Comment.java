package com.test.common.comment.model;

import java.util.Date;

public class Comment {
    private Long id;

    private String content;

    private Long user_id;

    private Long question_id;

    private Date created_time;

    private Date updated_time;

    public Comment(Long id, String content, Long user_id, Long question_id, Date created_time, Date updated_time) {
        this.id = id;
        this.content = content;
        this.user_id = user_id;
        this.question_id = question_id;
        this.created_time = created_time;
        this.updated_time = updated_time;
    }

    public Comment() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public Date getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(Date updated_time) {
        this.updated_time = updated_time;
    }
}