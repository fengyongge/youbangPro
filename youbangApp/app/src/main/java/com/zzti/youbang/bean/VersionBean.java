package com.zzti.youbang.bean;

/**
 * @author fengyongge
 * @Description
 * @date 2017/9/28 0028 下午 2:21
 */
public class VersionBean {

    private String versionCode;
    private String updateContent;
    private String AndroidAddress;

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getAndroidAddress() {
        return AndroidAddress;
    }

    public void setAndroidAddress(String androidAddress) {
        AndroidAddress = androidAddress;
    }
}
