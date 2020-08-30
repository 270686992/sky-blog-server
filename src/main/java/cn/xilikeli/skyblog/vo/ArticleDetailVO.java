package cn.xilikeli.skyblog.vo;

import cn.xilikeli.skyblog.model.Article;
import cn.xilikeli.skyblog.model.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 文章详情信息 VO
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 14:39
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ArticleDetailVO extends Article {

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章分类名称
     */
    private String categoryName;

    /**
     * 文章创建时间
     */
    private Date createTime;

    /**
     * 标签列表
     */
    private List<Tag> tagList;

    /**
     * 构造函数,构造一个文章详情信息 VO
     *
     * @param article      用于构造 VO 的文章实体
     * @param content      用于构造 VO 的文章内容
     * @param categoryName 用于构造 VO 的文章分类名称
     * @param tagList      用于构造 VO 的标签列表
     */
    public ArticleDetailVO(Article article, String content, String categoryName, List<Tag> tagList) {
        BeanUtils.copyProperties(article, this);
        this.content = content;
        this.categoryName = categoryName;
        this.tagList = tagList;
    }

}