package cn.xilikeli.skyblog.common.exception.http;

/**
 * <p>
 * 用于返回删除资源成功响应结果的自定义异常类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 11:51
 * @since JDK1.8
 */
public class DeleteSuccess extends HttpException {

    private static final long serialVersionUID = -8434374191836584139L;

    /**
     * 构造函数
     *
     * @param code 消息码
     */
    public DeleteSuccess(int code) {
        this.code = code;
        this.httpStatusCode = 200;
    }

}