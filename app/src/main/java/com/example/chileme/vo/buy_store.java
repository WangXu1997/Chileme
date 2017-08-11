package com.example.chileme.vo;

import org.litepal.crud.DataSupport;

/**
 * Created by a on 2017/8/11.
 */

public class buy_store extends DataSupport{
    int store_id;
    String store_name;
    float totalprice;

    public buy_store() {
    }

    public buy_store(int store_id, String store_name, float totalprice) {

        this.store_id = store_id;
        this.store_name = store_name;
        this.totalprice = totalprice;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }
}
