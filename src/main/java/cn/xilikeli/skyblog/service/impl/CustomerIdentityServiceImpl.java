package cn.xilikeli.skyblog.service.impl;

import cn.xilikeli.skyblog.common.constant.CodeMessageConstant;
import cn.xilikeli.skyblog.common.constant.IdentityConstant;
import cn.xilikeli.skyblog.common.exception.http.FailedException;
import cn.xilikeli.skyblog.common.util.EncryptUtil;
import cn.xilikeli.skyblog.model.Customer;
import cn.xilikeli.skyblog.model.CustomerIdentity;
import cn.xilikeli.skyblog.repository.CustomerIdentityRepository;
import cn.xilikeli.skyblog.service.CustomerIdentityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客用户授权信息业务操作接口类的实现类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 19:08
 * @since JDK1.8
 */
@Service
@AllArgsConstructor
public class CustomerIdentityServiceImpl implements CustomerIdentityService {

    private final CustomerIdentityRepository customerIdentityRepository;

    @Override
    public CustomerIdentity createIdentity(CustomerIdentity customerIdentity) {
        CustomerIdentity saveCustomerIdentity;

        try {
            saveCustomerIdentity = this.customerIdentityRepository.save(customerIdentity);
        } catch (Exception e) {
            throw new FailedException(CodeMessageConstant.CREATE_CUSTOMER_FAILED);
        }

        return saveCustomerIdentity;
    }

    @Override
    public CustomerIdentity createIdentity(Integer customerId, String identityType, String identifier, String credential) {
        CustomerIdentity customerIdentity = new CustomerIdentity();
        customerIdentity.setCustomerId(customerId);
        customerIdentity.setIdentityType(identityType);
        customerIdentity.setIdentifier(identifier);
        customerIdentity.setCredential(credential);
        return this.createIdentity(customerIdentity);
    }

    @Override
    public CustomerIdentity createUsernamePasswordIdentity(Integer customerId, String username, String password) {
        // 加密密码
        password = EncryptUtil.encrypt(password);
        return this.createIdentity(customerId, IdentityConstant.USERNAME_PASSWORD_IDENTITY, username, password);
    }

    @Override
    public boolean verifyUsernamePassword(Integer customerId, String username, String password) {
        CustomerIdentity customerIdentity = this.getCustomerUsernamePasswordIdentityByCustom(customerId, username, IdentityConstant.USERNAME_PASSWORD_IDENTITY);

        return EncryptUtil.verify(customerIdentity.getCredential(), password);
    }

    @Override
    public CustomerIdentity getCustomerUsernamePasswordIdentityByCustom(Customer customer) {
        return this.customerIdentityRepository.findByCustomerIdAndIdentifierAndIdentityType(customer.getId(), customer.getUsername(), IdentityConstant.USERNAME_PASSWORD_IDENTITY);
    }

    @Override
    public CustomerIdentity getCustomerUsernamePasswordIdentityByCustom(Integer customerId, String username, String password) {
        return this.customerIdentityRepository.findByCustomerIdAndIdentifierAndIdentityType(customerId, username, IdentityConstant.USERNAME_PASSWORD_IDENTITY);
    }

    @Override
    public boolean changeUsername(Customer customer, String newUsername) {
        // 获取相应用户认证信息
        CustomerIdentity customerIdentity = this.getCustomerUsernamePasswordIdentityByCustom(customer);

        // 更新新用户名
        customerIdentity.setIdentifier(newUsername);

        try {
            this.customerIdentityRepository.save(customerIdentity);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean changePassword(Customer customer, String newPassword) {
        // 加密新密码
        newPassword = EncryptUtil.encrypt(newPassword);

        // 获取相应用户认证信息
        CustomerIdentity customerIdentity = this.getCustomerUsernamePasswordIdentityByCustom(customer);

        // 更新新密码
        customerIdentity.setCredential(newPassword);

        try {
            this.customerIdentityRepository.save(customerIdentity);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}