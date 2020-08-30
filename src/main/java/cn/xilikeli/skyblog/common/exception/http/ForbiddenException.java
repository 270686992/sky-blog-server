package cn.xilikeli.skyblog.common.exception.http;

/**
 * <p>
 * 当访问接口时被拒绝访问的自定义异常类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 11:30
 * @since JDK1.8
 */
public class ForbiddenException extends HttpException {

    private static final long serialVersionUID = -8237350414548974953L;

    /**
     * 构造函数
     *
     * @param code 消息码
     */
    public ForbiddenException(int code) {
        this.code = code;
        this.httpStatusCode = 403;
    }

}