package com.example.demo.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.User;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.Service.BookService;
import com.example.demo.Service.UserService;
import com.example.demo.Utils.Result;
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
    public Result getBookByCid(@PathVariable String bid) throws SQLException {
        Book book = bookService.findByBid(bid);
//        System.out.println("===================================>" + book.getBname());
        return Result.ok().data("data", book);
    }

    @ApiOperation("根据分类查询图书")
    @GetMapping("/category/{cid}")
    public Result getBooksByCategory(@PathVariable String cid, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) throws SQLException {
        IPage<Book> book = bookService.getBooksByCategory(cid, page, size);
        return Result.ok().data("data", book);
    }

    @ApiOperation("根据书名查询图书")
    @GetMapping("/search/byName")
    public Result searchBooksByName(@RequestParam String bname, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) throws SQLException {
        IPage<Book> book = bookService.searchBooksByName(bname, page, size);
        return Result.ok().data("data", book);
    }

    @ApiOperation("根据作者查询图书")
    @GetMapping("/search/byAuthor")
    public Result searchBooksByAuthor(@RequestParam String author, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) throws SQLException{
        IPage<Book> book = bookService.searchBooksByAuthor(author, page, size);
        return Result.ok().data("data", book);
    }

    @ApiOperation("根据出版社查询图书")
    @GetMapping("/search/byPress")
    public Result searchBooksByPress(@RequestParam String press, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) throws SQLException {
        IPage<Book> book = bookService.searchBooksByPress(press, page, size);
        return Result.ok().data("data", book);
    }

    @ApiOperation("组合条件，综合查询图书")
    @GetMapping("/search")
    public Result searchBooks(@RequestParam(required = false) String bname, @RequestParam(required = false) String author, @RequestParam(required = false) String press, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) throws SQLException {
        IPage<Book> book = bookService.searchBooks(bname, author, press, page, size);
        return Result.ok().data("data", book);
    }
    @ApiOperation("根据分类查询图书总数")
    @GetMapping("/category/{cid}/count")
    public Result getBookCountByCategory(@PathVariable String cid) throws SQLException{
        int num = bookService.getBookCountByCategory(cid);
        return Result.ok().data("data", num);
    }

    @ApiOperation("添加图书信息")
    @PostMapping("/")
    public Result addBook(@RequestBody Map<String, Object> requestData) throws SQLException {
//        System.out.println("=================================>" + book);
        bookService.add(requestData);
        return Result.ok();
    }

    @ApiOperation("修改图书信息（不包括bid、img、thumimg）")
    @PutMapping("/")
public Result updateBook(@RequestBody Map<String, Object> requestData) throws SQLException {
//        System.out.println("=================================>" + book);
        bookService.edit(requestData);
        return Result.ok();
    }

    @ApiOperation("删除图书")
    @DeleteMapping("/{bid}")
    public Result deleteBook(@PathVariable String bid) throws SQLException {
        bookService.delete(bid);
        return Result.ok().data("data", bid);
    }
}
