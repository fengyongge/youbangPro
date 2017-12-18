package com.test.common.questions.model;

import java.util.Date;

public class Questions {
    private Long id;

    private Long user_id;

    private String title;

    private String content;

    private Date created_time;

    private Date updated_time;

    public Questions(Long id, Long user_id,  String title, String content, Date created_time, Date updated_time) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.created_time = created_time;
        this.updated_time = updated_time;
    }

    public Questions() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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