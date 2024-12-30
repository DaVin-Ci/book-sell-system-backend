package com.example.demo.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.example.demo.Entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    // 查看所有订单
    public IPage<Order> findAllOrder(Integer pc, Integer size) throws SQLException;

    // 根据oid查询订单详细信息
    public Order findByOid(String oid) throws SQLException;

    // 根据订单状态查询所有订单
    public IPage<Order> findByStatus(int status, Integer pc, Integer size) throws SQLException;

    // 给目标订单发货（实际上就是修改订单状态为3，只有已付款未发货的订单才能发货）
    public int deliver(String oid) throws SQLException;

    // 取消订单(状态改为5，只有未付款的订单才能取消)
    public int cancel(String oid) throws SQLException;
}
