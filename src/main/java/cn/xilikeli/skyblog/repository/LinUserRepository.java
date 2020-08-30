package cn.xilikeli.skyblog.repository;

import cn.xilikeli.skyblog.model.LinUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 后台用户 Repository 接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 22:25
 * @since JDK1.8
 */
public interface LinUserRepository extends JpaRepository<LinUser, Integer> {

    /**
     * 通过后台用户 ID 查询对应的后台用户信息
     *
     * @param linUserId 后台用户 ID
     * @return 返回查询的后台用户信息
     */
    LinUser findOneById(Integer linUserId);

}