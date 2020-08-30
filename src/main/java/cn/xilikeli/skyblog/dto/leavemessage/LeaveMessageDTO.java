package cn.xilikeli.skyblog.dto.leavemessage;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <p>
 * 留言的数据传输类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 22:58
 * @since JDK1.8
 */
@Data
public class LeaveMessageDTO {

    /**
     * 根留言 ID,即一级留言 ID
     */
    @Min(value = 0, message = "{leave-message.root-id.min}")
    private Integer rootId;

    /**
     * 父级留言 ID
     */
    @Min(value = 0, message = "{leave-message.parent-id.min}")
    private Integer parentId;

    /**
     * 标记当前留言是否为一级留言: 1-一级留言,0-二级留言
     */
    @NotNull(message = "{leave-message.root.not-null}")
    @Range(min = 0, max = 1, message = "{leave-message.root.range}")
    private Integer root;

    /**
     * 留言内容
     */
    @NotBlank(message = "{leave-message.content.not-blank}")
    @Size(min = 1, max = 500, message = "{leave-message.content.length}")
    private String content;

}