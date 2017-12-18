package com.test.common.version.model;

public class Version {
    private String versionCode;

    private String updateContent;

    private String androidAddress;

    public Version(String versionCode, String updateContent, String androidAddress) {
        this.versionCode = versionCode;
        this.updateContent = updateContent;
        this.androidAddress = androidAddress;
    }

    public Version() {
        super();
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode == null ? null : versionCode.trim();
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent == null ? null : updateContent.trim();
    }

    public String getAndroidAddress() {
        return androidAddress;
    }

    public void setAndroidAddress(String androidAddress) {
        this.androidAddress = androidAddress == null ? null : androidAddress.trim();
    }
}