package cn.xilikeli.skyblog.service.impl;

import cn.xilikeli.skyblog.common.constant.CodeMessageConstant;
import cn.xilikeli.skyblog.common.constant.CommonConstant;
import cn.xilikeli.skyblog.common.exception.http.NotFoundException;
import cn.xilikeli.skyblog.common.util.HttpRequestProxy;
import cn.xilikeli.skyblog.common.util.LocalCacheUtil;
import cn.xilikeli.skyblog.model.*;
import cn.xilikeli.skyblog.repository.*;
import cn.xilikeli.skyblog.service.ArticleService;
import cn.xilikeli.skyblog.vo.ArticleDetailVO;
import cn.xilikeli.skyblog.vo.ArticleSimplifyVO;
import cn.xilikeli.skyblog.vo.CommentDetailVO;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 文章业务操作接口类的实现类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/19 - 19:07
 * @since JDK1.8
 */
@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final ArticleContentRepository articleContentRepository;

    private final CategoryRepository categoryRepository;

    private final TagRepository tagRepository;

    private final CommentRepository commentRepository;

    private final LinUserRepository linUserRepository;

    private final CustomerRepository customerRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleDetailVO getArticleDetailById(Integer articleId) {
        // 获取文章信息和文章内容详细
        Article article = this.articleRepository.findOneById(articleId);
        ArticleContent articleContent = this.articleContentRepository.findOneByArticleId(articleId);

        if (article == null || articleContent == null) {
            throw new NotFoundException(CodeMessageConstant.NOT_FOUND_ARTICLE);
        }

        // 获取文章所属分类的信息
        Category category = this.categoryRepository.findOneById(article.getCategoryId());
        if (category == null) {
            throw new NotFoundException(CodeMessageConstant.NOT_FOUND_ARTICLE);
        }

        // 获取文章评论数
        int commentNumber = this.commentRepository.countByArticleId(articleId);
        article.setCommentNumber(commentNumber);

        // 获取文章的标签信息
        List<Tag> tagList = this.tagRepository.findTagListByArticleId(articleId);

        // 更新文章阅读量
        // 查看当前阅读文章的 ip 地址是否在缓存中并且没有过期,如果没有过期则不更新文章阅读量,反之更新
        String ip = HttpRequestProxy.getRemoteRealIp();
        String ipCache = LocalCacheUtil.getLocalCache(LocalCacheUtil.CACHE_PREFIX + ip);
        if (StringUtils.isBlank(ipCache)) {
            // 缓存过期,更新文章阅读量,并且重新设置缓存
            LocalCacheUtil.setLocalCache(LocalCacheUtil.CACHE_PREFIX + ip, CommonConstant.IP_CACHE_VALUE);
            article.setViews(article.getViews() + 1);
            this.articleRepository.save(article);
        }

        // 封装文章详情信息 VO 返回
        return new ArticleDetailVO(article, articleContent.getContent(), category.getName(), tagList);
    }

    @Override
    public List<CommentDetailVO> getCommentListByArticleId(Integer articleId) {
        // 判断文章是否存在
        Article article = this.articleRepository.findOneById(articleId);
        if (article == null) {
            throw new NotFoundException(CodeMessageConstant.NOT_FOUND_ARTICLE);
        }

        // 获取该文章下的所有评论
        List<Comment> allCommentList = this.commentRepository.findAllByArticleIdOrderByIdDesc(articleId);
        if (allCommentList.isEmpty()) {
            return Collections.emptyList();
        }

        // 把 allCommentList 转换为 VO 列表并返回
        return this.getCommentDetailVOList(allCommentList);
    }

    /**
     * 将文章评论列表转换为文章评论详情信息 VO 列表
     *
     * @param commentList 文章评论列表
     * @return 返回转换后的文章评论详情信息 VO 列表
     */
    private List<CommentDetailVO> getCommentDetailVOList(List<Comment> commentList) {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        List<CommentDetailVO> allCommentVOList = new ArrayList<>();

        commentList.forEach(comment -> {
            CommentDetailVO commentDetailVO = mapper.map(comment, CommentDetailVO.class);

            if (comment.getAdminUserId() != null) {
                LinUser linUser = this.linUserRepository.findOneById(comment.getAdminUserId());
                commentDetailVO.setNickname(linUser.getNickname());
                commentDetailVO.setAvatar(linUser.getAvatar());
                commentDetailVO.setEmail(linUser.getEmail());
            } else {
                Customer customer = this.customerRepository.findOneById(comment.getCustomerId());
                commentDetailVO.setNickname(customer.getNickname());
                commentDetailVO.setAvatar(customer.getAvatar());
                commentDetailVO.setEmail(customer.getEmail());
            }

            allCommentVOList.add(commentDetailVO);
        });

        return allCommentVOList;
    }

    @Override
    public Page<Article> getLatestArticleList(Integer page, Integer count) {
        Pageable pageable = PageRequest.of(page, count);
        return this.articleRepository.findAll(pageable);
    }

    @Override
    public List<ArticleSimplifyVO> getStarArticleList() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Article> articlePage = this.articleRepository.findAll(pageable);
        List<Article> articleList = articlePage.getContent();

        if (articleList.isEmpty()) {
            return Collections.emptyList();
        }

        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        List<ArticleSimplifyVO> hotArticleList = new ArrayList<>();
        articleList.forEach(a -> {
            ArticleSimplifyVO vo = mapper.map(a, ArticleSimplifyVO.class);
            hotArticleList.add(vo);
        });

        return hotArticleList;
    }

    @Override
    public Page<Article> getLatestArticleListByCategoryId(Integer page, Integer count, Integer categoryId) {
        Pageable pageable = PageRequest.of(page, count);
        return this.articleRepository.findByCategoryId(categoryId, pageable);
    }

    @Override
    public Page<Article> getLatestArticleListByTagId(Integer page, Integer count, Integer tagId) {
        Pageable pageable = PageRequest.of(page, count);
        return this.articleRepository.findArticleListByTagId(tagId, pageable);
    }

    @Override
    public Page<Article> getLatestArticleListByKeyword(Integer page, Integer count, String keyword) {
        Pageable pageable = PageRequest.of(page, count);

        if (StringUtils.isBlank(keyword)) {
            return this.articleRepository.findAll(pageable);
        } else {
            keyword = "%" + keyword + "%";
            return this.articleRepository.findByTitleLikeOrDescriptionLike(keyword, keyword, pageable);
        }
    }

}