package cn.xilikeli.skyblog.service.impl;

import cn.xilikeli.skyblog.common.constant.CodeMessageConstant;
import cn.xilikeli.skyblog.common.exception.http.NotFoundException;
import cn.xilikeli.skyblog.common.manager.redis.RedisKeyConstant;
import cn.xilikeli.skyblog.common.util.JsonUtil;
import cn.xilikeli.skyblog.common.manager.redis.RedisOperator;
import cn.xilikeli.skyblog.model.WebSiteInfo;
import cn.xilikeli.skyblog.repository.WebSiteInfoRepository;
import cn.xilikeli.skyblog.service.WebSiteInfoService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 站点信息业务操作接口类的实现类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 20:57
 * @since JDK1.8
 */
@Service
@AllArgsConstructor
public class WebSiteInfoServiceImpl implements WebSiteInfoService {

    private final WebSiteInfoRepository webSiteInfoRepository;

    private final RedisOperator redisOperator;

    @Override
    public WebSiteInfo getWebSiteInfo() {
        String webSiteInfoStr = this.redisOperator.get(RedisKeyConstant.WEB_SITE_INFO_KEY);
        WebSiteInfo webSiteInfo;

        if (StringUtils.isBlank(webSiteInfoStr)) {
            // redis 中没有,从数据库取并设置到 redis 中
            List<WebSiteInfo> all = this.webSiteInfoRepository.findAll();

            if (all.isEmpty()) {
                throw new NotFoundException(CodeMessageConstant.NOT_FOUND_WEB_SITE_INFO);
            }

            webSiteInfo = all.get(0);

            // 将数据缓存到 redis 中,过期时间 30 天
            this.redisOperator.set(RedisKeyConstant.WEB_SITE_INFO_KEY, JsonUtil.objectToJson(webSiteInfo), 3600 * 24 * 30);
        } else {
            // redis 中有,从 redis 中取
            webSiteInfo = JsonUtil.jsonToPojo(webSiteInfoStr, WebSiteInfo.class);
        }

        return webSiteInfo;
    }
}