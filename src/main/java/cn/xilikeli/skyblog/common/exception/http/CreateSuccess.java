package cn.xilikeli.skyblog.common.exception.http;

/**
 * <p>
 * 用于返回创建资源成功响应结果的自定义异常类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 11:40
 * @since JDK1.8
 */
public class CreateSuccess extends HttpException {

    private static final long serialVersionUID = -8784297409086438103L;

    /**
     * 构造函数
     *
     * @param code 消息码
     */
    public CreateSuccess(int code) {
        this.code = code;
        this.httpStatusCode = 201;
    }

}