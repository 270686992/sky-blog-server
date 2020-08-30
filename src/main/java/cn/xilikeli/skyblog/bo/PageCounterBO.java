package cn.xilikeli.skyblog.bo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 分页参数 BO
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/19 - 21:12
 * @since JDK1.8
 */
@Getter
@Setter
@Builder
@ToString
public class PageCounterBO {

    /**
     * start 转换后的当前页数
     */
    private Integer page;

    /**
     * 每页的条数
     */
    private Integer count;

}