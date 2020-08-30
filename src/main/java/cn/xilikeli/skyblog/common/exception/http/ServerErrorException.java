package cn.xilikeli.skyblog.common.exception.http;

/**
 * <p>
 * 当访问接口时产生服务器内部错误的自定义异常类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 11:37
 * @since JDK1.8
 */
public class ServerErrorException extends HttpException {

    private static final long serialVersionUID = -981824306460277537L;

    /**
     * 构造函数
     *
     * @param code 消息码
     */
    public ServerErrorException(int code) {
        this.code = code;
        this.httpStatusCode = 500;
    }

}