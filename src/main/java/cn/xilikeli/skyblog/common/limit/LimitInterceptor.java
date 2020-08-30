package cn.xilikeli.skyblog.common.limit;

import cn.xilikeli.skyblog.common.constant.CodeMessageConstant;
import cn.xilikeli.skyblog.common.exception.http.RequestLimitException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 接口限流拦截器
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/23 - 21:55
 * @since JDK1.8
 */
@Slf4j
public class LimitInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private Limiter limiter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean ok = limiter.handle(request);
        log.info("接口限流-限流结果 : {}", ok);
        if (!ok) {
            throw new RequestLimitException(CodeMessageConstant.TOO_MANY_REQUESTS);
        }
        return super.preHandle(request, response, handler);
    }

}