package com.example.demo.Service;

import com.example.demo.Entity.User;

import java.util.List;

public interface UserService {
    User getUserById(int id);

    int add(User user); // int返回值表示插入了几条数据，插入失败则为0
}
