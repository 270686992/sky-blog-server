package cn.xilikeli.skyblog.service;

import cn.xilikeli.skyblog.model.FriendLink;

import java.util.List;

/**
 * <p>
 * 友情链接业务操作接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 21:34
 * @since JDK1.8
 */
public interface FriendLinkService {

    /**
     * 根据友情链接类型获取相应友情链接列表
     *
     * @param kind 友情链接类型: 0-友情链接,1-推荐链接,2-站长个人链接
     * @return 返回获取的指定类型的友情链接列表
     */
    List<FriendLink> getFriendLinkListByKind(Integer kind);

}