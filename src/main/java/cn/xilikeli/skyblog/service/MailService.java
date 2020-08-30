package cn.xilikeli.skyblog.service;

import cn.xilikeli.skyblog.model.Email;

/**
 * <p>
 * 邮件服务业务操作接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 11:54
 * @since JDK1.8
 */
public interface MailService {

    /**
     * 发送游客进行评论/留言时的通知邮件
     *
     * @param email 邮件信息
     */
    void sendInformEmail(Email email);

}