package cn.xilikeli.skyblog.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 留言详情信息 VO
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 22:03
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
public class LeaveMessageDetailVO {

    /**
     * 留言 ID
     */
    private Integer id;

    /**
     * 留言所属用户的昵称
     */
    private String nickname;

    /**
     * 留言所属用户的邮箱
     */
    private String email;

    /**
     * 留言所属用户的头像 url
     */
    private String avatar;

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

    /**
     * 留言创建时间
     */
    private Date createTime;

    /**
     * 子留言列表
     */
    private List<LeaveMessageDetailVO> childLeaveMessageList;

}