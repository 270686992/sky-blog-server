package cn.xilikeli.skyblog.common.enumeration;

import java.util.stream.Stream;

/**
 * <p>
 * 邮件类型枚举类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 12:03
 * @since JDK1.8
 */
public enum EmailKindEnum {

    /**
     * 游客评论时的通知邮件
     */
    CUSTOMER_COMMENT(1, "游客评论时的通知邮件"),

    /**
     * 游客留言时的通知邮件
     */
    CUSTOMER_LEAVE_MESSAGE(2, "游客留言时的通知邮件"),

    /**
     * 不支持的邮件类型
     */
    NONEXISTENT_EMAIL_KIND(60000, "不支持的邮件类型");;

    /**
     * 枚举值
     */
    private Integer value;

    /**
     * 枚举描述
     */
    private String description;

    /**
     * 构造函数
     *
     * @param value       枚举值
     * @param description 枚举描述
     */
    EmailKindEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * 将枚举值 value 转换为对应的枚举类型
     *
     * @param value 枚举值
     * @return 转换成功返回枚举值 value 对应的枚举类型,转换失败返回不支持的邮件类型
     */
    public static EmailKindEnum toType(Integer value) {
        // 通过流的方式从所有枚举值中过滤出值为 value 的枚举类型返回,找不到则返回不支持的邮件类型
        return Stream.of(EmailKindEnum.values())
                .filter(emailKind -> emailKind.value.equals(value))
                .findAny()
                .orElse(NONEXISTENT_EMAIL_KIND);
    }

}