package cn.xilikeli.skyblog.service;

import cn.xilikeli.skyblog.dto.comment.CommentDTO;

/**
 * <p>
 * 文章评论业务操作接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 12:49
 * @since JDK1.8
 */
public interface CommentService {

    /**
     * 添加一个评论
     *
     * @param commentDTO 评论的数据传输对象
     */
    void createComment(CommentDTO commentDTO);

}