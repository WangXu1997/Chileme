package com.example.chileme.vo;

/**
 * Created by Wang Xu on 2017/8/8.
 */

public class AllOrder {
    private String store_name;
    private String photo_source;
    private float totalPrice;
    private boolean state;
    private String food_name;
    private int totalCount;
    public String getStore_name() {
        return store_name;
    }
    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }
    public String getPhoto_source() {
        return photo_source;
    }
    public void setPhoto_source(String photo_source) {
        this.photo_source = photo_source;
    }
    public float getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
    public String getFood_name() {
        return food_name;
    }
    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
