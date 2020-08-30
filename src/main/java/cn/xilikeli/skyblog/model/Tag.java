package cn.xilikeli.skyblog.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>
 * 标签 Model
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
@Where(clause = "delete_time is null and online = 1")
public class Tag extends BaseEntity {

    /**
     * 标签 ID
     */
    @Id
    private Integer id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签的上线状态: true-上线,false-下线
     */
    private Boolean online;

}