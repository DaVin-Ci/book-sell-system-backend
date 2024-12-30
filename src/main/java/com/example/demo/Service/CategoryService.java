package com.example.demo.Service;

import com.example.demo.Entity.Category;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CategoryService {
    // 一级分类
    // 查询所有父级分类编号为空的分类
    public List<Category> findAllParentCategory() throws SQLException;

    // 查询指定一级分类下二级分类的个数
    public int getCategoryCountByParent(String cid) throws SQLException;

    // 删除分类（如果该分类下存在子分类 or 存在图书，则不能删除）
    public int delete(String cid) throws SQLException;

    // 修改分类（兼容一级和二级分类）
    public void edit(Map<String, Object> requestData) throws SQLException;

    // 添加分类（兼容一级和二级分类）
    public int add(Map<String, Object> requestData) throws SQLException;

    // 二级分类
    // 通过父分类查询子分类
    public List<Category> findByParent(String pid) throws SQLException;
}
