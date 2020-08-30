package cn.xilikeli.skyblog.service;

import cn.xilikeli.skyblog.model.WebSiteInfo;

/**
 * <p>
 * 站点信息业务操作接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 20:57
 * @since JDK1.8
 */
public interface WebSiteInfoService {

    /**
     * 获取博客站点通用信息
     *
     * @return 返回获取的博客站点通用信息
     */
    WebSiteInfo getWebSiteInfo();

}