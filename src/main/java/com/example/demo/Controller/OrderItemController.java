package com.example.demo.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.Entity.Order;
import com.example.demo.Entity.OrderItem;
import com.example.demo.Service.OrderItemService;
import com.example.demo.Utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderItemController {
    @Autowired
    OrderItemService orderItemService;;

    @ApiOperation("根据oitemid查询条目详细信息")
    @GetMapping("/items/{o_itemid}")
    public Result getOrderDetailByOid(@PathVariable String o_itemid) throws SQLException {
        OrderItem items = orderItemService.findDetailItemByOItemId(o_itemid);
        System.out.println("===================================>" + items);
        return Result.ok().data("data", items);
    }
}
