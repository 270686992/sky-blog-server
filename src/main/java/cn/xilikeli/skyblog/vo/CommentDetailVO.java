package cn.xilikeli.skyblog.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 文章评论详情信息 VO
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 21:05
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
public class CommentDetailVO {

    /**
     * 文章评论 ID
     */
    private Integer id;

    /**
     * 评论所属用户的昵称
     */
    private String nickname;

    /**
     * 评论所属用户的邮箱
     */
    private String email;

    /**
     * 评论所属用户的头像 url
     */
    private String avatar;

    /**
     * 根评论 ID,即一级评论 ID
     */
    private Integer rootId;

    /**
     * 父级评论 ID
     */
    private Integer parentId;

    /**
     * 标记当前评论是否为一级评论: true-一级评论,false-二级评论
     * 回复文章的是一级评论,其它的都是二级评论
     */
    private Boolean root;

    /**
     * 是否显示当前评论: true-显示,false-隐藏
     */
    private Boolean displayState;

    /**
     * 评论内容/回复内容
     */
    private String content;

    /**
     * 评论者当前 IP
     */
    private String ip;

    /**
     * 评论者当前 IP 解析出的详细地址
     */
    private String address;

    /**
     * 评论创建时间
     */
    private Date createTime;

    /**
     * 子评论列表
     */
    private List<CommentDetailVO> childCommentList;

}