package cn.xilikeli.skyblog.vo;

import cn.xilikeli.skyblog.model.Customer;
import lombok.*;

/**
 * <p>
 * 博客用户信息 VO
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/28 - 21:37
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVO {

    /**
     * 博客用户信息
     */
    private Customer customer;

}