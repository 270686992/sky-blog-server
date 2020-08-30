package cn.xilikeli.skyblog.dto.customer;

import lombok.Data;

/**
 * <p>
 * token 令牌的数据传输类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 14:47
 * @since JDK1.8
 */
@Data
public class TokenDTO {

    /**
     * JWT 令牌信息
     */
    private String token;

}