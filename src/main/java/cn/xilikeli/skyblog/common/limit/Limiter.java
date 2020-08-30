package cn.xilikeli.skyblog.common.limit;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 接口限流接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/23 - 21:49
 * @since JDK1.8
 */
public interface Limiter {

    /**
     * 监听请求是否允许被访问
     *
     * @param request 请求
     * @return 返回 true 允许请求被访问,返回 false 拒绝请求被访问
     */
    boolean handle(HttpServletRequest request);

}