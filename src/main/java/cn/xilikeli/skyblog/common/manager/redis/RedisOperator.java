package cn.xilikeli.skyblog.common.manager.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 使用 redisTemplate 的 redis 操作工具类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/23 - 23:20
 * @since JDK1.8
 */
@Component
public class RedisOperator {

    private final StringRedisTemplate redisTemplate;

    public RedisOperator(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 实现命令: TTL key, 以秒为单位, 返回给定 key 的剩余生存时间
     *
     * @param key 指定的 key
     * @return 以秒为单位, 返回给定 key 的剩余生存时间
     */
    public Long ttl(String key) {
        return this.redisTemplate.getExpire(key);
    }

    /**
     * 实现命令: expire , 设置指定 key 的过期时间, 单位秒
     *
     * @param key 指定的 key
     */
    public void expire(String key, long timeout) {
        this.redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令: DEL key, 删除一个key
     *
     * @param key 指定的 key
     */
    public void del(String key) {
        this.redisTemplate.delete(key);
    }

    /**
     * 实现命令: SET key value, 设置一个 key-value（将字符串值 value 关联到 key）
     *
     * @param key   指定的 key
     * @param value 指定的 value
     */
    public void set(String key, String value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 实现命令: SET key value EX seconds, 设置 key-value 和过期时间（秒）
     *
     * @param key     指定的 key
     * @param value   指定的 value
     * @param timeout 过期时间（以秒为单位）
     */
    public void set(String key, String value, long timeout) {
        this.redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令：GET key，返回 key 所关联的字符串值。
     *
     * @param key 指定的 key
     * @return 返回 key 所关联的字符串值
     */
    public String get(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }

}