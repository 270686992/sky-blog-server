package cn.xilikeli.skyblog.controller.v1;

import cn.xilikeli.skyblog.model.Article;
import cn.xilikeli.skyblog.service.ArticleService;
import cn.xilikeli.skyblog.vo.ArticleDetailVO;
import cn.xilikeli.skyblog.vo.ArticleSimplifyVO;
import cn.xilikeli.skyblog.vo.CommentDetailVO;
import cn.xilikeli.skyblog.vo.PagingDozerVO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * <p>
 * 文章业务接口控制器
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/19 - 19:09
 * @since JDK1.8
 */
@RequestMapping("/v1/article")
@Validated
@RestController
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    /**
     * 通过文章 ID 获取对应的文章详情信息
     *
     * @param articleId 文章 ID
     * @return 返回获取的文章详情信息
     */
    @GetMapping("/{id}/detail")
    public ArticleDetailVO getArticleDetailById(@PathVariable(name = "id") @Positive(message = "{id.positive}") Integer articleId) {
        return this.articleService.getArticleDetailById(articleId);
    }

    /**
     * 根据文章 ID 获取该文章下的评论列表
     *
     * @param articleId 文章 ID
     * @return 返回获取的指定文章下的评论列表
     */
    @GetMapping("/get/comment")
    public List<CommentDetailVO> getCommentListByArticleId(@RequestParam(name = "id") @NotNull(message = "{id.not-null}") @Positive(message = "{id.positive}") Integer articleId) {
        return this.articleService.getCommentListByArticleId(articleId);
    }

    /**
     * 根据分页查询参数 page、count 获取当前页的文章列表
     *
     * @param page  当前页数
     * @param count 每页的数据条数
     * @return 返回封装着获取的文章列表的分页视图对象
     */
    @GetMapping("/latest")
    public PagingDozerVO<Article, ArticleSimplifyVO> getLatestArticleList(@RequestParam(name = "page", required = false, defaultValue = "0")
                                                                          @Min(value = 0, message = "{page.page.min}") Integer page,
                                                                          @RequestParam(name = "count", required = false, defaultValue = "10")
                                                                          @Min(value = 1, message = "{page.count.min}")
                                                                          @Max(value = 30, message = "{page.count.max}") Integer count) {
        // 查询出源数据分页对象
        Page<Article> sourceArticlePage = this.articleService.getLatestArticleList(page, count);
        // 转换为目标类型的分页视图对象返回
        return new PagingDozerVO<>(sourceArticlePage, ArticleSimplifyVO.class);
    }

    /**
     * 获取精选文章列表, 取权重值最前面的 5 篇文章
     *
     * @return 返回精选文章列表
     */
    @GetMapping("/star")
    public List<ArticleSimplifyVO> getStarArticleList() {
        return this.articleService.getStarArticleList();
    }

    /**
     * 根据分页查询参数 page、count 和文章分类 ID 获取该分类下当前页的文章列表
     *
     * @param page       当前页数
     * @param count      每页的数据条数
     * @param categoryId 文章分类 ID
     * @return 返回封装着获取的文章列表的分页视图对象
     */
    @GetMapping("/by/category")
    public PagingDozerVO<Article, ArticleSimplifyVO> getLatestArticleListByCategoryId(@RequestParam(name = "page", required = false, defaultValue = "0")
                                                                                      @Min(value = 0, message = "{page.page.min}") Integer page,
                                                                                      @RequestParam(name = "count", required = false, defaultValue = "10")
                                                                                      @Min(value = 1, message = "{page.count.min}")
                                                                                      @Max(value = 30, message = "{page.count.max}") Integer count,
                                                                                      @RequestParam(name = "id") @NotNull(message = "{id.not-null}") @Positive(message = "{id.positive}") Integer categoryId) {
        // 查询出源数据分页对象
        Page<Article> sourceArticlePage = this.articleService.getLatestArticleListByCategoryId(page, count, categoryId);
        // 转换为目标类型的分页视图对象返回
        return new PagingDozerVO<>(sourceArticlePage, ArticleSimplifyVO.class);
    }

    /**
     * 根据分页查询参数 page、count 和标签 ID 获取该标签下当前页的文章列表
     *
     * @param page  当前页数
     * @param count 每页的数据条数
     * @param tagId 标签 ID
     * @return 返回封装着获取的文章列表的分页视图对象
     */
    @GetMapping("/by/tag")
    public PagingDozerVO<Article, ArticleSimplifyVO> getLatestArticleListByTagId(@RequestParam(name = "page", required = false, defaultValue = "0")
                                                                                 @Min(value = 0, message = "{page.page.min}") Integer page,
                                                                                 @RequestParam(name = "count", required = false, defaultValue = "10")
                                                                                 @Min(value = 1, message = "{page.count.min}")
                                                                                 @Max(value = 30, message = "{page.count.max}") Integer count,
                                                                                 @RequestParam(name = "id") @NotNull(message = "{id.not-null}") @Positive(message = "{id.positive}") Integer tagId) {
        // 查询出源数据分页对象
        Page<Article> sourceArticlePage = this.articleService.getLatestArticleListByTagId(page, count, tagId);
        // 转换为目标类型的分页视图对象返回
        return new PagingDozerVO<>(sourceArticlePage, ArticleSimplifyVO.class);
    }

    /**
     * 根据分页查询参数 page、count 和查询关键字获取当前页的文章列表
     *
     * @param page    当前页数
     * @param count   每页的数据条数
     * @param keyword 查询关键字
     * @return 返回封装着获取的文章列表的分页视图对象
     */
    @GetMapping("/search")
    public PagingDozerVO<Article, ArticleSimplifyVO> getLatestArticleListByKeyword(@RequestParam(name = "page", required = false, defaultValue = "0")
                                                                                   @Min(value = 0, message = "{page.page.min}") Integer page,
                                                                                   @RequestParam(name = "count", required = false, defaultValue = "10")
                                                                                   @Min(value = 1, message = "{page.count.min}")
                                                                                   @Max(value = 30, message = "{page.count.max}") Integer count,
                                                                                   @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword) {
        // 查询出源数据分页对象
        Page<Article> sourceArticlePage = this.articleService.getLatestArticleListByKeyword(page, count, keyword);
        // 转换为目标类型的分页视图对象返回
        return new PagingDozerVO<>(sourceArticlePage, ArticleSimplifyVO.class);
    }

}