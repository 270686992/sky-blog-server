package cn.xilikeli.skyblog.service;

import cn.xilikeli.skyblog.model.Category;

import java.util.List;

/**
 * <p>
 * 文章分类业务操作接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 21:19
 * @since JDK1.8
 */
public interface CategoryService {

    /**
     * 获取所有文章分类
     *
     * @return 返回包含所有文章分类的列表
     */
    List<Category> getAllCategory();

    /**
     * 根据文章分类 ID 获取相应文章分类的信息
     *
     * @param categoryId 文章分类 ID
     * @return 返回获取的文章分类的信息
     */
    Category getCategoryById(Integer categoryId);

}