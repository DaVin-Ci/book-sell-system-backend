package com.example.demo.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.User;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.Service.BookService;
import com.example.demo.Service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @ApiOperation("获取图书信息") // Swagger添加注解来描述接口信息
    @GetMapping("/{bid}")
    public String getBookByCid(@PathVariable String bid) throws SQLException {
        Book book = bookService.findByBid(bid);
        System.out.println("===================================>" + book.getBname());
        return "====================>" + book + "";
    }

    @ApiOperation("根据分类查询图书")
    @GetMapping("/category/{cid}")
    public IPage<Book> getBooksByCategory(@PathVariable String cid, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) throws SQLException {
        return bookService.getBooksByCategory(cid, page, size);
    }

    @ApiOperation("根据书名查询图书")
    @GetMapping("/search/byName")
    public IPage<Book> searchBooksByName(@RequestParam String bname, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) throws SQLException {
        return bookService.searchBooksByName(bname, page, size);
    }

    @ApiOperation("根据作者查询图书")
    @GetMapping("/search/byAuthor")
    public IPage<Book> searchBooksByAuthor(@RequestParam String author, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) throws SQLException{
        return bookService.searchBooksByAuthor(author, page, size);
    }

    @ApiOperation("根据出版社查询图书")
    @GetMapping("/search/byPress")
    public IPage<Book> searchBooksByPress(@RequestParam String press, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) throws SQLException {
        return bookService.searchBooksByPress(press, page, size);
    }

    @ApiOperation("组合条件，综合查询图书")
    @GetMapping("/search")
    public IPage<Book> searchBooks(@RequestParam(required = false) String bname, @RequestParam(required = false) String author, @RequestParam(required = false) String press, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) throws SQLException {
        return bookService.searchBooks(bname, author, press, page, size);
    }
    @ApiOperation("根据分类查询图书总数")
    @GetMapping("/category/{cid}/count")
    public int getBookCountByCategory(@PathVariable String cid) throws SQLException{
        return bookService.getBookCountByCategory(cid);
    }


    @PostMapping("/")
    public String addBook(@RequestBody Map<String, Object> requestData) throws SQLException {
//        System.out.println("=================================>" + book);
        int rows = bookService.add(requestData);
        return "================>添加成功！" + "----" + rows;
    }

    @PutMapping("/")
public String updateBook(@RequestBody Map<String, Object> requestData) throws SQLException {
//        System.out.println("=================================>" + book);
        bookService.edit(requestData);
        return "修改成功啦！！";
//        return "================>添加成功！" + "----" + rows;
    }
//
//    @DeleteMapping("/user/{id}")
//    public String deleteUser(@PathVariable int id) {
//        System.out.println(id);
//        return "删除ID:" + id;
//    }
}
