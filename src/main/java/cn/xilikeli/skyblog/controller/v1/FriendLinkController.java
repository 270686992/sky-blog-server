package cn.xilikeli.skyblog.controller.v1;

import cn.xilikeli.skyblog.model.FriendLink;
import cn.xilikeli.skyblog.service.FriendLinkService;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 友情链接业务接口控制器
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 21:35
 * @since JDK1.8
 */
@RequestMapping("/v1/friend-link")
@Validated
@RestController
@AllArgsConstructor
public class FriendLinkController {

    private final FriendLinkService friendLinkService;

    /**
     * 根据友情链接类型获取相应友情链接列表
     *
     * @param kind 友情链接类型: 0-友情链接,1-推荐链接,2-站长个人链接
     * @return 返回获取的指定类型的友情链接列表
     */
    @GetMapping("/list")
    public List<FriendLink> getFriendLinkListByKind(@RequestParam(name = "kind")
                                                    @NotNull(message = "{friend-link.kind.not-null}")
                                                    @Range(min = 0, max = 2, message = "{friend-link.kind.range}") Integer kind) {
        return this.friendLinkService.getFriendLinkListByKind(kind);
    }

}