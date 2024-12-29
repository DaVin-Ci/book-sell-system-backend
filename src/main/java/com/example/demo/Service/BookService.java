package com.example.demo.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.Entity.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BookService {
    // 删除图书
    public void delete(String bid) throws SQLException;

    // 修改图书
    public void edit(Map<String, Object> requestData) throws SQLException;

    // 添加图书
    public int add(Map<String, Object> requestData) throws SQLException;

    // 查询
    // 根据bid查询
    public Book findByBid(String bid) throws SQLException;

    // 根据分类查询
    public IPage<Book> getBooksByCategory(String cid, Integer pc, Integer size) throws SQLException;

    // 根据书名模糊查询
    public IPage<Book> searchBooksByName(String bname, Integer pc, Integer size) throws SQLException;

    // 根据作者姓名查询
    public IPage<Book> searchBooksByAuthor(String author, Integer pc, Integer size) throws SQLException;

    // 根据出版社查询
    public IPage<Book> searchBooksByPress(String press, Integer pc, Integer size) throws SQLException;

    // 查询指定分类下图书的个数
    public int getBookCountByCategory(String cid) throws SQLException;

    // 多条件组合查询
    public IPage<Book> searchBooks(String bname, String author, String press, Integer pc, Integer size) throws SQLException;

    // 通用查询，islike变量为true时开启模糊查询，否则精准查询
    public IPage<Book> commonSearch(Map<String, Object> params, Integer pc, Integer size, Boolean islike) throws SQLException;

}
