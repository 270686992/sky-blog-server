package cn.xilikeli.skyblog.common.exception.http;

/**
 * <p>
 * 当访问接口时参数出现错误的自定义异常类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 11:35
 * @since JDK1.8
 */
public class ParameterException extends HttpException {

    private static final long serialVersionUID = -1846981373757073503L;

    /**
     * 构造函数
     *
     * @param code 消息码
     */
    public ParameterException(int code) {
        this.code = code;
        this.httpStatusCode = 400;
    }

}