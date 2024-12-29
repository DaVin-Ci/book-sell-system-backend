package com.example.demo.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.PageConstants;
import com.example.demo.Mapper.BookMapper;
import com.example.demo.Utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public void delete(String bid) throws SQLException {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bid", bid);
        int rows = bookMapper.delete(queryWrapper);
//        if (rows > 0) {
//            System.out.println("Users deleted successfully.");
//        } else {
//            System.out.println("No users deleted.");
//        }
    }

    @Override
    public void edit(Map<String, Object> requestData) throws SQLException {
        // 不能直接使用update，因为Book类中cid字段是通过一个Category类实例来实现的
        // 而数据库中cid值是一个String
        Book book = CommonUtils.toBean(requestData, Book.class);//把Map中大部分数据封装到Book对象中
//        if (requestData.containsKey("cid")) {
            Category category = CommonUtils.toBean(requestData, Category.class);//把Map中cid封装到Category中
            book.setCategory(category);
        int row = bookMapper.update(book);
    }

    @Override
    public int add(Map<String, Object> requestData) throws SQLException {
        Book book = CommonUtils.toBean(requestData, Book.class);//把Map中大部分数据封装到Book对象中
        Category category = CommonUtils.toBean(requestData, Category.class);//把Map中cid封装到Category中
        book.setCategory(category);
        book.setBid(CommonUtils.uuid());
//        System.out.println("#################################################################");
//        System.out.println(book);
//        System.out.println(book.getCategory().getCid());
//        System.out.println("#################################################################");
//        System.out.println(category);
//        System.out.println("#################################################################");
        return bookMapper.add(book);
    }

    @Override
    public Book findByBid(String bid) throws SQLException {
        return bookMapper.findById(bid);
    }

    @Override
    public IPage<Book> getBooksByCategory(String cid, Integer pc, Integer size) throws SQLException {
        // 查询的分页结果集，是封装的分页对象IPage，当中包含了其他信息如总页数、当前页数，方便前端去显示
        Map<String, Object> params = new HashMap<>();
        params.put("cid", cid);
        return commonSearch(params, pc, size, false);
    }

    @Override
    public IPage<Book> searchBooksByName(String bname, Integer pc, Integer size) throws SQLException{
        Map<String, Object> params = new HashMap<>();
        params.put("bname", bname);
        return commonSearch(params, pc, size, true);
    }

    @Override
    public IPage<Book> searchBooksByAuthor(String author, Integer pc, Integer size) throws SQLException{
        Map<String, Object> params = new HashMap<>();
        params.put("author", author);
        return commonSearch(params, pc, size, true);
    }

    @Override
    public IPage<Book> searchBooksByPress(String press, Integer pc, Integer size) throws SQLException{
        Map<String, Object> params = new HashMap<>();
        params.put("press", press);
        return commonSearch(params, pc, size, true);
    }

    @Override
    public IPage<Book> searchBooks(String bname, String author, String press, Integer pc, Integer size) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        if (bname != null && bname.length() > 0) {
            params.put("bname", bname);
        }
        if (author != null && author.length() > 0) {
            params.put("author", author);
        }
        if (press != null && press.length() > 0) {
            params.put("press", press);
        }
        return commonSearch(params, pc, size, true);
    }

    @Override
    public int getBookCountByCategory(String cid) throws SQLException {
        return bookMapper.countByCategory(cid);
    }

    @Override
    public IPage<Book> commonSearch(Map<String, Object> params, Integer pc, Integer size, Boolean islike) throws SQLException {
        if (pc == null) {
            pc = 1; // 默认页码为1
        }
        if (size == null) {
            size = PageConstants.BOOK_PAGE_SIZE; // 默认每页显示数量为5
        }
        if (islike == null) {
            islike = true;
        }

        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                if (islike) {
                    queryWrapper.like(entry.getKey(), entry.getValue());
                } else {
                    queryWrapper.eq(entry.getKey(), entry.getValue());
                }
            }
        }

        Page<Book> page = new Page<>(pc, size);
        return bookMapper.selectPage(page, queryWrapper);
    }
}
