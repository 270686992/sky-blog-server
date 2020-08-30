package cn.xilikeli.skyblog.common.enumeration;

/**
 * <p>
 * 文章评论级别枚举类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/23 - 17:29
 * @since JDK1.8
 */
public enum CommentLevelEnum {

    /**
     * 一级评论
     */
    ROOT(1, "一级评论"),

    /**
     * 二级评论
     */
    NOT_ROOT(0, "二级评论");

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
    CommentLevelEnum(Integer value, String description) {
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

}