package cn.xilikeli.skyblog.controller.v1;

import cn.xilikeli.skyblog.model.WebSiteInfo;
import cn.xilikeli.skyblog.service.WebSiteInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 站点信息业务接口控制器
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 20:56
 * @since JDK1.8
 */
@RequestMapping("/v1/web-site-info")
@RestController
@AllArgsConstructor
public class WebSiteInfoController {

    private final WebSiteInfoService webSiteInfoService;

    /**
     * 获取博客站点通用信息,由于 CMS 端限制最多只能添加一条站点信息,所以这里设计为查询所有站点信息的方式从中返回第一条
     *
     * @return 返回获取的博客站点通用信息
     */
    @GetMapping("")
    public WebSiteInfo getWebSiteInfo() {
        return this.webSiteInfoService.getWebSiteInfo();
    }

}