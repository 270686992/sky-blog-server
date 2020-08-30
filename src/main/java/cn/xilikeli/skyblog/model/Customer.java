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
 * 博客用户 Model
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
public class Customer extends BaseEntity {

    /**
     * 博客用户 ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户状态,1-正常,0-冻结
     */
    private Integer state;

    /**
     * 用户头像 url
     */
    private String avatar;

}