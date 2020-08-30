package cn.xilikeli.skyblog.common.util;

import cn.xilikeli.skyblog.bo.PageCounterBO;

/**
 * <p>
 * 公用工具类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/19 - 21:11
 * @since JDK1.8
 */
public class CommonUtil {

    /**
     * 分页参数转换,移动端分页参数转换为 PC 端分页参数
     *
     * @param start 当前数据索引
     * @param count 每页的数据条数
     * @return 返回封装着转换后的分页参数的分页参数业务对象
     */
    public static PageCounterBO convertToPageParameter(Integer start, Integer count) {
        int page = start / count;
        return PageCounterBO.builder()
                .page(page)
                .count(count)
                .build();
    }

}