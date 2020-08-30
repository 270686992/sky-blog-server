package cn.xilikeli.skyblog.common.interceptor;

import cn.xilikeli.skyblog.common.LocalCustomer;
import cn.xilikeli.skyblog.common.constant.CodeMessageConstant;
import cn.xilikeli.skyblog.common.constant.CommonConstant;
import cn.xilikeli.skyblog.common.exception.http.ForbiddenException;
import cn.xilikeli.skyblog.common.exception.http.UnAuthenticatedException;
import cn.xilikeli.skyblog.common.util.JwtTokenUtil;
import cn.xilikeli.skyblog.model.Customer;
import cn.xilikeli.skyblog.service.CustomerService;
import com.auth0.jwt.interfaces.Claim;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 权限拦截器
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 17:42
 * @since JDK1.8
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private CustomerService customerService;

    public PermissionInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求进入 Controller 之前的处理,放行返回 true,不放行返回 false
        // 获取当前要访问的 API 的 ScopeLevel
        Optional<ScopeLevel> scopeLevel = this.getScopeLevel(handler);
        if (!scopeLevel.isPresent()) {
            // 公开 API,返回 true
            return true;
        }

        // 获取请求中的 JWT 令牌(传递 token 的键: Authorization)
        String bearerToken = request.getHeader("Authorization");

        // 判断令牌是否为空
        if (StringUtils.isBlank(bearerToken)) {
            throw new UnAuthenticatedException(CodeMessageConstant.TOKEN_INVALID);
        }

        // 判断令牌是否开头带有 Bearer
        if (!bearerToken.startsWith(CommonConstant.BEARER)) {
            throw new UnAuthenticatedException(CodeMessageConstant.TOKEN_INVALID);
        }

        // 对令牌判空,避免数组越界
        String[] tokens = bearerToken.split(CommonConstant.SPACE);
        if (tokens.length != 2) {
            throw new UnAuthenticatedException(CodeMessageConstant.TOKEN_INVALID);
        }

        // 提取 JWT 令牌
        String token = tokens[1];
        // 校验 token
        Optional<Map<String, Claim>> optionalMap = JwtTokenUtil.verifyTokenAndGetClaims(token);
        Map<String, Claim> map = optionalMap.orElseThrow(() -> new UnAuthenticatedException(CodeMessageConstant.TOKEN_INVALID));
        // 令牌合法,比对用户分组级别判断当前用户是否拥有足够的权限
        boolean valid = this.hasPermission(scopeLevel.get(), map);
        if (valid) {
            // 比对通过,设置当前登录用户的信息
            this.setToThreadLocal(map);
        }
        return valid;
    }

    /**
     * 判断令牌中携带的用户信息是否具有访问 API 的权限
     *
     * @param scopeLevel 用户分组级别
     * @param map        令牌中的信息
     * @return 具有访问权限返回 true,没有访问权限抛出一个禁止操作异常
     */
    private boolean hasPermission(ScopeLevel scopeLevel, Map<String, Claim> map) {
        // 获取要访问的 API 的级别的值
        int level = scopeLevel.value();
        // 获取令牌中携带的用户级别
        int scope = map.get("scope").asInt();
        // 进行级别比对判断
        if (level > scope) {
            // 用户级别低于 API 级别,没有权限
            throw new ForbiddenException(CodeMessageConstant.UNAUTHORIZED_CUSTOMER);
        }
        // 用户级别大于等于 API 级别,拥有权限,返回 true
        return true;
    }

    /**
     * 设置当前登录用户的信息到 ThreadLocal 中
     *
     * @param map 令牌中的信息
     */
    private void setToThreadLocal(Map<String, Claim> map) {
        // 获取用户 ID 和用户级别
        Integer uid = map.get("uid").asInt();
        Integer scope = map.get("scope").asInt();
        // 获取当前登录用户的信息
        Customer customer = this.customerService.getCustomerById(uid);
        // 设置到 ThreadLocal 中
        LocalCustomer.setCustomer(customer, scope);
    }

    /**
     * 从要访问的 API 的方法上读取 ScopeLevel
     *
     * @param handler 用于获取要访问的 API 的方法的信息
     * @return 返回要访问的 API 的方法上的 ScopeLevel
     */
    private Optional<ScopeLevel> getScopeLevel(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取 ScopeLevel 注解
            ScopeLevel scopeLevel = handlerMethod.getMethod().getAnnotation(ScopeLevel.class);
            if (scopeLevel == null) {
                return Optional.empty();
            }
            // 确定 scopeLevel 必须要有值的话,使用 of 方法,如果没有值会在这里报错,容易早发现
            return Optional.of(scopeLevel);
        }
        return Optional.empty();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // SpringBoot 渲染页面之前的处理(可以修改页面),这里不使用
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清理资源的处理
        // 释放 threadLocal 线程资源,即清理当前登录用户
        LocalCustomer.clear();
        super.afterCompletion(request, response, handler, ex);
    }

}