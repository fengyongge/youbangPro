package com.zzti.youbang.bean;

import java.io.Serializable;

/**
 * @author fengyongge
 * @Description
 * @date 2017/9/27 0027 下午 2:09
 */
public class LoginBean implements Serializable {

    private String id;
    private String name;
    private String mobile;
    private String gender;
    private String des;
    private String pic;

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
}
