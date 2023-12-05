package com.example.Item.Models;


import java.util.List;


public class OrdersResponse {
    List<Orders> Orders;

    public OrdersResponse() {
    }

    public OrdersResponse(List<Orders> orders) {
        Orders = orders;
    }

    public List<Orders> getOrders() {
        return Orders;
    }

    public void setOrders(List<Orders> orders) {
        Orders = orders;
    }
}