package cn.xilikeli.skyblog.service.impl;

import cn.xilikeli.skyblog.common.LocalCustomer;
import cn.xilikeli.skyblog.common.constant.CodeMessageConstant;
import cn.xilikeli.skyblog.common.enumeration.CommentLevelEnum;
import cn.xilikeli.skyblog.common.enumeration.EmailKindEnum;
import cn.xilikeli.skyblog.common.exception.http.FailedException;
import cn.xilikeli.skyblog.common.exception.http.ForbiddenException;
import cn.xilikeli.skyblog.common.exception.http.ParameterException;
import cn.xilikeli.skyblog.common.manager.redis.RedisKeyConstant;
import cn.xilikeli.skyblog.common.manager.redis.RedisOperator;
import cn.xilikeli.skyblog.common.util.HttpRequestProxy;
import cn.xilikeli.skyblog.common.util.IpParseUtil;
import cn.xilikeli.skyblog.dto.comment.CommentDTO;
import cn.xilikeli.skyblog.model.Article;
import cn.xilikeli.skyblog.model.Comment;
import cn.xilikeli.skyblog.model.Customer;
import cn.xilikeli.skyblog.model.Email;
import cn.xilikeli.skyblog.repository.ArticleRepository;
import cn.xilikeli.skyblog.repository.CommentRepository;
import cn.xilikeli.skyblog.service.CommentService;
import cn.xilikeli.skyblog.service.MailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 文章评论业务操作接口类的实现类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 12:49
 * @since JDK1.8
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final MailService mailService;

    private final ArticleRepository articleRepository;

    private final RedisOperator redisOperator;

    /**
     * 通知邮箱
     */
    @Value("${sky-blog.inform-email}")
    private String informEmail;

    /**
     * 站点网址首页地址
     */
    @Value("${sky-blog.home-page-url}")
    private String homePageUrl;

    public CommentServiceImpl(CommentRepository commentRepository, MailService mailService, ArticleRepository articleRepository, RedisOperator redisOperator) {
        this.commentRepository = commentRepository;
        this.mailService = mailService;
        this.articleRepository = articleRepository;
        this.redisOperator = redisOperator;
    }

    @Override
    public void createComment(CommentDTO commentDTO) {
        // 校验文章评论的数据传输对象中的数据是否符合规则
        this.checkCommentDTO(commentDTO);

        // 获取 IP 并解析为相应地址
        String ip = HttpRequestProxy.getRemoteRealIp();
        String address = IpParseUtil.getAddressByIp(ip);

        // 获取当前登录用户
        Customer customer = LocalCustomer.getCustomer();

        // 缓存 key
        String createCommentKey = RedisKeyConstant.CREATE_COMMENT_KEY_PREFIX + customer.getId();
        // 缓存 key 对应的缓存内容
        String commentRecord = this.redisOperator.get(createCommentKey);

        if (StringUtils.isBlank(commentRecord)) {
            // 缓存中没有评论记录, 即 10s 内没有重复提交的情况
            // 添加评论
            Comment comment = new Comment();
            BeanUtils.copyProperties(commentDTO, comment);
            comment.setRoot(commentDTO.getRoot().equals(CommentLevelEnum.ROOT.getValue()));
            comment.setCustomerId(customer.getId());
            comment.setIp(ip);
            comment.setAddress(address);
            comment.setDisplayState(true);

            try {
                this.commentRepository.save(comment);
            } catch (Exception e) {
                throw new FailedException(CodeMessageConstant.CREATE_COMMENT_FAILED);
            }

            // 评论成功之后,发送邮件给站长通知
            // 封装邮件信息
            Map<String, Object> customerParameters = new HashMap<>(16);
            customerParameters.put("articleUrl", this.homePageUrl + "/article/" + comment.getArticleId());

            Email email = Email.builder()
                    .subject("博客评论通知")
                    .emailKind(EmailKindEnum.CUSTOMER_COMMENT.getValue())
                    .recipientEmail(this.informEmail)
                    .content(comment.getContent())
                    .customParameters(customerParameters)
                    .build();
            // 发送邮件
            this.mailService.sendInformEmail(email);

            // 将数据缓存到 redis 中,过期时间 10 s
            this.redisOperator.set(createCommentKey, "提交评论", 10);
        } else {
            // 缓存中有评论记录, 即 10s 内有重复提交的情况
            throw new ForbiddenException(CodeMessageConstant.TOO_MANY_SUBMIT_COMMENT);
        }
    }

    /**
     * 业务校验,校验文章评论的数据传输对象中的数据是否符合规则
     *
     * @param commentDTO 文章评论的数据传输对象
     */
    private void checkCommentDTO(CommentDTO commentDTO) {
        Article article = this.articleRepository.findOneById(commentDTO.getArticleId());
        if (article == null) {
            throw new ForbiddenException(CodeMessageConstant.FORBIDDEN_CREATE_COMMENT);
        }

        Integer root = commentDTO.getRoot();
        Integer rootId = commentDTO.getRootId();
        Integer parentId = commentDTO.getParentId();

        if (root == 1) {
            // 一级评论, rootId 和 parentId 应为 0
            if (rootId != 0 || parentId != 0) {
                throw new ForbiddenException(CodeMessageConstant.FORBIDDEN_CREATE_COMMENT);
            }
        } else {
            // 二级评论, rootId 和 parentId 不应为 0
            if (rootId == 0 || parentId == 0) {
                throw new ForbiddenException(CodeMessageConstant.FORBIDDEN_CREATE_COMMENT);
            }

            // 判断 rootId 和 parentId 是否有对应的数据
            Comment rootComment = this.commentRepository.findOneById(rootId);
            Comment parentComment = this.commentRepository.findOneById(parentId);

            if (rootComment == null || parentComment == null) {
                throw new ForbiddenException(CodeMessageConstant.FORBIDDEN_CREATE_COMMENT);
            }
        }
    }

}