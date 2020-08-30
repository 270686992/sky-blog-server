package cn.xilikeli.skyblog.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>
 * 文章分类 Model
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
public class Category extends BaseEntity {

    /**
     * 文章分类 ID
     */
    @Id
    private Integer id;

    /**
     * 文章分类权重,权重值越低,展示的位置越上
     */
    private Integer priority;

    /**
     * 文章分类的上线状态: true-上线,false-下线
     */
    private Boolean online;

    /**
     * 文章分类名称
     */
    private String name;

    /**
     * 文章分类描述
     */
    private String description;

    /**
     * 文章分类图标 url
     */
    private String icon;

}