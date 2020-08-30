package cn.xilikeli.skyblog.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * <p>
 * 邮件 Model
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 11:52
 * @since JDK1.8
 */
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class Email {

    /**
     * 收件人邮箱
     */
    private String recipientEmail;

    /**
     * 收件人昵称
     */
    private String nickname;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 邮件类型: 1-游客评论时的通知邮件,2-游客留言时的通知邮件
     */
    private Integer emailKind;

    /**
     * 自定义参数(非必填)
     */
    private Map<String, Object> customParameters;

}