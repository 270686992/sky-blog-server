package cn.xilikeli.skyblog.service;

import cn.xilikeli.skyblog.model.Tag;

import java.util.List;

/**
 * <p>
 * 标签业务操作接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 21:28
 * @since JDK1.8
 */
public interface TagService {

    /**
     * 获取所有标签
     *
     * @return 返回包含所有标签的列表
     */
    List<Tag> getAllTag();

}