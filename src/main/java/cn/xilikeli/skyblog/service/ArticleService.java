package cn.xilikeli.skyblog.service;

import cn.xilikeli.skyblog.model.Article;
import cn.xilikeli.skyblog.vo.ArticleDetailVO;
import cn.xilikeli.skyblog.vo.ArticleSimplifyVO;
import cn.xilikeli.skyblog.vo.CommentDetailVO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * <p>
 * 文章业务操作接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/19 - 19:07
 * @since JDK1.8
 */
public interface ArticleService {

    /**
     * 通过文章 ID 获取对应的文章详情信息
     *
     * @param articleId 文章 ID
     * @return 返回获取的文章详情信息
     */
    ArticleDetailVO getArticleDetailById(Integer articleId);

    /**
     * 根据文章 ID 获取该文章下的评论列表
     *
     * @param articleId 文章 ID
     * @return 返回获取的指定文章下的评论列表
     */
    List<CommentDetailVO> getCommentListByArticleId(Integer articleId);

    /**
     * 根据分页查询参数 page、count 获取当前页的文章列表
     *
     * @param page  当前页数
     * @param count 每页的数据条数
     * @return 返回封装着获取的文章列表的分页对象
     */
    Page<Article> getLatestArticleList(Integer page, Integer count);

    /**
     * 获取精选文章列表, 取权重值最前面的 5 篇文章
     *
     * @return 返回精选文章列表
     */
    List<ArticleSimplifyVO> getStarArticleList();

    /**
     * 根据分页查询参数 page、count 和文章分类 ID 获取该分类下当前页的文章列表
     *
     * @param page       当前页数
     * @param count      每页的数据条数
     * @param categoryId 文章分类 ID
     * @return 返回封装着获取的文章列表的分页对象
     */
    Page<Article> getLatestArticleListByCategoryId(Integer page, Integer count, Integer categoryId);

    /**
     * 根据分页查询参数 page、count 和标签 ID 获取该标签下当前页的文章列表
     *
     * @param page  当前页数
     * @param count 每页的数据条数
     * @param tagId 标签 ID
     * @return 返回封装着获取的文章列表的分页对象
     */
    Page<Article> getLatestArticleListByTagId(Integer page, Integer count, Integer tagId);

    /**
     * 根据分页查询参数 page、count 和查询关键字获取当前页的文章列表
     *
     * @param page    当前页数
     * @param count   每页的数据条数
     * @param keyword 查询关键字
     * @return 返回封装着获取的文章列表的分页对象
     */
    Page<Article> getLatestArticleListByKeyword(Integer page, Integer count, String keyword);

}