package cn.xilikeli.skyblog.common.enumeration;

/**
 * <p>
 * 留言级别枚举类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/23 - 17:30
 * @since JDK1.8
 */
public enum LeaveMessageLevelEnum {

    /**
     * 一级留言
     */
    ROOT(1, "一级留言"),

    /**
     * 二级留言
     */
    NOT_ROOT(0, "二级留言");

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
    LeaveMessageLevelEnum(Integer value, String description) {
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