package com.example.chileme.vo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wang Xu on 2017/8/10.
 */

public class Store {
    private Integer storeId;
    private String storeUsername;
    private String storePassword;
    private String storePhoneNumber;
    private float storeMoney;
    private String storeAddress;
    private String storeDistrict;
    private String storeIntroduction;
    private Float storeGrade;
    private Integer storeSaleCount;
    private String storePhotoSource;
    private Set orders = new HashSet(0);
    private Set favorites = new HashSet(0);
    private Set foods = new HashSet(0);

    public Store() {
    }

    public Store(String storeUsername, String storePassword, String storePhoneNumber, float storeMoney,
                 String storeAddress, String storeDistrict) {
        this.storeUsername = storeUsername;
        this.storePassword = storePassword;
        this.storePhoneNumber = storePhoneNumber;
        this.storeMoney = storeMoney;
        this.storeAddress = storeAddress;
        this.storeDistrict = storeDistrict;
    }

    public Store(String storeUsername, String storePassword, String storePhoneNumber, float storeMoney,
                 String storeAddress, String storeDistrict, String storeIntroduction, Float storeGrade,
                 Integer storeSaleCount, String storePhotoSource, Set orders, Set favorites, Set foods) {
        this.storeUsername = storeUsername;
        this.storePassword = storePassword;
        this.storePhoneNumber = storePhoneNumber;
        this.storeMoney = storeMoney;
        this.storeAddress = storeAddress;
        this.storeDistrict = storeDistrict;
        this.storeIntroduction = storeIntroduction;
        this.storeGrade = storeGrade;
        this.storeSaleCount = storeSaleCount;
        this.storePhotoSource = storePhotoSource;
        this.orders = orders;
        this.favorites = favorites;
        this.foods = foods;
    }

    public Integer getStoreId() {
        return this.storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreUsername() {
        return this.storeUsername;
    }

    public void setStoreUsername(String storeUsername) {
        this.storeUsername = storeUsername;
    }

    public String getStorePassword() {
        return this.storePassword;
    }

    public void setStorePassword(String storePassword) {
        this.storePassword = storePassword;
    }

    public String getStorePhoneNumber() {
        return this.storePhoneNumber;
    }

    public void setStorePhoneNumber(String storePhoneNumber) {
        this.storePhoneNumber = storePhoneNumber;
    }

    public float getStoreMoney() {
        return this.storeMoney;
    }

    public void setStoreMoney(float storeMoney) {
        this.storeMoney = storeMoney;
    }

    public String getStoreAddress() {
        return this.storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreDistrict() {
        return this.storeDistrict;
    }

    public void setStoreDistrict(String storeDistrict) {
        this.storeDistrict = storeDistrict;
    }

    public String getStoreIntroduction() {
        return this.storeIntroduction;
    }

    public void setStoreIntroduction(String storeIntroduction) {
        this.storeIntroduction = storeIntroduction;
    }

    public Float getStoreGrade() {
        return this.storeGrade;
    }

    public void setStoreGrade(Float storeGrade) {
        this.storeGrade = storeGrade;
    }

    public Integer getStoreSaleCount() {
        return this.storeSaleCount;
    }

    public void setStoreSaleCount(Integer storeSaleCount) {
        this.storeSaleCount = storeSaleCount;
    }

    public String getStorePhotoSource() {
        return this.storePhotoSource;
    }

    public void setStorePhotoSource(String storePhotoSource) {
        this.storePhotoSource = storePhotoSource;
    }

    public Set getOrders() {
        return this.orders;
    }

    public void setOrders(Set orders) {
        this.orders = orders;
    }

    public Set getFavorites() {
        return this.favorites;
    }

    public void setFavorites(Set favorites) {
        this.favorites = favorites;
    }

    public Set getFoods() {
        return this.foods;
    }

    public void setFoods(Set foods) {
        this.foods = foods;
    }
}
