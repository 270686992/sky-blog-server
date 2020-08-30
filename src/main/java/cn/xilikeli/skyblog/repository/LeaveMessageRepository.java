package cn.xilikeli.skyblog.repository;

import cn.xilikeli.skyblog.model.LeaveMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 留言 Repository 接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 21:58
 * @since JDK1.8
 */
public interface LeaveMessageRepository extends JpaRepository<LeaveMessage, Integer> {

    /**
     * 根据留言 ID 获取相应留言的信息
     *
     * @param leaveMessageId 留言 ID
     * @return 返回获取的留言信息
     */
    LeaveMessage findOneById(Integer leaveMessageId);

}