package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Entity.Category;
import org.apache.ibatis.annotations.*;
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

    @Select("select  count(*) from  t_category where pid = #{cid}")
    int countByParentCid(String cid);

    @Insert("insert into t_category(cid,cname,pid,description) values(#{cid}, #{cname}, #{parent.cid}, #{description})")
    int insertChild(Category category);

    @Update("update t_category set cname=#{cname},pid=#{parent.cid},description=#{description} where cid = #{cid}")
    void updateChild(Category category);
}

