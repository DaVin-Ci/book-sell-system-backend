package com.example.demo.Controller;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.Category;
import com.example.demo.Service.BookService;
import com.example.demo.Service.CategoryService;
import com.example.demo.Utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation("获取一级分类信息") // Swagger添加注解来描述接口信息
    @GetMapping("/")
    public Result findAllParentCategory() throws SQLException {
        List<Category> category = categoryService.findAllParentCategory();
        System.out.println("===================================>" + category);
        return Result.ok().data("data", category);
    }

    @ApiOperation("查询指定一级分类下二级分类的个数")
    @GetMapping("/{cid}")
    public Result getCategoryCountByParent(@PathVariable String cid) throws SQLException {
        int num = categoryService.getCategoryCountByParent(cid);
        return Result.ok().data("data", num);
    }

    @ApiOperation("添加分类（一级二级均可）")
    @PostMapping("/")
    public Result addParentCategory(@RequestBody Map<String, Object> requestData) throws SQLException {
//        System.out.println("=================================>" + book);
        int num = categoryService.add(requestData);
        return Result.ok().data("data", num);
    }


    @ApiOperation("修改分类（一级二级均可）")
    @PutMapping("/")
    public Result updateParentCategory(@RequestBody Map<String, Object> requestData) throws SQLException {
//        System.out.println("=================================>" + book);
        categoryService.edit(requestData);
        return Result.ok();
    }
//
    @ApiOperation("删除分类（如果该分类下存在子分类 or 存在图书，则不能删除）")
    @DeleteMapping("/{cid}")
    public Result deleteParentCategory(@PathVariable String cid) throws SQLException {
        int deletedCount = categoryService.delete(cid);
        if (deletedCount > 0) { // 删除成功
            return Result.ok().data("data", cid);
        } else {
            // 该分类下还有子目录或图书！无法删除
            return Result.error().data("data", cid);
        }

    }
}
