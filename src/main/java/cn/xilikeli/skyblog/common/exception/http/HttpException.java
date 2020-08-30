package cn.xilikeli.skyblog.common.exception.http;

import lombok.Getter;

/**
 * <p>
 * HTTP 自定义异常基类
 * 其子类用于处理各种情况的请求发生的异常
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 11:28
 * @since JDK1.8
 */
@Getter
public class HttpException extends RuntimeException {

    private static final long serialVersionUID = -7989987511474462370L;

    /**
     * 消息码
     */
    protected int code;

    /**
     * HTTP 状态码
     */
    protected int httpStatusCode = 500;

}