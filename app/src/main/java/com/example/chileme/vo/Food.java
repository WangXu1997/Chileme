package com.example.chileme.vo;

import org.litepal.crud.DataSupport;

/**
 * Created by a on 2017/8/10.
 */

public class Food extends DataSupport{
    int food_id;
    String food_name;
    float price;
    int number;
    float xiaoji;

    public Food(int food_id, String food_name, float price, int number, float xiaoji) {
        this.food_id = food_id;
        this.food_name = food_name;
        this.price = price;
        this.number = number;
        this.xiaoji = xiaoji;
    }
    public Food(){};

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getXiaoji() {
        return xiaoji;
    }

    public void setXiaoji(float xiaoji) {
        this.xiaoji = xiaoji;
    }
}
