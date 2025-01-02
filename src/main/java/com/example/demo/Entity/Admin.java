package com.example.demo.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Arrays;
import java.util.List;

@TableName("t_admin")
public class Admin {
        @TableId(type = IdType.AUTO)
        private int adminid;//管理员id主键
        private String account;//管理员账户
        private String name;//用户名
        private String password;//登录密码
        private String introduction;//简介
        private String avatar;//头像地址

        @TableField(exist = false)
//        private String roles;//用户权限 形如"['editor', 'admin']"
        private List<Role> roles;//用户权限角色列表

        @TableField(exist = false)
        private List<String> roleNames;//用户权限角色对应的String列表(不用前端再做数据处理)

        public int getAdminid() {
                return adminid;
        }

        public void setAdminid(int adminid) {
                this.adminid = adminid;
        }

        public String getAccount() {
                return account;
        }

        public void setAccount(String account) {
                this.account = account;
        }

        public String getIntroduction() {
                return introduction;
        }

        public void setIntroduction(String introduction) {
                this.introduction = introduction;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getAvatar() {
                return avatar;
        }

        public void setAvatar(String avatar) {
                this.avatar = avatar;
        }

        public List<Role> getRoles() {
                return roles;
        }

        public void setRoles(List<Role> roles) {
                this.roles = roles;
        }

        public List<String> getRoleNames() {
                return roleNames;
        }

        public void setRoleNames(List<String> roleNames) {
                this.roleNames = roleNames;
        }

        @Override
        public String toString() {
                return "Admin{" +
                        "adminid=" + adminid +
                        ", account='" + account + '\'' +
                        ", name='" + name + '\'' +
                        ", password='" + password + '\'' +
                        ", introduction='" + introduction + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", roles=" + roles +
                        ", roleNames=" + roleNames +
                        '}';
        }
}
