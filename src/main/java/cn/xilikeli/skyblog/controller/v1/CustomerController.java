package cn.xilikeli.skyblog.controller.v1;

import cn.xilikeli.skyblog.common.LocalCustomer;
import cn.xilikeli.skyblog.common.UnifyResponse;
import cn.xilikeli.skyblog.common.constant.CodeMessageConstant;
import cn.xilikeli.skyblog.common.interceptor.ScopeLevel;
import cn.xilikeli.skyblog.common.util.JwtTokenUtil;
import cn.xilikeli.skyblog.dto.customer.*;
import cn.xilikeli.skyblog.model.Customer;
import cn.xilikeli.skyblog.model.Token;
import cn.xilikeli.skyblog.service.CustomerService;
import cn.xilikeli.skyblog.vo.CustomerVO;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 博客用户业务接口控制器
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 14:35
 * @since JDK1.8
 */
@RequestMapping("/v1/customer")
@Validated
@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    /**
     * 注册一个博客用户
     *
     * @param registerDTO 注册信息的数据传输对象
     */
    @PostMapping("/register")
    public void registerCustomer(@RequestBody @Validated RegisterDTO registerDTO) {
        this.customerService.registerCustomer(registerDTO);
        UnifyResponse.createSuccess(CodeMessageConstant.CREATE_CUSTOMER_SUCCESS);
    }

    /**
     * 用户登录
     *
     * @param loginDTO 登录信息的数据传输对象
     * @return 登录成功返回 token 令牌
     */
    @PostMapping("/login")
    public Token login(@RequestBody @Validated LoginDTO loginDTO) {
        return this.customerService.login(loginDTO);
    }

    /**
     * 校验 token 令牌是否合法
     *
     * @param tokenDTO token 令牌的数据传输对象
     * @return 返回封装着校验结果的 map 对象
     */
    @PostMapping("/verify")
    public Map<String, Boolean> verify(@RequestBody TokenDTO tokenDTO) {
        Map<String, Boolean> map = new HashMap<>(16);
        Boolean valid = JwtTokenUtil.verifyToken(tokenDTO.getToken());
        map.put("is_valid", valid);
        return map;
    }

    /**
     * 修改当前登录用户的密码
     *
     * @param changePasswordDTO 修改密码信息的数据传输对象
     */
    @PutMapping("/change-password")
    @ScopeLevel
    public void changePassword(@RequestBody @Validated ChangePasswordDTO changePasswordDTO) {
        this.customerService.changePassword(changePasswordDTO);
        UnifyResponse.updateSuccess(CodeMessageConstant.UPDATE_PASSWORD_SUCCESS);
    }

    /**
     * 修改当前登录用户的资料信息
     *
     * @param customerInformationDTO 博客用户资料信息的数据传输对象
     */
    @PutMapping("")
    @ScopeLevel
    public void updateCustomerInformation(@RequestBody @Validated CustomerInformationDTO customerInformationDTO) {
        this.customerService.updateCustomerInformation(customerInformationDTO);
        UnifyResponse.updateSuccess(CodeMessageConstant.UPDATE_CUSTOMER_INFORMATION_SUCCESS);
    }

    /**
     * 获取当前登录用户的信息
     */
    @GetMapping("/information")
    @ScopeLevel
    public CustomerVO getCustomerInformation() {
        Customer customer = LocalCustomer.getCustomer();
        return new CustomerVO(customer);
    }

}