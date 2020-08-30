package cn.xilikeli.skyblog.repository;

import cn.xilikeli.skyblog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>
 * 标签 Repository 接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 14:45
 * @since JDK1.8
 */
public interface TagRepository extends JpaRepository<Tag, Integer> {

    /**
     * 根据文章 ID 查询该文章的标签列表
     *
     * @param articleId 文章 ID
     * @return 返回查询的指定文章下的标签列表
     */
    @Query("select t from Tag t\n" +
            "join TagArticle ta on t.id = ta.tagId\n" +
            "join Article a on a.id = ta.articleId\n" +
            "where a.id = :articleId")
    List<Tag> findTagListByArticleId(Integer articleId);

}