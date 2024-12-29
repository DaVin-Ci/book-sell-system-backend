package com.example.demo.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Entity.Category;
import com.example.demo.Mapper.CategoryMapper;
import com.example.demo.Utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BookService bookService;

    @Override
    public List<Category> findAllParentCategory() throws SQLException {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("pid");
        List<Category> parents = categoryMapper.selectList(queryWrapper);
        for(Category parent : parents) {
            // 查询出当前父分类的所有子分类
            List<Category> children = findByParent(parent.getCid());
            // 设置给父分类
            parent.setChildren(children);
        }
        return parents;
    }

    @Override
    public int getCategoryCountByParent(String cid) throws SQLException {
        return categoryMapper.countByParentCid(cid);
    }

    @Override
    public int add(Map<String, Object> requestData) throws SQLException {
        // 一级分类没有父级分类，pid字段值为null；反之则为二级分类
        if (requestData.containsKey("pid") && requestData.get("pid") != null) { // 添加的是二级分类
//            封装二级分类的表单数据到Category中
            Category child = CommonUtils.toBean(requestData, Category.class);
            child.setCid(CommonUtils.uuid());//设置cid

            // 手动映射pid到child当中
            String pid = requestData.get("pid").toString();
            Category parent = new Category();
            parent.setCid(pid);
            child.setParent(parent);
            return categoryMapper.insertChild(child);
        } else { // 添加的是一级分类
            Category parent = CommonUtils.toBean(requestData, Category.class);
            parent.setCid(CommonUtils.uuid());//设置cid
            return categoryMapper.insert(parent);
        }
    }


    @Override
    public int delete(String cid) throws SQLException {
        int childrenCount = categoryMapper.countByParentCid(cid);
        int bookCount = bookService.getBookCountByCategory(cid);
        if(childrenCount > 0 || bookCount > 0) { // 说明该分类下还有子分类或图书，不能删除
            return -1; // 提示删除失败，返回失败信息
        } else { // 可以删除
            return categoryMapper.deleteById(cid);
        }
    }


    @Override
    public void edit(Map<String, Object> requestData) throws SQLException {
// 一级分类没有父级分类，pid字段值为null；反之则为二级分类
        if (requestData.containsKey("pid") && requestData.get("pid") != null) { // 添加的是二级分类
            Category child = CommonUtils.toBean(requestData, Category.class);

            // 手动映射pid到child当中
            String pid = requestData.get("pid").toString();
            Category parent = new Category();
            parent.setCid(pid);
            child.setParent(parent);
            categoryMapper.updateChild(child);
        } else { // 添加的是一级分类
            Category parent = CommonUtils.toBean(requestData, Category.class);
            categoryMapper.updateById(parent);
        }
    }

    @Override
    public List<Category> findByParent(String pid) throws SQLException {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", pid);
        return categoryMapper.selectList(queryWrapper);
    }
}
