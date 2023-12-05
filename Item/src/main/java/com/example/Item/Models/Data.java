package com.example.Item.Models;

public class Data {
    OrdersResponse data;

    public Data() {
    }

    public Data(OrdersResponse data) {
        this.data = data;
    }

    public OrdersResponse getData() {
        return data;
    }

    public void setData(OrdersResponse data) {
        this.data = data;
    }
}
