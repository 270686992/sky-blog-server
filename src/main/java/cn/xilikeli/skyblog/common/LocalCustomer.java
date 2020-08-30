package cn.xilikeli.skyblog.common;

import cn.xilikeli.skyblog.model.Customer;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 线程安全的当前登录用户, 如果用户未登录, 则得到 null
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 17:30
 * @since JDK1.8
 */
public class LocalCustomer {

    /**
     * 用于注入用户信息的 threadLocal
     */
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    /**
     * 获取当前登录用户的信息
     *
     * @return 返回当前登录用户信息或 null
     */
    public static Customer getCustomer() {
        Map<String, Object> map = LocalCustomer.threadLocal.get();
        return (Customer) map.get("customer");
    }

    /**
     * 获取当前登录用户的分组级别信息
     *
     * @return 返回当前登录用户的分组级别信息或 null
     */
    public static Integer getScope() {
        Map<String, Object> map = LocalCustomer.threadLocal.get();
        return (Integer) map.get("scope");
    }

    /**
     * 设置当前登录用户的信息和其分组级别信息
     *
     * @param customer 当前登录用户
     * @param scope    当前登录用户的分组级别
     */
    public static void setCustomer(Customer customer, Integer scope) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("customer", customer);
        map.put("scope", scope);
        LocalCustomer.threadLocal.set(map);
    }

    /**
     * 释放 threadLocal 线程资源,即清理当前登录用户
     */
    public static void clear() {
        LocalCustomer.threadLocal.remove();
    }

}