package com.example.demo.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_orderitem")
public class OrderItem {
    @TableId
    private String oitemid;//订单条目编号
    private int quantity;//数量
    private double subtotal;//小计
    @TableField(exist = false)
    private Book book;//所关联的Book
    @TableField(exist = false)
    private Order order;//所属的订单

    public String getOrderItemId() {
        return oitemid;
    }
    public void setOrderItemId(String orderItemId) {
        this.oitemid = orderItemId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "oitemid='" + oitemid + '\'' +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", book=" + book +
                ", order=" + order +
                '}';
    }
}
