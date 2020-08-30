package cn.xilikeli.skyblog.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * <p>
 * 文章 Model
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
public class Article extends BaseEntity {

    /**
     * 文章 ID
     */
    @Id
    private Integer id;

    /**
     * 文章所属分类的 ID
     */
    private Integer categoryId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章简述
     */
    private String description;

    /**
     * 文章封面图 url
     */
    private String coverImage;

    /**
     * 文章阅读量
     */
    private Integer views;

    /**
     * 文章权重,权重值越低,展示的位置越上
     */
    private Integer priority;

    /**
     * 文章类型: 1-原创,0-转载
     */
    private Integer kind;

    /**
     * 文章评论数量
     */
    private Integer commentNumber;

    /**
     * 文章发布状态: true-发布,false-私密
     */
    private Boolean publishState;

    /**
     * 文章评论开启状态: true-允许评论,false-不允许评论
     */
    private Boolean enableComment;

}