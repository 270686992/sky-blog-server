package cn.xilikeli.skyblog.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 * 留言 Model
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
public class LeaveMessage extends BaseEntity {

    /**
     * 留言 ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    /**
     * 留言所属用户的 ID
     */
    private Integer customerId;

    /**
     * 留言所属管理员用户的 ID(管理员给留言回复)
     */
    private Integer adminUserId;

    /**
     * 根留言 ID,即一级留言 ID
     */
    private Integer rootId;

    /**
     * 父级留言 ID
     */
    private Integer parentId;

    /**
     * 是否显示当前留言: true-显示,false-隐藏
     */
    private Boolean displayState;

    /**
     * 标记当前留言是否为一级留言: true-一级留言,false-二级留言
     */
    private Boolean root;

    /**
     * 留言内容/回复内容
     */
    private String content;

    /**
     * 留言者当前 IP
     */
    private String ip;

    /**
     * 留言者当前 IP 解析出的详细地址
     */
    private String address;

}