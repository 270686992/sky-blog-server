package cn.xilikeli.skyblog.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>
 * 站点信息 Model
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
public class WebSiteInfo extends BaseEntity {

    /**
     * 站点信息 ID
     */
    @Id
    private Integer id;

    /**
     * 站点名称
     */
    private String name;

    /**
     * 站点个性标题
     */
    private String title;

    /**
     * 站点个性签名
     */
    private String signature;

    /**
     * 版权时间,例如: 2020-2020
     */
    private String copyrightTime;

    /**
     * 站点版本号,例如: V1.0.0
     */
    private String versionNumber;

    /**
     * ICP 备案号
     */
    private String icpNumber;

    /**
     * 站点作者昵称,用于展示在前台
     */
    private String nickname;

    /**
     * 站点作者默认头像 url,站点管理用户没有设置头像时,进行评论/留言的回复时使用该默认头像
     */
    private String authorAvatar;

    /**
     * 站点图标 url
     */
    private String icon;

    /**
     * 评论者/留言者默认头像 url,评论/留言用户没有设置头像时,进行评论/留言时使用该默认头像
     */
    private String commentatorAvatar;

    /**
     * 友链申请说明
     */
    private String applicationExplain;

    /**
     * 关于我的说明
     */
    private String aboutMeExplain;

    /**
     * 关于站点的说明
     */
    private String aboutSiteExplain;

    /**
     * 关于版权的说明
     */
    private String aboutCopyrightExplain;

    /**
     * 特别说明
     */
    private String specialExplain;

    /**
     * 站点公告
     */
    private String announcement;

}