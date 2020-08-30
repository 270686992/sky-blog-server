package cn.xilikeli.skyblog.service;

import cn.xilikeli.skyblog.dto.leavemessage.LeaveMessageDTO;
import cn.xilikeli.skyblog.vo.LeaveMessageDetailVO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * <p>
 * 留言业务操作接口类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 21:59
 * @since JDK1.8
 */
public interface LeaveMessageService {

    /**
     * 根据分页查询参数 page、count 获取当前页的留言列表
     *
     * @param page  当前页数
     * @param count 每页的数据条数
     * @return 返回封装着获取的留言列表的分页对象
     */
    Page<LeaveMessageDetailVO> getLatestLeaveMessageList(Integer page, Integer count);

    /**
     * 获取所有留言
     *
     * @return 返回所有留言组成的列表
     */
    List<LeaveMessageDetailVO> getAllLeaveMessageList();

    /**
     * 添加一个留言
     *
     * @param leaveMessageDTO 留言的数据传输对象
     */
    void createLeaveMessage(LeaveMessageDTO leaveMessageDTO);

}