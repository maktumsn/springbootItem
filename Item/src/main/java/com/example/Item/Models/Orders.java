package com.example.Item.Models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Component
public class Orders {
    private int ordernumber;
    private Date pickupdate;
    private Time pickuptime;
    private long customerid;

    public Orders() {
    }

    public Orders(int ordernumber, Date pickupdate, Time pickuptime, long customerid) {
        this.ordernumber = ordernumber;
        this.pickupdate = pickupdate;
        this.pickuptime = pickuptime;
        this.customerid = customerid;
    }

    public int getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(int ordernumber) {
        this.ordernumber = ordernumber;
    }

    public Date getPickupdate() {
        return pickupdate;
    }

    public void setPickupdate(Date pickupdate) {
        this.pickupdate = pickupdate;
    }

    public Time getPickuptime() {
        return pickuptime;
    }

    public void setPickuptime(Time pickuptime) {
        this.pickuptime = pickuptime;
    }

    public long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(long customerid) {
        this.customerid = customerid;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "ordernumber=" + ordernumber +
                ", pickupdate=" + pickupdate +
                ", pickuptime=" + pickuptime +
                ", customerid=" + customerid +
                '}';
    }
}


