package com.example.demo.Mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
//public interface UserMapper {
//    @Select("select * from user where id=#{id}")
//    public User find(int id);
//
//    @Insert("insert into user values(#{id}, #{username}, #{password})")
//    @Options(useGeneratedKeys = true, keyProperty = "id") // 通知MyBatis生成的(自增)主键会被设置到User对象的id属性中
//    public int add(User user);
//}
public interface UserMapper extends BaseMapper<User> {

}
