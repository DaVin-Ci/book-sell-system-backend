package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Entity.Category;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    @Select("select * from t_category where cid = #{cid}")
    @Results({
            @Result(column = "cid", property = "cid"),
            @Result(column = "cname", property = "cname"),
            @Result(column = "desc", property = "desc"),
            @Result(column = "pid", property = "category", javaType = Category.class,
                    one = @One(select = "com.example.demo.Mapper.CategoryMapper.findById")
            )
    })
    Category findById(String cid);
}

