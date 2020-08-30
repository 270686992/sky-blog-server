package cn.xilikeli.skyblog.repository;

import cn.xilikeli.skyblog.model.ArticleContent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 文章内容 Repository 接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 14:43
 * @since JDK1.8
 */
public interface ArticleContentRepository extends JpaRepository<ArticleContent, Integer> {

    /**
     * 通过文章 ID 查询对应的文章内容
     *
     * @param articleId 文章 ID
     * @return 返回查询的文章内容
     */
    ArticleContent findOneByArticleId(Integer articleId);

}