package com.example.demo.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;

@TableName("t_cartitem")
public class CartItem {
    @TableId
    private String c_itemid;// 购物车编号
    private int quantity;// 数量
    private Book book;// 条目对应的图书
    private User user;// 所属用户

    // 计算小计金额
    public double getSubtotal() {
        /*
         * 使用BigDecimal不会有误差
         * 要求必须使用String类型构造器
         */
        BigDecimal b1 = new BigDecimal(book.getPrice() + "");
        BigDecimal b2 = new BigDecimal(quantity + "");
        BigDecimal b3 = b1.multiply(b2);
        return b3.doubleValue();
    }

    public String getCartItemId() {
        return c_itemid;
    }

    public void setCartItemId(String c_itemid) {
        this.c_itemid = c_itemid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static void main(String[] args) {
        System.out.println("main============>" + (2.0-1.1));//0.8999999999999999
    }
}