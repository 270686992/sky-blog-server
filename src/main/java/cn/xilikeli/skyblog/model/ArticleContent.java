package cn.xilikeli.skyblog.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>
 * 文章内容 Model
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
public class ArticleContent extends BaseEntity {

    /**
     * 文章内容 ID
     */
    @Id
    private Integer id;

    /**
     * 文章内容所属文章的 ID
     */
    private Integer articleId;

    /**
     * 文章内容
     */
    private String content;

}