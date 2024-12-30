package com.example.demo.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.Order;
import com.example.demo.Entity.OrderItem;
import com.example.demo.Service.BookService;
import com.example.demo.Service.OrderItemService;
import com.example.demo.Service.OrderService;
import com.example.demo.Utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @ApiOperation("查看所有订单") // Swagger添加注解来描述接口信息
    @GetMapping("/")
    public Result findAllOrder(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) throws SQLException {
        IPage<Order> orders = orderService.findAllOrder(page, size);
        System.out.println("===================================>" + orders);
        return Result.ok().data("data", orders);
    }

    @ApiOperation("根据oid查询订单详细条目信息") // Swagger添加注解来描述接口信息
    @GetMapping("/{oid}")
    public Result getOrderDetailByOid(@PathVariable String oid) throws SQLException {
        Order order = orderService.findByOid(oid);
        return Result.ok().data("data", order);
    }

    @ApiOperation("根据订单状态查询所有订单(1未付款, 2已付款但未发货, 3已发货未确认收货, 4确认收货了交易成功, 5已取消(未付款的订单才能取消))")
    @GetMapping("/search/{status}")
    public Result findByStatus(@PathVariable int status, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) throws SQLException {
        IPage<Order> orders = orderService.findByStatus(status, page, size);
        return Result.ok().data("data", orders);
    }

    @ApiOperation("给目标订单发货（实际上就是修改订单状态为3，只有已付款未发货的订单才能发货）")
    @PutMapping("/deliver/{oid}")
    public Result deliverOrder(@PathVariable String oid) throws SQLException {
        int row = orderService.deliver(oid);
        if (row > 0) {
            return Result.ok().data("data", oid);
        } else {
            return Result.error().data("data", row);
        }
    }

    @ApiOperation("取消订单(状态改为5，只有未付款的订单才能取消)")
    @PutMapping("/cancel/{oid}")
    public Result cancelOrder(@PathVariable String oid) throws SQLException {
        int row = orderService.cancel(oid);
        if (row > 0) {
            return Result.ok().data("data", oid);
        } else {
            return Result.error().data("data", row);
        }
    }
}
