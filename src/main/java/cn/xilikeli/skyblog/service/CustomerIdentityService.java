package cn.xilikeli.skyblog.service;

import cn.xilikeli.skyblog.model.Customer;
import cn.xilikeli.skyblog.model.CustomerIdentity;

/**
 * <p>
 * 博客用户授权信息业务操作接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 19:07
 * @since JDK1.8
 */
public interface CustomerIdentityService {

    /**
     * 添加用户认证信息
     *
     * @param customerIdentity 用户认证信息
     * @return 返回添加的用户认证信息
     */
    CustomerIdentity createIdentity(CustomerIdentity customerIdentity);

    /**
     * 添加用户认证信息
     *
     * @param customerId   用户 ID
     * @param identityType 登录类型(手机号,邮箱,用户名或第三方应用名称(微信,微博等)
     * @param identifier   标识(手机号,邮箱,用户名或第三方应用的唯一标识)
     * @param credential   密码凭证(站内的保存密码,站外的不保存或保存 token)
     * @return 返回添加的用户认证信息
     */
    CustomerIdentity createIdentity(Integer customerId, String identityType, String identifier, String credential);

    /**
     * 添加用户认证信息 (USERNAME_PASSWORD)
     *
     * @param customerId 用户 ID
     * @param username   用户名
     * @param password   密码
     * @return 返回添加的用户认证信息
     */
    CustomerIdentity createUsernamePasswordIdentity(Integer customerId, String username, String password);

    /**
     * 验证用户认证信息 (USERNAME_PASSWORD)
     *
     * @param customerId 用户 ID
     * @param username   用户名
     * @param password   密码
     * @return 验证成功返回 true,验证失败返回 false
     */
    boolean verifyUsernamePassword(Integer customerId, String username, String password);

    /**
     * 根据当前登录用户的信息获取该用户的认证信息
     *
     * @param customer 当前登录用户
     * @return 返回获取的指定用户的认证信息
     */
    CustomerIdentity getCustomerUsernamePasswordIdentityByCustom(Customer customer);

    /**
     * 根据当前登录用户的信息获取该用户的认证信息
     *
     * @param customerId 用户 ID
     * @param username   用户名
     * @param password   密码
     * @return 返回获取的指定用户的认证信息
     */
    CustomerIdentity getCustomerUsernamePasswordIdentityByCustom(Integer customerId, String username, String password);

    /**
     * 修改当前登录用户的用户名
     *
     * @param customer    当前登录用户
     * @param newUsername 新用户名
     * @return 修改成功返回 true, 修改失败返回 false
     */
    boolean changeUsername(Customer customer, String newUsername);

    /**
     * 修改当前登录用户的密码
     *
     * @param customer    当前登录用户
     * @param newPassword 新密码
     * @return 修改成功返回 true, 修改失败返回 false
     */
    boolean changePassword(Customer customer, String newPassword);

}