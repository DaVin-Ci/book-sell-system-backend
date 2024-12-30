package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.Order;
import com.example.demo.Entity.OrderItem;
import com.example.demo.Entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderItemMapper extends BaseMapper<OrderItem> {
    // 根据订单的oid查询到所有条目item（一对多）（每一个item中有book属性用于存储对应bid的图书信息）
    @Select("select * from t_orderitem where oitemid = #{oitemid}")
    @Results({
            @Result(column = "oitemid", property = "oitemid"),
            @Result(column = "quantity", property = "quantity"),
            @Result(column = "subtotal", property = "subtotal"),
            @Result(column = "bid", property = "book", javaType = Book.class,
                    one = @One(select = "com.example.demo.Mapper.BookMapper.findById")
            )
    })
    public OrderItem findDetailItemByOItemId(String oitemid);

    @Select("select * from t_orderitem where oid=#{oid}")
    public List<OrderItem> findAllItemsByOid(String oid);
}
