package cn.xilikeli.skyblog.common.exception.http;

/**
 * <p>
 * 当访问没有权限的接口时的自定义异常类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 11:38
 * @since JDK1.8
 */
public class UnAuthenticatedException extends HttpException {

    private static final long serialVersionUID = 1951236468090562086L;

    /**
     * 构造函数
     *
     * @param code 消息码
     */
    public UnAuthenticatedException(int code) {
        this.code = code;
        this.httpStatusCode = 401;
    }

}