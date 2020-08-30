package cn.xilikeli.skyblog.common.constant;

/**
 * <p>
 * 消息码常量类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 10:52
 * @since JDK1.8
 */
public class CodeMessageConstant {

    /**
     * 通用成功消息码
     */
    public static final int OK = 0;

    /**
     * 注册用户成功消息码
     */
    public static final int CREATE_CUSTOMER_SUCCESS = 20;

    /**
     * 修改密码成功消息码
     */
    public static final int UPDATE_PASSWORD_SUCCESS = 30;

    /**
     * 修改用户信息成功消息码
     */
    public static final int UPDATE_CUSTOMER_INFORMATION_SUCCESS = 40;



    /**
     * 服务器未知异常消息码
     */
    public static final int SERVER_ERROR = 9999;

    /**
     * 通用错误消息码
     */
    public static final int ERROR = 10000;

    /**
     * 通用参数错误消息码
     */
    public static final int PARAMETER_ERROR = 10001;

    /**
     * 未找到相关用户消息码
     */
    public static final int NOT_FOUND_CUSTOMER = 10002;

    /**
     * token 无效消息码
     */
    public static final int TOKEN_INVALID = 10003;

    /**
     * 用户无权限消息码
     */
    public static final int UNAUTHORIZED_CUSTOMER = 10004;

    /**
     * 用户名重复消息码
     */
    public static final int USERNAME_REPEAT = 10005;

    /**
     * 邮箱重复消息码
     */
    public static final int EMAIL_REPEAT = 10006;

    /**
     * 昵称重复消息码
     */
    public static final int NICKNAME_REPEAT = 10007;

    /**
     * 注册用户失败消息码
     */
    public static final int CREATE_CUSTOMER_FAILED = 10008;

    /**
     * 用户名或密码错误消息码
     */
    public static final int USERNAME_OR_PASSWORD_ERROR = 10009;

    /**
     * 修改密码失败消息码
     */
    public static final int UPDATE_PASSWORD_FAILED = 10010;

    /**
     * 老密码错误消息码
     */
    public static final int OLD_PASSWORD_ERROR = 10011;

    /**
     * 用户被冻结消息码
     */
    public static final int CUSTOMER_FROZEN = 10012;

    /**
     * 修改用户信息失败消息码
     */
    public static final int UPDATE_CUSTOMER_INFORMATION_FAILED = 10013;

    /**
     * 同一 ip 每天注册次数达到最大值消息码
     */
    public static final int IP_MAX_REGISTER_COUNT = 10014;

    /**
     * 同一 ip 每天登录次数达到最大值消息码
     */
    public static final int IP_MAX_LOGIN_COUNT = 10015;

    /**
     * 频繁提交评论消息码
     */
    public static final int TOO_MANY_SUBMIT_COMMENT = 10016;

    /**
     * 频繁提交留言消息码
     */
    public static final int TOO_MANY_SUBMIT_LEAVE_MESSAGE = 10017;



    /**
     * 请求体不可为空消息码
     */
    public static final int REQUEST_BODY_CANNOT_EMPTY = 10100;

    /**
     * 找不到对应路由接口消息码
     */
    public static final int NOT_FOUND_ROUTE = 10110;

    /**
     * 请求过于频繁消息码
     */
    public static final int TOO_MANY_REQUESTS = 10120;



    /**
     * 未找到相关文章消息码
     */
    public static final int NOT_FOUND_ARTICLE = 30000;



    /**
     * 未找到相关站点信息消息码
     */
    public static final int NOT_FOUND_WEB_SITE_INFO = 40000;



    /**
     * 添加留言成功消息码
     */
    public static final int CREATE_LEAVE_MESSAGE_SUCCESS = 2000;

    /**
     * 添加留言失败消息码
     */
    public static final int CREATE_LEAVE_MESSAGE_FAILED = 50000;

    /**
     * 禁止添加留言消息码
     */
    public static final int FORBIDDEN_CREATE_LEAVE_MESSAGE = 50010;



    /**
     * 不支持的邮件类型消息码
     */
    public static final int NONEXISTENT_EMAIL_KIND = 60000;



    /**
     * 添加评论成功消息码
     */
    public static final int CREATE_COMMENT_SUCCESS = 2030;

    /**
     * 添加评论失败消息码
     */
    public static final int CREATE_COMMENT_FAILED = 70000;

    /**
     * 禁止添加评论消息码
     */
    public static final int FORBIDDEN_CREATE_COMMENT = 70010;


    /**
     * 未找到相关分类消息码
     */
    public static final int NOT_FOUND_CATEGORY = 80000;

}