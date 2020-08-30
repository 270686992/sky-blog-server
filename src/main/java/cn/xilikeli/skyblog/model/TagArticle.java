package cn.xilikeli.skyblog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>
 * 标签和文章间关系 Model
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
public class TagArticle {

    /**
     * 标签和文章间关系的 ID
     */
    @Id
    private Integer id;

    /**
     * 标签 ID
     */
    private Integer tagId;

    /**
     * 文章 ID
     */
    private Integer articleId;

}