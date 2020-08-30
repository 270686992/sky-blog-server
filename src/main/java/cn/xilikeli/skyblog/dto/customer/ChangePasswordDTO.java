package cn.xilikeli.skyblog.dto.customer;

import cn.xilikeli.skyblog.dto.validator.EqualField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 修改密码信息的数据传输类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 23:09
 * @since JDK1.8
 */
@Data
@EqualField(srcField = "newPassword", dstField = "confirmPassword", message = "{password.equal-field}")
public class ChangePasswordDTO {

    /**
     * 旧密码
     */
    @NotBlank(message = "{password.old.not-blank}")
    private String oldPassword;

    /**
     * 新密码
     */
    @NotBlank(message = "{password.new.not-blank}")
    @Pattern(regexp = "^[A-Za-z0-9_]{6,20}$", message = "{password.pattern}")
    private String newPassword;

    /**
     * 确认密码
     */
    @NotBlank(message = "{password.confirm.not-blank}")
    private String confirmPassword;

}