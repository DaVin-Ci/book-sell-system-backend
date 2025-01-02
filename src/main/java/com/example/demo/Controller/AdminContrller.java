package com.example.demo.Controller;

import com.example.demo.Entity.Admin;
import com.example.demo.Entity.Book;
import com.example.demo.Service.AdminService;
import com.example.demo.Utils.CommonUtils;
import com.example.demo.Utils.JwtUtils;
import com.example.demo.Utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminContrller {
    @Autowired
    private AdminService adminService;

    @ApiOperation("管理员登录")
    @PostMapping("/login")
//    queryString: username=admin&password=123456 Admin admin, String account, String password
//    JSON: { account:admin, password:123456 }
    public Result login(@RequestBody Map<String, Object> loginInfo) throws SQLException {
        String token = adminService.login(loginInfo);
        if (token != null) {
            return Result.ok().data("token", token);
        } else {
            return  Result.error();
        }

    }

    @ApiOperation("获取用户信息")
    @GetMapping("/info") // "token: XXXXX"
    public Result info(String token) throws SQLException {
        Admin admin = adminService.getInfo(token);
        return Result.ok().data("name", admin.getName())
                .data("avatar", admin.getAvatar())
                .data("introduction", admin.getIntroduction())
                .data("roles", admin.getRoles())
                .data("roleNames", admin.getRoleNames());
    }

    @ApiOperation("管理员退出登录")
    @PostMapping("/logout")
    public Result logout() throws SQLException {
        return Result.ok();
    }

}

