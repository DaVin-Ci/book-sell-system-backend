package com.example.demo.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.Entity.OrderItem;
import com.example.demo.Mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItem> findAllItemsByOid(String oid) throws SQLException {
        // 根据oid查出一个订单下所有条目的信息（一个订单包含多个条目，用itemslist属性来存储）
        return orderItemMapper.findAllItemsByOid(oid);
    }

    @Override
    public OrderItem findDetailItemByOItemId(String o_itemid) throws SQLException {
        // 根据o_itemid查出一个条目的详细信息（包括图书信息）
        OrderItem item = orderItemMapper.findDetailItemByOItemId(o_itemid);
        return item;
    }
}
