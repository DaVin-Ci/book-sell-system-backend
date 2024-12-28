package com.example.demo.Controller;

import com.example.demo.Entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class testController {
//    @GetMapping("/hello")
//    等同于 @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    注意不允许的请求方法会返回405错误：方法不被允许

//    如果要接收前端传来的参数（不管前端使用哪一种方式传递参数，后端接收数据的方式不变）：
//    1. 使用查询字符串
//    http://localhost/hello?nickname=davinci&password=4444
//    @RequestParam参数映射：把请求参数绑定到控制器的方法参数上，如果二者参数名称一致则可以省略
//    使用参数注解则必须传递，否则不会调用对应的方法，访问会返回400参数；除非使用require = false可选
//    不匹配的话则接收为null
    @GetMapping("/hello")
    public String test1(String nickname, @RequestParam(value = "password", required = false) String pwd) {
//        System.out.println(password);
        return "Hello " + nickname + "---" + pwd + "!";
    }
    @RequestMapping(value = "/index", method = RequestMethod.POST)
//    如果对象属性值和前端传递的参数名称一致，则会把数据封装到对象当中，面向对象的思想，而不用写过多参数
//    前端发送POST请求体的类型是x-www-form-urlencoded
//    如果出现名称不一致，则不匹配的属性无法传递参数，值为null；如果是参数类型不一致，则在不能转换的情况下，会返回400，提示请求报文存在语法错误（Interger->String是可以的）
    public String test2(User user) {
        System.out.println(user);
        return "Hello " + user.getUsername() + "---" + user.getPassword() + "!";
    }
//    如果要接收JSON格式的请求体 (类型是application/json，在postman中是raw)，则还需要使用@RequestBody
//    public String test3(@RequestBody User user) {}

//    2. 动态路由传参
//    @PathVariable注解 可以获取到URL的动态路径参数，并传递给控制器中处理方法的参数


//    通配符请求
    @GetMapping("/test/**")
    public String test3() {
        return "通配符请求";
    }
}
