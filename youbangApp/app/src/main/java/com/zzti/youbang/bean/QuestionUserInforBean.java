package com.zzti.youbang.bean;

import java.io.Serializable;

/**
 * @author fengyongge
 * @Description
 * @date 2017/9/30 0030 下午 3:31
 */
public class QuestionUserInforBean implements Serializable {


     private String id;
    private String name;
    private String mobile;
    private String password;
    private String gender;
    private String des;
    private String pic;
    private String created_time;
    private String updated_time;
    private String is_delete;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }
}
