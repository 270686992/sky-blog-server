package cn.xilikeli.skyblog.service;

import cn.xilikeli.skyblog.dto.customer.ChangePasswordDTO;
import cn.xilikeli.skyblog.dto.customer.CustomerInformationDTO;
import cn.xilikeli.skyblog.dto.customer.LoginDTO;
import cn.xilikeli.skyblog.dto.customer.RegisterDTO;
import cn.xilikeli.skyblog.model.Customer;
import cn.xilikeli.skyblog.model.Token;

/**
 * <p>
 * 博客用户业务操作接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 14:34
 * @since JDK1.8
 */
public interface CustomerService {

    /**
     * 注册一个博客用户
     *
     * @param registerDTO 注册信息的数据传输对象
     */
    void registerCustomer(RegisterDTO registerDTO);

    /**
     * 用户登录
     *
     * @param loginDTO 登录信息的数据传输对象
     * @return 登录成功返回 token 令牌
     */
    Token login(LoginDTO loginDTO);

    /**
     * 根据博客用户 ID 获取相应博客用户的信息
     *
     * @param customerId 博客用户 ID
     * @return 返回获取的博客用户的信息
     */
    Customer getCustomerById(Integer customerId);

    /**
     * 根据博客用户的用户名获取相应博客用户的信息
     *
     * @param username 博客用户的用户名
     * @return 返回获取的博客用户的信息
     */
    Customer getCustomerByUsername(String username);

    /**
     * 修改当前登录用户的密码
     *
     * @param changePasswordDTO 修改密码信息的数据传输对象
     */
    void changePassword(ChangePasswordDTO changePasswordDTO);

    /**
     * 修改当前登录用户的资料信息
     *
     * @param customerInformationDTO 博客用户资料信息的数据传输对象
     */
    void updateCustomerInformation(CustomerInformationDTO customerInformationDTO);

}