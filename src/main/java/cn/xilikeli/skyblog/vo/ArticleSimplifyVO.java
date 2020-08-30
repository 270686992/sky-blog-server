package cn.xilikeli.skyblog.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 * 文章概要信息 VO
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 12:52
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
public class ArticleSimplifyVO {

    /**
     * 文章 ID
     */
    private Integer id;

    /**
     * 文章所属分类的 ID
     */
    private Integer categoryId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章简述
     */
    private String description;

    /**
     * 文章封面图 url
     */
    private String coverImage;

    /**
     * 文章阅读量
     */
    private Integer views;

    /**
     * 文章权重,权重值越低,展示的位置越上
     */
    private Integer priority;

    /**
     * 文章类型: 1-原创,0-转载
     */
    private Integer kind;

    /**
     * 文章评论数量
     */
    private Integer commentNumber;

    /**
     * 文章发布状态: true-发布,false-私密
     */
    private Boolean publishState;

    /**
     * 文章创建时间
     */
    private Date createTime;

}