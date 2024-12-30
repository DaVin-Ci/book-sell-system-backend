package com.example.demo.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.Order;
import com.example.demo.Entity.OrderItem;
import com.example.demo.Entity.PageConstants;
import com.example.demo.Mapper.OrderItemMapper;
import com.example.demo.Mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderItemMapper orderItemMapper;

    @Override
    public IPage<Order> findAllOrder(Integer pc, Integer size) throws SQLException {
        if (pc == null) {
            pc = 1; // 默认页码为1
        }
        if (size == null) {
            size = PageConstants.BOOK_PAGE_SIZE; // 默认每页显示数量为5
        }
        Page<Order> page = new Page<>(pc, size);
        return orderMapper.selectPage(page, null);
    }

    @Override
    public Order findByOid(String oid) throws SQLException {
        Order order = orderMapper.findByOid(oid);
        List<OrderItem> orderItems = order.getItemslist();
        List<OrderItem> detailItems = new ArrayList<>();
        for(OrderItem item : orderItems) {
//            System.out.println("##########################################!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(item);
            OrderItem newitem = orderItemMapper.findDetailItemByOItemId(item.getOrderItemId());
            detailItems.add(newitem);
//            System.out.println("===================================>newitem" + newitem);
        }
//        System.out.println("????????????????????????????????????????????????????????>" + detailItems);
        order.setItemslist(detailItems);
//        System.out.println("【END】????????????????????????????????????????????????????????>" + order);
        return order;
    }

    @Override
    public IPage<Order> findByStatus(int status, Integer pc, Integer size) throws SQLException {
        if (pc == null) {
            pc = 1; // 默认页码为1
        }
        if (size == null) {
            size = PageConstants.ORDER_PAGE_SIZE; // 默认每页显示数量为5
        }
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", status);
        Page<Order> page = new Page<>(pc, size);
        return orderMapper.selectPage(page, queryWrapper);

    }

    @Override
    public int deliver(String oid) throws SQLException {
        // 检验订单状态，只有状态为2（即已付款但未发货）时才能进行发货，否则返回-1表示错误
        Order order = orderMapper.selectById(oid);
        // 注意此时order.getItemslist()是为null的，不可以用作if判断条件
//        System.out.println("【order】-----------------" + order);

        if (order != null && order.getStatus() == 2) {
            UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("oid", oid);
            Order updateOrder = new Order();
            updateOrder.setStatus(3);
            updateOrder.setTotal(order.getTotal());
            int row = orderMapper.update(updateOrder, updateWrapper);
            return row;
        } else {
            return -1;
        }
    }

    @Override
    public int cancel(String oid) throws SQLException {
        // 检验订单状态，只有状态为1（即未付款）时才能取消订单，否则返回-1表示错误
        Order order = orderMapper.selectById(oid);
        if (order != null && order.getStatus() == 1) {
            UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("oid", oid);
            Order updateOrder = new Order();
            updateOrder.setStatus(5);
            updateOrder.setTotal(order.getTotal());
            int row = orderMapper.update(updateOrder, updateWrapper);
            return row;
        } else {
            return -1;
        }
    }
}
