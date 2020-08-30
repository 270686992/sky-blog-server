package cn.xilikeli.skyblog.controller.v1;

import cn.xilikeli.skyblog.model.Tag;
import cn.xilikeli.skyblog.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 标签业务接口控制器
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 21:29
 * @since JDK1.8
 */
@RequestMapping("/v1/tag")
@RestController
@AllArgsConstructor
public class TagController {

    private final TagService tagService;

    /**
     * 获取所有标签
     *
     * @return 返回包含所有标签的列表
     */
    @GetMapping("/list")
    public List<Tag> getAllTag() {
        return this.tagService.getAllTag();
    }

}