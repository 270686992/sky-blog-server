package cn.xilikeli.skyblog.common.exception.http;

/**
 * <p>
 * 当访问接口时操作失败了的自定义异常类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/23 - 14:19
 * @since JDK1.8
 */
public class FailedException extends HttpException {

    private static final long serialVersionUID = 2032595962150252935L;

    /**
     * 构造函数
     *
     * @param code 消息码
     */
    public FailedException(int code) {
        this.code = code;
        this.httpStatusCode = 500;
    }

}