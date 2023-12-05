package com.example.Item.Models;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName("Data")
public class ItemsResponse {
    List<Items> Items;

    public ItemsResponse() {
    }

    public ItemsResponse(List<com.example.Item.Models.Items> items) {
        Items = items;
    }

    public List<com.example.Item.Models.Items> getItems() {
        return Items;
    }

    public void setItems(List<com.example.Item.Models.Items> items) {
        Items = items;
    }
}
