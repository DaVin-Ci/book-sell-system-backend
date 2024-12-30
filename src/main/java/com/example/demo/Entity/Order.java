package com.example.demo.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

@TableName("t_order")
public class Order {
    @TableId
    private String oid;//订单编号
    @TableField("ordertime")
    private String orderTime;//创建时间
    private double total;//总计金额
    private int status;//订单状态
    // 1未付款, 2已付款但未发货, 3已发货未确认收货, 4确认收货了交易成功, 5已取消(未付款的订单才能取消)
    private String address;//收货地址
    @TableField(exist = false)
    private User owner;//订单的所有者
    @TableField(exist = false)
    private List<OrderItem> itemslist;

    public List<OrderItem> getItemslist() {
        return itemslist;
    }
    public void setItemslist(List<OrderItem> itemslist) {
        this.itemslist = itemslist;
    }
    public String getOid() {
        return oid;
    }
    public void setOid(String oid) {
        this.oid = oid;
    }
    public String getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", total=" + total +
                ", status=" + status +
                ", address='" + address + '\'' +
                ", owner=" + owner +
                ", itemslist=" + itemslist +
                '}';
    }
}