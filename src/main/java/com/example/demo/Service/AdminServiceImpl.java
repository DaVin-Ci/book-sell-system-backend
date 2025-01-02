package com.example.demo.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Entity.Admin;
import com.example.demo.Entity.AdminRole;
import com.example.demo.Entity.Role;
import com.example.demo.Mapper.AdminMapper;
import com.example.demo.Mapper.AdminRoleMapper;
import com.example.demo.Mapper.RoleMapper;
import com.example.demo.Utils.CommonUtils;
import com.example.demo.Utils.JwtUtils;
import com.example.demo.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements  AdminService{
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Qualifier("roleMapper")
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public String login(Map<String, Object> loginInfo) throws SQLException {
        Admin admin = CommonUtils.toBean(loginInfo, Admin.class);
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", admin.getAccount());
        Admin info = adminMapper.selectOne(queryWrapper);
//        System.out.println(info);
        if (info != null && info.getPassword().equals(admin.getPassword())) {
            return JwtUtils.generateToken(admin.getAccount());
        } else {
            return null;
        }
    }

    @Override
    public Admin getInfo(String token) throws SQLException {
        String account = JwtUtils.getClaimsByToken(token).getSubject(); // 获取生成Token时使用的载荷(account)
        QueryWrapper<Admin> adminWrapper = new QueryWrapper<>();
        adminWrapper.eq("account", account);
        Admin admin = adminMapper.selectOne(adminWrapper);
//        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + admin);

        // 根据关联中间表，通过用户id查询当前用户对应的角色列表
        QueryWrapper<AdminRole> roleWrapper = new QueryWrapper<>();
        roleWrapper.eq("adminid", admin.getAdminid());
        List<AdminRole> adminRoles = adminRoleMapper.selectList(roleWrapper);
        //  转换角色id为角色对象，获取到用户对应的角色List，赋值给用户的角色列表属性
        List<Integer> rolesId = adminRoles.stream().map(AdminRole::getAdminid).collect(Collectors.toList());
        List<Role> roles = roleMapper.selectBatchIds(rolesId);
        admin.setRoles(roles);

        // 把用户角色对象转换为String结果，可以直接供前端使用
        List<String> roleNames = roles.stream().map(Role::getRname).toList();
        admin.setRoleNames(roleNames);
        System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP"+admin);
        return admin; // 最终返回的是包括了用户名name、头像avatar、角色权限列表roles等所有信息的管理员对象
    }
}
