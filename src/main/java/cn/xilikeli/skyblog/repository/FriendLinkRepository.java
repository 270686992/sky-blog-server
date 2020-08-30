package cn.xilikeli.skyblog.repository;

import cn.xilikeli.skyblog.model.FriendLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <p>
 * 友情链接 Repository 接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 21:33
 * @since JDK1.8
 */
public interface FriendLinkRepository extends JpaRepository<FriendLink, Integer> {

    /**
     * 根据友情链接类型查询相应友情链接列表
     *
     * @param kind 友情链接类型: 0-友情链接,1-推荐链接,2-站长个人链接
     * @return 返回查询的指定类型的友情链接列表
     */
    List<FriendLink> findByKind(Integer kind);

}