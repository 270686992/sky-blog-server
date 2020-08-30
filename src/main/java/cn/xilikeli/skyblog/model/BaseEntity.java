package cn.xilikeli.skyblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * <p>
 * Model 的抽象基类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/19 - 13:12
 * @since JDK1.8
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    /**
     * 创建时间
     */
    @JsonIgnore
    @Column(insertable = false, updatable = false)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonIgnore
    @Column(insertable = false, updatable = false)
    private Date updateTime;

    /**
     * 删除时间
     */
    @JsonIgnore
    private Date deleteTime;

}