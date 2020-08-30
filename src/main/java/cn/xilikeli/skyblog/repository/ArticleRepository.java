package cn.xilikeli.skyblog.repository;

import cn.xilikeli.skyblog.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * <p>
 * 文章 Repository 接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/19 - 19:06
 * @since JDK1.8
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    /**
     * 通过文章 ID 查询对应的文章信息
     *
     * @param articleId 文章 ID
     * @return 返回查询的文章信息
     */
    Article findOneById(Integer articleId);

    /**
     * 通过文章分类 ID 和分页查询对象分页查询该分类下的文章列表
     *
     * @param categoryId 文章分类 ID
     * @param pageable   分页查询对象
     * @return 查询成功返回封装着符合查询条件的文章列表的分页对象, 查询不到返回封装着空列表的分页对象
     */
    Page<Article> findByCategoryId(Integer categoryId, Pageable pageable);

    /**
     * 通过标签 ID 和分页查询对象分页查询该标签下的文章列表
     *
     * @param tagId    标签 ID
     * @param pageable 分页查询对象
     * @return 查询成功返回封装着符合查询条件的文章列表的分页对象, 查询不到返回封装着空列表的分页对象
     */
    @Query("select a from Article a\n" +
            "join TagArticle ta on a.id = ta.articleId\n" +
            "join Tag t on t.id = ta.tagId\n" +
            "where t.id = :tagId\n" +
            "order by a.priority")
    Page<Article> findArticleListByTagId(Integer tagId, Pageable pageable);

    /**
     * 根据查询关键字和分页查询对象分页查询和文章标题或者文章简述相应的文章列表
     *
     * @param likeKeyword1 查询关键字
     * @param likeKeyword2 查询关键字
     * @param pageable     分页查询对象
     * @return 查询成功返回封装着符合查询条件的文章列表的分页对象, 查询不到返回封装着空列表的分页对象
     */
    Page<Article> findByTitleLikeOrDescriptionLike(String likeKeyword1, String likeKeyword2, Pageable pageable);

}