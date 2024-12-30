package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.Order;
import com.example.demo.Entity.User;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    @Select("select * from t_order where oid = #{oid}")
    @Results({
            @Result(column = "oid", property = "oid"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "total", property = "total"),
            @Result(column = "status", property = "status"),
            @Result(column = "address", property = "address"),
            @Result(column = "uid", property = "owner", javaType = User.class,
                    one = @One(select = "com.example.demo.Mapper.UserMapper.findById")
            ),
            @Result(column = "oid", property = "itemslist", javaType = List.class,
                    many = @Many(select = "com.example.demo.Mapper.OrderItemMapper.findAllItemsByOid")
            )
    })
    Order findByOid(String oid);
}
