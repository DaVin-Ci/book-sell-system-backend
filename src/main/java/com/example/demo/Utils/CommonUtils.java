package com.example.demo.Utils;

//这里要引入文件夹中依赖的jar包: beanutils
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import java.util.Map;
import java.util.UUID;

/**
 * 小小工具
 * @author qdmmy6
 *
 */
public class CommonUtils {
    /**
     * 返回一个不重复的字符串
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(0, 10);
    }

    /**
     * 把map转换成对象
     * @param map
     * @param clazz
     * @return
     *
     * 把Map转换成指定类型
     */
    @SuppressWarnings("rawtypes")
    public static <T> T toBean(Map map, Class<T> clazz) {
        try {
            /*
             * 1. 通过参数clazz创建实例
             * 2. 使用BeanUtils.populate把map的数据封闭到bean中
             */
            T bean = clazz.newInstance();
            ConvertUtils.register(new DateConverter(), java.util.Date.class);
            BeanUtils.populate(bean, map);
            return bean;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
