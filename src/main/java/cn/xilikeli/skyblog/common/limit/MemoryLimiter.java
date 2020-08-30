package cn.xilikeli.skyblog.common.limit;

import cn.xilikeli.skyblog.common.util.HttpRequestProxy;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 接口限流组件
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/23 - 21:52
 * @since JDK1.8
 */
@Component
@Slf4j
public class MemoryLimiter implements Limiter {

    /**
     * 记录器
     */
    private Map<String, RateLimiter> record = new ConcurrentHashMap<>();

    @Value("${sky-blog.limit.value:5}")
    private Integer value;

    @Override
    public boolean handle(HttpServletRequest request) {
        // 获取当前请求的 ip 地址作为限流的 id
        String uniqueId = getUniqueId();
        log.info("接口限流-uniqueId: {}", uniqueId);

        RateLimiter currentLimiter = record.get(uniqueId);
        if (currentLimiter != null) {
            // 尝试获取 1 个令牌，不会阻塞当前线程。立即返回是否获取成功(即立即返回 true 或 false)。
            return currentLimiter.tryAcquire(1);
        } else {
            // 减去当前访问的一次
            RateLimiter limiter = RateLimiter.create(value);
            record.put(uniqueId, limiter);
            return true;
        }
    }

    /**
     * 获取当前请求的 ip 地址作为限流的 id
     *
     * @return 返回当前请求的 ip 地址作为限流的 id
     */
    private String getUniqueId() {
        return HttpRequestProxy.getRemoteRealIp();
    }

}