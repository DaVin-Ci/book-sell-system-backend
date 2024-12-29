package com.example.demo.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_orderitem")
public class OrderItem {
    @TableId
    private String o_itemid;//订单条目编号
    private int quantity;//数量
    private double subtotal;//小计
    private Book book;//所关联的Book
    private Order order;//所属的订单

    public String getOrderItemId() {
        return o_itemid;
    }
    public void setOrderItemId(String orderItemId) {
        this.o_itemid = orderItemId;
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


}
