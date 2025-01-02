package com.example.demo.Service;

import com.example.demo.Entity.Admin;

import java.sql.SQLException;
import java.util.Map;

public interface AdminService {
    // 登录
    public String login(Map<String, Object> loginInfo) throws SQLException;

    // 获取用户信息
    public Admin getInfo(String account) throws SQLException;
}
