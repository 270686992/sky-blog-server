package cn.xilikeli.skyblog.repository;

import cn.xilikeli.skyblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <p>
 * 文章评论 Repository 接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 21:11
 * @since JDK1.8
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    /**
     * 通过文章 ID 查询该文章下的评论列表
     *
     * @param articleId 文章 ID
     * @return 返回查询的指定文章下的评论列表
     */
    List<Comment> findAllByArticleIdOrderByIdDesc(Integer articleId);

    /**
     * 通过文章 ID 查询该文章的评论数
     *
     * @param articleId 文章 ID
     * @return 返回查询的指定文章下的评论数量
     */
    int countByArticleId(Integer articleId);

    /**
     * 根据评论 ID 获取相应评论的信息
     *
     * @param commentId 评论 ID
     * @return 返回获取的评论信息
     */
    Comment findOneById(Integer commentId);

}