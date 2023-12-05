package com.example.Item.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Items {
    @Id
    long itemno;
    String itemdescription;
    long itemquantity;
    int ordernumber;
    public Items()
    {

    }

    public Items(long itemno, String itemdescription, long itemquantity, int ordernumber) {
        this.itemno = itemno;
        this.itemdescription = itemdescription;
        this.itemquantity = itemquantity;
        this.ordernumber = ordernumber;
    }

    public long getItemno() {
        return itemno;
    }

    public void setItemno(long itemno) {
        this.itemno = itemno;
    }

    public String getItemdescription() {
        return itemdescription;
    }

    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }

    public long getItemquantity() {
        return itemquantity;
    }

    public void setItemquantity(long itemquantity) {
        this.itemquantity = itemquantity;
    }

    public int getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(int ordernumber) {
        this.ordernumber = ordernumber;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemno=" + itemno +
                ", itemdescription='" + itemdescription + '\'' +
                ", itemquantity=" + itemquantity +
                ", ordernumber=" + ordernumber +
                '}';
    }
}
