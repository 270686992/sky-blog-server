package cn.xilikeli.skyblog.service.impl;

import cn.xilikeli.skyblog.common.LocalCustomer;
import cn.xilikeli.skyblog.common.constant.CodeMessageConstant;
import cn.xilikeli.skyblog.common.enumeration.CustomerStateEnum;
import cn.xilikeli.skyblog.common.exception.http.FailedException;
import cn.xilikeli.skyblog.common.exception.http.ForbiddenException;
import cn.xilikeli.skyblog.common.exception.http.NotFoundException;
import cn.xilikeli.skyblog.common.exception.http.ParameterException;
import cn.xilikeli.skyblog.common.util.HttpRequestProxy;
import cn.xilikeli.skyblog.common.util.JwtTokenUtil;
import cn.xilikeli.skyblog.common.util.LocalCacheUtil;
import cn.xilikeli.skyblog.dto.customer.ChangePasswordDTO;
import cn.xilikeli.skyblog.dto.customer.CustomerInformationDTO;
import cn.xilikeli.skyblog.dto.customer.LoginDTO;
import cn.xilikeli.skyblog.dto.customer.RegisterDTO;
import cn.xilikeli.skyblog.model.Customer;
import cn.xilikeli.skyblog.model.Token;
import cn.xilikeli.skyblog.repository.CustomerRepository;
import cn.xilikeli.skyblog.service.CustomerIdentityService;
import cn.xilikeli.skyblog.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 博客用户业务操作接口类的实现类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 14:35
 * @since JDK1.8
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerIdentityService customerIdentityService;

    /**
     * 同一 ip 每天最多注册次数
     */
    @Value("${sky-blog.ip-max-register-count}")
    private int ipMaxRegisterCount;

    /**
     * 同一 ip 每天最多注册次数
     */
    @Value("${sky-blog.ip-max-login-count}")
    private int ipMaxLoginCount;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerIdentityService customerIdentityService) {
        this.customerRepository = customerRepository;
        this.customerIdentityService = customerIdentityService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerCustomer(RegisterDTO registerDTO) {
        // 校验当前的 ip 当天的注册次数是否已经达到限值
        this.checkIpRegisterCountIsMax();

        // 根据用户名判断用户是否已经存在
        boolean exist = this.checkCustomerExistByUsername(registerDTO.getUsername());
        if (exist) {
            throw new ForbiddenException(CodeMessageConstant.USERNAME_REPEAT);
        }

        // 判断邮箱是否已被使用
        exist = this.checkCustomerExistByEmail(registerDTO.getEmail());
        if (exist) {
            throw new ForbiddenException(CodeMessageConstant.EMAIL_REPEAT);
        }

        // 判断昵称是否已被使用
        exist = this.checkCustomerExistByNickname(registerDTO.getNickname());
        if (exist) {
            throw new ForbiddenException(CodeMessageConstant.NICKNAME_REPEAT);
        }

        // 注册用户
        Customer customer = new Customer();
        BeanUtils.copyProperties(registerDTO, customer);
        customer.setState(CustomerStateEnum.NORMAL.getValue());

        try {
            customer = this.customerRepository.save(customer);
        } catch (Exception e) {
            throw new FailedException(CodeMessageConstant.CREATE_CUSTOMER_FAILED);
        }

        // 添加用户认证信息
        this.customerIdentityService.createUsernamePasswordIdentity(customer.getId(), customer.getUsername(), registerDTO.getPassword());
    }

    /**
     * 校验当前的 ip 当天的注册次数是否已经达到限值
     */
    private void checkIpRegisterCountIsMax() {
        // 获取当前请求的 ip 地址
        String ip = HttpRequestProxy.getRemoteRealIp();
        String ipRegisterCount = LocalCacheUtil.getLocalCache(LocalCacheUtil.CACHE_PREFIX + ip + "register");
        if (StringUtils.isBlank(ipRegisterCount)) {
            // 该 ip 地址第一次注册,设置初始值
            int initCount = 0;
            LocalCacheUtil.setLocalCache(LocalCacheUtil.CACHE_PREFIX + ip + "register", String.valueOf(initCount));
        } else {
            // 该 ip 地址已经注册过,判断注册次数是否达到限定值,如果未达到则 +1
            int registerCount = Integer.parseInt(ipRegisterCount);
            if (registerCount == ipMaxRegisterCount) {
                throw new ForbiddenException(CodeMessageConstant.IP_MAX_REGISTER_COUNT);
            } else {
                registerCount += 1;
                LocalCacheUtil.setLocalCache(LocalCacheUtil.CACHE_PREFIX + ip + "register", String.valueOf(registerCount));
            }
        }
    }

    @Override
    public Token login(LoginDTO loginDTO) {
        // 校验当前的 ip 当天的登录次数是否已经达到限值
        this.checkIpLoginCountIsMax();

        // 获取相应用户
        Customer customer = this.getCustomerByUsername(loginDTO.getUsername());

        // 校验密码是否正确
        boolean valid = this.customerIdentityService.verifyUsernamePassword(customer.getId(), customer.getUsername(), loginDTO.getPassword());
        if (!valid) {
            throw new ParameterException(CodeMessageConstant.USERNAME_OR_PASSWORD_ERROR);
        }

        // 校验用户状态是否为冻结状态
        if (customer.getState().equals(CustomerStateEnum.FROZEN.getValue())) {
            throw new ForbiddenException(CodeMessageConstant.CUSTOMER_FROZEN);
        }

        // 生成令牌返回
        String token = JwtTokenUtil.makeToken(customer.getId());
        return new Token(token);
    }

    /**
     * 校验当前的 ip 当天的登录次数是否已经达到限值
     */
    private void checkIpLoginCountIsMax() {
        // 获取当前请求的 ip 地址
        String ip = HttpRequestProxy.getRemoteRealIp();
        String ipLoginCount = LocalCacheUtil.getLocalCache(LocalCacheUtil.CACHE_PREFIX + ip + "login");
        if (StringUtils.isBlank(ipLoginCount)) {
            // 该 ip 地址第一次登录,设置初始值
            int initCount = 0;
            LocalCacheUtil.setLocalCache(LocalCacheUtil.CACHE_PREFIX + ip + "login", String.valueOf(initCount));
        } else {
            // 该 ip 地址已经登录过,判断登录次数是否达到限定值,如果未达到则 +1
            int loginCount = Integer.parseInt(ipLoginCount);
            if (loginCount == ipMaxLoginCount) {
                throw new ForbiddenException(CodeMessageConstant.IP_MAX_LOGIN_COUNT);
            } else {
                loginCount += 1;
                LocalCacheUtil.setLocalCache(LocalCacheUtil.CACHE_PREFIX + ip + "login", String.valueOf(loginCount));
            }
        }
    }

    /**
     * 判断用户名是否已被使用
     *
     * @param username 用户名
     * @return 用户名已被使用返回 true,否则返回 false
     */
    private boolean checkCustomerExistByUsername(String username) {
        int rows = this.customerRepository.countByUsername(username);
        return rows > 0;
    }

    /**
     * 判断用户名是否已被使用
     *
     * @param username   用户名
     * @param customerId 用户 ID
     * @return 用户名已被使用返回 true,否则返回 false
     */
    private boolean checkCustomerExistByUsername(String username, Integer customerId) {
        int rows = this.customerRepository.countByUsernameAndIdIsNot(username, customerId);
        return rows > 0;
    }

    /**
     * 判断邮箱是否已被使用
     *
     * @param email 邮箱
     * @return 邮箱已被使用返回 true,否则返回 false
     */
    private boolean checkCustomerExistByEmail(String email) {
        int rows = this.customerRepository.countByEmail(email);
        return rows > 0;
    }

    /**
     * 判断邮箱是否已被使用
     *
     * @param email      邮箱
     * @param customerId 用户 ID
     * @return 邮箱已被使用返回 true,否则返回 false
     */
    private boolean checkCustomerExistByEmail(String email, Integer customerId) {
        int rows = this.customerRepository.countByEmailAndIdIsNot(email, customerId);
        return rows > 0;
    }

    /**
     * 判断昵称是否已被使用
     *
     * @param nickname 昵称
     * @return 昵称已被使用返回 true,否则返回 false
     */
    private boolean checkCustomerExistByNickname(String nickname) {
        int rows = this.customerRepository.countByNickname(nickname);
        return rows > 0;
    }

    /**
     * 判断昵称是否已被使用
     *
     * @param nickname   昵称
     * @param customerId 用户 ID
     * @return 昵称已被使用返回 true,否则返回 false
     */
    private boolean checkCustomerExistByNickname(String nickname, Integer customerId) {
        int rows = this.customerRepository.countByNicknameAndIdIsNot(nickname, customerId);
        return rows > 0;
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        Customer customer = this.customerRepository.findOneById(customerId);

        if (customer == null) {
            throw new NotFoundException(CodeMessageConstant.NOT_FOUND_CUSTOMER);
        }

        return customer;
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        Customer customer = this.customerRepository.findByUsername(username);

        if (customer == null) {
            throw new NotFoundException(CodeMessageConstant.USERNAME_OR_PASSWORD_ERROR);
        }

        return customer;
    }

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        // 获取当前登录用户
        Customer customer = LocalCustomer.getCustomer();

        // 校验老密码是否正确
        boolean valid = this.customerIdentityService.verifyUsernamePassword(customer.getId(), customer.getUsername(), changePasswordDTO.getOldPassword());
        if (!valid) {
            throw new ParameterException(CodeMessageConstant.OLD_PASSWORD_ERROR);
        }

        // 更改密码并判断更改结果
        valid = this.customerIdentityService.changePassword(customer, changePasswordDTO.getNewPassword());
        if (!valid) {
            throw new FailedException(CodeMessageConstant.UPDATE_PASSWORD_FAILED);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCustomerInformation(CustomerInformationDTO customerInformationDTO) {
        // 获取当前登录用户
        Customer customer = LocalCustomer.getCustomer();

        // 校验用户名是否已被使用
        boolean exist = this.checkCustomerExistByUsername(customerInformationDTO.getUsername(), customer.getId());
        if (exist) {
            throw new ForbiddenException(CodeMessageConstant.USERNAME_REPEAT);
        }

        // 判断邮箱是否已被使用
        exist = this.checkCustomerExistByEmail(customerInformationDTO.getEmail(), customer.getId());
        if (exist) {
            throw new ForbiddenException(CodeMessageConstant.EMAIL_REPEAT);
        }

        // 判断昵称是否已被使用
        exist = this.checkCustomerExistByNickname(customerInformationDTO.getNickname(), customer.getId());
        if (exist) {
            throw new ForbiddenException(CodeMessageConstant.NICKNAME_REPEAT);
        }

        // 更新用户认证信息中的用户名
        boolean valid = this.customerIdentityService.changeUsername(customer, customerInformationDTO.getUsername());
        if (!valid) {
            throw new FailedException(CodeMessageConstant.UPDATE_CUSTOMER_INFORMATION_FAILED);
        }

        // 更新用户资料信息
        BeanUtils.copyProperties(customerInformationDTO, customer);
        try {
            this.customerRepository.save(customer);
        } catch (Exception e) {
            throw new FailedException(CodeMessageConstant.UPDATE_CUSTOMER_INFORMATION_FAILED);
        }
    }

}