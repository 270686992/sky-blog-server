package cn.xilikeli.skyblog.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 * 文章评论 Model
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/19 - 13:11
 * @since JDK1.8
 */
@Entity
@Getter
@Setter
@Where(clause = "delete_time is null")
public class Comment extends BaseEntity {

    /**
     * 文章评论 ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    /**
     * 评论所属文章的 ID
     */
    private Integer articleId;

    /**
     * 评论所属用户的 ID
     */
    private Integer customerId;

    /**
     * 评论所属管理员用户的 ID(管理员给评论回复)
     */
    private Integer adminUserId;

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

}