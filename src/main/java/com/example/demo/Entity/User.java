package com.example.demo.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_user")
public class User {
    @TableId
    private String uid;//会员编号
    private String nickname;//昵称
    private String password;//登录密码
    private String gender; //性别
    private String email;//邮箱


    // 注册表单
    @TableField(exist = false)
    private String reloginpass;//确认密码
    @TableField(exist = false)
    private String verifyCode;//验证码

    // 修改密码表单
    @TableField(exist = false) // 这三个字段在数据库中都不存在
    private String newpass;//新密码

    public String getReloginpass() {
        return reloginpass;
    }
    public void setReloginpass(String reloginpass) {
        this.reloginpass = reloginpass;
    }
    public String getVerifyCode() {
        return verifyCode;
    }
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewpass() {
        return newpass;
    }

    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", reloginpass='" + reloginpass + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                ", newpass='" + newpass + '\'' +
                '}';
    }
}
