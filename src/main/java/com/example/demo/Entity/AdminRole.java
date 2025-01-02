package com.example.demo.Entity;

import com.baomidou.mybatisplus.annotation.TableName;

// 用户角色关联表(中间表)
@TableName("t_adminrole")
public class AdminRole {
    private Integer rid;//角色id
    private Integer adminid;//管理员id

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "rid=" + rid +
                ", adminid=" + adminid +
                '}';
    }
}
