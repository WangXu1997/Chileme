package com.example.chileme.vo;

/**
 * Created by Wang Xu on 2017/8/10.
 */

public class HistoryOrder {
    private int storeid;
    public int getStoreid() {
        return storeid;
    }
    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }
    private int historyCount;
    private String storeUsername;
    private String storeIntroduction;
    private String storePhotoSource;
    private int historySale;
    private int grade;
    public int getHistorySale() {
        return historySale;
    }
    public void setHistorySale(int historySale) {
        this.historySale = historySale;
    }
    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public String getStoreUsername() {
        return storeUsername;
    }
    public void setStoreUsername(String storeUsername) {
        this.storeUsername = storeUsername;
    }
    public String getStoreIntroduction() {
        return storeIntroduction;
    }
    public void setStoreIntroduction(String storeIntroduction) {
        this.storeIntroduction = storeIntroduction;
    }
    public String getStorePhotoSource() {
        return storePhotoSource;
    }
    public void setStorePhotoSource(String storePhotoSource) {
        this.storePhotoSource = storePhotoSource;
    }
    public int getHistoryCount() {
        return historyCount;
    }
    public void setHistoryCount(int historyCount) {
        this.historyCount = historyCount;
    }
}
