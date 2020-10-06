package cn.xilikeli.skyblog.repository;

import cn.xilikeli.skyblog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>
 * 文章分类 Repository 接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 14:44
 * @since JDK1.8
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    /**
     * 查询所有文章分类
     *
     * @return 返回所有文章分类
     */
    @Query("select c from Category c where c.priority > 0 order by c.priority")
    List<Category> findAllCategory();

    /**
     * 通过文章分类 ID 查询对应的文章分类信息
     *
     * @param categoryId 文章分类 ID
     * @return 返回查询的文章分类信息
     */
    Category findOneById(Integer categoryId);

}