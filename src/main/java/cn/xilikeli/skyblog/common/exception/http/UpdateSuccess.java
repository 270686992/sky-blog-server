package cn.xilikeli.skyblog.common.exception.http;

/**
 * <p>
 * 用于返回更新资源成功响应结果的自定义异常类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 11:53
 * @since JDK1.8
 */
public class UpdateSuccess extends HttpException {

    private static final long serialVersionUID = -7292822015854367767L;

    /**
     * 构造函数
     *
     * @param code 消息码
     */
    public UpdateSuccess(int code) {
        this.code = code;
        this.httpStatusCode = 200;
    }

}