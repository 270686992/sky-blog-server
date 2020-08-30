package cn.xilikeli.skyblog.dto.comment;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

/**
 * <p>
 * 文章评论的数据传输类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 13:00
 * @since JDK1.8
 */
@Data
public class CommentDTO {

    /**
     * 评论所属文章的 ID
     */
    @NotNull(message = "{comment.article-id.not-null}")
    @Positive(message = "{comment.article-id.positive}")
    private Integer articleId;

    /**
     * 根评论 ID,即一级评论 ID
     */
    @Min(value = 0, message = "{comment.root-id.min}")
    private Integer rootId;

    /**
     * 父级评论 ID
     */
    @Min(value = 0, message = "{comment.parent-id.min}")
    private Integer parentId;

    /**
     * 标记当前评论是否为一级评论: 1-一级评论,0-二级评论
     */
    @NotNull(message = "{comment.root.not-null}")
    @Range(min = 0, max = 1, message = "{comment.root.range}")
    private Integer root;

    /**
     * 评论内容
     */
    @NotBlank(message = "{comment.content.not-blank}")
    @Size(min = 1, max = 500, message = "{comment.content.length}")
    private String content;

}