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
 * 博客用户认证信息 Model
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
public class CustomerIdentity extends BaseEntity {

    /**
     * 博客用户认证信息 ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    /**
     * 认证信息所属博客用户的 ID
     */
    private Integer customerId;

    /**
     * 登录类型(手机号,邮箱,用户名或第三方应用名称(微信,微博等)
     */
    private String identityType;

    /**
     * 标识(手机号,邮箱,用户名或第三方应用的唯一标识)
     */
    private String identifier;

    /**
     * 密码凭证(站内的保存密码,站外的不保存或保存 token)
     */
    private String credential;

    /**
     * 找回密码问题
     */
    private String question;

    /**
     * 找回密码答案
     */
    private String answer;

}