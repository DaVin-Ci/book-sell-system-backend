package com.example.demo.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.Entity.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemService {
    // 根据oid查出一个订单下所有条目的信息（简略）
    public List<OrderItem> findAllItemsByOid(String o_itemid) throws SQLException;

    // 根据根据o_itemid查出一个条目的详细信息（包括图书信息）
    public OrderItem findDetailItemByOItemId(String o_itemid) throws SQLException;
}
