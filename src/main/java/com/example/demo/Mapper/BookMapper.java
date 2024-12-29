package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.Category;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper  extends BaseMapper<Book> {
    @Update("update t_book set bname=#{bname},author=#{author},price=#{price}," +
            "discount=#{discount},press=#{press},edition=#{edition},pageNum=#{pageNum},wordNum=#{wordNum}," +
            "cid=#{category.cid} where bid=#{bid}")
    int update(Book book);

    @Insert("insert into t_book(bid,bname,author,price,discount,press,edition,pageNum,wordNum,cid,img,thumimg) values(#{bid},#{bname},#{author},#{price}," +
            "#{discount},#{press},#{edition},#{pageNum},#{wordNum}," +
            "#{category.cid},#{img},#{thumimg})")
    int add(Book book);


    @Select("select  count(*) from  t_book where cid = #{cid}")
    int countByCategory(String cid);

    @Select("select * from t_book where bid = #{bid}")
    @Results({
            @Result(column = "bid", property = "bid"),
            @Result(column = "bname", property = "bname"),
            @Result(column = "author", property = "author"),
            @Result(column = "price", property = "price"),
            @Result(column = "discount", property = "discount"),
            @Result(column = "press", property = "press"),
            @Result(column = "edition", property = "edition"),
            @Result(column = "pageNum", property = "pageNum"),
            @Result(column = "wordNum", property = "wordNum"),
            @Result(column = "img", property = "img"),
            @Result(column = "thum_img", property = "thum_img"),
            @Result(column = "cid", property = "category", javaType = Category.class,
                    one = @One(select = "com.example.demo.Mapper.CategoryMapper.findById")
            )
    })
    Book findById(String bid);
}