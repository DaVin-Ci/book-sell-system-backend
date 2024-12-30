package com.example.demo.Mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Entity.User;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from t_user where uid=#{uid}")
    public User findById(String uid);
}
