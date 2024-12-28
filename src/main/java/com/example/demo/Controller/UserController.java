package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.Service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Qualifier("userMapper")
    @Autowired
    private UserMapper userMapper;

    @ApiOperation("获取用户信息") // Swagger添加注解来描述接口信息
    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        System.out.println("===================================>" + id);
        System.out.println(user);
//        return "获取ID: " + id + "的信息";
        return user + "";
    }

    @PostMapping("/user")
    public String addUser(@RequestBody User user) {
        int addNum = userMapper.insert(user);
        System.out.println("添加用户成功 ====> " + user);
        return "插入成功";
    }

    @PutMapping("/user")
    public String updateUser(@RequestBody User user) {
        return "更新用户" + user;
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable int id) {
        System.out.println(id);
        return "删除ID:" + id;
    }
}
