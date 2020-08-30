package cn.xilikeli.skyblog.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>
 * 友情链接 Model
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
public class FriendLink extends BaseEntity {

    /**
     * 友情链接 ID
     */
    @Id
    private Integer id;

    /**
     * 友情链接权重,权重值越低,展示的位置越上
     */
    private Integer priority;

    /**
     * 友情链接类型: 0-友情链接,1-推荐链接,2-站长个人链接
     */
    private Integer kind;

    /**
     * 是否显示当前友情链接: true-显示,false-隐藏
     */
    private Boolean displayState;

    /**
     * 友情链接名称
     */
    private String name;

    /**
     * 友情链接 url
     */
    private String url;

    /**
     * 友情链接图标 url
     */
    private String icon;

    /**
     * 友情链接描述
     */
    private String description;

}