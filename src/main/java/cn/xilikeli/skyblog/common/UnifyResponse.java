package cn.xilikeli.skyblog.common;

import cn.xilikeli.skyblog.common.exception.http.CreateSuccess;
import cn.xilikeli.skyblog.common.exception.http.DeleteSuccess;
import cn.xilikeli.skyblog.common.exception.http.UpdateSuccess;
import lombok.Getter;

/**
 * <p>
 * 统一响应结果类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 11:04
 * @since JDK1.8
 */
@Getter
public class UnifyResponse {

    /**
     * 消息码
     */
    private int code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 请求的 url
     */
    private String request;

    public UnifyResponse(int code, String message, String request) {
        this.code = code;
        this.message = message;
        this.request = request;
    }

    /**
     * 根据消息码返回创建资源成功的响应结果
     *
     * @param code 消息码
     */
    public static void createSuccess(int code) {
        throw new CreateSuccess(code);
    }

    /**
     * 根据消息码返回更新资源成功的响应结果
     *
     * @param code 消息码
     */
    public static void updateSuccess(int code) {
        throw new UpdateSuccess(code);
    }

    /**
     * 根据消息码返回删除资源成功的响应结果
     *
     * @param code 消息码
     */
    public static void deleteSuccess(int code) {
        throw new DeleteSuccess(code);
    }

}