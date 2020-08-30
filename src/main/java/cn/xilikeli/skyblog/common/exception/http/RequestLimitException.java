package cn.xilikeli.skyblog.common.exception.http;

/**
 * <p>
 * 当访问接口超过限制 QPS 时的自定义异常类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/23 - 22:04
 * @since JDK1.8
 */
public class RequestLimitException extends HttpException {

    private static final long serialVersionUID = 6760092257562723357L;

    /**
     * 构造函数
     *
     * @param code 消息码
     */
    public RequestLimitException(int code) {
        this.code = code;
        this.httpStatusCode = 429;
    }

}