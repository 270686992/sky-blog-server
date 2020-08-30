package cn.xilikeli.skyblog.dto.customer;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 登录信息的数据传输类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 14:49
 * @since JDK1.8
 */
@Data
public class LoginDTO {

    /**
     * 用户名
     */
    @NotBlank(message = "{username.not-blank}")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "{password.not-blank}")
    private String password;

}