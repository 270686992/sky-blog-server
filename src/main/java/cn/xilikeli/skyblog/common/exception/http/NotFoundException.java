package cn.xilikeli.skyblog.common.exception.http;

/**
 * <p>
 * 当访问接口时找不到资源的自定义异常类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 11:33
 * @since JDK1.8
 */
public class NotFoundException extends HttpException {

    private static final long serialVersionUID = -7374768886502508477L;

    /**
     * 构造函数
     *
     * @param code 消息码
     */
    public NotFoundException(int code) {
        this.code = code;
        this.httpStatusCode = 404;
    }

}