package cn.xilikeli.skyblog.dto.customer;

import cn.xilikeli.skyblog.dto.validator.EqualField;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 注册信息的数据传输类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 15:06
 * @since JDK1.8
 */
@Data
@EqualField(srcField = "password", dstField = "confirmPassword", message = "{password.equal-field}")
public class RegisterDTO {

    /**
     * 用户名
     */
    @NotBlank(message = "{username.not-blank}")
    @Length(min = 4, max = 20, message = "{username.length}")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "{password.not-blank}")
    @Pattern(regexp = "^[A-Za-z0-9_]{6,20}$", message = "{password.pattern}")
    private String password;

    /**
     * 确认密码
     */
    @NotBlank(message = "{password.confirm.not-blank}")
    private String confirmPassword;

    /**
     * 邮箱
     */
    @Email(message = "{email}")
    @NotBlank(message = "{email.not-blank}")
    @Length(min = 3, max = 50, message = "{email.length}")
    private String email;

    /**
     * 昵称
     */
    @NotBlank(message = "{nickname.not-blank}")
    @Length(min = 1, max = 20, message = "{nickname.length}")
    private String nickname;

}