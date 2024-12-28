package com.example.demo.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

//@TableName("users") 手写sql时添加注解就没有作用了
// 因为底层MyBatis中@TableName注解功能的原理是通过添加as别名实现的
// 所以只有在MyBatisPlus提供的LambdaQuery查询中 才会在翻译sql语句时自动添加别名；而手写sql太灵活了
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
//    @TableField("username")
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
