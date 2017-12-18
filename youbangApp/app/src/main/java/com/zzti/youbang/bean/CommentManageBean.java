package com.zzti.youbang.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengyongge
 * @Description
 * @date 2017/9/30 0030 下午 1:21
 */
public class CommentManageBean {

    List<CommentBean> list = new ArrayList<>();


    public List<CommentBean> getList() {
        return list;
    }

    public void setList(List<CommentBean> list) {
        this.list = list;
    }
}
