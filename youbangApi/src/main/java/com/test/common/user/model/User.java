package com.test.common.user.model;

import java.util.Date;

public class User {
    private Long id;

    private String name;

    private String mobile;

    private String password;

    private Long gender;

    private String des;

    private String pic;

    private Date created_time;

    private Date updated_time;

    private Byte is_delete;


    public Byte getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Byte is_delete) {
        this.is_delete = is_delete;
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

    public User() {
        super();
    }

    public User(Long id, String name, String mobile, String password, Long gender, String des, String pic, Date created_at, Date updated_at) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.password = password;
        this.gender = gender;
        this.des = des;
        this.pic = pic;
        this.created_time = created_at;
        this.updated_time = updated_at;
    }


    public User(Long id, String name, String mobile,String des, String pic) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.des = des;
        this.pic = pic;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }
}