package cn.xilikeli.skyblog.dto.customer;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 博客用户资料信息的数据传输类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/23 - 18:41
 * @since JDK1.8
 */
@Data
public class CustomerInformationDTO {

    /**
     * 用户名
     */
    @NotBlank(message = "{username.not-blank}")
    @Length(min = 4, max = 20, message = "{username.length}")
    private String username;

    /**
     * 昵称
     */
    @NotBlank(message = "{nickname.not-blank}")
    @Length(min = 1, max = 20, message = "{nickname.length}")
    private String nickname;

    /**
     * 邮箱
     */
    @Email(message = "{email}")
    @NotBlank(message = "{email.not-blank}")
    @Length(min = 3, max = 50, message = "{email.length}")
    private String email;

    /**
     * 用户头像 url
     */
    @Length(max = 255, message = "{image.length}")
    private String avatar;

}