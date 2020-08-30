package cn.xilikeli.skyblog.controller.v1;

import cn.xilikeli.skyblog.common.UnifyResponse;
import cn.xilikeli.skyblog.common.constant.CodeMessageConstant;
import cn.xilikeli.skyblog.common.interceptor.ScopeLevel;
import cn.xilikeli.skyblog.dto.comment.CommentDTO;
import cn.xilikeli.skyblog.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章评论业务接口控制器
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 12:50
 * @since JDK1.8
 */
@RequestMapping("/v1/comment")
@Validated
@RestController
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 添加一个评论
     *
     * @param commentDTO 评论的数据传输对象
     */
    @PostMapping("")
    @ScopeLevel
    public void createComment(@RequestBody @Validated CommentDTO commentDTO) {
        this.commentService.createComment(commentDTO);
        UnifyResponse.createSuccess(CodeMessageConstant.CREATE_COMMENT_SUCCESS);
    }

}