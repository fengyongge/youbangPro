package com.zzti.youbang.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fengyongge
 * @Description
 * @date 2017/9/28 0028 上午 11:40
 */
public class QuestionManageBean implements Serializable{

    private String total;
    List<QuestionBean> list =new ArrayList<>();


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<QuestionBean> getList() {
        return list;
    }

    public void setList(List<QuestionBean> list) {
        this.list = list;
    }
}
